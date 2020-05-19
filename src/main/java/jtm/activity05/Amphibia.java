package jtm.activity05;

import jtm.activity04.Road;
import jtm.activity04.Transport;
//Implement Amphibia class in a such way, that it is a Transport:
//Make all internal properties of Amphibia private.

public class Amphibia extends Transport {

	//implement constructor Amphibia(String id, float consumption, int tankSize, byte sails, int wheels)
	private byte numberOfSails;
	private int numberOfWheels;
	
	public Amphibia(String id, float consumption, int tankSize, byte numberOfSails, int numberOfWheels) {
		super(id, consumption, tankSize);
		this.numberOfSails = numberOfSails;
		this.numberOfWheels = numberOfWheels;
	}

	//Override move(Road road) method, that Amhibia behaves like a Vehicle on ground road and like a Ship on water road.
	@Override
	public String move(Road road) {
		String message = "";
		
		if (road.getClass() == Road.class) {
			message = super.getId() +" "+ this.getClass().getSimpleName() + " is driving on " +road.toString() + " with " + numberOfWheels + " wheels";
			System.out.println(message);
			
		} else {
			message=  super.getId() + " " + this.getClass().getSimpleName() + " is sailing on " + road.toString() + " with " +numberOfSails + " sails";
			System.out.println(message);
	
	}
		return message;
		

	}
	
}
