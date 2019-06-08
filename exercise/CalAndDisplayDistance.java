package com.oops.exercise;

import java.awt.Graphics;

interface CalAndDisplayDistance {

	void calculateWidthAndHeight(int width, int height);

	void displayDistanceToBelow(Graphics g); /// loop for string display hight to below

	void displayDistanceToRight(Graphics g); /// loop for string display distance to right

	void displayHight(Graphics g, int width, int height, int j);

	void displayCoordinatesAndDistanceToRight(Graphics g, int width, int height);
}