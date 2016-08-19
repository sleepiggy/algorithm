package com.fwhalee.code;

public class Test {

	public static void main(String[] args) {
		
		
		// comma operator
		// don't work in java (int j = (i=3,i+2);)
		for (int i = 0, j = 10; i < 10; i++, j--) {
			System.out.println("i: " + i + "/ j: " + j);
		}
		
	}

}
