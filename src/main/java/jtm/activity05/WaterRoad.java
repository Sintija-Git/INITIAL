package jtm.activity05;

import jtm.activity04.Road;

/*
Create class WaterRoad as a subclass of Road
Select: Constructors from superclass, Inherited abstract methods and press Finish
Or, if you have not done it in class creation wizard: Select Source — Generate Constructors from Superclass..., 
select both constructors Road() and Road(String,String,int) and press OK.
 */

public class WaterRoad extends Road {

	public WaterRoad(String from, String to, int distance) {
		super(from, to, distance);
		
	}

	public WaterRoad() {
		
	}
	
	//Select Source — Override/Implement methods... and select method toString().
	//Override .toString() method which returns string in form: WaterRoad From — To, 00km

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getClass().getSimpleName() + " " + super.toString();
	}
	
	

}
