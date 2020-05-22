package jtm.activity06;

import java.util.ArrayList;
import java.util.Arrays;

public class Martian implements Humanoid, Alien, Cloneable {
	
	private int weight = 0;
	Object vomit = null;
	ArrayList<Object> contenOfStomach = new ArrayList<Object> ();
	
	//SETTERS GETTERS FOR VARIABLES
	
	
	
	@Override
    public Object clone() throws CloneNotSupportedException {
    return clone(this);
	}
	
//  TODO implement cloning of current object
//  and its stomach
	private Object clone(Object current) throws CloneNotSupportedException { 
	return super.clone();

	}
	

	
	/**
	 * @param If Alien is hungry (stomach is empty), eat Object. Possibly eaten
	 *           items are:
	 * 
	 *           1. Integer, 2. Humanoid, 3. Alien.
	 * 
	 *           Gain weight of eaten item, and kill it, if possible
	 */
	
	
	@Override
	public void eat(Integer food) {
		if (food instanceof Integer) {
			contenOfStomach.add(food);
			weight = getWeight();
		}
		
	}
	@Override
	public void eat(Object item) {
		
		if (item instanceof Human) {
		weight = ((Human) item).getWeight();
		 contenOfStomach.add(item);
		 ((Human) item).killHimself();
		
		} else if (item instanceof Alien) {
		weight = ((Martian) item).getWeight();
		contenOfStomach.add(item);	
		}
	}

	
	@Override
	public Object vomit() {

		if (contenOfStomach.equals(null)) {
			 return null;
		} else {
			vomit = contenOfStomach;
			contenOfStomach.clear();
	}
		return vomit;
	}

	@Override
	public String isAlive() {
		
		return "I AM IMMORTAL!";
	}

	@Override
	public String killHimself() {
		
		return "I AM IMMORTAL!";
	}

	@Override
	public int getWeight() {
		weight = Alien.BirthWeight + weight;
		return weight;
	}
	
	
	
	@Override
	public String toString() {
		
		return getClass().getSimpleName() + ": " + weight + " " + contenOfStomach; 
	}
	
	

}
