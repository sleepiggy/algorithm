package com.fwhalee.sample;

public class GcdLcm {

	public static void main(String[] args) {
		
		
		int n = 4;
		int m = 8;
		
		int gcd = getGcd(n, m);
		System.out.println("GCD: " + gcd);
		
		int lcm = getLcm(n, m);
		System.out.println("LCM: " + lcm);
		
		
	}
	
	
	/**
	 * get greatest common divisor
	 * 
	 * @return
	 */
	public static int getGcd(int n, int m) {
		
		if (n < m) {
			int temp = n;
			n = m;
			m = temp;
		}
		
		while (m != 0) {
			int temp = n % m;
			n = m;
			m = temp;
		}
		
		return n;
	}
	
	
	/**
	 * get least common multiple
	 * 
	 * @return
	 */
	public static int getLcm(int n, int m) {
		return n * m / getGcd(n, m);
	}
	
	
}
