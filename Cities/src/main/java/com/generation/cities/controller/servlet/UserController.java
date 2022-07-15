package com.generation.cities.controller.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.generation.cities.model.database.Database;
import com.generation.cities.model.entities.User;

public class UserController
{
    Database database;
    
    
    public UserController(Database database)
    {
    	this.database = database;
    }


    public void formLogin(HttpServletRequest request, HttpServletResponse response) 
	    throws ServletException, IOException
    {
    	request.getRequestDispatcher(Index.PREFIX+Index.FORMLOGINPAGE).forward(request,response);
    }


	public void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		User user = database.login(email,password);
		
		if(user!=null)
		{
			// ok, login
			request.getSession().setAttribute("loggedUser", user);
			// imposto un attributo di SESSIONE!
			request.setAttribute(Index.ATTR_CITIES, database.getCities());
			request.getRequestDispatcher(Index.PREFIX+Index.MAINPAGE).forward(request, response);
		}
		else
		{
			// ko, errore
			request.setAttribute("error", "Bad login");
			request.getRequestDispatcher(Index.PREFIX + Index.FORMLOGINPAGE).forward(request, response);
		}
		
	}
    
    
    
    
    
}
