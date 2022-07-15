package com.generation.cities.model.database;

import java.util.List;

import com.generation.cities.model.entities.Body;
import com.generation.cities.model.entities.Citizen;
import com.generation.cities.model.entities.City;
import com.generation.cities.model.entities.User;


public interface Database
{
	List<City> getCities();
	
	City getCity(String ID);

	List<Body> getBodiesByKey(String key);

	List<Body> getFreeHouses(String cityID, int minsize);
	
	List<Body> getBodiesByType(String type);

	List<City> getCitiesByKey(String key);

	List<Citizen> getCitizensByKey(String key);

	void insertCity(City city);
	
	void insertBody(Body body);

	void deleteBody(String iD, String cityID);

	void insertCitizen(Citizen citizen);

	void deleteCitizen(String iD, String bodyID);

	Citizen getCitizen(String ID);
	
	void deleteCity(String ID);

	User login(String email, String password);
}
