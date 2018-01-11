package com.softeem.plane;

import javax.swing.*;

import com.softeem.plane.main.GamePanel;

public class GameMain extends JFrame{
	//构造器（对象创建时完成一些初始化操作）
	public GameMain()
	{
		//设置默认布局方式
		//this.setLayout(null);
		//将游戏面板加入到窗体中
		this.add(new GamePanel());
		//设置窗体标题
		this.setTitle("全民打飞机");
		//设置窗体大小
		this.setSize(400, 650);
		//禁止拖拽改变大小
		this.setResizable(false);
		//设置窗体显示到屏幕正中间
		this.setLocationRelativeTo(null);
		//设置当窗体关闭时停止JVM
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//显示窗体
		this.setVisible(true);
	}
	
	//主方法，入口
	public static void main(String[] args) {
		new GameMain();//创建窗体对象
	}
}
