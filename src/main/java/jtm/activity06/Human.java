package jtm.activity06;


public class Human implements Humanoid {

	private int stomach = 0;
	private int weight = BirthWeight;
	private int vomit = 0;
	private boolean isAlive = true;

	public Human() {} //default constructor
	
	
	@Override
	public void eat(Integer food) {
	
		if(stomach == 0) {
			stomach += food;
			weight += food;
		}
	}
	

	@Override
	public Integer vomit() {
		vomit = stomach;
		if(stomach != 0) {
		weight -= stomach;
		stomach = 0;
		}
		return vomit;
	}

	
	@Override
	public String isAlive() { 
		if(isAlive == true) {
		return "Alive";
		} else {
		return "Dead";
		}			
	}
	
	
	@Override
	public String killHimself() {
		isAlive = false;
		return "Dead";	
	}

	
 
	@Override
	public int getWeight() {
		weight = Humanoid.BirthWeight + stomach;
		return weight;	
	}
	
	
	@Override
	public String toString() {
		return getClass().getSimpleName() + ": " + getWeight()  +" ["+ stomach +"]";
	}
	
	
	//getters&setters

	public int getStomach() {
		return stomach;
	}

	public void setStomach(int stomach) {
		this.stomach = stomach;
	}

	public int getVomit() {
		return vomit;
	}

	public void setVomit(int vomit) {
		this.vomit = vomit;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	
} //end class
