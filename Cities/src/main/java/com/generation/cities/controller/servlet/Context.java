package com.generation.cities.controller.servlet;

import java.util.ArrayList;
import java.util.List;

import com.generation.cities.model.database.Database;
import com.generation.cities.model.database.MockDatabase;

public class Context
{
    
    private static List<Object> dependencies = new ArrayList<Object>();
    
    static
    {
	//Database database = DatabaseFactory.make("city.db");
	Database database = new MockDatabase();
	dependencies.add(database);
	dependencies.add(new CityController(database));
	dependencies.add(new BodyController(database));
	dependencies.add(new CitizenController(database));
    }
    
    public static Object getDependency(String typeName)
    {
	for(Object o:dependencies)
	    if(o.getClass().getSimpleName().equals(typeName))
		return o;
	
	throw new RuntimeException("No object found for type "+typeName);
    }
    
}
