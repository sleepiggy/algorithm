package com.fwhalee.code;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Potion {
	
	
	private static int[] potion = new int[200];
	private static int[] putPotion = new int[200];
	private static int[] dividePotion = new int[200];
	private static int[] rate = new int[200];
	
	
	public static void main(String[] args) {
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String in = "";
		
		try {
		    
		    in = br.readLine();
		    int caseNum = Integer.parseInt(in);
            for (int i = 0; i < caseNum; i++) {
            	
                in = br.readLine();
                int potionNum = Integer.parseInt(in);
                
                in = br.readLine();
                String[] rateArry = in.split("\\s+");
                in = br.readLine();
                String[] putArry = in.split("\\s+");
                
                potion = new int[200];
            	putPotion = new int[200];
            	dividePotion = new int[200];
            	rate = new int[200];
            	
                for (int j = 0; j < potionNum; j++) {
                	potion[j] = Integer.parseInt(rateArry[j]);
                	putPotion[j] = Integer.parseInt(putArry[j]);
                }
                
                int gcd = potion[0];
                for (int j = 1; j < potionNum; j++) {
                	gcd = gcd_recursion(gcd, potion[j]);
                }
                
                if (gcd > 0) {
	                for (int j = 0; j < potionNum; j++) {
	                	dividePotion[j] = potion[j] / gcd;
	                }
                }
                
                int answer2 = 1;
                int change2 = 0;
                boolean temp = false;
                for (int j = 0; j < potionNum; j++) {
                	if (putPotion[j] > potion[j]) {
                		temp = true;
                		break;
                	}
                }
                
                if (temp) {
	                for (int j = 0; j < potionNum; j++) {
	                	if (putPotion[j] > dividePotion[j]) {
	                		if (answer2 <= (putPotion[j] / dividePotion[j])) {
	                			answer2 = putPotion[j] / dividePotion[j];
	                			change2 = putPotion[j] % dividePotion[j];
	                		}
	                	}
	                }
	                
	                
	                if (change2 > 0) answer2++;
	                for (int j = 0; j < potionNum; j++) {
	                	rate[j] = dividePotion[j] * (answer2);
	                }
	                
	            	for (int j = 0; j < potionNum; j++) {
	            		System.out.print((rate[j] - putPotion[j]) + " ");
	            	}
                } else {
                	for (int j = 0; j < potionNum; j++) {
	            		System.out.print((potion[j] - putPotion[j]) + " ");
	            	}
                }
                
                System.out.print("\n");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
		}
		
		
	}
	
	
	public static int gcd_recursion(int u, int v){
	    
	    if (v == 0){
	        return u;
	    }
	    else{
	        return gcd_recursion(v, u%v);
	    }
	}
	
}
