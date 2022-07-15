package com.generation.cities.controller.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.generation.cities.model.entities.User;


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
    public static final String FORMLOGINPAGE 		= "FormLogin.jsp";
    public static final String ATTR_CITIES		= "cities";
    public static final String ATTR_CITY		= "city";
    public static final String ATTR_BODIES		= "bodies";
    public static final String ATTR_CITIZEN	 	= "citizen";
    public static final String ATTR_CITIZENS	 	= "citizens";
    public static final String ATTR_BODY 		= "body";
    
    // DEVO SCRIVERE UNA PAGINA WEB PER INSERIRE UN NUOVO BUILDING
    // DUE NUOVI COMANDI: 	formnewbuilding, newbuilding
    // UNA NUOVA CLASSE: 	BodyController, con due metodi
    //					 	formNewBuilding() e insertBuilding()
    // UNA NUOVA JSP   : 	formNewBuilding.jsp
    //						se l'inserimento riesce, andare alla pagina 
    //						della città del body
    //						se fallisce tornare alla form

    
    CityController 	cityController 	= (CityController) Context.getDependency("CityController");
    BodyController 	bodyController 	= (BodyController) Context.getDependency("BodyController");
    CitizenController 	citizenController = (CitizenController) Context.getDependency("CitizenController");
    UserController 	userController	= (UserController) Context.getDependency("UserController");
    
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
		
		User user = (User) request.getSession().getAttribute("loggedUser");
		//		   request   session     la session è un insieme di ATTRIBUTI che durano nel tempo
		
		
		String cmd = request.getParameter("cmd");
		if (cmd == null)
		    cmd = "";
		
		// CONTROLLO DI SICUREZZA: se tu provi a eseguire dei comandi che NON SIANO LOGIN
		// o FORMLOGIN da non loggato, io ti rimando alla formlogin
		if(user==null && !cmd.equals("formlogin") && !cmd.equals("login"))
			cmd = "formlogin";
		
			
		
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
		    case "formlogin":
		    	userController.formLogin(request,response);
		    break;
		    case "login":
		    	userController.login(request,response);
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
