package com.fwhalee.code;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class RunningMedian {
	
	
	private static ArrayList<Double> numList;
	
	
	public static void main(String[] args) {
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String in = "";
		
		try {
		    
		    in = br.readLine();
		    int caseNum = Integer.parseInt(in);
            for (int i = 0; i < caseNum; i++) {
            	
                in = br.readLine();
                String[] numArry = in.split("\\s+");
                int num = Integer.parseInt(numArry[0]);
                int a = Integer.parseInt(numArry[1]);
                int b = Integer.parseInt(numArry[2]);
                
                numList = new ArrayList<Double>();
                numList.add((double) 1983);
                double temp = 1983;
                double sum = temp;
                for (int j = 1; j < num; j++) {
                	temp = ((temp * a) + b) % 20090711;
                	insert(temp);
                	
                	int midIdx = (numList.size() - 1) / 2;
                	sum += numList.get(midIdx);
                }
                
                System.out.printf("%.0f\n", sum % 20090711);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
		}
		
	}
	
	
	public static void insert(double num) {
		
	    // num <= numList[0]
		if (numList.get(0) >= num) {
			numList.add(0, num);
			return;
		}
		
		// numList[last] <= num
		if (numList.get(numList.size() - 1) <= num) {
			numList.add(num);
			return;
		}
		
		// numList [0] < num < numList[last]
		insertArry(num, 0, numList.size() - 1);
		
	}
	
	
	public static void insertArry(double num, int lower, int upper) {
		
		int midIdx = (lower + upper) / 2;
		double midValue = numList.get(midIdx);
		
		if (lower == upper) {
			if (midValue < num) {
				numList.add(midIdx + 1, num);
			} else {
				numList.add(midIdx, num);
			}
			return;
		}
		
		if (midValue == num) {
			numList.add(midIdx, num);
			return;
		}
		
		if (midValue > num) {
			if (lower == midIdx) {
				insertArry(num, lower, midIdx);
			} else {
				insertArry(num, lower, midIdx - 1);
			}
			
		}
		
		if (midValue < num) {
			if (midIdx == upper) {
				insertArry(num, midIdx, upper);
			} else {
				insertArry(num, midIdx + 1, upper);
			}
		} 
		
	}
	
	
}
