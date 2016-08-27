package com.fwhalee.code;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Quantization {
    
    
	// 수열 데이터
    private static ArrayList<Integer> sequence;
    
    
    public static void main(String[] args) {
        
        
        try {
            
            Scanner reader = new Scanner(System.in);
            
            int firstLine = reader.nextInt();
            for (int i = 0; i < firstLine; i++) {
                
            	sequence = new ArrayList<Integer>();
            	
                // 수열 개수
            	int sequenceNum = reader.nextInt();
            	// 최대 양자화 수
                int quantizeNum = reader.nextInt();
                
                // 수열 입력
                for (int j = 0; j < sequenceNum; j++) {
                	sequence.add(reader.nextInt());
                }
                
                // 수열 오름차순 정렬
                Collections.sort(sequence);
                
                int result = getTotalMinError(quantizeNum, 0);
//                System.out.println(result);
                System.out.println("Total Min Error:" + result);
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    
    public static int getTotalMinError(int quantizeNum, int offset) {
        
        System.out.println("quantizeNum: " + quantizeNum + " / offset: " + offset);
        
        if (quantizeNum == 1) {
        	return getMinError(offset, sequence.size() - offset);
        }
        
        int minError = Integer.MAX_VALUE;
        
        // 수열 분할에 대한 recursive
        for (int i = 0; i < (sequence.size() - offset - quantizeNum + 1); i++) {
            int tempMinError = getMinError(offset, i + 1) + getTotalMinError(quantizeNum - 1, offset + i + 1);
            minError = Math.min(minError, tempMinError);
        }
        
        return minError;
    }
    
    
    public static int getMinError(int offset, int size) {
    	
    	System.out.println("offset:" + offset + " / size: " + size);
        
        // offset ~ end 데이터 평균 구하기
        int sum = 0;
        for (int i = offset; i < (offset + size); i++) {
            sum += sequence.get(i);
        }
        
        // 평균은 가까운 정수로 반올림
        int avg = Math.round((float) sum / (float) size);
        
        int error = 0;
        for (int i = offset; i < (offset + size); i++) {
        	System.out.print(sequence.get(i) + " | ");
            error += (int) Math.pow((sequence.get(i) - avg), 2);
        }
        System.out.println("\nerror: " + error);
        System.out.println("\n");
        return error;
    }
    
    
}
