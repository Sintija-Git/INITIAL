package jtm.activity07;

public class Dog extends Mammal {
	
	private String name;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {	
		
	    for (int i = 0; i<name.length(); i++) {

	        if  (!Character.isUpperCase(name.charAt(0)) && (!Character.isLetter(i))){   
	        	this.name = "";
	        } else {
	        	this.name = name;
	        }
	            
	    	}
		}	
		
	}


