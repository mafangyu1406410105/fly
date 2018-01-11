package com.softeem.plane.entry;

import java.awt.image.BufferedImage;
import java.util.Random;

import com.softeem.plane.main.GamePanel;
import com.softeem.plane.utils.ImageLoader;

/**
 * 敌机类:每一架敌机都是一个独立的线程对象
 * @author mrchai
 */
public class Enemy extends Thread{

	public BufferedImage img;
	public int x;//随机产生
	public int y;
	public int width;
	public int height;
	public int type;//0,1,2 敌机类型(随机)
	public int speed;//飞行速度(随机)
	
	public Enemy()
	{
		Random r = new Random();
		//随机产生敌机类型6:3:1
		int i = r.nextInt(10) + 1;
		type = (i % 10 == 0) ? 2 : 
					(i % 3 == 0) ? 1 : 0;
		//根据类型加载飞机图片
		img = ImageLoader.load("enemy"+type+".png");
		//获取图片宽高
		width = img.getWidth()/2;
		height = img.getHeight()/2;
		//随机产生飞行速度（5~14）
		speed = r.nextInt(10)+5;
		//随机产生出现的x轴
		x = r.nextInt(400-width);
	}
	
	@Override
	public void run() {
		//当敌机还在可视范围内时继续移动
		while(y < 650){
			y += speed;
			try {
				sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//将当前敌机从集合移除
		GamePanel.enemys.remove(this);
	}

	public void boom() {
		GamePanel.enemys.remove(this);
	}
}
