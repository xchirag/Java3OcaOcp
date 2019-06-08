package com.oops.exercise;

import java.util.Scanner;

public interface UserInputDao {
	public int takeInputOfGridSize(Scanner in);

	public void takeInputOfNodeNumbers(Scanner scanner, int rowsInput);

	public void displayFrameProperties(int rowsInput);
}