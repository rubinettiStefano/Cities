package com.generation.cities.model.database;

import java.util.List;

import com.generation.cities.model.database.dao.BodyDAO;
import com.generation.cities.model.database.dao.CitizenDAO;
import com.generation.cities.model.database.dao.CityDAO;
import com.generation.cities.model.entities.Body;
import com.generation.cities.model.entities.Citizen;
import com.generation.cities.model.entities.City;


/**
 * Questo sarà' un oggetto database REALE che userà' tre DAO
 * @author rubin
 *
 */
public class BasicDatabase implements Database
{
    //Mi servono i DAO
    private CityDAO cityDAO;
    private BodyDAO bodyDAO;
    private CitizenDAO citizenDAO;
    
    public BasicDatabase(CityDAO cityDAO, BodyDAO bodyDAO, CitizenDAO citizenDAO)
    {
	this.cityDAO = cityDAO;
	this.bodyDAO = bodyDAO;
	this.citizenDAO = citizenDAO;
    }
    
    

    @Override
    public List<City> getCities()
    {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public City getCity(String ID)
    {
	City city = cityDAO.getCity(ID);
	if(city==null)
	    return null;
	
	List<Body> bodies = bodyDAO.getBodiesByCity(ID);
	
	for(Body body:bodies)
	    body.setCitizens(citizenDAO.getCitizensByBody(body.ID));
	
	city.setBodies(bodies);
	
	return city;
    }

    @Override
    public List<Citizen> getCitizenByKey(String key)
    {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public List<Body> getFreeHouses(String cityID, int minsize)
    {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public List<Body> getBodies(String type)
    {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public List<City> getCities(String key)
    {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public List<Citizen> getCitizens(String key)
    {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void insertCity(City city)
    {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void insertBody(Body body)
    {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void deleteBody(String iD, String cityID)
    {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void insertCitizen(Citizen citizen)
    {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void deleteCitizen(String iD, String bodyID)
    {
	// TODO Auto-generated method stub
	
    }

    @Override
    public Citizen getCitizen(String ID)
    {
	// TODO Auto-generated method stub
	return null;
    }
    
    
    
}
