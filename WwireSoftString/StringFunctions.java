package com.exercise;

import java.util.Arrays;
/**
 * 
 * Use of various String functions, binary search and buble sort examples
 *
 */
public class StringFunctions {
	public static void main(String[] args) {
		System.out.println("print x");

		int xp = 5;
		System.out.println(xp);

		String s = "abcd57IO&*^abc12";
		System.out.println(s);
		for (int x = 0; x < s.length(); x++) {
			char charAt = s.charAt(x);
			if (Character.isLetter(charAt))
				System.out.print(charAt);
			else
				System.out.print("*");
		}

		System.out.println();
		s = "ab 12 cd er";
		String temp = "";
		for (int p = s.length() - 1; p >= 0; p--) {
			if (!Character.isWhitespace(s.charAt(p)))
				temp += s.charAt(p);
		}

		System.out.println(s);
		System.out.println(temp);

		s = "ab 12 cd er";
		temp = "";
		for (int p = 0; p < s.length(); p++) {
			if (s.charAt(p) == 'a' || s.charAt(p) == 'e' || s.charAt(p) == 'i' || s.charAt(p) == 'o'
					|| s.charAt(p) == 'u')
				temp += s.charAt(p);
		}

		System.out.println(s);
		System.out.println(temp);

		for (int num1 = 0; num1 < 5; num1++) {
			for (int num2 = num1; num2 < 5; num2++)
				System.out.print(num1 + "," + num2 + "    ");
			System.out.println();
		}

		StringFunctions spy = new StringFunctions();
		int arr[] = new int[] { 1, 3, 8, 9, 14, 123, 0, -32 };
		int last = arr.length - 1;

		spy.bubbleSort(arr);
		System.out.println(Arrays.toString(arr));
		int result = spy.binarySearch(arr, 0, last, 123);
		System.out.println(result);

		String input = "abcd";

		temp = "";
		for (int x = input.length() - 1; x >= 0; x--)
			temp += input.charAt(x);

		System.out.println(temp);

		// int input = 3;
		// int answer = 1;
		// for(int x = 1; x <= input; x++){
		// answer *= x;
		// }
		// System.out.println(answer);
	}

	public int binarySearch(int arr[], int first, int last, int key) {
		if (last >= first) {
			int mid = first + (last - first) / 2;
			if (arr[mid] == key) {
				return mid;
			}
			if (arr[mid] > key) {
				return binarySearch(arr, first, mid - 1, key);// search in left subarray
			} else {
				return binarySearch(arr, mid + 1, last, key);// search in right subarray
			}
		}
		return -1;
	}

	void bubbleSort(int arr[]) {
		int n = arr.length;
		for (int i = 0; i < n - 1; i++)
			for (int j = 0; j < n - i - 1; j++)
				if (arr[j] > arr[j + 1]) {
					// swap arr[j+1] and arr[i]
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
	}
}
