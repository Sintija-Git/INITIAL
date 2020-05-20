package jtm.activity05;

import jtm.activity04.Road;
import jtm.activity04.Transport;

//Create class Vehicle as subclass of Transport
public class Vehicle extends Transport {
	
	//Allow to store protected int number of wheels for vehicle.
	protected int numberOfWheels;

	//Implement Vehicle(String id, float consumption, int tankSize, int wheels) constructor,
	public Vehicle(String id, float consumption, int tankSize, int numberOfWheels) {
		super(id, consumption, tankSize);
		this.numberOfWheels = numberOfWheels;

	}

	//Override method move(Road) for vehicle, which returns String in form:
	//ID Vehicle is driving on (Road as String) with x wheels where:
	// (Road as String) is string representation of the road, x is actual number of wheels.
	// return Cannot drive on (Road as String) if it is not Road.
	
	@Override
	public String move (Road road) {
		
		String message = "";
		
		float fuelNeeded = super.getConsumption()*road.getDistance()/100;
		
		
		if (road.getClass() == Road.class && fuelNeeded <= getFuelInTank()) {
			super.setFuelInTank (getFuelInTank() - fuelNeeded);
			
			message = super.getType() + " is driving on " +road.toString() + " with " + numberOfWheels + " wheels";
		
		}else {
			
			message =  "Cannot drive on " + road.toString();
			
	}
			
			return message;
		
	}

}
