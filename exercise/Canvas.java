package com.oops.exercise;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

public class Canvas extends JComponent implements DrawGrid, DrawNode, CalAndDisplayDistance {

	private static int tempHight, tempWidth;
	public static int rows, cols;
	public static List<Integer> listNodes = new ArrayList<>();
	
	

	public Canvas() {
		super();
	}

	public void paint(Graphics g) {

		int width = getSize().width;
		int height = getSize().height;

		drawHorizontalLines(g, width, height);
		drawVerticalLines(g, width, height);
		calculateWidthAndHeight(width, height);
		drawingNodesAsPerInput(g);
		displayDistanceToRight(g);
		displayDistanceToBelow(g);
	}

	@Override
	public void drawVerticalLines(Graphics graphics, int width, int height) {
		int rowWid = width / (cols);
		for (int loopCounter = 0; loopCounter < cols; loopCounter++)
			graphics.drawLine(loopCounter * rowWid, 0, loopCounter * rowWid, height);
	}

	@Override
	public void drawHorizontalLines(Graphics graphics, int width, int height) {
		int rowHt = height / (rows);
		for (int counter = 0; counter < rows; counter++)
			graphics.drawLine(0, counter * rowHt, width, counter * rowHt);
	}

	@Override
	public void drawingNodesAsPerInput(Graphics graphics) {
		for (int loopCounter = 0, multiplyWit = 1; loopCounter < listNodes.size(); loopCounter++, multiplyWit++)
			for (int varible = 1; varible <= listNodes.get(loopCounter); varible++)
				drawNode(graphics, tempWidth * varible, tempHight * multiplyWit);
		
	}

	@Override
	public void drawNode(Graphics graphics, int width, int height) {
		Graphics2D ga2D = (Graphics2D) graphics;
		ga2D.setPaint(Color.red);
		ga2D.fillOval(width, height, 7, 7);
	}

	@Override
	public void calculateWidthAndHeight(int width, int height) {
		tempWidth = Math.round((width / rows)) - 1;
		tempHight = Math.round((height / rows)) - 1;
	}

	@Override
	public void displayDistanceToRight(Graphics graphics) {

		for (int outerLoopCounter = 0, j = 1; outerLoopCounter < listNodes.size(); outerLoopCounter++, j++)
			for (int innerLoopCounter = 1; innerLoopCounter < listNodes.get(outerLoopCounter); innerLoopCounter++)
				displayCoordinatesAndDistanceToRight(graphics, tempWidth * innerLoopCounter, tempHight * j);
	}

	
	
	@Override
	public void displayDistanceToBelow(Graphics graphics) {
		/// loop for string display hight to below  
		/// need to develop further

		for (int hitCounter = 0, j = 1; hitCounter < listNodes.size() - 1; hitCounter++, j++)
			for (int lowCounter = 1; lowCounter <= listNodes.get(hitCounter); lowCounter++)
				displayHight(graphics, tempWidth * lowCounter, tempHight * j, j);
	}
	
	@Override
	public void displayHight(Graphics graphics, int width, int height, int j) {
//		Graphics2D ga = (Graphics2D) graphics;
//		ga.setPaint(Color.blue);
//		int heitCalc = tempHight * j;
//		int temp2 = heitCalc = tempHight;
//		if (temp2 > 0)
//			ga.drawString(temp2 + "", width + 80, height);		// require further modifications 

	}

	@Override
	public void displayCoordinatesAndDistanceToRight(Graphics graphics, int width, int height) {
		Graphics2D ga2D = (Graphics2D) graphics;
		ga2D.setPaint(Color.blue);
		ga2D.drawString("(" + (width / tempWidth) + " , " + (height / tempHight) + " => (" + tempWidth + ","  + tempHight+ ")", width, height);
	}

}