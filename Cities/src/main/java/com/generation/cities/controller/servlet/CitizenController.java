package com.generation.cities.controller.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.generation.cities.model.database.Database;
import com.generation.cities.model.entities.Citizen;

public class CitizenController
{
	Database database;

	public CitizenController(Database database)
	{
		this.database = database;
	}

	public void getCitizen(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		request.setAttribute(Index.ATTR_CITIZEN, database.getCitizen(request.getParameter("id")));
		request.getRequestDispatcher(Index.PREFIX + Index.CITIZENDETAILPAGE).forward(request, response);

	}

	public void formNewCitizen(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		request.getRequestDispatcher(Index.PREFIX + Index.FORMNEWCITIZENPAGE).forward(request, response);
	}

	public void newCitizen(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String ID = request.getParameter("id");
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String profession = request.getParameter("profession");
		String gender = request.getParameter("gender");
		String dob = request.getParameter("dob");
		String bodyID = request.getParameter("bodyid");
		try
		{
			int salary = Integer.parseInt(request.getParameter("salary"));

			Citizen citizen = new Citizen(ID, name, surname, profession, dob, gender, salary, bodyID);
			database.insertCitizen(citizen);

			request.setAttribute(Index.ATTR_CITIZEN, database.getCitizen(ID));
			request.getRequestDispatcher(Index.PREFIX + Index.CITIZENDETAILPAGE).forward(request, response);

		} catch (NumberFormatException e)
		{
			request.setAttribute("error", "Expected salary numeric");
			request.getRequestDispatcher(Index.PREFIX + Index.FORMNEWCITIZENPAGE).forward(request, response);
		} catch (RuntimeException e)
		{
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher(Index.PREFIX + Index.FORMNEWCITIZENPAGE).forward(request, response);
		}
	}

	public void deleteCitizen(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String ID = request.getParameter("id");
		String bodyID = request.getParameter("bodyid");
		String cityID = request.getParameter("cityid");
		try
		{
			database.deleteCitizen(ID, bodyID);
			request.setAttribute(Index.ATTR_CITY, database.getCity(cityID)); // alla pagina NON PASSO cityID
			request.getRequestDispatcher(Index.PREFIX + Index.CITYDETAILPAGE).forward(request, response);
		} catch (RuntimeException e)
		{
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher(Index.PREFIX + Index.CITIZENDETAILPAGE).forward(request, response);
		}

	}
}
