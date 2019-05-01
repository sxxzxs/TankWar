package com.pangxiaoshuai.tank;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame {
	
	Tank myTank = new Tank(200,200,Dir.DOWN);
	
	public TankFrame() {
		setSize(800, 600);	//初始化窗口大小
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
	
	//窗口需要重新绘制时候调用
	@Override
	public void paint(Graphics g) {		//Graphics相当于画笔
		myTank.paint(g);	//把这只画笔传给主站坦克，让它自己把自己画出来				
	}
	
	class MyKeyListener extends KeyAdapter{	//处理对于键盘的监听
		
		boolean bL = false;	//定义bL记录按键的状态
		boolean bU = false;
		boolean bR = false;
		boolean bD = false;
		
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
			if(bL) myTank.setDir(Dir.LEFT); 	//	根据bL值获得按下或者抬起的方向
			if(bU) myTank.setDir(Dir.UP);
			if(bR) myTank.setDir(Dir.RIGHT);
			if(bD) myTank.setDir(Dir.DOWN);
			
		}					
	}

}
