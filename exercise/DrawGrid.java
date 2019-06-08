package com.oops.exercise;

import java.awt.Graphics;

interface DrawGrid {
	public void drawVerticalLines(Graphics g, int width, int height);

	public void drawHorizontalLines(Graphics g, int width, int height);
}