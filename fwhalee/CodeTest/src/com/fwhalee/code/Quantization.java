package com.fwhalee.code;

import java.util.ArrayList;

public class Quantization {
    
    
    private static ArrayList<Integer> sequence = new ArrayList<Integer>();
    
    
    public static void main(String[] args) {
        
        
        try {
            
//            Scanner reader = new Scanner(System.in);
            
//            int firstLine = reader.nextInt();
            for (int i = 0; i < 1; i++) {
                
                // 수열 개수, 최대 양자화 수 받기
                // long line = reader.nextLong();
                int quantizeNum = 3;
                
                // 수열 받기
                /*sequence.add(1);
                sequence.add(2);
                sequence.add(3);
                sequence.add(4);*/
                
                /*sequence.add(1);
                sequence.add(1);
                sequence.add(2);
                sequence.add(2);
                sequence.add(2);
                sequence.add(2);
                sequence.add(3);
                sequence.add(3);
                sequence.add(3);
                sequence.add(3);
                */
                
                sequence.add(1);
                sequence.add(4);
                sequence.add(6);
                sequence.add(744);
                sequence.add(755);
                sequence.add(777);
                sequence.add(890);
                sequence.add(897);
                sequence.add(902);
                int result = getTotalMinError(quantizeNum, 0);
                System.out.println("total Min Error: " + result);
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    
    public static int getTotalMinError(int quantizeNum, int offset) {
        
        System.out.println("quantizeNum: " + quantizeNum + " / offset: " + offset);
        if (quantizeNum == 0) return Integer.MAX_VALUE;
        if (offset == sequence.size()) {
        	System.out.println("exit");
        	return 0;
        }
        if (quantizeNum == 1) {
            // S가 1일 때 최소 오차 구하기. return
        	return getMinError(offset, sequence.size() - offset);
        }
        int minError = Integer.MAX_VALUE;
        
        // 수열 분할에 대한 recursive
        for (int i = 0; i < (sequence.size() - offset - quantizeNum + 1); i++) {
            
            int tempMinError = getMinError(offset, i + 1) + getTotalMinError(quantizeNum - 1, offset + i + 1);
            if (minError >= tempMinError) {
            	minError = tempMinError;
            }
        }
        
        return minError;
    }
    
    
    public static int getMinError(int offset, int size) {
        
        // 평균 구하기
        int sum = 0;
        for (int i = offset; i < (offset + size); i++) {
            sum += sequence.get(i);
        }
        
        // 반올림
        int avg = Math.round(sum / size);
        int error = 0;
        System.out.println("offset:" + offset + " / size: " + size);
        for (int i = offset; i < (offset + size); i++) {
        	System.out.print(sequence.get(i) + "|");
            error += (int) Math.pow((sequence.get(i) - avg), 2);
        }
        System.out.print("error: " + error);
        System.out.println("");
        System.out.println("");
        return error;
    }
    
    
}
