package edu.qc.seclass;

/* 
 * Test Suite for buggyMethod3 
 * 100% Statement Coverage && <100% Branch Coverage
 * -> Reveals Fault
 * - Nazib Mondal 
 */

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BuggyClassTestSC3 {
	private BuggyClass bc;
	
	@Before
	public void setUp() throws Exception {
		bc = new BuggyClass();
	}

	@After
	public void tearDown() throws Exception {
		bc = null;
	}
	
	
	/*
	 * Test 1 - Covers statements in first if branch
	 * whereby the y input value is greater than 0;
	 * the result should return 5 when x = 20, y = 4
	 * as 5 = 20 / 4 
	 */
	@Test
	public void testBuggyMethod3_1sc() {
		assertEquals(5, bc.buggyMethod3(20, 4));
	}
	
	
	/*
	 * Test 2 - Covers final statements after branches
	 * and should catch the arithmetic divide by 0 fault;
	 * y = 0, x = 50; fault should be caught from 50/0
	 */
	@Test (expected = ArithmeticException.class)
	public void testBuggyMethod3_2sc() {
		assertEquals(0, bc.buggyMethod3(50, 0));
	}

}
