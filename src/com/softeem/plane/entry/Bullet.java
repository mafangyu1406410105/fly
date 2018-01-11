package com.softeem.plane.entry;

import java.awt.image.BufferedImage;

import com.softeem.plane.main.GamePanel;
import com.softeem.plane.utils.ImageLoader;

/**
 * �ӵ��ࣺÿһ���ӵ�����һ���������̶߳���
 * @author mrchai
 */
public class Bullet extends Thread{
	
	public BufferedImage img;
	public int x;
	public int y;
	public int width;
	public int height;
	public int speed = 20;//�ӵ��ķ����ٶ�
	
	public Bullet(int x,int y){
		this.x = x;
		this.y = y;
		img = ImageLoader.load("bullet1.png");
		width = img.getWidth() / 2;
		height = img.getHeight() / 2;
	}
	
	//�߳������ʵ��run����
	public void run(){
		while(true){
			try {
				//�����޸�y��λ��
				y = y - speed;
				//�ж��ӵ��Ƿ���л����غ�(��ײ)
				for(int i = 0;i<GamePanel.enemys.size();i++)
				{
					Enemy e = GamePanel.enemys.get(i);//��ȡһ�ܵл�
					if((x>=e.x && x<=e.x+e.width) &&
							(y>=e.y && y<=e.y+e.height)){
						//�л���ը(����)
						e.boom();
						//�ӵ�����
						GamePanel.bullets.remove(this);
					}
				}
				//�ж��ӵ��Ƿ񳬳��߽�
				if(y < 0){
					//���ӵ��Ӽ����Ƴ�
					GamePanel.bullets.remove(this);
					//�˳�ѭ��
					break;
				}
				//ÿ��0.1���ӵ�λ�øı�һ��
				sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
