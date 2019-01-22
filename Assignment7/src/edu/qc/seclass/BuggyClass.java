package edu.qc.seclass;

/**
 * @author Nazib Mondal - CS370
 *
 */
public class BuggyClass {
	
	/* Constructor */
	public BuggyClass() {
		// TODO Auto-generated constructor stub
	}
	
	
	/** T1 - Add to the class a method called buggyMethod1 that contains a
	division by zero fault such that (1) it is possible to create a test suite that
	achieves 100% statement coverage and does not​ reveal the fault, and (2) it
	is possible to create a test suite that achieves less than 50% statement
	coverage and reveals the fault */
	public int buggyMethod1(int x, int y) {
		if (y > 0) {
			int results = x/y;
			return results;
		}
		return x/y;
	}
	
	
	/** T2 - Add to the class a method called buggyMethod2 that contains a
	division by zero fault such that (1) it is possible to create a test suite that
	achieves 100% statement coverage and does not​ reveal the fault, and (2)
	every test suite that achieves more than 50% branch coverage reveals the
	fault. */
	public int buggyMethod2(int x, int y) {
		int result;
		if (y > 0) {
			result = x / y;
		}
		else if (x > y) {
			result = x / y;
		}
		else {
			result = x / y;
		}
		return result;
	}
	
	
	/** T3 - Add to the class a method called buggyMethod3 that contains a
	division by zero fault such that (1) it is possible to create a test suite that
	achieves 100% branch coverage and does not​ reveal the fault, and (2) it is
	possible to create a test suite that achieves 100% statement coverage, does
	not achieve 100% branch coverage, and reveals the fault. */
	public int buggyMethod3(int x, int y) {
		int result;
		if (y > 0) {
			result = x / y;
			return result;
		}
		if (x == 420) {
			// Do nothing
			// This if statement executes
			// to compare the value of x == 420,
			// as such, statement coverage still
			// stands with the addition of this
			// statement
		}
		return result = x / y;
	}
	
	
	/** T4 - Task​ ​4​: Add to the class a method called buggyMethod4 that contains a
	division by zero fault such that (1) every test suite that achieves 100%
	statement coverage reveals the fault, and (2) it is possible to create a test
	suite that achieves 100% branch coverage and does not​ reveal the fault. */
	public void buggyMethod4() {
		/* TL;DR
		 * To put it simply, the two subtasks are contradictory whereby 100%
		 * branch coverage will imply 100% statement coverage; if a fault
		 * is not revealed in 100% branch coverage, it cannot be revealed
		 * in 100% statement coverage either due to this property.
		 */
		
		/* The problem with this task is that it is impossible to create a test suite
		 * that will have 100% branch coverage without revealing the fault with
		 * 100% statement coverage that reveals the fault. The issue lies in the fact
		 * that 100% branch coverage implies 100% statement coverage, and so if there
		 * exists a suite with 100% branch coverage without revealing a fault, it means
		 * it is impossible for a fault to exist within any of the statements because
		 * 100% branch coverage will have covered every single statement. Thus, it 
		 * contradicts the ability for a test suite with 100% statement coverage
		 * that can reveal a fault to exist when 100% branch coverage does NOT
		 * reveal a fault.
		 */
	}
	
	
	/** T5 - Add to class BuggyClass a method buggyMethod5 by completing
	the code skeleton provided below so that (1) it is possible to create a test
	suite that achieves 100% statement coverage, and (2) the division by zero
	fault at line 4 cannot be revealed by any test suite. */
	/**
	 * 	1. public void buggyMethod5 (int i) {
	 *	2. int x;
	 *	3. [point where you can add code]
	 *	4. x = i/0;
	 *	5. [point where you can add code]
	 *	6. }
	 ^
	 */
	public void buggyMethod5() {
		/*
		 * This is impossible as to achieve 100% statement coverage line 4
		 * must be executed at some point in the execution's lifetime, which
		 * means the method must be written in a way that line 4 must execute.
		 * If the method is written as such, then subtask (2) will always fail
		 * because the moment line 4 executes, the fault will be revealed.
		 */
	}
	
}
