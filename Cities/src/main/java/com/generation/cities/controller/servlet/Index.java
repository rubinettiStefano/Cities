package com.generation.cities.controller.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.generation.cities.model.database.Database;
import com.generation.cities.model.database.MockDatabase;

/**
 * Servlet implementation class Index
 * io sono il CONTROLLER PRINCIPALE - DISPATCHER SERVLET, Servlet di SMISTAMENTO, FRONT CONTROLLER
 * La porta di ingresso per tutto il sistema
 */
@WebServlet("/Index")
public class Index extends HttpServlet
{
    public static final long   serialVersionUID	 = 1L;
    public static final String PREFIX		 = "WEB-INF/jsp/";
    public static final String MAINPAGE		 = "Main.jsp";
    public static final String CITYDETAILPAGE	 = "CityDetail.jsp";
    public static final String SEARCHRESULTSPAGE = "SearchResults.jsp";
    public static final String FORMNEWCITYPAGE	 = "FormNewCity.jsp";
    public static final String ATTR_CITIES	 = "cities";
    public static final String ATTR_CITY	 = "city";
    public static final String ATTR_BODIES	 = "bodies";
    public static final String ATTR_CITIZENS	 = "citizens";
    
    Database database = new MockDatabase();
    CityController cityController = new CityController(database);
    
    public Index()
    {
	super();
    }
    
    protected void doGet
    (
	    HttpServletRequest request, 
	    HttpServletResponse response
    )	 
	    throws ServletException, IOException
    {
	String cmd = request.getParameter("cmd");
	if (cmd == null)
	    cmd = "";
	
	switch (cmd)
	{
	    case "city":
		cityController.getCity(request,response);
	    break;
	    case "newcity":
		cityController.newCity(request,response);
	    break;
	    case "search":
		cityController.search(request, response);
            break;
	    case "formnewcity":
		cityController.forNewCity(request,response);
	    break;
	    default:
		cityController.mainPage(request,response);
	    
	}
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
	
    }
    
}
