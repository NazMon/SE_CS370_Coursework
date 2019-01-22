package edu.qc.seclass;

/* 
 * Test Suite for buggyMethod3 
 * 100% Branch Coverage -> No Fault
 * - Nazib Mondal 
 */

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BuggyClassTestBC3 {
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
	 * Test 1 - Tests the first branch (conditional if),
	 * whereby the y input value is positive and
	 * greater than 0; given the inputs x = 10, y = 1
	 * our result should be 10 = 10/1
	 */
	@Test
	public void testBuggyMethod3_1bc() {
		assertEquals(10, bc.buggyMethod3(10, 1));
	}
	
	
	/* 
	 * Test 2 - Tests the final branch (post else),
	 * whereby the y input value is negative and thus, not
	 * greater than 0; given the inputs x = 10, y = -2
	 * our result should be -5 = 10/-2
	 */
	@Test
	public void testBuggyMethod3_2bc() {
		assertEquals(-5, bc.buggyMethod3(10, -2));
	}

}
