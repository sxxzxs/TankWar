package com.pangxiaoshuai.tank;


import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet {
	private static final int SPEED = 10;
	//public static int WIDTH = 15,HEIGHT = 15;
	public static int WIDTH = ResourceMgr.bulletD.getWidth();
	public static int HEIGHT = ResourceMgr.bulletD.getHeight();
	
	private int x,y;
	private Dir dir;
	
	private boolean living = true;
	TankFrame tf = null;
	private Group group = Group.BAD;
	
	public Bullet(int x,int y,Dir dir,Group group,TankFrame tf) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.group = group;
		this.tf = tf;
	}
	
	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}
	
	public void paint(Graphics g) {
		if(!living) {
			tf.bullets.remove(this);
		}
		
		/*Color c = g.getColor();
		g.setColor(Color.RED);  //设置炮弹颜色
		g.fillOval(x, y, WIDTH, HEIGHT);
		g.setColor(c);  //把原来画笔颜色设回来
		*/	
		
		switch(dir) {
		case LEFT:
			g.drawImage(ResourceMgr.bulletL, x, y, null);
			break;
		case UP:
			g.drawImage(ResourceMgr.bulletU, x, y, null);
			break;
		case RIGHT:
			g.drawImage(ResourceMgr.bulletR, x, y, null);
			break;
		case DOWN:
			g.drawImage(ResourceMgr.bulletD, x, y, null);
			break;
		}
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
		/* case LEFT_UP:
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
             break;*/

		default:
			break;
		}
		
		if(x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) {
			living = false;
		}
					
	}
	
	public void collideWith(Tank tank) {
		
		if(this.group == tank.getGroup()) return;
		
		//TODO: 用一个rect来记录子弹的位置
		Rectangle rect1 = new Rectangle(this.x, this.y, WIDTH, HEIGHT);
		Rectangle rect2 = new Rectangle(tank.getX(), tank.getY(), Tank.WIDTH, Tank.HEIGHT);
		if(rect1.intersects(rect2)) {
			tank.die();
			this.die();
		}

	}

	private void die() {
		this.living = false;
	}

}
