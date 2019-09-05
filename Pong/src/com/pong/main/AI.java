package com.pong.main;

import java.awt.Color;
import java.awt.Graphics;

public class AI {
	int x;
	int y;
	int width;
	int height;
	Color color;
	
	int score;
	
	public AI(int x, int y, int width, int height, Color color) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
		
		score = 0;
	}
	
	public void tick(Ball ball) {
		//this.x = ball.x - this.width/2;
	}
	
	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect(this.x, this.y, this.width, this.height);
	}
}
