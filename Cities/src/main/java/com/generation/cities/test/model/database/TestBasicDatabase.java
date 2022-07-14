package com.generation.cities.test.model.database;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import com.generation.cities.model.database.Database;
import com.generation.cities.model.database.DatabaseFactory;
import com.generation.cities.model.entities.City;

class TestBasicDatabase
{
    
    @Test
    void test() throws ClassNotFoundException, SQLException
    {
	// TDD - Test Driven Development
	
	Database database = DatabaseFactory.make("test.db");
	// test db ha due città, monza e milano
	// monza ha tre edifici e 4 abitanti
	// milano ne ha 4, con 5 abitanti
	// monza ha id = CITY01
	
	City city = database.getCity("CITY01");
	
	assert(city.name.equals("Monza"));
	assert(city.bodies.size()==3);
	if(city.getPopulation()!=4)
	    fail("Expected 4 citizens for city CITY01, got "+city.getPopulation());
	
    }
    
}
