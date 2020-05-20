package jtm.extra03;

import java.util.ArrayList;



public class PracticalNumbers {

	// TODO Read article https://en.wikipedia.org/wiki/Practical_number
	// Implement method, which returns practical numbers in given range
	// including
	
	public String getPracticalNumbers(int from, int to) {
		
		
		ArrayList<Integer> practialNumbersList = new ArrayList<Integer> (); //list to store all practical numbers
	
		for(int i = from; i <= to; i++) { //for loop with set conditions using values of for / to
			if(FindPracticalNum(i)) { //calling FindPracticalNum to check if current number is practical
				practialNumbersList.add(i); //if true add to list
			}
			
			System.out.println(practialNumbersList.toString());
			System.out.println(from + " " +  to);
		}
		
		return practialNumbersList.toString(); //return list thats converted to String
		
	}

	

	public static boolean FindPracticalNum (int num)	{
	
		ArrayList<Integer> divisorsList = new ArrayList<Integer> (); //creating list to store all divisors
		
		for (int dev = 1; dev<num; dev++) { 
			if(num % dev == 0) {
				divisorsList.add(dev);
			}	
		}
		
		
		int[] divisors = new int[divisorsList.size()]; //creating array of list size
		for(int i = 0; i<divisors.length; i++) { //iterating through list 
			divisors[i] = divisorsList.get(i).intValue(); //and storing each its value in array
		}
		
		for(int i = num-1; i>0; i--) { //outer for loop to iterate through all numbers of given number - 1
			  int temp = i; //storing current number in temp
			  for(int j = divisors.length-1; j>=0; j--) { //outer loop, gives index of arrays value that going to be used for further calculation
			    if(temp>0) //
			    temp -= divisors[j];
			    if(temp <0)
			    temp+= divisors[j];
			    if(temp == 0)
			    break;
			    }
			    if(temp!= 0 )
			    return false;
			  }
				return true;
}
}