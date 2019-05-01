package com.pangxiaoshuai.tank;

import java.awt.Color;
import java.awt.Graphics;

public class Bullet {
	private static final int SPEED = 10;
	public static int WIDTH = 15,HEIGHT = 15;
	
	private int x,y;
	private Dir dir;
	
	private boolean live = true;
	TankFrame tf = null;
	
	public Bullet(int x,int y,Dir dir,TankFrame tf) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.tf = tf;
	}
	
	public void paint(Graphics g) {
		if(!live) {
			tf.bullets.remove(this);
		}
		
		Color c = g.getColor();
		g.setColor(Color.RED);  //设置炮弹颜色
		g.fillOval(x, y, WIDTH, HEIGHT);
		g.setColor(c);  //把原来画笔颜色设回来
		
		move();
						
	}

	private void move() {
		
		switch (dir) {	//根据坦克的方向进行坦克的移动
		case LEFT:
			x -= SPEED;
			break;
		case UP:
			y -= SPEED;
			break;
		case RIGHT:
			x += SPEED;
			break;
		case DOWN:
			y += SPEED;
			break;
		 case LEFT_UP:
             y -= SPEED;
             x -= SPEED;
             break;
         case LEFT_DOWN:
             x -= SPEED;
             y += SPEED;
             break;
         case RIGHT_UP:
             y -= SPEED;
             x += SPEED;
             break;
         case RIGHT_DOWN:
             x += SPEED;
             y += SPEED;
             break;

		default:
			break;
		}
		
		if(x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) {
			live = false;
		}
		
			
	}

}
