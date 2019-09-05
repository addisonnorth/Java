package com.pong.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player implements KeyListener {	
	int x;
	int y;
	int width;
	int height;
	Color color;
	
	int score;
	
	int speed;
	
	private boolean left;
	private boolean right;
	
	public Player(int x, int y, int width, int height, Color color) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
		
		score = 0;
		
		speed = 6;
		
		left = false;
		right = false;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			left = true;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			right = true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			left = false;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			right = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void tick() {
		if(left == true) {
			this.x = this.x - speed;
		}
		
		if (right == true) {
			this.x = this.x + speed;
		}
		
		if (this.x < 0) {
			this.x = 0;
		}else if (this.x + this.width > Game.WIDTH - 6) {
			this.x = Game.WIDTH - this.width - 6;
		}
	}
	
	public void render(Graphics g) {
		g.setColor(this.color);
		g.fillRect(this.x, this.y, this.width, this.height);
	}
}
