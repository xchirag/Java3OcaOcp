package com.oops.exercise;

import java.util.Scanner;

import javax.swing.JFrame;

public class UserInputImpl implements UserInputDao {

	public UserInputImpl() {
		super();
	}

	public int takeInputOfGridSize(Scanner in) {
		System.out.println("what is the grid size? : ");
		int rowsInput = in.nextInt();
		return rowsInput;
	}

	public void takeInputOfNodeNumbers(Scanner scanner, int rowsInput) {
		int counter;
		int nodeNumbers = 0;
		for (counter = 1; counter < rowsInput;) {
			System.out.println("how many nodes on [" + counter + "] rows ?");
			nodeNumbers = scanner.nextInt();
			if (nodeNumbers > rowsInput - 1 || nodeNumbers < 0) {
				System.out.println("not valid");
				continue;
			}
			counter++;
			Canvas.listNodes.add(nodeNumbers);
		}
	}

	public void displayFrameProperties(int rowsInput) {
		Canvas.rows = rowsInput;
		Canvas.cols = rowsInput;

		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setBounds(30, 30, 700, 700);
		window.getContentPane().add(new Canvas());
		window.setVisible(true);
		window.setResizable(false);

	}

}