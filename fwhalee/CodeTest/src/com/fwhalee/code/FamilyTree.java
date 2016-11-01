package com.fwhalee.code;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FamilyTree {
	
	
	public static int[] parentArry = new int[100001];
	
	public static int g_src;
	public static int g_dest;
	
	
	public static void main(String[] args) {
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String in = "";
		
		try {
		    
		    in = br.readLine();
		    int caseNum = Integer.parseInt(in);
            for (int i = 0; i < caseNum; i++) {
            	
                in = br.readLine();
                String[] numArry = in.split("\\s+");
                int caseNum2 = Integer.parseInt(numArry[1]);
                
                in = br.readLine();
                String[] peopleArry = in.split("\\s+");
                
                // set parent info
                parentArry[0] = -1;
                for (int j = 0; j < peopleArry.length; j++) {
                    parentArry[j+1] = Integer.parseInt(peopleArry[j]);
                }
                
                for (int j = 0; j < caseNum2; j++) {
                	
    	            in = br.readLine();
    	            String[] caseArry = in.split("\\s+");
    	            
    	            // set src, dest in global var
    	            int src = Integer.parseInt(caseArry[0]);
    	            int dest = Integer.parseInt(caseArry[1]);
    	            
    	            int distance = findDegree2(src, dest);
    	            System.out.println(distance);
    	            
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
		}
		
	}
	
	
	public static int findDegree2(int src, int dest) {
	    
	    
	    if (src == dest) return 0;
	    
	    
	    ArrayList<Integer> srcParentList = new ArrayList<Integer>();
	    ArrayList<Integer> destParentList = new ArrayList<Integer>();
	    
	    // src의 부모 배열
	    int current = src;
	    int parent = -1;
	    while(true) {
	        srcParentList.add(current);
	        parent = parentArry[current];
	        if (parent == -1) break;
	        current = parent;
	    }
	    
	    // dest의 부모 배열
	    current = dest;
        parent = -1;
	    while(true) {
	        destParentList.add(current);
            parent = parentArry[current];
            if (parent == -1) break;
            current = parent;
        }
	    
	    boolean isFind = false;
	    int srcIdx = 0;
	    int destIdx = 0;
	    for (int i = 0; i < srcParentList.size(); i++) {
	        for (int j = 0; j < destParentList.size(); j++) {
	            if (srcParentList.get(i) == destParentList.get(j)) {
	                srcIdx = i;
	                destIdx = j;
	                isFind = true;
	                break;
	            }
	        }
	        if (isFind) break;
	    }
	    
	    return srcIdx + destIdx;
	    
	}
	
	
	
	
	@Deprecated
	public static void findDegree(int current, int count) {
		/*
		findChild(current, 0);
		
		if (!isFind) {
			count++;
			parentCount = count;
			nodeList.get(current).setVisit(true);
			int parent = nodeList.get(current).getParent();
			findDegree(parent, count);
		}
		*/
	}
	
	
	@Deprecated
	public static void findChild(int current, int count) {
		/*
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
		*/
	}
	
	
}


