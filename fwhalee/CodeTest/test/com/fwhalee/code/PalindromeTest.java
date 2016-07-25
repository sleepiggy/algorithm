package com.fwhalee.code;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class PalindromeTest {

	@Test
	public void isPalindrome() {
		
		
		String inStr = "(())";
		
		boolean isPalindrome = Palindrome.isPalindrome(inStr);
		assertEquals(true, isPalindrome);
		
		
	}

}
