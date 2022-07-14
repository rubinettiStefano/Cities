package com.generation.cities.controller.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class Index
 * io sono il CONTROLLER PRINCIPALE - DISPATCHER SERVLET, Servlet di SMISTAMENTO, FRONT CONTROLLER
 * La porta di ingresso per tutto il sistema
 */
@WebServlet("/Index")
public class Index extends HttpServlet
{
    public static final long   serialVersionUID	 	= 1L;
    public static final String PREFIX		 	= "WEB-INF/jsp/";
    public static final String MAINPAGE		 	= "Main.jsp";
    public static final String CITYDETAILPAGE	 	= "CityDetail.jsp";
    public static final String CITIZENDETAILPAGE	= "CitizenDetail.jsp";
    public static final String SEARCHRESULTSPAGE	= "SearchResults.jsp";
    public static final String FORMNEWCITYPAGE		= "FormNewCity.jsp";
    public static final String FORMNEWBUILDINGPAGE	= "FormNewBuilding.jsp";
    public static final String FORMNEWCITIZENPAGE	= "FormNewCitizen.jsp";
    public static final String ATTR_CITIES		= "cities";
    public static final String ATTR_CITY		= "city";
    public static final String ATTR_BODIES		= "bodies";
    public static final String ATTR_CITIZEN	 	= "citizen";
    public static final String ATTR_CITIZENS	 	= "citizens";
    
    // DEVO SCRIVERE UNA PAGINA WEB PER INSERIRE UN NUOVO BUILDING
    // DUE NUOVI COMANDI: 	formnewbuilding, newbuilding
    // UNA NUOVA CLASSE: 	BodyController, con due metodi
    //					 	formNewBuilding() e insertBuilding()
    // UNA NUOVA JSP   : 	formNewBuilding.jsp
    //						se l'inserimento riesce, andare alla pagina 
    //						della città del body
    //						se fallisce tornare alla form

    
    CityController cityController 	= (CityController) Context.getDependency("CityController");
    BodyController bodyController 	= (BodyController) Context.getDependency("BodyController");
    CitizenController citizenController = (CitizenController) Context.getDependency("CitizenController");
    
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
	    case "citizen":
		citizenController.getCitizen(request,response);
	    break;
	    case "newcity":
		cityController.newCity(request,response);
	    break;
	    case "newcitizen":
		citizenController.newCitizen(request,response);
	    break;
	    
	    case "search":
		cityController.search(request, response);
            break;
	    case "formnewcity":
		cityController.formNewCity(request,response);
	    break;
	    case "formnewcitizen":
		citizenController.formNewCitizen(request,response);
	    break;
	    case "formnewbuilding":
		bodyController.formNewBuilding(request,response);
	    break;
	    case "newbuilding":
		bodyController.newBuilding(request,response);
	    break;
	    case "deletebuilding":
		bodyController.deleteBuilding(request,response);
	    case "deletecitizen":
		citizenController.deleteCitizen(request,response);
	    break;
	    default:
		cityController.mainPage(request,response);
	    
	}
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	    throws ServletException, IOException
    {
	doGet(request,response);
    }
    
}
