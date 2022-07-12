package com.generation.cities.model.database;

import java.util.ArrayList;
import java.util.List;

import com.generation.cities.model.entities.Body;
import com.generation.cities.model.entities.Citizen;
import com.generation.cities.model.entities.City;

public class MockDatabase implements Database
{

	List<City> cities = new ArrayList<City>();
	
	public MockDatabase()
	{
		City city  = new City("CITY01","Monza",100,100);
		
		Body b1 = new Body("BODY01", "Residential", "Casa Prof",1,1,2,2);
		b1.addCitizen(new Citizen("CITIZEN01","Stefano","Rubinetti"));
		b1.addCitizen(new Citizen("CITIZEN02","Ferdinando","Primerano"));
		city.addBody(b1);
		Body b2 = new Body("BODY02","Park","Parco di Monza", 10,10,30,30);
		b2.addCitizen(new Citizen("CITIZEN03","Gianni","Pinotto"));
		city.addBody(b2);
		Body b3 = new Body("BODY03", "Residential", "Casa Vuota Grande",40,40,45,45);
		city.addBody(b3);
		Body b4 = new Body("BODY04", "Residential", "Casa Vuota Piccola",46,46,47,47);
		city.addBody(b4);
		
		cities.add(city);
		cities.add(new City("CITY02","Vicenza",200,100));
		cities.add(new City("CITY03","Roma",1000,1000));
		cities.add(new City("CITY04","Budapest",500,500));
		cities.add(new City("CITY05","Lisbona",300,300));
	}
	
	@Override
	public List<City> getCities()
	{
		return cities;
	}
	
	@Override
	public City getCity(String ID)
	{
		for(City c : cities)
			if(c.ID.equals(ID))
				return c;
		return null;
	}

	@Override
	public List<Citizen> getCitizen(String key)
	{
		List<Citizen> res = new ArrayList<Citizen>();
		for(City city : getCities())
			for(Body b : city.bodies)
				for(Citizen c : b.citizens)
					if((c.name + c.surname).toLowerCase().contains(key.toLowerCase()))
						res.add(c);
		return res;
	}

	@Override
	public List<Body> getFreeHouses(String cityID, int minsize)
	{
		List<Body> res = new ArrayList<Body>();
		for(City c : getCities())
			if(c.ID.equalsIgnoreCase(cityID))
			{
				for(Body b : c.bodies)
					if(b.getArea()>=minsize && b.type.equals("Residential") && b.citizens.isEmpty())
						res.add(b);
				break;
			}
		return res;
	}
	
	public List<Body> getBodies()
	{
		List<Body> res = new ArrayList<Body>();
		for(City c : getCities())
			res.addAll(c.bodies);
		return res;
	}
	
	public List<Citizen> getCitizens()
	{
		List<Citizen> res = new ArrayList<Citizen>();
		for(Body b : getBodies())
			res.addAll(b.citizens);
		return res;
	}

	@Override
	public List<Body> getBodies(String key)
	{
		return getBodies().stream().filter(b->(b.name + " "+b.ID+" "+b.type+" "+b.city.name).toLowerCase().contains(key.toLowerCase())).toList();
	}

	@Override
	public List<City> getCities(String key)
	{
	    return 	getCities().stream()
		    	.filter(c -> (c.name + " "+c.ID).toLowerCase().contains(key.toLowerCase()))
		    	.toList();
	}

	@Override
	public List<Citizen> getCitizens(String key)
	{
	    return 	getCitizens().stream()
		    	.filter(c -> (c.name + " "+c.surname + " "+c.ID+" "+c.body.city.name + " "+c.body.name).toLowerCase().contains(key.toLowerCase()))
		    	.toList();
	}

	@Override
	public void insertCity(City city)
	{
	    if(city==null)
		throw new RuntimeException("Cannot save null city");
	    if(city.ID==null    || city.ID.isBlank())
		throw new RuntimeException("Cannot save a city without an ID");
	    if(city.name==null  || city.name.isBlank())	
		throw new RuntimeException("Cannot save a city without a name");
	    if(city.w<=0 || city.h<=0)	
		throw new RuntimeException("Cannot save a city with w or h <=0");
	    if(getCity(city.ID)!=null)	
		throw new RuntimeException("Unique ID constriction FAILED");

	    cities.add(city);
	}
}
