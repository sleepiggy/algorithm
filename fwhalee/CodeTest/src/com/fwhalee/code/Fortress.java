package com.fwhalee.code;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Fortress {
	
	
	private static int first = 0;
	private static int second = 0;
	
	
	public static void main(String[] args) {
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String in = "";
		
		try {
		    
		    in = br.readLine();
		    int caseNum = Integer.parseInt(in);
            for (int i = 0; i < caseNum; i++) {
            	
            	
                in = br.readLine();
                int wallNum = Integer.parseInt(in);
                
                first = 0;
                second = 0;
                List<Wall> wallList = new ArrayList<Wall>();
                for (int j = 0; j < wallNum; j++) {
                	
                	if (j == 0) {
                		in = br.readLine();
                		continue;
                	}
                	in = br.readLine();
                    String[] numArry = in.split("\\s+");
                    int x = Integer.parseInt(numArry[0]);
                    int y = Integer.parseInt(numArry[1]);
                    int r = Integer.parseInt(numArry[2]);
                    
                    Wall tempWall = new Wall(x, y, r);
                    wallList.add(tempWall);
                }
                
                Collections.sort(wallList, new AscCompare());
                
                List<OuterWall> outerWallList = new ArrayList<OuterWall>();
                OuterWall tempOuterWall = new OuterWall(wallList.get(0));
                outerWallList.add(tempOuterWall);
                for (int j = 1; j < wallList.size(); j++) {
                	
                	boolean isInclude = false;
                	for (int k = 0; k < outerWallList.size(); k++) {
                		double d = getDistance(outerWallList.get(k).getWall(), wallList.get(j));
                		
                		if (wallList.get(j).getR() >= d) {
                			outerWallList.get(k).getWall().setX(wallList.get(j).getX());
                			outerWallList.get(k).getWall().setY(wallList.get(j).getY());
                			outerWallList.get(k).getWall().setR(wallList.get(j).getR());
                			
                			int level = outerWallList.get(k).getLevel() + 1;
                			outerWallList.get(k).setLevel(level);
                			
                			if (level > first) {
                				first = level;
                			} else if (first >= level && level > second) {
                				second = level;
                			}
                			isInclude = true;
                		} 
                	}
                	
                	if (!isInclude) {
                		OuterWall tempOuterWall2 = new OuterWall(wallList.get(j));
                        outerWallList.add(tempOuterWall2);
                	}
                }
                
                System.out.println(first + second);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
		}
		
		
	}
	
	
	/**
	 * sort java object by ascending
	 * @author fwhalee
	 *
	 */
	static class AscCompare implements Comparator<Wall> {
		
		@Override
		public int compare(Wall arg0, Wall arg1) {
			return arg0.getR() < arg1.getR() ? -1 : arg0.getR() > arg1.getR() ? 1:0;
		}
		
	}
	
	
	public static double getDistance(Wall s, Wall d) {
		return Math.pow(s.getX() - d.getX(), 2) + Math.pow(s.getY() - d.getY(), 2);
	}
	
	
}


class Wall {
	
	
	private int x;
	private int y;
	private int r;
	
	
	Wall(int x, int y, int r) {
		this.x = x;
		this.y = y;
		this.r = r;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getR() {
		return r;
	}
	public void setR(int r) {
		this.r = r;
	}
	
	
}


class OuterWall {
	
	
	private Wall wall;
	private int level;
	
	
	OuterWall(Wall wall) {
		this.wall = wall;
		this.level = 1;
	}
	public Wall getWall() {
		return wall;
	}
	public void setWall(Wall wall) {
		this.wall = wall;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	
	
}




