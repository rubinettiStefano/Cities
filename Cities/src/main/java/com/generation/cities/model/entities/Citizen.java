package com.generation.cities.model.entities;

public class Citizen extends Entity
{
	// ho anche ID, anche se non si vede
	// campi miei
	public String name;
	public String surname,profession,dob,gender;
	public int salary;
	
	// riferimenti all edificio in cui abito(chiave esterna e collegamento diretto)
	public String bodyID;
	public Body body;
	
	public Citizen(String ID, String name, String surname, String bodyID) 
	{
		this.ID   = ID;
		this.name = name;
		this.surname = surname;
		this.bodyID = bodyID;
	}
	
	
	
	
	public Citizen(String ID,String name, String surname, String profession, String dob, String gender, int salary,
		String bodyID)
	{
	    this.ID   = ID;
	    this.name = name;
	    this.surname = surname;
	    this.profession = profession;
	    this.dob = dob;
	    this.gender = gender;
	    this.salary = salary;
	    this.bodyID = bodyID;
	}




	public Citizen(String ID, String name, String surname) 
	{
		this.ID   = ID;
		this.name = name;
		this.surname = surname;
	}
	
	public Citizen(String csv) // COSTRUTTORE DA CSV
	{
		String[] parts = csv.split(",");
		ID  		= parts[0];
		name		= parts[1];
		surname		= parts[2];
		bodyID		= parts[3];
	}
	
	
	
}
