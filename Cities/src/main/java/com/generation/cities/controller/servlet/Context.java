package com.generation.cities.controller.servlet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.generation.cities.model.database.Database;
import com.generation.cities.model.database.DatabaseFactory;

public class Context
{

	private static List<Object> dependencies = new ArrayList<Object>();

	static
	{
		try
		{
			Database database = DatabaseFactory
					.make("C:\\Users\\rubin\\git\\Cities\\Cities\\src\\main\\webapp\\city.db");
			dependencies.add(database);
			dependencies.add(new CityController(database));
			dependencies.add(new BodyController(database));
			dependencies.add(new CitizenController(database));
			dependencies.add(new UserController(database));
		} catch (ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
			System.exit(-1); // UCCIDI TUTTO. TERMINA PURE IL SERVER, SPEGNI TOMCAT
		}
	}

	public static Object getDependency(String typeName)
	{
		for (Object o : dependencies)
			if (o.getClass().getSimpleName().equals(typeName))
				return o;

		throw new RuntimeException("No object found for type " + typeName);
	}

}
