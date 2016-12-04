package com.fwhalee.code;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class EditorWars {
	
	
	static int[] editorArry;
	static int[] groupCount;
	
	static int group;
	static boolean error = false;
	
	
	public static void main(String[] args) {
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String in = "";
		
		try {
		    
		    in = br.readLine();
		    int caseNum = Integer.parseInt(in);
            for (int i = 0; i < caseNum; i++) {
            	
            	editorArry = new int[10001];
            	groupCount = new int[10001];
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
                    
                    if (!error) {
                    	if (opn.equals("ACK")) {
                    		processACK(p1, p2, j+1);
                    	} else {
                    		if (p1 == p2) {
                    			System.out.println("CONTRADICTION AT " + (j+1));
            					error = true;
            					continue;
                    		}
                    		processDIS(p1, p2, j+1);
                    	}
                    }
                }
                
                if (!error) {
                	
                	for (int j = 0; j < editorArry.length; j++) {
                		groupCount[editorArry[j]]++;
                	}
                	
                	int sum = 0;
                	int sum1 = 0;
                	int sum2 = 0;
                	for (int j = 1; j <= group + 1; j+=2) {
                		int max = groupCount[j];
                		if (groupCount[j] <= groupCount[j + 1])
                			max = groupCount[j + 1];
                		sum += max;
                		sum1 += groupCount[j];
                		sum2 += groupCount[j+1];
                	}
                	
                	int change = peopleNum - sum1 - sum2;
	                sum += change;
	            	System.out.println("MAX PARTY SIZE IS " + sum);
                }
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
		}
		
	}
	
	
	public static void processACK(int p1, int p2, int idx) {
		
		boolean hasP1 = false;
		boolean hasP2 = false;
		
		if (editorArry[p1] > 0)
			hasP1 = true;
		
		if (editorArry[p2] > 0)
			hasP2 = true;
		
		if (hasP1 || hasP2) {
			
			if (hasP1 && !hasP2) {
				editorArry[p2] = editorArry[p1];
				return;
			}
			if (!hasP1 && hasP2) {
				editorArry[p1] = editorArry[p2];
				return;
			}
			
			// 둘다 기존 포함될 경우
			int diff = Math.abs(editorArry[p1] - editorArry[p2]);
			if (diff >= 2) {
				mergeGroup(editorArry[p1], editorArry[p2]);
				
			} else {
				if (editorArry[p1] != editorArry[p2]) {
					System.out.println("CONTRADICTION AT " + idx);
					error = true;
					return;
				}
			}
			
		} else {
			// p1, p2 기존 포함 안될 경우
			group += 2;
			editorArry[p1] = group;
			editorArry[p2] = group;
			return;
		}
		
		
	}
	
	
	public static void processDIS(int p1, int p2, int idx) {
		
		
		boolean hasP1 = false;
		boolean hasP2 = false;
		
		if (editorArry[p1] > 0)
			hasP1 = true;
		
		if (editorArry[p2] > 0)
			hasP2 = true;
		
		if (hasP1 || hasP2) {
			
			if (hasP1 && !hasP2) {
				if ((editorArry[p1] % 2) == 1) {
					editorArry[p2] = editorArry[p1] + 1;
				} else {
					editorArry[p2] = editorArry[p1] - 1;
				}
				
				return;
			}
			if (!hasP1 && hasP2) {
				if ((editorArry[p2] % 2) == 1) {
					editorArry[p1] = editorArry[p2] + 1;
				} else {
					editorArry[p1] = editorArry[p2] - 1;
				}
				
				return;
			}
			
			// 둘다 기존 포함될 경우
			int diff = Math.abs(editorArry[p1] - editorArry[p2]);
			if (diff >= 2) {
				// 둘은 다른 그룹
				int p2G = editorArry[p2];
				if ((p2G % 2) == 1) {
					mergeGroup(editorArry[p1], p2G + 1);
				} else {
					mergeGroup(editorArry[p1], p2G - 1);
				}
				
			} else {
				if (editorArry[p1] == editorArry[p2]) {
					System.out.println("CONTRADICTION AT " + idx);
					error = true;
					return;
				}
			}
			
		} else {
			// p1, p2 기존 포함 안될 경우
			group += 2;
			editorArry[p1] = group;
			editorArry[p2] = group + 1;
			return;
		}
		
	}
	
	
	public static void mergeGroup(int g1, int g2) {
		
		int cG = g1;
		int eG = g2;
		if (g1 > g2) {
			cG = g2;
			eG = g1;
		}
		
		// 해당 G > 앞 G로 변경
		for (int i = 0; i < editorArry.length; i++) {
			if (editorArry[i] == eG)
				editorArry[i] = cG;
		}
		
		// 나머지 G > 앞 G'로 변경
		if ((cG % 2) == 1) {
			cG++;
		} else {
			cG--;
		}
		
		if ((eG % 2) == 1) {
			eG++;
		} else {
			eG--;
		}
		for (int i = 0; i < editorArry.length; i++) {
			if (editorArry[i] == eG)
				editorArry[i] = cG;
		}
		
	}
	
	
}
