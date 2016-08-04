package com.fwhalee.code;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class EndianTest {

	@Test
	public void isEndian() {
		
		
		long result = Endian.getEndian(2018915346);
		assertEquals(305419896, result);
		
		result = Endian.getEndian(1);
		assertEquals(16777216, result);
		
		result = Endian.getEndian(100000);
		assertEquals(2693136640L, result);
		
		result = Endian.getEndian(4294967295L);
		assertEquals(4294967295L, result);
		
	}

}
