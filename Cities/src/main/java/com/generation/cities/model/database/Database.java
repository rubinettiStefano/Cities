package com.generation.cities.model.database;

import java.util.List;

import com.generation.cities.model.entities.Body;
import com.generation.cities.model.entities.Citizen;
import com.generation.cities.model.entities.City;


public interface Database
{
	List<City> getCities();

	City getCity(String ID);

	List<Citizen> getCitizen(String key);

	List<Body> getFreeHouses(String cityID, int minsize);
	
	List<Body> getBodies(String type);

	List<City> getCities(String key);

	List<Citizen> getCitizens(String key);

	void insertCity(City city);
	
	void insertBody(Body body);
}
