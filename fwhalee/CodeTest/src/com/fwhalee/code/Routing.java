package com.fwhalee.code;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * node간 최소 noise path 찾기
 * @author fwhalee
 *
 */
public class Routing {
	
	
	private static ArrayList<ArrayList<ConnectInfo>> connectInfoList;
	private static ArrayList<Integer> visitList;
	private static int destIndex;
	private static double minNoise = Double.MAX_VALUE;
	
	
	public static void main(String[] args) {
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String in = "";
		
		try {
		    
		    in = br.readLine();
		    int caseNum = Integer.parseInt(in);
            for (int i = 0; i < caseNum; i++) {
            	
                in = br.readLine();
                String[] componentNumArry = in.split("\\s+");
                int nodeNum = Integer.parseInt(componentNumArry[0]);
                int edgeNum = Integer.parseInt(componentNumArry[1]);
            	
            	connectInfoList = new ArrayList<ArrayList<ConnectInfo>>();
            	visitList = new ArrayList<Integer>();
            	ArrayList<ConnectInfo> tempList = null;
            	for (int j = 0; j < nodeNum; j++) {
            	    tempList = new ArrayList<ConnectInfo>();
            		connectInfoList.add(tempList);
            		visitList.add(0);
            	}
            	
            	for (int j = 0; j < edgeNum; j++) {
            	    
            	    in = br.readLine();
                    String[] nodeInfoArry = in.split("\\s+");
                    int source = Integer.parseInt(nodeInfoArry[0]);
                    int dest = Integer.parseInt(nodeInfoArry[1]);
                    double noise = Double.parseDouble(nodeInfoArry[2]);
            		
            		connectInfoList.get(source).add(new ConnectInfo(dest, noise));
            		connectInfoList.get(dest).add(new ConnectInfo(source, noise));
            	}
            	
            	
            	destIndex = nodeNum - 1;
            	getMinPath(0, 1);
            	System.out.printf("%.10f\n", minNoise);
            	
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
		}
	}
	
	
	public static void getMinPath(int index, double total) {
		
		if (visitList.get(index) != 1) {
			
			if (total >= minNoise) return;
			
			if (destIndex == index) {
				if (minNoise >= total) minNoise = total;
				return;
			}
			
			visitList.set(index, 1);
			double tempTotal = -1;
			for (int i = 0; i < connectInfoList.get(index).size(); i++) {
				tempTotal = total;
				tempTotal *= connectInfoList.get(index).get(i).noise;
				getMinPath(connectInfoList.get(index).get(i).node, tempTotal);
			}
			visitList.set(index, 0);
		}
		
	}
	
	
}


class ConnectInfo {
	
	
	public int node;
	public double noise;
	
	
	ConnectInfo(int node, double noise) {
		this.node = node;
		this.noise = noise;
	}
	
	
}

