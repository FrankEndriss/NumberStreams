package com.happypeople.numberstreams.sources;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.happypeople.numberstreams.NumberSource;

public class BufferedNumberSourceTest {
	private NumberSource source;
	private BufferedNumberSource out;
	private static int MIN_LEFT=-5;
	private static double DELTA=0.0000001;

	@Before
	public void setUp() throws Exception {
		source=new SequentialNumberSource();
		out=new BufferedNumberSource(source, MIN_LEFT);
	}

	@Test
	public void testReadDoubleArray() {
		final double[] d=new double[3000];
		out.read(d);
		assertEquals("should be same", d[2799], 2800, DELTA);
	}

	@Test
	public void testReadInt_0() {
		assertEquals("first read left of 0 should return 0", out.read(-1, true), 0, DELTA);
	}

	@Test
	public void testReadInt_1() {
		assertEquals("first read of idx=0 should return 1", 1, out.read(0, true), DELTA);
	}

	@Test
	public void testReadInt_2() {
		for(int i=0; i<10000; i++)
			assertEquals("sequential read: "+i, i+1, out.read(0, true), DELTA);
	}

	@Test
	public void testReadInt_3() {
		for(int i=0; i<100; i++)
			out.read(0, true); // just move the index
		assertEquals("call with MIN_LEFT should work", 101+MIN_LEFT, out.read(MIN_LEFT, true), DELTA);
		
		try {
			out.read(MIN_LEFT-1, true);
			fail("should have failed");
		}catch (final Exception e) {
			// ok
		}

	}

}
