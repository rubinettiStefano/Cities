package com.generation.cities.test.model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import com.generation.cities.model.database.dao.UserDAOSQLite;
import com.generation.cities.model.entities.User;

class SimpleUserDAOSQLiteTest
{
    static UserDAOSQLite dao;
    
    static
    {
	Connection connection;
	try
	{
	    Class.forName("org.sqlite.JDBC");
	    connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\rubin\\git\\Cities\\Cities\\src\\main\\webapp\\city.db");
	    dao = new UserDAOSQLite(connection);
	} catch (SQLException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (ClassNotFoundException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
    
    @Test
    void testGet()
    {
	User u = dao.getUser("prova@email.com");
	assert(u.ID.equals("USER01"));
    }
    
    @Test
    void testGetAll()
    {
	assert(dao.getUsers().size()==2);
    }
    
    @Test
    void testInsDel()
    {
	User u = new User("USER03","provaprova@email.com","pippopluto");
	dao.saveUser(u);
	assert(dao.getUsers().size()==3);
	dao.deleteUser(u.ID);
	assert(dao.getUsers().size()==2);
    }
    
}
