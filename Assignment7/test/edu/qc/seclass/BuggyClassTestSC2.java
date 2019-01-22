package edu.qc.seclass;

/* 
 * Test Suite for buggyMethod2 
 * 100% Branch Coverage -> No Fault
 * - Nazib Mondal 
 */

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BuggyClassTestSC2 {
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
	 * First test - when y has an input greater
	 * than 0 (is positive); this should return
	 * -4 from x = -8, y = 2 as -4 = -8/2; this
	 * tests the 1st conditional
	 */
	@Test
	public void testBuggyMethod2_sc1() {
		assertEquals(-4, bc.buggyMethod2(-8, 2));
	}
	
	/*
	 * Second test - when x input
	 * is greater than y input value
	 * and the y input value is negative;
	 * should return -2 when x = 4, y = -2
	 * as -2 = 4/-2; tests 2nd conditional
	 */
	@Test
	public void testbuggyMethod2_sc2() {
		assertEquals(-2, bc.buggyMethod2(4, -2));
	}
	
	/*
	 * Third test - when x has input that is
	 * less than the y input value and y input
	 * is less than 0 (negative); this should
	 * return 2 here when x = -4, y = -2 as
	 * 2 = -4/-2; tests final else branch
	 */
	@Test
	public void testBuggyMethod2_sc3() {
		assertEquals(2, bc.buggyMethod2(-4, -2));
	}
}
