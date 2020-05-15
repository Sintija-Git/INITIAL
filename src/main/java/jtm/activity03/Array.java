package jtm.activity03;

import java.util.Arrays;

public class Array {
	static int[] array;

	public static void main(String[] args) {
		
	
		// TODO Use passed parameters for main method to initialize array
		// Hint: use Run— Run configurations... Arguments to pass parameters to
		// main method when calling from Eclipse
		
		array = new int [args.length];
		
		// Sort elements in this array in ascending order
		// Hint: use Integer.parseInt(args[n]) to convert passed parameters from String to int
		
		for(int i = 0; i<args.length; i++) {
			array[i] = Integer.parseInt(args[i]);
		}
		
		
		returnSortedArray();
		printSortedArray();
		
				
		// Hint: use Arrays.sort(...) from  Arrays.sort(args);
		// https://docs.oracle.com/javase/7/docs/api/java/util/Arrays.html
	}

	public static void printSortedArray() {
		// TODO print content of array on standard output
		// Hint: use Arrays.toString(array) method for this
		System.out.println(Arrays.toString(array));
	}
	
	

	public static int[] returnSortedArray() {
		Arrays.sort(array);
		return array;
	}

}
