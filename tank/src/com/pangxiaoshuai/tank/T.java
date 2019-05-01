package com.pangxiaoshuai.tank;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class T {

	public static void main(String[] args) {
		Frame f = new Frame(); //new了一窗口
		f.setSize(800, 600);	//初始化窗口大小
		f.setResizable(false);  //显示出来的窗口大小不能改变
		f.setTitle("tank war");  //给窗口起名字
		f.setVisible(true); //让窗口设为可见
		
		//点cha时候关窗口
		f.addWindowListener(new WindowAdapter() { 
			@Override
			public void windowClosing(WindowEvent e) {				
				System.exit(0);
			}			
		});
		
	}

}
