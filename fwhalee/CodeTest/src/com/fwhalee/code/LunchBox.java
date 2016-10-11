package com.fwhalee.code;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class LunchBox {
	
	
	private static ArrayList<Package> packageList;
	
	
	public static void main(String[] args) {
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String in = "";
		
		try {
		    
		    in = br.readLine();
		    int caseNum = Integer.parseInt(in);
            for (int i = 0; i < caseNum; i++) {
            	
                in = br.readLine();
                int participants = Integer.parseInt(in);
                
                packageList = new ArrayList<Package>();
                in = br.readLine();
                String[] heatTimeArray = in.split("\\s+");
                for (int j = 0; j < participants; j++) {
                	Package p = new Package(Integer.parseInt(heatTimeArray[j]), 0);
                	packageList.add(p);
                }
                
                in = br.readLine();
                String[] eatTimeArray = in.split("\\s+");
                for (int j = 0; j < participants; j++) {
                	packageList.get(j).setEatTime(Integer.parseInt(eatTimeArray[j]));
                }
                
                Collections.sort(packageList, new NoDescCompare());
                
                int sum = 0;
                int max = -1;
                for (int j = 0; j < packageList.size(); j++) {
                	sum += packageList.get(j).getHeatTime();
                	int tempMax = sum + packageList.get(j).getEatTime();
                	if (max < tempMax)
                		max = tempMax;
                }
                
            	System.out.println(max);
            	
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
		}
		
	}
	
	
	static class NoDescCompare implements Comparator<Package> {
		
		@Override
		public int compare(Package arg0, Package arg1) {
			return arg0.getEatTime() > arg1.getEatTime() ? -1 : arg0.getEatTime() < arg1.getEatTime() ? 1:0;
		}
		
	}
	
	
}


class Package {
	
	
	private int heatTime;
	private int eatTime;
	
	
	Package(int heatTime, int eatTime) {
		this.heatTime = heatTime;
		this.eatTime = eatTime;
	}
	
	public int getHeatTime() {
		return heatTime;
	}
	public void setHeatTime(int heatTime) {
		this.heatTime = heatTime;
	}
	public int getEatTime() {
		return eatTime;
	}
	public void setEatTime(int eatTime) {
		this.eatTime = eatTime;
	}
	
	
}

