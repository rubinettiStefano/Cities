package com.generation.cities.model.database;

import java.util.ArrayList;
import java.util.List;

import com.generation.cities.model.database.dao.BodyDAO;
import com.generation.cities.model.database.dao.CitizenDAO;
import com.generation.cities.model.database.dao.CityDAO;
import com.generation.cities.model.database.dao.UserDAO;
import com.generation.cities.model.entities.Body;
import com.generation.cities.model.entities.Citizen;
import com.generation.cities.model.entities.City;
import com.generation.cities.model.entities.User;

/**
 * Questo sarà' un oggetto database REALE che userà' tre DAO
 * 
 * @author rubin
 *
 */
public class BasicDatabase implements Database
{
	// Mi servono i DAO
	private CityDAO cityDAO;
	private BodyDAO bodyDAO;
	private CitizenDAO citizenDAO;
	private UserDAO userDAO;

	public BasicDatabase(CityDAO cityDAO, BodyDAO bodyDAO, CitizenDAO citizenDAO, UserDAO userDAO)
	{
		this.cityDAO = cityDAO;
		this.bodyDAO = bodyDAO;
		this.citizenDAO = citizenDAO;
		this.userDAO = userDAO;
	}

	@Override
	public List<City> getCities()
	{
		List<City> cities = cityDAO.getCities();
		List<Body> bodies = bodyDAO.getBodies();
		List<Citizen> citizens = citizenDAO.getCitizens();

		for (City city : cities)
			for (Body body : bodies)
				if (city.ID.equals(body.cityID))
					city.addBody(body);

		for (Body body : bodies)
			for (Citizen citizen : citizens)
				if (body.ID.equals(citizen.bodyID))
					body.addCitizen(citizen);

		return cities;

	}

	public List<Body> getBodies()
	{
		List<Body> res = new ArrayList<Body>();

		for (City c : getCities())
			res.addAll(c.bodies);

		return res;
	}

	public List<Citizen> getCitizens()
	{
		List<Citizen> res = new ArrayList<Citizen>();

		for (Body b : getBodies())
			res.addAll(b.citizens);

		return res;
	}

	@Override
	public City getCity(String ID)
	{
		City city = cityDAO.getCity(ID);
		if (city == null)
			return null;

		List<Body> bodies = bodyDAO.getBodiesByCity(ID);

		for (Body body : bodies)
			body.setCitizens(citizenDAO.getCitizensByBody(body.ID));

		city.setBodies(bodies);

		return city;
	}

	@Override
	public List<Citizen> getCitizensByKey(String key)
	{
		List<Citizen> res = new ArrayList<Citizen>();

		for (Citizen c : getCitizens())
			if ((c.name + " " + c.surname).toLowerCase().contains(key.toLowerCase()))
				res.add(c);

		return res;
	}

	@Override
	public List<Body> getFreeHouses(String cityID, int minsize)
	{
		List<Body> res = new ArrayList<Body>();

		for (Body b : getBodiesByCityID(cityID))
			if (b.type.equals("Residential") && b.citizens.isEmpty())
				res.add(b);

		return res;
	}

	@Override
	public List<Body> getBodiesByType(String type)
	{
		List<Body> res = new ArrayList<Body>();

		for (Body b : getBodies())
			if (b.type.toLowerCase().equalsIgnoreCase(type))
				res.add(b);

		return res;
	}

	@Override
	public List<City> getCitiesByKey(String key)
	{
		List<City> res = new ArrayList<City>();

		for (City c : getCities())
			if ((c.ID + " " + c.name).toLowerCase().contains(key.toLowerCase()))
				res.add(c);

		return res;
	}

	@Override
	public List<Body> getBodiesByKey(String key)
	{
		List<Body> res = new ArrayList<Body>();

		for (Body b : getBodies())
			if ((b.name + " " + b.address + " " + b.type + " " + b.city.name).toLowerCase().contains(key.toLowerCase()))
				res.add(b);

		return res;
	}

	@Override
	public void insertCity(City city)
	{

		if (city == null)
			throw new RuntimeException("city is null, cannot insert");
		if (city.ID == null || city.name == null || city.w <= 0 || city.h <= 0)
			throw new RuntimeException("city is not valid, cannot insert");

		if (cityDAO.getCity(city.ID) == null)
			cityDAO.saveCity(city);
		else
			throw new RuntimeException("City with ID " + city.ID + " already present");
	}

	@Override
	public void insertBody(Body body)
	{
		if (body == null)
			throw new RuntimeException("body is null, cannot insert");
		if (body.ID == null || body.name == null || body.type == null || body.left <= 0 || body.bottom <= 0
				|| body.right <= 0 || body.top <= 0 || body.cityID == null)
			throw new RuntimeException("body is not valid, cannot insert");
		if (getBody(body.ID) != null)
			throw new RuntimeException("body is already present, cannot insert");

		bodyDAO.saveBody(body);

	}

	@Override
	public void deleteBody(String ID, String cityID)
	{
		Body b = getBody(ID);
		if (b == null)
			throw new RuntimeException("Body is not present, cannot delete");
		if (b.citizens.size() > 0)
			throw new RuntimeException("Body has at least 1 citizen, cannot delete");

		getCity(cityID).removeBody(ID);

		bodyDAO.deleteBody(ID);
	}

	@Override
	public void insertCitizen(Citizen citizen)
	{
		if (citizen == null)
			throw new RuntimeException("citizen is null, cannot insert");
		if (citizen.name == null || citizen.surname == null || citizen.bodyID == null)
			throw new RuntimeException("citizen is not valid, cannot insert");
		if (getCitizen(citizen.ID) != null)
			throw new RuntimeException("citizen is already present, cannot insert");

		citizenDAO.saveCitizen(citizen);
	}

	@Override
	public void deleteCitizen(String ID, String bodyID)
	{
		if (getCitizen(ID) == null)
			throw new RuntimeException("citizen is not present, cannot delete");

		getBody(bodyID).removeCitizen(ID);
		;

		citizenDAO.deleteCitizen(ID);
	}

	@Override
	public Citizen getCitizen(String ID)
	{
		for (Citizen c : getCitizens())
			if (c.ID.equals(ID))
				return c;

		return null;
	}

	public Body getBody(String ID)
	{
		for (Body b : getBodies())
			if (b.ID.equals(ID))
				return b;

		return null;
	}

	@Override
	public void deleteCity(String ID)
	{
		// dovrebbe caricarmi la città per intero... con bodies e citizens
		City city = getCity(ID);

		if (city == null)
			throw new RuntimeException("City with ID " + ID + " not present, cannot delete");
		if (city.bodies.size() > 0)
			throw new RuntimeException("city with ID " + ID + " has at least 1 body, cannot delete");

		cityDAO.deleteCity(ID);
	}

	public List<Body> getBodiesByCityID(String cityID)
	{
		City c = getCity(cityID);
		if (c == null)
			throw new RuntimeException("City with ID " + cityID + " not present");
		return c.bodies;
	}

	@Override
	public User login(String email, String password)
	{
		User user = userDAO.getUser(email);

		if (user == null)
			return null;
		// VERIFICA DELLA PASSWORD
		return user.password.equals(password) ? user : null;

	}

}
