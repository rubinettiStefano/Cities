package com.generation.cities.model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.generation.cities.model.database.dao.BodyDAOSQLite;
import com.generation.cities.model.database.dao.CitizenDAOSQLite;
import com.generation.cities.model.database.dao.CityDAOSQLite;


public class DatabaseFactory
{
    public static Database make(String dbname) throws SQLException, ClassNotFoundException
    {
	Class.forName("org.sqlite.JDBC");
	Connection connection = DriverManager.getConnection("jdbc:sqlite:"+dbname);
	
	return new BasicDatabase
				(
					new CityDAOSQLite(connection),
					new BodyDAOSQLite(connection),
					new CitizenDAOSQLite(connection)
				);
	
    }
}
