package com.generation.cities.model.database.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.generation.cities.model.entities.Body;

public class BodyDAOSQLite implements BodyDAO
{
    private static final String INSERTSQL = 	"insert into body (id,name,type,address,left,bottom,right,top,cityID) "
	    					+ "values('[id]','[name]','[type]','[address]'," + "'[left]','[bottom]','[right]','[top]','[cityID]')";
    
    private static final String UPDATESQL = 	"UPDATE body set name = '[name]', type='[type]',address='[address]',left='[left]',bottom='[bottom]',right='[right]',top='[top]',cityID='[cityID]' where id='[id]'";
    
    Connection connection;
    
    public BodyDAOSQLite(Connection connection)
    {
	this.connection = connection;
    }
    
    @Override
    public List<Body> getBodies()
    {
	List<Body> res = new ArrayList<Body>();
	try
	{
	    Statement readCmd = connection.createStatement();
	    String sql = "select * from Body";
	    ResultSet rows = readCmd.executeQuery(sql);
	    
	    while (rows.next()) // while(reader.hasNextLine())
	    {
		// facendo next ho già letto String row = reader.nextLine();
		Body body = _rowToBody(rows); // body body = _rowTobody(row);
		res.add(body); // res.add(body);
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
    
    private Body _rowToBody(ResultSet rows) throws SQLException
    {
	return new Body(
		rows.getString("id"), rows.getString("type"), rows.getString("name"), rows.getString("address"),
		rows.getInt("left"), rows.getInt("bottom"), rows.getInt("right"), rows.getInt("top"),
		rows.getString("cityID")
	);
    }
    
    @Override
    public List<Body> getBodies(String namePart)
    {
	// TODO Auto-generated method stub
	return null;
    }
    
    @Override
    public Body getBody(String ID)
    {
	try
	{
	    Statement readCmd = connection.createStatement();
	    String sql = "select * from Body where id = '" + ID + "'";
	    ResultSet row = readCmd.executeQuery(sql);
	   
	    if (row.next())
	    {
		Body b= _rowToBody(row);
		readCmd.close();
		return b;
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
    
    private String _replaceInQuery(String sql, Body body)
    {
	sql = sql.replace("[id]", body.ID);
	sql = sql.replace("[name]", body.name);
	sql = sql.replace("[type]", body.type + "");
	sql = sql.replace("[address]", body.address + "");
	sql = sql.replace("[left]", body.left + "");
	sql = sql.replace("[bottom]", body.bottom + "");
	sql = sql.replace("[right]", body.right + "");
	sql = sql.replace("[top]", body.top + "");
	sql = sql.replace("[cityID]", body.cityID + "");
	return sql;
    }
    
    @Override
    public boolean saveBody(Body body)
    {
	try
	{
	    Statement writeCmd = connection.createStatement();
	    writeCmd.execute(
		    		getBody(body.ID) == null ? // isNew
		    		_replaceInQuery(INSERTSQL, body) : // prendo INSERTSQL, sostituisco ed eseguo
		    		_replaceInQuery(UPDATESQL, body) // prendo UPDATESQL
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
    public boolean deleteBody(String ID)
    {
	try
	{
	    Statement writeCmd = connection.createStatement();
	    // soluzione poco carina... ma va per ora.
	    // COSTRUZIONE DI UNA QUERY DI INSERIMENTO
	    String sql = "Delete from Body where id= '" + ID + "'";
	    
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
    public List<Body> getBodiesByCity(String cityID)
    {
	List<Body> res = new ArrayList<Body>();
	try
	{
	    Statement readCmd = connection.createStatement();
	    String sql = "select * from Body where cityID='"+cityID+"'";
	    ResultSet rows = readCmd.executeQuery(sql);
	    
	    while (rows.next()) // while(reader.hasNextLine())
	    {
		// facendo next ho già letto String row = reader.nextLine();
		Body body = _rowToBody(rows); // body body = _rowTobody(row);
		res.add(body); // res.add(body);
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
