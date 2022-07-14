package com.generation.cities.model.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Un body � qualunque cosa io possa aggiungere alla citt�
 * (parco, casa, centro commerciale...)
 * definito GEOGRAFICAMENTE. so sempre dov�. <br/>
 * E' una Entity quindi eredita ID.
 * @author Lorenzo
 *
 */
public class Body extends Entity
{
	// PROPRIETA DI OGGETTO CLASSE BODY
	// VARCHAR(100)
	public String type; // Rresidential, commercial, park...
	public String name; //OPZIONALE
	public String address;
	public int left, bottom, right, top;  						// DICHIARAZIONI VARIABILI
	Segment horizontal;
	Segment vertical;
	//CHIAVE ESTERNA
	public String cityID;
	public City city;
	public List<Citizen> citizens = new ArrayList<Citizen>();
	
	
	/**
	 * costruttore di body
	 * proprieta che definiscono un corpo
	 * @param ID
	 * @param type
	 * @param left
	 * @param bottom
	 * @param right
	 * @param top
	 */
	public Body(String ID, String type, int left, int bottom, int right, int top) // COSTRUTTORE (METODO SPECIALE)
	{																				// CREA UN OGETTO DI QUESTA CLASSE
		this.ID	  =ID;
		this.type = type;
		this.left = left;
		this.bottom = bottom;
		this.right = right;
		this.top = top;
		horizontal = new Segment(left , right);
		vertical = new Segment(bottom, top);
	}
	
	public Body(String type, int left, int bottom, int right, int top) // COSTRUTTORE (METODO SPECIALE)
	{																				// CREA UN OGETTO DI QUESTA CLASSE
		this.type = type;
		this.left = left;
		this.bottom = bottom;
		this.right = right;
		this.top = top;
		horizontal = new Segment(left , right);
		vertical = new Segment(bottom, top);
	}
	
	public Body(String ID,String type, String name, int left, int bottom, int right, int top) //2 COSTRUTTORE CON AGGIUNTA DEL PARAMETRO NOME
	{
		this.ID	  =ID;
		this.type = type;
		this.name = name;
		this.left = left;
		this.bottom = bottom;
		this.right = right;
		this.top = top;
		horizontal = new Segment(left , right);
		vertical = new Segment(bottom, top);
	}
	
	public Body(String iD, String type, String name,String address, int left, int bottom, int right, int top, String cityID) 
	{
		ID = iD;
		this.type = type;
		this.name = name;
		this.address = address;
		this.left = left;
		this.bottom = bottom;
		this.right = right;
		this.top = top;
		this.cityID = cityID;
		horizontal = new Segment(left , right);
		vertical = new Segment(bottom, top);
	}
	
	
	public Body(String csv) // COSTRUTTORE DA FILE CSV
	{
		String[] parts = csv.split(","); // VETTORE DI STRINGHE E LE DIVIDIAMO CON LA ","
		ID		= parts[0];
		type	= parts[1];
		name	= parts[2];
		left	= Integer.parseInt(parts[3]); //GLI INTERI VANNO TRASFORMATI PERCHE 
		bottom	= Integer.parseInt(parts[4]); //SONO TUTTE CONSIDERATE STRINGHE
		right	= Integer.parseInt(parts[5]);
		top		= Integer.parseInt(parts[6]);
		cityID	= parts[7];
		horizontal = new Segment(left , right);
		vertical = new Segment(bottom, top);
		
	}
	
	public String toString() // METODO DI RITORNO STRINGA PER STAMPARE 
	{
		return type+ " located at ("+left+","+bottom+")-("+right+","+top+")";
	}
	
	/**
	 * restituisce l area del body
	 * @return
	 */
	public int getArea() // METODO PER CALCOLARE L AREA
	{
		return 	horizontal.getLength()
				*
				vertical.getLength();		
	}
	
	/**
	 * vediamo se il body che arriva da fuori collide con quello 
	 * che abbiamo noi
	 * @param other
	 * @return
	 */
	public boolean collides(Body other) // METODO BOOLEANO RESTITUISCE DUE VALORI TRUE O FALSE
	{
		return this.horizontal.collides(other.horizontal) && this.vertical.collides(other.vertical);
	}
	// CI ARRIVA UN PARAMETRO DA FUORI CHE IN QUESTO CASO LO CHIAMIAMO OTHER(DI TIPO BODY)
	// E VEDIAMO SE COLLIDONO CON I NOSTRI BODY

	public boolean addCitizen(Citizen newCitizen)
	{
		citizens.add(newCitizen); // SE E' ARRIVATO FIN QUI VUOL DIRE CHE NON COLLIDE CON NESSUNO E VIENE
		// IMPOSTO LA CHIAVE ESTERNA IN BODY!
		newCitizen.bodyID = this.ID;
		// collego il campo city di newbody a ME STESSO
		newCitizen.body = this;
		
		return true;	
	}

	public void removeCitizen(String ID)
	{
	    Citizen c = null;
	    for(Citizen cit : citizens)
		if(cit.ID.equals(ID))
		    c = cit;
	    
	    if(c==null)
		throw new RuntimeException("Cannot find the citizen with the ID: "+ID+" in the body "+this.ID);
	    
	    citizens.remove(c);
	    c.body = null;
	    c.bodyID = null;
	}
	
	public void setCitizens(List<Citizen> newcitizens)
	{
	    citizens.clear();    
	    for(Citizen c:newcitizens)
		addCitizen(c);
	}
}	

