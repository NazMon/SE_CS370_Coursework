package edu.qc.seclass;

/*
 * Test Suite for buggyMethod1
 * <50% Statement Coverage -> Reveals Fault
 * - Nazib Mondal
 */

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BuggyClassTestSC1a {
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
	 * First test that tests two 
	 * positive input value for both 
	 * x and y; the expected output 
	 * is 2 from 4/2
	 */
	@Test
	public void testBuggyMethod1_sc1a() {
		assertEquals(2, bc.buggyMethod1(4, 2));
	}
	
	/*
	 * Second test that tests two 
	 * negative input value for both 
	 * x and y; the expected output 
	 * is 2 from -4/-2
	 */
	@Test
	public void testBuggyMethod1_sc2a() {
		assertEquals(2, bc.buggyMethod1(-4, -2));
	}
	
	/*
	 * Third test tests the case of one 
	 * negative input value for x and  
	 * one positive value for y; 
	 * the expected output is -4 from -8/2
	 */
	@Test
	public void testBuggyMethod1_sc3a() {
		assertEquals(-4, bc.buggyMethod1(-8, 2));
	}
	
	/*
	 * Fourth test tests the case of one 
	 * positive input value for x and  
	 * one negative value for y; 
	 * the expected output is -4 from 8/-2
	 */
	@Test
	public void testBuggyMethod1_sc4a() {
		assertEquals(-4, bc.buggyMethod1(8, -2));
	}
	
	
	/*
	 * Fifth test tests the case of 0 value 
	 * for x and one positive value for y; 
	 * the expected output is 0 from 0/1000
	 */
	@Test
	public void testBuggyMethod1_sc5a() {
		assertEquals(0, bc.buggyMethod1(0, 1000));
	}
	
	
	/*
	 * Sixth test tests the case of 0 value 
	 * for x and one negative value for y; 
	 * the expected output is 0 from 0/1000
	 */
	@Test
	public void testBuggyMethod1_sc6a() {
		assertEquals(0, bc.buggyMethod1(0, -1000));
	}

}
