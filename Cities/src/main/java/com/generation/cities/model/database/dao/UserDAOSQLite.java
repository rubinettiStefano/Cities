package com.generation.cities.model.database.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.generation.cities.model.entities.User;

public class UserDAOSQLite implements UserDAO
{
	private static final String INSERTSQL = "insert into User (id,email,password) values('[id]','[email]','[password]')";

	Connection connection;

	public UserDAOSQLite(Connection connection)
	{
		this.connection = connection;
	}

	@Override
	public User getUser(String email)
	{
		try
		{
			Statement readCmd = connection.createStatement();
			String sql = "select * from User where email = '" + email + "'";
			ResultSet row = readCmd.executeQuery(sql);

			if (row.next())
			{

				User res = _rowToUser(row);
				readCmd.close();
				return res;
			} else
			{
				readCmd.close();
				return null;
			}
		} catch (SQLException e)
		{
			e.printStackTrace();

			return null;
		}
	}

	@Override
	public List<User> getUsers()
	{
		List<User> res = new ArrayList<>();
		try
		{
			Statement readCmd = connection.createStatement();
			String sql = "select * from User";
			ResultSet rows = readCmd.executeQuery(sql);
			while (rows.next()) // while(reader.hasNextLine())
			{
				// facendo next ho già letto String row = reader.nextLine();
				User user = _rowToUser(rows); // City city = _rowToCity(row);
				res.add(user); // res.add(city);
			}
			readCmd.close();

		} catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		}

		return res;
	}

	@Override
	public boolean saveUser(User user)
	{
		try
		{
			Statement writeCmd = connection.createStatement();
			writeCmd.execute(_replaceInQuery(INSERTSQL, user));
			writeCmd.close();
		} catch (SQLException e)
		{
			e.printStackTrace();
			return false;
		}

		return true;

	}

	@Override
	public boolean deleteUser(String ID)
	{
		try
		{
			Statement writeCmd = connection.createStatement();
			// soluzione poco carina... ma va per ora.
			// COSTRUZIONE DI UNA QUERY DI INSERIMENTO
			String sql = "Delete from User where id= '" + ID + "'";

			writeCmd.execute(sql);
			writeCmd.close();
		} catch (SQLException e)
		{
			e.printStackTrace();
			return false;
		}
		return true;

	}

	private String _replaceInQuery(String sql, User user)
	{

		sql = sql.replace("[id]", user.ID);
		sql = sql.replace("[email]", user.email);
		sql = sql.replace("[password]", user.password + "");
		return sql;
	}

	private User _rowToUser(ResultSet rows) throws SQLException
	{
		return new User(rows.getString("id"), rows.getString("email"), rows.getString("password"));
	}

}
