package edu.qc.seclass;

/* 
 * Test Suite for buggyMethod2 
 * >50% Branch Coverage -> Reveals Fault
 * - Nazib Mondal 
 */
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BuggyClassTestBC2 {
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
	 * First test - when x has input that is
	 * greater than the y input value and y input
	 * is less than 0 (negative); this should
	 * return -3 here when x = 9, y = -3 as
	 * -3 = 9/-3; tests 2nd conditional branch
	 */
	@Test
	public void testBuggyMethod2_bc1() {
		assertEquals(-3, bc.buggyMethod2(9, -3));
	}
	
	/*
	 * Second test - when x is less than y, and
	 * y is equal to 0; this is to test the
	 * final else branch, and to view the fault;
	 * this should throw an illegal divide by
	 * zero fault
	 */
	@Test (expected = ArithmeticException.class)
	public void testBuggyMethod2_bc2() {
		assertEquals(0, bc.buggyMethod2(10, 0));
	}

}
