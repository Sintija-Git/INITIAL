package jtm.activity07;



public class Animal {

	
	private int age; //Add private field int age;
	

	public int getAge() { //Add public method int getAge();
		return age;
	}

	public void setAge(int age) { // Add public method void setAge(int age), which allow to set only non-negative number;
		 
		  if(age >= 0 ){
		    this.age = age;
		  		}else{
		  			this.age = 0;
		  		}
	}
	
	
	
}


