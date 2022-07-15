package com.generation.cities.controller.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.generation.cities.model.database.Database;
import com.generation.cities.model.entities.Body;
import com.generation.cities.model.entities.City;

public class BodyController
{
	Database database;

	public BodyController(Database database)
	{
		this.database = database;
	}

	public void formNewBuilding(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		request.getRequestDispatcher(Index.PREFIX + Index.FORMNEWBUILDINGPAGE).forward(request, response);
	}

	public void newBuilding(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String ID = request.getParameter("id");
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		String address = request.getParameter("address");
		String cityID = request.getParameter("cityid");
		try
		{
			int left = Integer.parseInt(request.getParameter("left"));
			int bottom = Integer.parseInt(request.getParameter("bottom"));
			int top = Integer.parseInt(request.getParameter("top"));
			int right = Integer.parseInt(request.getParameter("right"));

			Body body = new Body(ID, type, name, address, left, bottom, right, top, cityID);
			database.insertBody(body); // Lui deve fare un sacco di controlli

			City city = database.getCity(cityID);
			// io voglio finire alla pagina di dettaglio della città.
			// di conseguenza devo dire a quella pagina QUALE CITTA' VOGLIO VEDERE
			request.setAttribute(Index.ATTR_CITY, city); // alla pagina NON PASSO cityID
			request.getRequestDispatcher(Index.PREFIX + Index.CITYDETAILPAGE).forward(request, response);

		} catch (NumberFormatException e)
		{
			request.setAttribute("error", "Expected left,bottom,top,right numeric");
			request.getRequestDispatcher(Index.PREFIX + Index.FORMNEWBUILDINGPAGE).forward(request, response);
		} catch (RuntimeException e)
		{
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher(Index.PREFIX + Index.FORMNEWBUILDINGPAGE).forward(request, response);
		}
	}

	public void deleteBuilding(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String ID = request.getParameter("id");
		String cityID = request.getParameter("cityid");
		try
		{
			database.deleteBody(ID, cityID);
			request.setAttribute(Index.ATTR_CITY, database.getCity(cityID)); // alla pagina NON PASSO cityID
			request.getRequestDispatcher(Index.PREFIX + Index.CITYDETAILPAGE).forward(request, response);
		} catch (RuntimeException e)
		{
			request.setAttribute("error", e.getMessage());
			request.setAttribute(Index.ATTR_CITY, database.getCity(cityID)); // alla pagina NON PASSO cityID
			request.getRequestDispatcher(Index.PREFIX + Index.CITYDETAILPAGE).forward(request, response);
		}

	}
}
