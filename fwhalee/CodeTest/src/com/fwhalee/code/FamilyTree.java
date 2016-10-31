package com.fwhalee.code;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FamilyTree {
	
	
	public static ArrayList<Node> nodeList;
	
	public static int g_src;
	public static int g_dest;
	
	public static boolean isFind = false;
	
	public static int childCount = 0;
	public static int parentCount = 0;
	
	
	public static void main(String[] args) {
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String in = "";
		
		try {
		    
		    in = br.readLine();
		    int caseNum = Integer.parseInt(in);
            for (int i = 0; i < caseNum; i++) {
            	
                in = br.readLine();
                String[] numArry = in.split("\\s+");
                // int peopleNum = Integer.parseInt(numArry[0]);
                int caseNum2 = Integer.parseInt(numArry[1]);
                
                in = br.readLine();
                String[] peopleArry = in.split("\\s+");
                nodeList = new ArrayList<Node>();
                // for root
                nodeList.add(new Node(0));
                
                // set parent info
                for (int j = 0; j < peopleArry.length; j++) {
                	nodeList.add(new Node(Integer.parseInt(peopleArry[j])));
                }
                
                // set child info
                for (int j = 0; j < peopleArry.length; j++) {
                	nodeList.get(Integer.parseInt(peopleArry[j])).getChildList().add(j+1);
                }
                
                for (int j = 0; j < caseNum2; j++) {
                	
                	childCount = parentCount = 0;
                	isFind = false;
                	for (int k = 0; k < nodeList.size(); k++)
                		nodeList.get(k).setVisit(false);
                	
    	            in = br.readLine();
    	            String[] caseArry = in.split("\\s+");
    	            
    	            // set src, dest in global var
    	            g_src = Integer.parseInt(caseArry[0]);
    	            g_dest = Integer.parseInt(caseArry[1]);
    	            findDegree(g_src, 0);
    	            
    	            System.out.println(childCount + parentCount);
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
		}
		
	}
	
	
	public static void findDegree(int current, int count) {
		
		findChild(current, 0);
		
		if (!isFind) {
			count++;
			parentCount = count;
			nodeList.get(current).setVisit(true);
			int parent = nodeList.get(current).getParent();
			findDegree(parent, count);
		}
		
	}
	
	
	public static void findChild(int current, int count) {
		
		if (isFind) return;
		
		if (current == g_dest) {
			isFind = true;
			childCount = count;
			return;
		}
		
		count++;
		ArrayList<Integer> tempList = nodeList.get(current).getChildList();
		for (int i = 0; i < tempList.size(); i++) {
			if (!nodeList.get(tempList.get(i)).isVisit()) {
				findChild(tempList.get(i), count);
			}
		}
	}
	
	
}


class Node {
	
	
	public boolean visit = false;
	public int parent;
	public ArrayList<Integer> childList = new ArrayList<Integer>();
	
	
	Node(int parent) {
		this.parent = parent;
	}


	public boolean isVisit() {
		return visit;
	}
	public void setVisit(boolean visit) {
		this.visit = visit;
	}
	public int getParent() {
		return parent;
	}
	public void setParent(int parent) {
		this.parent = parent;
	}
	public ArrayList<Integer> getChildList() {
		return childList;
	}
	public void setChildList(ArrayList<Integer> childList) {
		this.childList = childList;
	}
	
	
}


