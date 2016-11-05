package com.fwhalee.code;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Potion {
	
	
	private static int[] ingreIntArry;
	private static int[] putIngreIntArry;
	private static int[] dividedIngreIntArry;
	private static int[] finallIngreIntArry;
	
	
	public static void main(String[] args) {
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String in = "";
		
		try {
		    
		    in = br.readLine();
		    int caseNum = Integer.parseInt(in);
            for (int i = 0; i < caseNum; i++) {
            	
            	ingreIntArry = new int[200];
            	putIngreIntArry = new int[200];
            	dividedIngreIntArry = new int[200];
            	finallIngreIntArry = new int[200];
            	
            	
            	in = br.readLine();
                int ingreNum = Integer.parseInt(in);
                
                in = br.readLine();
                String[] ingreStrArry = in.split("\\s+");
                in = br.readLine();
                String[] putIngreStrArry = in.split("\\s+");
                
                for (int j = 0; j < ingreNum; j++) {
                	ingreIntArry[j] = Integer.parseInt(ingreStrArry[j]);
                	putIngreIntArry[j] = Integer.parseInt(putIngreStrArry[j]);
                }
                
                getAnswer(ingreNum);
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
		}
		
		
	}
	
	
	public static void getAnswer(int ingreNum) {
		
		int gcd = ingreIntArry[0];
        for (int j = 1; j < ingreNum; j++) {
        	gcd = getGcd(gcd, ingreIntArry[j]);
        }
        
        for (int j = 0; j < ingreNum; j++) {
        	dividedIngreIntArry[j] = ingreIntArry[j] / gcd;
        }
        
        boolean calculate = false;
        for (int j = 0; j < ingreNum; j++) {
        	if (putIngreIntArry[j] > ingreIntArry[j]) {
        		calculate = true;
        		break;
        	}
        }
        
        int answer = 1;
        int rest = 0;
        if (calculate) {
            for (int j = 0; j < ingreNum; j++) {
            	if (putIngreIntArry[j] > dividedIngreIntArry[j]) {
            		if (answer <= (putIngreIntArry[j] / dividedIngreIntArry[j])) {
            			answer = putIngreIntArry[j] / dividedIngreIntArry[j];
            			rest = putIngreIntArry[j] % dividedIngreIntArry[j];
            		}
            	}
            }
            
            if (rest > 0) answer++;
            for (int j = 0; j < ingreNum; j++) {
            	finallIngreIntArry[j] = dividedIngreIntArry[j] * (answer);
            	System.out.print((finallIngreIntArry[j] - putIngreIntArry[j]) + " ");
            }
            
        } else {
        	for (int j = 0; j < ingreNum; j++) {
        		System.out.print((ingreIntArry[j] - putIngreIntArry[j]) + " ");
        	}
        }
        
        System.out.print("\n");
        
	}
	
	
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
	
	
}
