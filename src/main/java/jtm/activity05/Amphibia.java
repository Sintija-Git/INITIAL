package jtm.activity05;

import jtm.activity04.Road;
import jtm.activity04.Transport;

//Implement Amphibia class in a such way, that it is a Transport:


public class Amphibia extends Transport {

	//Make all internal properties of Amphibia private.
	private byte numberOfSails;
	private int numberOfWheels;
	
	//implement constructor Amphibia(String id, float consumption, int tankSize, byte sails, int wheels)
	public Amphibia(String id, float consumption, int tankSize, byte numberOfSails, int numberOfWheels) {
		super(id, consumption, tankSize);
		this.numberOfSails = numberOfSails;
		this.numberOfWheels = numberOfWheels;
	}

	//Override move(Road road) method, that Amhibia behaves like a Vehicle on ground road and like a Ship on water road.
	@Override
	public String move(Road road) {
		
		String message = "";
		
		float fuelNeeded = super.getConsumption()*road.getDistance()/100;
		
		
		if (road.getClass() == Road.class && fuelNeeded <= getFuelInTank()) {
			
			message = super.getType() + " is driving on " +road.toString() + " with " + numberOfWheels + " wheels";
			super.setFuelInTank (getFuelInTank() - fuelNeeded);
			
			
		} else {
			message=  super.getType() + " is sailing on " + road.toString() + " with " +numberOfSails + " sails";
			System.out.println(message);
	
	}
		return message;
		

	}
	
}
