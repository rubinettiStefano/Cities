package com.generation.cities.model.database.dao;

import java.util.List;

import com.generation.cities.model.entities.Citizen;


public interface CitizenDAO 
{

	List<Citizen> getCitizens();

	List<Citizen> getCitizens(String surnamePart);
	
	boolean saveCitizen(Citizen citizen);
	
	boolean deleteCitizen(String ID);
	
	Citizen getCitizen(String ID);

	List<Citizen> getCitizensByBody(String iD);
	
	
}