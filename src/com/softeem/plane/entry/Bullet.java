package com.softeem.plane.entry;

import java.awt.image.BufferedImage;

import com.softeem.plane.main.GamePanel;
import com.softeem.plane.utils.ImageLoader;

/**
 * 子弹类：每一颗子弹都是一个独立的线程对象
 * @author mrchai
 */
public class Bullet extends Thread{
	
	public BufferedImage img;
	public int x;
	public int y;
	public int width;
	public int height;
	public int speed = 20;//子弹的飞行速度
	
	public Bullet(int x,int y){
		this.x = x;
		this.y = y;
		img = ImageLoader.load("bullet1.png");
		width = img.getWidth() / 2;
		height = img.getHeight() / 2;
	}
	
	//线程类必须实现run方法
	public void run(){
		while(true){
			try {
				//不断修改y轴位置
				y = y - speed;
				//判断子弹是否与敌机有重合(碰撞)
				for(int i = 0;i<GamePanel.enemys.size();i++)
				{
					Enemy e = GamePanel.enemys.get(i);//获取一架敌机
					if((x>=e.x && x<=e.x+e.width) &&
							(y>=e.y && y<=e.y+e.height)){
						//敌机爆炸(销毁)
						e.boom();
						//子弹销毁
						GamePanel.bullets.remove(this);
					}
				}
				//判断子弹是否超出边界
				if(y < 0){
					//将子弹从集合移除
					GamePanel.bullets.remove(this);
					//退出循环
					break;
				}
				//每隔0.1秒子弹位置改变一次
				sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
