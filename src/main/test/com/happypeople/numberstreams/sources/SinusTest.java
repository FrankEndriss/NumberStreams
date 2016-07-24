package com.happypeople.numberstreams.sources;

import static org.junit.Assert.*;

import org.junit.Test;

public class SinusTest {
	
	@Test
	public void test_read() {
		
		final Sinus input=new Sinus(3.1);
		final double[] buf=new double[35];
		input.read(buf);
		assertTrue(Math.abs(buf[31]-0.5)<0.0000000001);
	}

}
