package com.softeem.plane.entry;

import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

import com.softeem.plane.main.GamePanel;
import com.softeem.plane.utils.ImageLoader;

/**
 * 英雄机:玩家飞机
 * @author mrchai
 */
public class Hero {

	public BufferedImage img; //飞机的图片
	public int x;//飞机绘制的起始位置x轴
	public int y;//飞机绘制的起始位置y轴
	public int width;//显示的宽度
	public int height;//显示的高度
	public int speed=10;//飞行速度
	public int flag = 1;//飞机显示的图片标记(1,2)
	//创建一个定时器对象
	Timer timer = new Timer();
	
	public Hero()
	{
		//加载指定的图片
		img = ImageLoader.load("hero"+flag+".png");
		//获取图片的宽度
		width = img.getWidth() / 2;
		//获取图片的高度
		height= img.getHeight() / 2;
		//设置飞机显示的x轴，y轴
		x = 400/2 - width/2;
		y = 650 - height - 40;
		
		//开始发射子弹（调度定时任务）
		timer.schedule(new ShootTask(), 1000, 200);
	}
	
	//创建一个定时任务，制造子弹
	class ShootTask extends TimerTask{
		@Override
		public void run() {	
			//创建子弹对象
			Bullet b = new Bullet(x + width/2, y);
			//保存创建的子弹对象(保存到集合)
			GamePanel.bullets.add(b);
			//启动子弹的飞行线程
			b.start();
		}
	}
	//移动
	public void moveTo(int pos)
	{
		switch(pos){
		case 37:
			x -= speed;//左移动
			if(x < 0){
				x = 0;//防止左移超过边界
			}
			break;
		case 38:
			y -= speed;//上移
			if(y < 0){
				y = 0;//防止超过上边界
			}
			break;
		case 39:
			x += speed;//右移
			if(x > 400-width){
				x = 400-width;
			}
			break;
		case 40:
			y += speed;//下移
			if(y > 650-height-40){
				y = 650-height-40;
			}
			break;
		}
	}
}
