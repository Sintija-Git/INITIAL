package jtm.activity05;

import jtm.activity04.Road;
import jtm.activity04.Transport;

//Create class Ship as a subclass of Transport

public class Ship extends Transport {
	
	//Allow to store protected byte number of sails for Ship:
	protected byte numberOfSails;

	
	//Create Ship(String id, byte sails) constructor,
	public Ship(String id, byte numberOfSails) {
		super(id, 0f, 0); 
		this.numberOfSails = numberOfSails;
		
	}
	
	
	/*Override move(Road) to return String in form:
	//ID Ship is sailing on (Road as String) with x sails. where: (Road as String) is string representation of the road 
	(without brackets), x is actual number of sails.*/
	//return Cannot sail on (Road as String) if it is not WaterRoad.
	
	@Override
	public String move(Road road) {
		String message = "";
		
		if (road instanceof WaterRoad) { 
			
			message = super.getType() + " is sailing on " + road.toString() + " with " +numberOfSails + " sails";
					
		} else {
			
		message = "Cannot sail on " + road.toString();
		
		}
		
		return message;
	}		
 	
}
	
	
