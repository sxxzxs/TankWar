package com.pangxiaoshuai.tank;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class TankFrame extends Frame {
	
	Tank myTank = new Tank(200,200,Dir.DOWN,this);
	List<Bullet> bullets = new ArrayList<>();
	Bullet b = new Bullet(300,300,Dir.DOWN,this);
	static final int GAME_WIDTH = 800,GAME_HEIGHT = 600;
	
	
	public TankFrame() {
		setSize(GAME_WIDTH, GAME_HEIGHT);	//初始化窗口大小
		setResizable(false);  //显示出来的窗口大小不能改变
		setTitle("tank war");  //给窗口起名字
		setVisible(true); //让窗口设为可见
		
		this.addKeyListener(new MyKeyListener()); 	//窗口事件的监听,处理对象是MyKeyListener
		
		//点cha时候关窗口
		addWindowListener(new WindowAdapter() { 
			@Override
			public void windowClosing(WindowEvent e) {				
				System.exit(0);
			}			
		});
	}
	
	/*解决双缓冲,没必要深究，截获update,首先把画出来的东西（坦克，子弹）先画在内存的图片中，
	图片大小和游戏画面一致，然后把内存中图片一次性画到屏幕上（把内存的内容复制到显存）*/
	Image offScreenImage = null;
	@Override
	public void update(Graphics g) {
		if(offScreenImage == null) {
			offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
		}
		Graphics gOffScreen = offScreenImage.getGraphics();
		Color c = gOffScreen.getColor();
		gOffScreen.setColor(Color.BLACK);
		gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
		gOffScreen.setColor(c);
		paint(gOffScreen);
		g.drawImage(offScreenImage, 0, 0, null);
	}
	
	//窗口需要重新绘制时候调用
	@Override
	public void paint(Graphics g) {		//Graphics相当于画笔
		Color c = g.getColor();	//和设置坦克、子弹意思一样
		g.setColor(Color.WHITE);
		g.drawString("子弹的数量: "+ bullets.size(), 10,60);
		g.setColor(c);
		
		myTank.paint(g);	//把这只画笔传给主站坦克，让它自己把自己画出来	
		for(int i = 0; i < bullets.size(); i++) {
			bullets.get(i).paint(g);
		}
	}
	
	class MyKeyListener extends KeyAdapter{	//处理对于键盘的监听
		
		boolean bL = false;	//定义bL记录按键的状态
		boolean bU = false;
		boolean bR = false;
		boolean bD = false;
		boolean LEFT_UP = false;
		boolean LEFT_DOWN = false;
		boolean RIGHT_UP = false;
		boolean RIGHT_DOWN = false;
		
		@Override
		public void keyPressed(KeyEvent e) {	//一个键被按下去时候调用
			//x += 200;
			//repaint();	//会默认调用paint(),让窗口重新绘制;
			int key = e.getKeyCode(); 	//	得到按下键的代码
			switch (key) {
			case KeyEvent.VK_LEFT:		//按下的是向左的键吗
				bL = true;
				break;
			case KeyEvent.VK_UP:		
				bU = true;
				break;
			case KeyEvent.VK_RIGHT:		
				bR = true;
				break;
			case KeyEvent.VK_DOWN:		
				bD = true;
				break;
			case KeyEvent.VK_CONTROL:
				myTank.fire();
				break;

			default:
				break;
			}
			
			setMainTankDir();	//根据按键的状态改变坦克的方向
		}

		@Override
		public void keyReleased(KeyEvent e) {	//一个键被抬起啦时候调用
			int key = e.getKeyCode(); 	//	得到抬起键的代码
			switch (key) {
			case KeyEvent.VK_LEFT:		//抬起的是向左的键吗
				bL = false;
				break;
			case KeyEvent.VK_UP:		
				bU = false;
				break;
			case KeyEvent.VK_RIGHT:		
				bR = false;
				break;
			case KeyEvent.VK_DOWN:		
				bD = false;
				break;

			default:
				break;
			}
						
			setMainTankDir();
			
		}

		private void setMainTankDir() {	
			if(!bL && !bU && !bR && !bD) myTank.setMoving(false);
			else {
			myTank.setMoving(true);	
			
			if(bL) myTank.setDir(Dir.LEFT); 	//	根据bL值获得按下或者抬起的方向
			if(bU) myTank.setDir(Dir.UP);
			if(bR) myTank.setDir(Dir.RIGHT);
			if(bD) myTank.setDir(Dir.DOWN);
			if(bL&&bU) myTank.setDir(Dir.LEFT_UP);
			if(bL&&bD) myTank.setDir(Dir.LEFT_DOWN);
			if(bR&&bU) myTank.setDir(Dir.RIGHT_UP);
			if(bR&&bD) myTank.setDir(Dir.RIGHT_DOWN);
			}
															
		}					
	}

}
