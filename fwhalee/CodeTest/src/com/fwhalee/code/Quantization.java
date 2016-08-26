package com.fwhalee.code;

import java.util.ArrayList;
import java.util.Scanner;

public class Quantization {
    
    
    private static ArrayList<Integer> sequence = new ArrayList<Integer>();
    
    
    public static void main(String[] args) {
        
        
        try {
            
            Scanner reader = new Scanner(System.in);
            
            int firstLine = reader.nextInt();
            for (int i = 0; i < firstLine; i++) {
                
                // 수열 개수, 최대 양자화 수 받기
                // long line = reader.nextLong();
                int quantizeNum = 2;
                
                // 수열 받기 
                sequence.add(1);
                sequence.add(2);
                sequence.add(3);
                sequence.add(4);
                
                int result = getTotalMinError(quantizeNum, 1);
                System.out.println(result);
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    
    public static int getTotalMinError(int quantizeNum, int offset) {
        
        
        System.out.println("quantinzeNum: " + quantizeNum);
        System.out.println("sequence: " + sequence);
        
        if (quantizeNum == 1) {
            // S가 1일 때 최소 오차 구하기. return
        }
        int minError = Integer.MAX_VALUE;
        
        // 수열 분할에 대한 recursive
        for (int i = 0; i < (sequence.size() - offset + 1); i++) {
            
            int tempMinError = getMinError(offset, i + 1) + getTotalMinError(quantizeNum - 1, offset + 1);
        }
        
        return -1;
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
        for (int i = offset; i < (offset + size); i++) {
            error = (int) Math.pow((error - avg), 2);
        }
        
        return error;
    }
    
    
}
