package com.pong.main;

import java.awt.Color;
import java.awt.Graphics;

public class Ball {
	Player player;
	AI ai;
	
	int x;
	int y;
	int size;
	Color color;
	
	private int speed;
	
	private boolean xcoll;
	private boolean ycoll;
	
	public Ball(Player p, AI a, int x, int y, int size, Color color) {
		player = p;
		ai = a;
		
		this.x = x;
		this.y = y;
		this.size = size;
		this.color = color;
		
		speed = 2;
		
		xcoll = false;
		ycoll = false;
	}
	
	public void tick() {
		if (xcoll == false) {
			this.x = this.x + speed;
		}else {
			this.x = this.x - speed;
		}
		
		if (ycoll == false) {
			this.y = this.y + speed;
		}else {
			this.y = this.y - speed;
		}
		
		if (this.x <= 0) {
			xcoll = false;
		}else if (this.x + (this.size + 8) >= Game.WIDTH) {
			xcoll = true;
		}
		
		if (this.y + this.size <= -50) {
		player.score = player.score + 1;
		if (player.score >= 10) {
			this.x = -30;
			this.y = Game.HEIGHT;
			speed = 0;
			System.out.println("Player Wins!");
		}else {
			this.x = Game.WIDTH/2;
			this.y = 200;
			speed = 3;
			ycoll = false;
			if ((int)(Math.random() * 10 + 1) % 2 == 0) {
				xcoll = false;
			}else {
				xcoll = true; 
			}
		}
	}
	
		if (this.y + (this.size * 2) + 3 >= Game.HEIGHT + 60) {
			ai.score = ai.score + 1;
			if (ai.score >= 10) {
				this.x = -30;
				this.y = Game.HEIGHT/2;
				speed = 0;
				System.out.println("Ai wins");
			}else {
				this.x = Game.WIDTH/2;
				this.y = 400;
				speed = 3;
				ycoll = true;
				if ((int)(Math.random() * 10 + 1) % 2 == 0) {
					xcoll = false;
				}else {
					xcoll = true; 
				}
			}
		}
		
		playerCollision();
		aiCollision();
	
	}
	
	public void playerCollision() {
		if ((this.x <= player.x + player.width) && (this.x + this.size >= player.x)) {
			if ((this.y <= player.y + player.height) && (this.y + this.size >= player.y)) {
				ycoll = true;
				speed = speed + (int)0.46;
			}			
		}
	}
	
	public void aiCollision() {
		if ((this.x <= ai.x + ai.width) && (this.x + this.size >= ai.x)) {
			if ((this.y <= ai.y + ai.height) && (this.y + this.size >= ai.y)) {
				ycoll = false;
				speed = speed + (int)0.46;
				ai.x -= 3;
			}
		}
	}
	
	public void render(Graphics g) {
		g.setColor(this.color);
		g.fillOval(this.x, this.y, this.size, this.size);
	} 
}
