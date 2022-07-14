package com.generation.cities.model.database.dao;

import java.util.List;

import com.generation.cities.model.entities.Body;


public interface BodyDAO 
{

	List<Body> getBodies();

	List<Body> getBodies(String namePart);
	
	Body getBody(String ID);
	
	boolean saveBody(Body body);
	
	boolean deleteBody(String ID);

	List<Body> getBodiesByCity(String cityID);
	
	
	
}