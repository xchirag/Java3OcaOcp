package com.exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
/**
 * 
 * find second largest digit from the given String
 *
 */
public class InputDigit {

	static List<Integer> list = new ArrayList<>();
	static int answer = Integer.MIN_VALUE;

	public static int secondHighestDigit(String input) {

		String withNumbersOnly = findNumbers(input);
		if (withNumbersOnly.length() <= 1)
			answer = -1;
		else {
			int foundMax = findMax(withNumbersOnly);
			answer = foundMax;
		}
		answer = findSecondMaxFromList(list, answer);
		return answer;
	}

	private static int findSecondMaxFromList(List<Integer> list2, int answer2) {

		list2.sort((x1, x2) -> x2 - x1);
		int max = list2.get(0);
		int secondHighest = max;
		// System.out.println(list2);
		for (int x : list2) {
			if (x < max) {
				secondHighest = x;
				break;
			}
		}
		return secondHighest;
	}

	private static int findMax(String withNumbersOnly) {
		Optional<Integer> max = list.stream().max((x1, x2) -> x1 - x2);
		if (max.isPresent()) {
			System.out.println(max.get());
			return max.get();
		} else
			return -1;
	}

	public static String findNumbers(String input) {
		String temp = "";
		for (int counter = 0; counter < input.length(); counter++) {
			if (Character.isDigit(input.charAt(counter))) {
				temp += input.charAt(counter);
				list.add(Integer.parseInt(input.charAt(counter) + ""));
			}
		}
		return temp;
	}

	public static void main(String[] args) {
		System.out.println("abc4234");
		String input = "ab4234";
		System.out.println(secondHighestDigit(input));
	}
}
