package com.softeem.plane.entry;

import java.awt.image.BufferedImage;
import java.util.Random;

import com.softeem.plane.main.GamePanel;
import com.softeem.plane.utils.ImageLoader;

/**
 * �л���:ÿһ�ܵл�����һ���������̶߳���
 * @author mrchai
 */
public class Enemy extends Thread{

	public BufferedImage img;
	public int x;//�������
	public int y;
	public int width;
	public int height;
	public int type;//0,1,2 �л�����(���)
	public int speed;//�����ٶ�(���)
	
	public Enemy()
	{
		Random r = new Random();
		//��������л�����6:3:1
		int i = r.nextInt(10) + 1;
		type = (i % 10 == 0) ? 2 : 
					(i % 3 == 0) ? 1 : 0;
		//�������ͼ��طɻ�ͼƬ
		img = ImageLoader.load("enemy"+type+".png");
		//��ȡͼƬ���
		width = img.getWidth()/2;
		height = img.getHeight()/2;
		//������������ٶȣ�5~14��
		speed = r.nextInt(10)+5;
		//����������ֵ�x��
		x = r.nextInt(400-width);
	}
	
	@Override
	public void run() {
		//���л����ڿ��ӷ�Χ��ʱ�����ƶ�
		while(y < 650){
			y += speed;
			try {
				sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//����ǰ�л��Ӽ����Ƴ�
		GamePanel.enemys.remove(this);
	}

	public void boom() {
		GamePanel.enemys.remove(this);
	}
}
