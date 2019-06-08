package com.oops.exercise;

import java.util.Scanner;

public class MainSprint extends UserInputImpl {
	public static void main(String[] a) {
		Scanner in = new Scanner(System.in);
		UserInputImpl infoRetrived = new MainSprint();
		int rowsInput = infoRetrived.takeInputOfGridSize(in);
		infoRetrived.takeInputOfNodeNumbers(in, rowsInput);
		infoRetrived.displayFrameProperties(rowsInput);
	}
}
