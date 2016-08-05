package com.fwhalee.code;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


/**
 * Endian 수 변환 (only 32bit)
 * @author fwhalee
 *
 */
public class Endian {
    
    
    private final static int BIT_32 = 32;
	private final static int EIGHT = 8;
	
	
    public static void main(String[] args) {
        
    	
		try {
			
			Scanner reader = new Scanner(System.in);
			
			int firstLine = reader.nextInt();
			for (int i = 0; i < firstLine; i++) {
				long line = reader.nextLong();
				long endianNum = getEndian(line);
				System.out.println(String.valueOf(endianNum));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
    }
    
    
	/**
	 * Endian 수 변환 (only 32bit)
	 * @param num
	 * @return
	 */
	public static long getEndian(long num) {
		
    	String binStr = Long.toBinaryString(num);
        
    	// 해당 문자열에 32자리가 될때까지 0을 채워준다.
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < (BIT_32 - binStr.length()); i++)
        	sb.append("0");
        String newStr = sb.toString() + binStr;
        
        // 문자열을 8자리 단위로 나눈다.
        List<String> packageEightUnit = new ArrayList<String>();
        for (int i = 0; i < 4; i++)
        	packageEightUnit.add(newStr.substring(EIGHT * i, EIGHT * (i+1)));
        
        Collections.reverse(packageEightUnit);
        StringBuilder sb2 = new StringBuilder();
        for (String s : packageEightUnit)
        	sb2.append(s);
        
        return Long.parseLong(sb2.toString(), 2);
	}
	
	
}
