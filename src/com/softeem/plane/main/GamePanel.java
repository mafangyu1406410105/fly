package com.softeem.plane.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import javax.swing.*;//javax.swing.Timer  java.util.Timer 

import com.softeem.plane.entry.Bullet;
import com.softeem.plane.entry.Enemy;
import com.softeem.plane.entry.Hero;
import com.softeem.plane.utils.ImageLoader;

/**
 * 游戏的主面板:绘制游戏相关的元素
 * 背景，飞机，子弹...
 * @author mrchai
 */
public class GamePanel extends JPanel{
	
	BufferedImage bg;//界面背景
	int x = 0;	//渲染的起始位置x轴
	int y = 0;	//渲染的起始位置y轴
	int width; //渲染区域宽度
	int height; //渲染区域高度
	
	int speed = 10;//初始化飞行速度
	java.util.Timer timer = new java.util.Timer();//创建定时器
	Hero hero = new Hero();
	//创建子弹集合（存储所有由英雄机发射的子弹对象）
	public static List<Bullet> bullets = new ArrayList<>();
	//创建敌机集合(存储所有产生的敌方飞机)
	public static List<Enemy> enemys = new ArrayList<Enemy>();
	
	public GamePanel()
	{
		//获取背景图片对象
		bg = ImageLoader.load("background.png");
		width = bg.getWidth();//获取图片真实宽度
		height = bg.getHeight();//获取图片真实高度
		y = 650 - height;//设置y轴初始位置
		//设置布局方式为绝对布局
		setLayout(null);
		//启动定时任务,立即启动，每隔100毫秒重复调度一次
		timer.schedule(new StartTask(), 0, 100);
		//启动创建敌机的定时任务（每隔一秒创建一架敌机）
		timer.schedule(new EnemyTask(), 0,1000);
		//为当前面板设置按键监听事件
		addKeyListener(new MoveLis());
		this.setFocusable(true);//设置当前面板获得焦点
	}
	//创建按键监听器
	class MoveLis extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			//获取被按下按键的按键码
			int code = e.getKeyCode();
			hero.moveTo(code);
			//立即重绘
			repaint();
		}
	}
	//产生敌机的任务
	class EnemyTask extends TimerTask{
		@Override
		public void run() {
			//生产敌机
			Enemy e = new Enemy();
			enemys.add(e);//将产生的敌机装入集合
			e.start();//启动飞行线程
		}
	}
	
	//创建一个定时任务(背景改变，飞机飞行)
	class StartTask extends TimerTask{
		@Override
		public void run() {
			//修改飞机显示图片的flag值
			if(hero.flag == 1){
				hero.flag = 2;
			}else{
				hero.flag = 1;
			}
			//修改飞机显示的图片
			hero.img = ImageLoader.load("hero"+hero.flag+".png");
			y += speed;//改变y轴位置
			if(y > 0){
				//还原图片的位置为原始位置
				y = 650 - height;
			}
			
			//界面元素重绘
			repaint();
		}
	}
	
	//绘制游戏元素
	@Override
	public void paint(Graphics g) {
		//绘制背景
		g.drawImage(bg, x, y, width, height, null);
		//绘制英雄机
		g.drawImage(hero.img, hero.x, hero.y, hero.width, hero.height, null);
		//绘制子弹(遍历子弹集合)
		for(int i = 0;i<bullets.size();i++)
		{
			Bullet b = bullets.get(i);
			g.drawImage(b.img,b.x,b.y,b.width,b.height, null);
		}
		//绘制敌机(jdk1.5+ forEach)
		for(int i = 0;i<enemys.size();i++)
		{
			Enemy e = enemys.get(i);
			g.drawImage(e.img, e.x, e.y, e.width, e.height, null);
		}
		//绘制得分情况
		g.drawString("总分:1000", 20, 20);
	}
}
