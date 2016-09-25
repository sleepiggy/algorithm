package com.fwhalee.code;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * node간 최소 noise path 찾기
 * @author fwhalee
 *
 */
public class Routing {
	
	
	private static ArrayList<ArrayList<ConnectInfo>> connectInfoList;
	private static ArrayList<Integer> visitList;
	private static int destIndex;
	private static float minNoise = 100000;
	
	
	public static void main(String[] args) {
		
		
		Scanner reader = new Scanner(System.in);
		try {
            
            int caseNum = reader.nextInt();
            for (int i = 0; i < caseNum; i++) {
            	
            	int nodeNum = reader.nextInt();
            	int edgeNum = reader.nextInt();
            	
            	connectInfoList = new ArrayList<ArrayList<ConnectInfo>>();
            	visitList = new ArrayList<Integer>();
            	for (int j = 0; j < nodeNum; j++) {
            		ArrayList<ConnectInfo> tempList = new ArrayList<ConnectInfo>();
            		connectInfoList.add(tempList);
            		visitList.add(0);
            	}
            	
            	ConnectInfo sourceConnectInfo = null;
            	ConnectInfo destConnectInfo = null;
            	for (int j = 0; j < edgeNum; j++) {
            		int source = reader.nextInt();
            		int dest = reader.nextInt();
            		float noise = reader.nextFloat();
            		
            		sourceConnectInfo = new ConnectInfo(dest, noise);
            		connectInfoList.get(source).add(sourceConnectInfo);
            		
            		destConnectInfo = new ConnectInfo(source, noise);
            		connectInfoList.get(dest).add(destConnectInfo);
            	}
            	
            	
            	destIndex = nodeNum - 1;
            	getMinPath(0, 1);
            	System.out.printf("%.10f", minNoise);
            	
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
		}
	}
	
	
	public static void getMinPath(int index, float total) {
		
		if (visitList.get(index) != 1) {
			
			if (total > minNoise) return;
			
			if (destIndex == index) {
				if (minNoise >= total ) minNoise = total;
				return;
			}
			
			visitList.set(index, 1);
			for (int i = 0; i < connectInfoList.get(index).size(); i++) {
				float tempTotal = total;
				tempTotal *= connectInfoList.get(index).get(i).getNoise();
				getMinPath(connectInfoList.get(index).get(i).getNode(), tempTotal);
			}
			visitList.set(index, 0);
		}
		
	}
	
	
}


class ConnectInfo {
	
	
	private int node;
	private float noise;
	
	
	ConnectInfo(int node, float noise) {
		this.node = node;
		this.noise = noise;
	}

	public int getNode() {
		return node;
	}
	public void setNode(int node) {
		this.node = node;
	}
	public float getNoise() {
		return noise;
	}
	public void setNoise(float noise) {
		this.noise = noise;
	}
	
	
}

