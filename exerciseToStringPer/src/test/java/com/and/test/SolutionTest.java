package com.and.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import static com.and.test.Solution.*;
import static com.and.test.Solution.answerString;

public class SolutionTest {

	String actual;

	@Before
	public void setup() {
		actual = null;
		answerString = new StringBuilder();
	}

	@Test
	public void whenSolutionMethodIsPassedWithAStringHavingNoDigitsThenItReturnEmptyString() {
		assertEquals(0, solution("abcd").length());
	}

	@Test
	public void whenSolutionMethodIsPassedWithStringHavingTwoDigitsThenItReturnsTwoCombinations() {
		String expected = "97,79";
		actual = solution("9ad7pqrz");
		assertEquals(expected, actual);
	}

	@Test
	public void whenSolutionMethodIsPassedWithEmptyStringItReturnsEmptyString() {
		assertEquals(0, solution("").length());
	}

	@Test
	public void whenSolutionMethodIsPassedWithStringHavingThreeDigitsItOutputsSixCombinationsCombined() {
		String expected = "457,475,547,574,754,745";
		actual = solution("45*ab7abc*bd&^%!");
		assertEquals(expected, actual);

	}

	@Test
	public void thereIsNoCommaAtTheEndOfFinalOutputStringWhenSolutionMethodIsPassedWithOneOrMoreDigits() {
		actual = solution("38*ab7a3c*buuo9");
		assertNotSame(",", actual);
	}

	@Test
	public void removeLastCommaMethodRemovesCommaFromTheEnd() {

		answerString.append("234,");
		String actual = removeLastComma();
		assertEquals("234", actual);
	}

	@Test
	public void getDigitsOutReturnsOnlyDigitsFromTheProvidedString() {
		answerString = getDigitsOut("1234*&^!:@56ABcd");
		String expected = "123456";
		assertEquals(expected, answerString.toString());
	}
	@Test
	public void arrangeStringInCommaSeperatedMethodRemovesTheCommaFromTheLastAndArrangeElementsInDescendingOrder() {
		String actual = arrangeStringInCommaSeperated("123,321,213,231,132,312,");
		String expected = "321,312,231,213,132,123";
		assertEquals(expected, actual);
	}
	
	@Test
	public void getResultsMethodSplitsTheStringWithCommaAndThenArragesThemInReverseOrder() {
		String actual = getResults("187,178,718,781,817,871,");
		String expected = "871,817,781,718,187,178,";
		assertEquals(expected, actual);
	}
	
	@Test
	public void changeMethodRearrangesTheGivenDigitsAsPerIndexGivenToStartDigitAtThatIndexNumber() {
		StringBuilder givenSbuild = new StringBuilder("123");
		String actual = change(givenSbuild, 0, 1).toString();
		String expected = "213";
		assertEquals(expected, actual);
	}
}
