package com.fwhalee.code;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class EditorWars {
	
	
	static int[] viArry;
	static int[] emacsArry;
	static int[] groupViArry;
	static int[] groupEmacsArry;
	
	static int group;
	static boolean error = false;
	
	
	public static void main(String[] args) {
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String in = "";
		
		try {
		    
		    in = br.readLine();
		    int caseNum = Integer.parseInt(in);
            for (int i = 0; i < caseNum; i++) {
            	
            	viArry = new int[10000];
            	emacsArry = new int[10000];
            	groupViArry = new int[10000];
            	groupEmacsArry = new int[10000];
            	
            	group = 1;
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
                    
                    if (!error)
                    	process(opn, p1, p2, j + 1);
                }
                
                if (!error) {
	                int sum = 0;
	                int viSum = 0;
	                int emacsSum = 0;
	                
	                for (int j = 1; j <= group; j++) {
	                	int viListSize = groupViArry[j];
	                	int emacsListSize = groupEmacsArry[j];
	                	if (viListSize <= emacsListSize) {
	                		sum += emacsListSize;
	                	} else {
	                		sum += viListSize;
	                	}
	                	viSum += viListSize;
	                	emacsSum += emacsListSize;
	                }
	                int change = peopleNum - viSum - emacsSum;
	                sum += change;
	            	System.out.println("MAX PARTY SIZE IS " + sum);
                }
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
		}
		
	}
	
	
	public static void process(String opn, int p1, int p2, int idx) {
		
		boolean isContain = false;
		
		boolean viP1 = false;
		boolean viP2 = false;
		boolean emacsP1 = false;
		boolean emacsP2 = false;
		
		int viP1Before = 0;
		int viP2Before = 0;
		int emacsP1Before = 0;
		int emacsP2Before = 0;
		
		if (viArry[p1] > 0) {
			viP1 = true;
			viP1Before = viArry[p1]; 
		}
		if (viArry[p2] > 0 ) {
			viP2 = true;
			viP2Before = viArry[p2];
		}
		if (emacsArry[p1] > 0) {
			emacsP1 = true;
			emacsP1Before = emacsArry[p1];
		}
		if (emacsArry[p2] > 0) {
			emacsP2 = true;
			emacsP2Before = emacsArry[p2];
		}
		if (opn.equals("ACK")) {
			
			if ((viP1 && emacsP2) || (viP2 && emacsP1)) {
				System.out.println("CONTRADICTION AT " + idx);
				error = true;
				return;
			}
			
			if (viP1 || viP2) {
				if (!viP1) {
					viArry[p1] = group;
					groupViArry[group]++;
				}
				if (!viP2) {
					viArry[p2] = group;
					groupViArry[group]++;
				}
				isContain = true;
			}
			
			if (emacsP1 || emacsP2) {
				if (!emacsP1) {
					emacsArry[p1] = group;
					groupEmacsArry[group]++;
				}
				if (!emacsP2) {
					emacsArry[p2] = group;
					groupEmacsArry[group]++;
				}
				isContain = true;
			}
			
			if (!isContain) {
				group++;
				viArry[p1] = group;
				viArry[p2] = group;
				groupViArry[group] += 2;
			}
			
		} else {
			
			if ((viP1 && viP2) || (emacsP1 && emacsP2)) {
				System.out.println("CONTRADICTION AT " + idx);
				error = true;
				return;
			}
			
			if (viP1 || emacsP2) {
				if (!viP1) {
					viArry[p1] = group;
					groupViArry[group]++;
				}
				if (!emacsP2) {
					emacsArry[p2] = group;
					groupEmacsArry[group]++;
				}
				isContain = true;
			}
			
			if (emacsP1 || viP2) {
				if (!emacsP1) {
					emacsArry[p1] = group;
					groupEmacsArry[group]++;
				}
				if (!viP2) {
					viArry[p2] = group;
					groupViArry[group]++;
				}
				isContain = true;
			}
			
			if (!isContain) {
				group++;
				viArry[p1] = group;
				emacsArry[p2] = group;
				groupViArry[group]++;
				groupEmacsArry[group]++;
			}
		}
		
		// merge
		
		
	}
	
	
}
