package com.and.test;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Solution {

	static StringBuilder answerString = new StringBuilder();

	/**
	 * The following is the method where the solution shall be written
	 */

	public static String solution(String input) throws NumberFormatException {
		StringBuilder convert = new StringBuilder();
		convert.append(input);
		convert = getDigitsOut(input);
		if (convert.length() == 0) {
			return "";
		} else {
			multiplyString(convert, 0, convert.length() - 1);
			String tempString = removeLastComma();
			return tempString;
		}

	}

	public static void main(String args[]) {

		String given = solution("p7c86aAc");
		// String given = solution("abcd*");
		String result = getResults(given);

		if (result.length() > 0)
			System.out.println(result);
		else
			System.out.println("No digits provided into given string");
		

	}

	private static void multiplyString(StringBuilder strBuild, int stratIndex, int endIndex) {
		if (stratIndex == endIndex) {
			answerString.append(strBuild + ",");
		} else {
			for (int counter = stratIndex; counter <= endIndex; counter++) {
				strBuild = change(strBuild, stratIndex, counter);
				multiplyString(strBuild, stratIndex + 1, endIndex);
				strBuild = change(strBuild, stratIndex, counter);
			}
		}
	}

	public static StringBuilder change(StringBuilder strBuild, int actualStartIndex, int makeThisAsStartIndex) {
		char tempChar;
		char[] charArray = strBuild.toString().toCharArray();

		tempChar = charArray[actualStartIndex];
		charArray[actualStartIndex] = charArray[makeThisAsStartIndex];
		charArray[makeThisAsStartIndex] = tempChar;
		String valueOf = String.valueOf(charArray);
		StringBuilder tempBuild = new StringBuilder(valueOf);
		return tempBuild;
	}

	public static String getResults(String answer) {
		StringBuilder strBuild = new StringBuilder();
		strBuild.append(answer);

		if (strBuild.length() > 0) {
			String result = arrangeStringInCommaSeperated(strBuild.reverse().toString());
			return result;
		} else
			return "";
	}

	public static String arrangeStringInCommaSeperated(String sb) {
		String[] split = sb.split(",");
		StringBuilder sbTremp = new StringBuilder();

		Stream.of(split).sorted((str1, str2) -> str2.compareTo(str1)).forEach(element -> sbTremp.append(element + ","));

		answerString = sbTremp;
		String reOrderedString = removeLastComma();

		return reOrderedString;

	}

	public static String removeLastComma() {
		int removeFinalComma = answerString.length();
		String modifiedString = answerString.substring(0, removeFinalComma - 1);
		return modifiedString;
	}

	public static StringBuilder getDigitsOut(String str) {
		StringBuilder strBuild = new StringBuilder();

		for (int counter = 0; counter < str.length(); counter++) {
			char eachCharacter = str.charAt(counter);
			if (Character.isDigit(eachCharacter)) {
				strBuild.append(eachCharacter);
			}
		}
		return strBuild;
	}

}
