package com.generation.cities.controller.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.generation.cities.model.database.Database;
import com.generation.cities.model.entities.Body;
import com.generation.cities.model.entities.Citizen;
import com.generation.cities.model.entities.City;
//Import statico delle costanti in Index
import static com.generation.cities.controller.servlet.Index.*;

/**
 * 
 * Io sono  un controller di secondo livello, 
 * vengo chiamato dalla DISPATCHER SERVLET e mi occupo delle richieste relative alle città
 * @author Stefano
 *
 */
public class CityController
{
    Database database;
    
    public CityController(Database database)
    {
	this.database = database;
    }

    public void search( HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException
    {
	String key = request.getParameter("key");
	List<City> cities = database.getCities(key);
	List<Body> bodies = database.getBodies(key);
	List<Citizen> citizens = database.getCitizens(key);
	request.setAttribute(ATTR_CITIES, cities);
	request.setAttribute(ATTR_BODIES, bodies);
	request.setAttribute(ATTR_CITIZENS, citizens);
	request.getRequestDispatcher(PREFIX+SEARCHRESULTSPAGE).forward(request,response);
    }

    public void mainPage(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException
    {
	request.setAttribute(ATTR_CITIES, database.getCities());
	request.getRequestDispatcher(PREFIX + MAINPAGE).forward(request, response);
    }

    /**
     * L'utente mi sta inviando 5 parametri
     * - Se sono corretti, inserisco la città e vado alla pagina principale
     * _ Se NON lo sono, genero un messaggio di errore e torno alla form
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void newCity(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException
    {
	String ID = request.getParameter("id");
	String name = request.getParameter("name");
	
	try
	{
	    int w = Integer.parseInt(request.getParameter("w"));
	    int h = Integer.parseInt(request.getParameter("h"));

	    City city = new City(ID,name,w,h);
	    database.insertCity(city);
	    			//"cities"	//la lista di città
	    request.setAttribute(ATTR_CITIES, database.getCities());
	    request.getRequestDispatcher(PREFIX + MAINPAGE).forward(request, response);
	}
	catch(NumberFormatException e)
	{
	    request.setAttribute("error", "Expected width and height numeric");
	    request.getRequestDispatcher(PREFIX+FORMNEWCITYPAGE).forward(request, response);
	}
	catch(RuntimeException e)
	{
	    request.setAttribute("error", e.getMessage());
	    request.getRequestDispatcher(PREFIX+FORMNEWCITYPAGE).forward(request, response);
	}
    }

    public void getCity(HttpServletRequest request, HttpServletResponse response) 
	    throws ServletException, IOException
    {
	request.setAttribute(ATTR_CITY, database.getCity(request.getParameter("id")));
	request.getRequestDispatcher(PREFIX + CITYDETAILPAGE).forward(request, response);
	
    }

    public void formNewCity(HttpServletRequest request, HttpServletResponse response) 
	    throws ServletException, IOException
    {
	request.getRequestDispatcher(PREFIX + FORMNEWCITYPAGE).forward(request, response);
    }
    
    
    
}
