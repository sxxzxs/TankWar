package com.pangxiaoshuai.tank;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		TankFrame tf = new TankFrame(); //new了一窗口	
		
		while(true) {
			Thread.sleep(50);	//歇50ms
			tf.repaint();
		}
	}

}
