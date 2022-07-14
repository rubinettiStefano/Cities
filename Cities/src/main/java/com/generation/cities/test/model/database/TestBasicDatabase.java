package com.generation.cities.test.model.database;

import static org.junit.jupiter.api.Assertions.fail;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.generation.cities.model.database.Database;
import com.generation.cities.model.database.DatabaseFactory;
import com.generation.cities.model.entities.Body;
import com.generation.cities.model.entities.Citizen;
import com.generation.cities.model.entities.City;

class TestBasicDatabase
{
    static Database database;
    
    static //Blocco di inizializzazione statico, parte appena la classe viene GENERATA 
    {
	try
	{
	    database = DatabaseFactory.make("test.db");
	}
			//una sola sbarretta       solo una e alla fine
	catch(SQLException | ClassNotFoundException e)
	{
	    e.printStackTrace();
	}
    }
    
    @Test
    void testGetCities()
    {
	List<City> cities = database.getCities();
	
	assert(cities.size()==2);
    }
    
    @Test
    void testGetCity()
    {
	City city = database.getCity("CITY01");
	assert(city.name.equals("Monza"));
	assert(city.bodies.size()==3);
	if(city.getPopulation()!=4)
	    fail("Expected 4 citizens for city CITY01, got "+city.getPopulation());
    }
    
    @Test
    void testCD()
    {
	int nCities = database.getCities().size();
	City newCity = new City("CITY6666", "Vimodrone", 30,30);	
	database.insertCity(newCity);
	assert(database.getCities().size() - nCities ==1);
	
	database.deleteCity("CITY6666");
	assert(database.getCities().size() == nCities );
	
	Body newBody = new Body("BODY999","Park","ProvaCD","Via DB",10,10,12,12,"CITY02");
	database.insertBody(newBody);
	assert(database.getCity("CITY02").bodies.size()==1);
	database.deleteBody("BODY999", "CITY02");
	assert(database.getCity("CITY02").bodies.size()==0);
	
	Citizen newCitizen = new Citizen("CITIZEN100","Prova","De Prove","Cavia","14/07/2022","BYTE",2,"BODY02");
	int initialPopulation = database.getCity("CITY01").getPopulation();
	database.insertCitizen(newCitizen);
	assert(database.getCity("CITY01").getPopulation()-1==initialPopulation);
	database.deleteCitizen("CITIZEN100", "BODY02");
	assert(database.getCity("CITY01").getPopulation()==initialPopulation);
    }
}
