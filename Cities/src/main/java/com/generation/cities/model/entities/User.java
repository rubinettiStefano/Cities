package com.generation.cities.model.entities;

public class User extends Entity
{
    public String email;
    public String password;
    public User(String ID,String email, String password)
    {
	this.ID = ID;
	this.email = email;
	this.password = password;
    }
    
    
    
}
