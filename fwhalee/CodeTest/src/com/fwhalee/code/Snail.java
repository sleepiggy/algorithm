package com.fwhalee.code;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Snail {
	
	
	static int g_dist;
	static int g_level;
	
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
                g_level = Integer.parseInt(numArry[1]);
                g_prob = 0;
                
                // process(0.75, 0, 1);
                // process(0.25, 0, 1);
                process2();
                System.out.printf("%.10f\n", g_prob);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
		}
		
	}
	
	
	public static void process(double prob, int dist, int level) {
		
		if (dist >= g_dist) {
			g_prob += prob;
			return;
		}
		
		if (level > g_level) {
			if (dist >= g_dist) {
				g_prob += prob;
			}
			return;
		}
		
		double prob1 = prob * 0.75;
		int dist1 = dist + 2;
		
		double prob2 = prob * 0.25;
		int dist2 = dist + 1;
		
		level++;
		process(prob1, dist1, level);
		process(prob2, dist2, level);
		
	}
	
	
	public static void process2() {
		
		for (int i = 0; i <= g_level; i++) {
			int j = g_level - i;
			
			double prob = Math.pow(0.75, i) * Math.pow(0.25, j);
			int dist = (2 * i) + j;
			
			if (dist >= g_dist) {
				
				int temp = g_level;
				double probSum = g_level;
				while (true) {
					if (temp == g_level - i + 1) break;
					temp--;
					probSum *= temp;
				}
				 
				int fac = i;
				double facSum = fac;
				while (true) {
					if (fac == 1) break;
					fac--;
					facSum *= fac;
				}
				probSum = probSum / facSum;
				g_prob += (prob * probSum);
			}
			
			
		}
	}
	
	
}
