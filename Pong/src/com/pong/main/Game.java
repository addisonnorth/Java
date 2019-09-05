package com.pong.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable{
	private static final long serialVersionUID = 8319728193694498369L;
	
	public static final int WIDTH = 860;
	public static final int HEIGHT = WIDTH/12 * 9;
	
	private Thread thread;
	private boolean running = false;
	
	private AI ai;
	private Player player;
	private Ball ball;
	
	public Game() {
		new Window(WIDTH, HEIGHT, "Pong", this);
		this.requestFocus();

		ai = new AI(WIDTH/2 - 36, 35, 100, 20, (Color.red));
		player = new Player(WIDTH/2 - 36, HEIGHT - 80, 100, 20, (Color.green));
		ball = new Ball(player, ai, 20, 100, 28, (Color.blue));	
		
		this.addKeyListener(player);
	}

	public synchronized void start() {
		thread = new Thread(this);
		thread.start(); 
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000/amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		
		while(running) {
			long now = System.nanoTime();
			delta = delta + (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta = delta - 1;
			}
			if (running) {
				render();
			}
			frames = frames + 1;
			
			if (System.currentTimeMillis() - timer > 1000) {
				timer = timer + 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}
	
	private void tick() {
		ai.tick(ball);
		player.tick();
		ball.tick();
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.white);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		ai.render(g);
		player.render(g);
		ball.render(g);
		
		g.setColor(Color.green);
		g.setFont(new Font("Impact", Font.BOLD, 80));
		g.drawString(String.valueOf(player.score), WIDTH/2 - 10, HEIGHT - 130);
		
		g.setColor(Color.red);
		g.setFont(new Font("Impact", Font.BOLD, 80));
		g.drawString(String.valueOf(ai.score), WIDTH/2 - 10, 170);
		
		g.dispose();
		bs.show();
		
		ball.ai.x -= 1;
	}
	
	public static void main(String args[]) {
		new Game();
	}
}
