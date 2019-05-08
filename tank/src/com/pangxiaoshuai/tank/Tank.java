package com.pangxiaoshuai.tank;


import java.awt.Graphics;
import java.util.Random;

public class Tank {
	private int x, y;
	private Dir dir = Dir.DOWN;
	private static final int SPEED = 1;
	private TankFrame tf = null;
	public static int WIDTH = ResourceMgr.tankD.getWidth();
	public static int HEIGHT = ResourceMgr.tankD.getHeight();

	private Random random = new Random();
	private boolean moving = true;
	private boolean living = true;
	private Group group = Group.BAD;
	
	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Dir getDir() {
		return dir;
	}

	public void setDir(Dir dir) {
		this.dir = dir;
	}
		
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public void die() {
		this.living = false;
	}

	public Tank(int x, int y, Dir dir,Group group,TankFrame tf) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.group = group;
		this.tf = tf;
	}

	public void paint(Graphics g) {
		/*Color c = g.getColor();	
		g.setColor(Color.YELLOW); 	//设置主战坦克颜色
		g.fillRect(x, y, 50, 50);	//填充一个矩形fillRect(x轴,y轴，宽，高)
		g.setColor(c); 	//把原来画笔颜色设回来
		*/	
		if(!living) tf.tanks.remove(this);
		
		switch(dir) {
		case LEFT:
			g.drawImage(ResourceMgr.tankL, x, y, null);
			break;
		case UP:
			g.drawImage(ResourceMgr.tankU, x, y, null);
			break;
		case RIGHT:
			g.drawImage(ResourceMgr.tankR, x, y, null);
			break;
		case DOWN:
			g.drawImage(ResourceMgr.tankD, x, y, null);
			break;
		}
		
		move();
						
	}

	private void move() {
		if(!moving) return;
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
		if(random.nextInt(10) > 8) this.fire();	
	}

	public void fire() {
		//tf.bullets.add(new Bullet(this.x, this. y, this.dir,this.tf));
		int bX = this.x + Tank.WIDTH/2 - Bullet.WIDTH/2;
		int bY = this.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;

		tf.bullets.add(new Bullet(bX, bY, this.dir,this.group, this.tf));
		
	}
	

}
