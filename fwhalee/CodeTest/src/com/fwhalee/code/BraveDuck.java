package com.fwhalee.code;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 주어진 radius에 대해 node의 연결성 판단
 * @author fwhalee
 *
 */
public class BraveDuck {
	
	
	private static ArrayList<Coordinate> coorList;
	private static ArrayList<ArrayList<Integer>> connectInfo;
	private static boolean isConnected;
	
	
	public static void main(String[] args) {
		
		
		Scanner reader = new Scanner(System.in);
		try {
            
            
            int caseNum = reader.nextInt();
            for (int i = 0; i < caseNum; i++) {
            	
            	int radius = reader.nextInt();
            	coorList = new ArrayList<Coordinate>();
            	coorList.add(new Coordinate(reader.nextInt(), reader.nextInt()));
            	coorList.add(new Coordinate(reader.nextInt(), reader.nextInt()));
            	
            	int coorNum = reader.nextInt();
            	for (int j = 0; j < coorNum; j++)
            		coorList.add(new Coordinate(reader.nextInt(), reader.nextInt()));
            	
            	
            	// 연결 정보 생성
            	connectInfo = new ArrayList<ArrayList<Integer>>();
            	for (int j = 0; j < coorList.size(); j++) {
            		
            		ArrayList<Integer> tempList = new ArrayList<Integer>();
            		for (int k = 0; k < coorList.size(); k++) {
            			
            			if (j == k) continue;
            			double distance = getDistance(coorList.get(j), coorList.get(k));
            			if (distance <= radius) tempList.add(k);
            		}
            		connectInfo.add(tempList);
            	}
            	
            	isConnected = false;
            	findPath(0, -1);
            	
            	if(isConnected) System.out.println("YES");	
            	else System.out.println("NO");
            	
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
		}
	}
	
	
	public static void findPath(int source, int before) {
		
		if (!isConnected) {
			if (source == 1) {
				isConnected = true;
				return;
			}
			
			int size = connectInfo.get(source).size();
			for (int i = 0; i < size; i++) {
				if (connectInfo.get(source).get(i) == before) continue;
				findPath(connectInfo.get(source).get(i), source);
			}
		}
		
	}
	
	
	public static double getDistance(Coordinate s, Coordinate d) {
		return Math.sqrt(Math.pow(s.getX() - d.getX(), 2) + Math.pow(s.getY() - d.getY(), 2));
	}
	
	
}


class Coordinate {
	
	
	private int x;
	private int y;
	
	
	Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	
}


