package com.fwhalee.code;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Snail {
	
	
	static int g_dist;
	static int g_date;
	
	static double g_prob;
	
	
	public static void main(String[] args) {
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String in = "";
		
		try {
		    
		    in = br.readLine();
		    int caseNum = Integer.parseInt(in);
            for (int i = 0; i < caseNum; i++) {
            	
                in = br.readLine();
                String[] numArry = in.split("\\s+");
                g_dist = Integer.parseInt(numArry[0]);
                g_date = Integer.parseInt(numArry[1]);
                g_prob = 0;
                
                process();
                System.out.printf("%.10f\n", g_prob);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
		}
		
	}
	
	
	public static void process() {
		
		for (int i = 0; i <= g_date; i++) {
			int j = g_date - i;
			
			double prob = Math.pow(0.75, i) * Math.pow(0.25, j);
			int dist = (2 * i) + j;
			
			if (dist >= g_dist) {
				g_prob = 1 - g_prob;
				break;
			}
			
			double comb = combination(g_date, i);
			g_prob += (prob * comb);
		}
			
	}
	
	
	public static double combination(int n, int r) {
		
		double comb = 1;
		if (n != r && r != 0) {
			double fac1 = 1;
			for (int i = 0; i < r; i++) {
				fac1 *= n;
				n--;
			}
			
			double fac2 = 1;
			for (int i = 0; i < r; i++) {
				fac2 *= r;
				r--;
			}
			comb = fac1 / fac2;
		}
		
		return comb;
	}
	
	
}
