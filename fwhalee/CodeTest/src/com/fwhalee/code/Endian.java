package com.fwhalee.code;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * 32��Ʈ�� Endian ���� ��´�.
 * @author fwhalee
 *
 */
public class Endian {

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
	 * 32��Ʈ�� ��ȯ�� Endian ���� ��´�.
	 * @param num
	 * @return
	 */
	public static long getEndian(long num) {
		
    	String binStr = Long.toBinaryString(num);
        int binStrSize = binStr.length();
        
        // ��Ʈ���� �����Ǿ� ���� ���� ���
        /*
        int quotient = binStrSize / EIGHT;
        quotient++;
        */
        int quotient = 4;
        int length = EIGHT * quotient;
        // 0�� ä�� �ڸ���
        int diff = length - binStrSize;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < diff; i++) {
        	sb.append("0");
        }
        
        String newStr = sb.toString() + binStr;
        List<String> packageEight = new ArrayList<String>();
        for (int i = 0; i < quotient; i++)
        {
        	packageEight.add(newStr.substring(EIGHT * i, EIGHT * (i+1)));
        }
        
        Collections.reverse(packageEight);
        StringBuilder sb2 = new StringBuilder();
        for (String s : packageEight) {
        	sb2.append(s);
        }
        
        return Long.parseLong(sb2.toString(), 2);
	}
	
	
	
	
}
