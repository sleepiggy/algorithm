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
		
		
		if (g_date > 0 && (g_date * 2) >= g_dist) {
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
		
	}
	
	
	public static double combination(int n, int r) {
		
		double temp_n = n;
		double temp_r = r;
		double comb = 1;
		if (n != r && r != 0) {
			int count = r;
			for (int i = 0; i < count; i++) {
				comb *= temp_n / temp_r; 
				temp_n--;
				temp_r--;
			}
		}
		
		return comb;
	}
	
	
}
