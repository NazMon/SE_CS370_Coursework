package edu.qc.seclass;

import java.util.Scanner;

public class MyCustomString implements MyCustomStringInterface{
	
	private String string = null;
	
	@Override
	public String getString() {
		return this.string;
	}

	@Override
	public void setString(String string) {
		this.string = string;
	}

	@Override
	public int countNumbers() {
		/* to keep track of how many numbers in our string */
		int numCounter = 0;
		String s = getString();
		
		/* tests if we are on a new number or still on the
		 * same number, but at its next digit
		 */
		boolean isPreviousDigit = false;
		
		/* traversing the string to find numbers */
		for (int i = 0; i < s.length(); i++) {
			if ( Character.isDigit(s.charAt(i)) ) {
				/* PreviousDigit flag not triggering? Must
				 * be a completely new number then, so add
				 * to the counter, and set the flag to true
				 * for the next iteration to know that we are
				 * on the same number, but different digit
				 */
				if (!isPreviousDigit) {
					numCounter++;
					isPreviousDigit = true;
				}
			}
			else {
				isPreviousDigit = false;
			}
		}
		
		return numCounter;
	}

	@Override
	public String getEveryNthCharacterFromBeginningOrEnd(int n, boolean startFromEnd) {
		String concat = "";
		String s = getString();
		int length = s.length();
		/* If we're not starting from the end, then we start
		 * with the nth character and work our way to the end,
		 * concatenating every nth character along the way
		 */
		if (!startFromEnd) {
			for (int i = n - 1; i < length; i += n) {
				concat += s.charAt(i);
			}
		}
		
		/* Otherwise, start from the last element - n, and work
		 * our way down the list n characters at a time, also
		 * concatenating along the way
		 */
		else {
			for (int i = length - n; i > 0; i -= n) {
				concat += s.charAt(i);
			}
			
			/* The above loop however creates a string that is
			 * reverse in comparison to the output we'd like,
			 * as such we reverse the concatenated string
			 * by using the builtin StringBuilder class, and
			 * return the "re-reversed" string
			 */
			StringBuilder sb = new StringBuilder(concat);
			sb.reverse();
			concat = sb.toString();
		}

		return concat;
	}

	@Override
	public void convertDigitsToNamesInSubstring(int startPosition, int endPosition) {
		// TODO Auto-generated method stub
		
	}

}
