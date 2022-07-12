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
 */
@WebServlet("/Index")
public class Index extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private static final String PREFIX 	 = "WEB-INF/jsp/";
	private static final String MAINPAGE = "Main.jsp";
	private static final String CITYDETAILPAGE = "CityDetail.jsp";
	private static final String ATTR_CITIES = "cities";
	private static final String ATTR_CITY = "city";
       
	Database database = new MockDatabase();

    public Index() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String cmd = request.getParameter("cmd");
		if(cmd==null) cmd = "";
		
		switch(cmd) 
		{
			case "city":
			{
				request.setAttribute(ATTR_CITY, database.getCity(request.getParameter("id")));
				request.getRequestDispatcher(PREFIX+CITYDETAILPAGE).forward(request, response);
			}
			break;
			default:
			{
				request.setAttribute(ATTR_CITIES, database.getCities());
				request.getRequestDispatcher(PREFIX+MAINPAGE).forward(request, response);
			}
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
	}

}
