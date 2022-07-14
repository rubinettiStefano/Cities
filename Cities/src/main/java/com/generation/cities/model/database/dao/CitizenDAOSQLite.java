package com.generation.cities.model.database.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.generation.cities.model.entities.Body;
import com.generation.cities.model.entities.Citizen;

public class CitizenDAOSQLite implements CitizenDAO
{
    
    private static final String	INSERTSQL = 	"insert into Citizen (id,name,surname,profession,dob,gender,salary,bodyID) "
	    					+ "values('[id]','[name]','[surname]','[profession]'," + "'[dob]','[gender]','[salary]','[bodyID]')";
    private static final String	UPDATESQL =	"UPDATE Citizen set name = '[name]', surname='[surname]', profession='[profession]',"
	    					+ " dob='[dob]',gender='[gender]',salary='[salary]',bodyID='[bodyID]' where id='[id]'";
    
    Connection connection;
    
    public CitizenDAOSQLite(Connection connection)
    {
	this.connection = connection;
    }
    
    @Override
    public List<Citizen> getCitizens()
    {
	List<Citizen> res = new ArrayList<Citizen>();
	
	try
	{
	    Statement readCmd = connection.createStatement();
	    String sql = "select * from Citizen";
	    ResultSet rows = readCmd.executeQuery(sql);
	 
	    while (rows.next()) // while(reader.hasNextLine())
	    {
		// facendo next ho già letto String row = reader.nextLine();
		Citizen citizen = _rowToCitizen(rows); // City city = _rowToCity(row);
		res.add(citizen); // res.add(city);
	    }
	    readCmd.close();
	} catch (SQLException e)
	{
	    e.printStackTrace();
	    return null;
	}
	return res;
    }
    
    private Citizen _rowToCitizen(ResultSet rows) throws SQLException
    {
	return new Citizen(
		rows.getString("id"), rows.getString("name"), rows.getString("surname"), rows.getString("profession"),
		rows.getString("dob"), rows.getString("gender"), rows.getInt("salary"), rows.getString("bodyId")
	
	);
    }
    
    @Override
    public List<Citizen> getCitizens(String surnamePart)
    {
	// TODO Auto-generated method stub
	return null;
    }
    
    private String _replaceInQuery(String sql, Citizen citizen)
    {
	sql = sql.replace("[id]", citizen.ID);
	sql = sql.replace("[name]", citizen.name);
	sql = sql.replace("[surname]", citizen.surname + "");
	sql = sql.replace("[profession]", citizen.profession + "");
	sql = sql.replace("[dob]", citizen.dob + "");
	sql = sql.replace("[gender]", citizen.gender + "");
	sql = sql.replace("[salary]", citizen.salary + "");
	sql = sql.replace("[bodyID]", citizen.bodyID + "");
	return sql;
    }
    
    @Override
    public boolean saveCitizen(Citizen citizen)
    {
	try
	{
	    Statement writeCmd = connection.createStatement();
	    writeCmd.execute(
		    		getCitizen(citizen.ID) == null ? // isNew
		    		_replaceInQuery(INSERTSQL, citizen) : // prendo INSERTSQL, sostituisco ed eseguo
		    		_replaceInQuery(UPDATESQL, citizen) // prendo UPDATESQL
		    	    );
	    writeCmd.close();
	} 
	catch (SQLException e)
	{
	    e.printStackTrace();
	    return false;
	}
	
	return true;
    }
    
    @Override
    public boolean deleteCitizen(String ID)
    {
	try
	{
	    Statement writeCmd = connection.createStatement();
	    // soluzione poco carina... ma va per ora.
	    // COSTRUZIONE DI UNA QUERY DI INSERIMENTO
	    String sql = "Delete from Citizen where id= '" + ID + "'";
	    
	    writeCmd.execute(sql);
	    writeCmd.close();
	} 
	catch (SQLException e)
	{
	    e.printStackTrace();
	    return false;
	}
	return true;
    }
    
    @Override
    public Citizen getCitizen(String ID)
    {
	try
	{
	    Statement readCmd = connection.createStatement();
	    String sql = "select * from Citizen where id = '" + ID + "'";
	    ResultSet row = readCmd.executeQuery(sql);
	    
	    if (row.next())
	    {
		Citizen c= _rowToCitizen(row);
		readCmd.close();
		return c;
	    }
	    else
	    {
		readCmd.close();
		return null;
	    }
	   
	} 
	catch (SQLException e)
	{
	    e.printStackTrace();
	    return null;
	}
    }

    @Override
    public List<Citizen> getCitizensByBody(String bodyID)
    {
	List<Citizen> res = new ArrayList<Citizen>();
	try
	{
	    Statement readCmd = connection.createStatement();
	    String sql = "select * from Citizen where bodyID='"+bodyID+"'";
	    ResultSet rows = readCmd.executeQuery(sql);
	    
	    while (rows.next()) // while(reader.hasNextLine())
	    {
		// facendo next ho già letto String row = reader.nextLine();
		Citizen citizen = _rowToCitizen(rows); // body body = _rowTobody(row);
		res.add(citizen); // res.add(body);
	    }
	    readCmd.close();
	} 
	catch (SQLException e)
	{
	    e.printStackTrace();
	    return null;
	}
	return res;
    }
    
}
