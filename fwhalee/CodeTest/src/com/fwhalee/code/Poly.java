package com.fwhalee.code;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Poly {
	
	
	static int[][] d;
	
	
	public static void main(String[] args) {
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String in = "";
		
		try {
		    
		    in = br.readLine();
		    int caseNum = Integer.parseInt(in);
            for (int i = 0; i < caseNum; i++) {
            	
                in = br.readLine();
                int num = Integer.parseInt(in);
                
                d = new int[101][100000];
                d[0][0] = 1;
                d[1][0] = 1;
                d[1][1] = 1;
                getPolyNum(num);
                System.out.println(d[num][0]);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
		}
		
	}
	
	
	public static void getPolyNum(int num) {
		
		
		for (int i = 2; i <= num; i++) {
			
			int totalSum = 0;
			
			for (int j = 1; j <= i; j++ ) {
				int sum = 0;
				for (int k = 1; k <= (i-j); k++) {
					if (i == j){
						d[i][j] = 1;
						sum += d[i][j];
						break;
					}
					sum += (j + k - 1) * d[i-j][k];
				}
				d[i][j] = sum;
				totalSum += d[i][j];
			}
			d[i][0] = totalSum;
		}
	}
	
	
}

