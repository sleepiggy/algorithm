package com.fwhalee.code;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;

public class Palindrome {
	
	
	public static void main(String[] args) {
		
		String inPath = "file/in/palindrome.in";
		List<String> strList = readFile(inPath);
		
		for (int i = 0; i < strList.size(); i++) {
			boolean result = isPalindrome(strList.get(i));
			System.out.print(strList.get(i) + " ");
			System.out.println(result);
		}
		
	}
	
	
	/**
	 * 구분자 없는 괄호 문자열을 입력하면 palindrome 여부를 판단한다.
	 * @param inStr
	 * @return
	 */
	public static boolean isPalindrome(String inStr) {
		
		String[] inStrArry = inStr.split("");
		
		Stack<String> stack = new Stack<String>();
		for (int i = 0; i < inStrArry.length; i++) {
			
			// System.out.print(inStrArry[i] + " ");
			
			String topElement = null;
			try {
				topElement = stack.lastElement();
			} catch (NoSuchElementException e) {
				topElement = null;
			}
			
			if (topElement == null) {
				stack.push(inStrArry[i]);
				continue;
			}
			
			// 동일 문자열일 경우
			// topElement.equals(inStrArry[i])
			// 괄호일 경우
			if (topElement.equals("(") && inStrArry[i].equals(")")) {
				stack.pop();
			} else {
				stack.push(inStrArry[i]);
			}
			
		}
		
		boolean result = false;
		if (stack.empty()) {
			result = true;
		}
		
		return result;
	}	
	
	
	public static List<String> readFile(String inPath) {
		
		// 파일 읽기
		BufferedReader br;
		List<String> strList = new ArrayList<String>();
		try {
			
			br = new BufferedReader(new FileReader(inPath));
			
			while (true) {
				String line = br.readLine();
				if (line == null) {
					break;
				}
				
				strList.add(line);
				// System.out.println(line);
			}
			
			br.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return strList;
	}
	
}
