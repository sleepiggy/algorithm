package com.fwhalee.code;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;

public class EditorWars {
	
	
	static ArrayList <HashSet <Integer>> viList;
	static ArrayList <HashSet <Integer>> emacsList;
	
	static boolean error = false;
	
	
	public static void main(String[] args) {
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String in = "";
		
		try {
		    
		    in = br.readLine();
		    int caseNum = Integer.parseInt(in);
            for (int i = 0; i < caseNum; i++) {
            	
            	viList = new ArrayList <HashSet <Integer>>();
            	emacsList = new ArrayList <HashSet <Integer>>();
            	error = false;
            	
                in = br.readLine();
                String[] numArry = in.split("\\s+");
                int peopleNum = Integer.parseInt(numArry[0]);
                int replyNum = Integer.parseInt(numArry[1]);
                
                for (int j = 0; j < replyNum; j++) {
                    in = br.readLine();
                    String[] replyArry = in.split("\\s+");
                    String opn = replyArry[0];
                    int p1 = Integer.parseInt(replyArry[1]);
                    int p2 = Integer.parseInt(replyArry[2]);
                    
                    process(opn, p1, p2, j + 1);
                }
                
                // hash에서 최대값 구해서 더하기
                int sum = 0;
                int viSum = 0;
                int emacsSum = 0;
                for (int j = 0; j < viList.size(); j++) {
                	int max = viList.get(j).size();
                	if (viList.get(j).size() <= emacsList.get(j).size()) {
                		max = emacsList.get(j).size();
                	}
                	sum += max;
                	viSum += viList.get(j).size();
                	emacsSum += emacsList.get(j).size();
                }
                int change = peopleNum - viSum - emacsSum;
                sum += change;
                if (!error)
                	System.out.println("MAX PARTY SIZE IS " + sum);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
		}
		
	}
	
	
	public static void process(String opn, int p1, int p2, int idx) {
		/*
		if (viList.size() == 0) {
			viList.add(new HashSet<Integer>());
			emacsList.add(new HashSet<Integer>());
		}
		*/
		if (opn.equals("ACK")) {
			
			boolean isContain = false;
			for (int i = 0; i < viList.size(); i++) {
				boolean viP1 = viList.get(i).contains(p1);
				boolean viP2 = viList.get(i).contains(p2);
				boolean emacsP1 = emacsList.get(i).contains(p1);
				boolean emacsP2 = emacsList.get(i).contains(p2);
				
				if ((viP1 && emacsP2) || (viP2 && emacsP1)) {
					// error
					System.out.println("CONTRADICTION AT " + idx);
					error = true;
					break;
				}
				
				if (viP1 || viP2) {
					viList.get(i).add(p1);
					viList.get(i).add(p2);
					isContain = true;
				}
				
				if (emacsP1 || emacsP2) {
					emacsList.get(i).add(p1);
					emacsList.get(i).add(p2);
					isContain = true;
				}
			}
			
			// 없으면 새로 생성
			if (!isContain) {
				HashSet<Integer> tempHash1 = new HashSet<Integer>();
				HashSet<Integer> tempHash2 = new HashSet<Integer>();
				tempHash1.add(p1);
				tempHash1.add(p2);
				viList.add(tempHash1);
				emacsList.add(tempHash2);
			}
		} else if (opn.equals("DIS")) {
			
			boolean isContain = false;
			for (int i = 0; i < viList.size(); i++) {
				boolean viP1 = viList.get(i).contains(p1);
				boolean viP2 = viList.get(i).contains(p2);
				boolean emacsP1 = emacsList.get(i).contains(p1);
				boolean emacsP2 = emacsList.get(i).contains(p2);
				
				if ((viP1 && viP2) || (emacsP1 && emacsP2)) {
					// error
					System.out.println("CONTRADICTION AT " + idx);
					error = true;
					break;
				}
				
				if (viP1 || emacsP2) {
					viList.get(i).add(p1);
					emacsList.get(i).add(p2);
					isContain = true;
				}
				
				if (emacsP1 || viP2) {
					emacsList.get(i).add(p1);
					viList.get(i).add(p2);
					isContain = true;
				}
			}
			
			// 없으면 새로 생성
			if (!isContain) {
				HashSet<Integer> tempHash1 = new HashSet<Integer>();
				HashSet<Integer> tempHash2 = new HashSet<Integer>();
				tempHash1.add(p1);
				tempHash2.add(p2);
				viList.add(tempHash1);
				emacsList.add(tempHash2);
			}
		}
	}
	
	
}
