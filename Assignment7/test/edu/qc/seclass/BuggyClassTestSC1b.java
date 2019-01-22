package edu.qc.seclass;

/*
 * Test Suite for buggyMethod1
 * 100% Statement Coverage -> No Fault
 * - Nazib Mondal
 */

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BuggyClassTestSC1b {
	private BuggyClass bc;
	
	@Before
	public void setUp() {
		bc = new BuggyClass();
	}

	@After
	public void tearDown() {
		bc = null;
	}
	
	/*
	 * First Test tests for division by zero
	 * fault with a value of 0 for y and 
	 * any value for x; should throw an
	 * arithmetic error exception
	 */
	@Test (expected = ArithmeticException.class)
	public void testBuggyMethod1_sc1b() {
		assertEquals(0, bc.buggyMethod1(10, 0));
	}
}
