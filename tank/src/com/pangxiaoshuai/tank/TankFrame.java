package com.pangxiaoshuai.tank;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame {
	int x = 200, y = 200;	//定义坐标
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
	
	//窗口需要重新绘制时候自动调用
	@Override
	public void paint(Graphics g) {		//Graphics相当于画笔
		g.fillRect(x, y, 50, 50);	//填充一个矩形fillRect(x轴,y轴，宽，高)
		x += 10;
		y += 10;			
	}
	
	class MyKeyListener extends KeyAdapter{	//处理对于键盘的监听

		@Override
		public void keyPressed(KeyEvent e) {	//一个键被按下去时候调用
			System.out.println("key pressed!");
		}

		@Override
		public void keyReleased(KeyEvent e) {	//一个键被抬起啦时候调用
			System.out.println("key released!");
		}	
		
		
	}

}
