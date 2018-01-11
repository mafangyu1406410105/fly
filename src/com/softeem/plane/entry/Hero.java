package com.softeem.plane.entry;

import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

import com.softeem.plane.main.GamePanel;
import com.softeem.plane.utils.ImageLoader;

/**
 * Ӣ�ۻ�:��ҷɻ�
 * @author mrchai
 */
public class Hero {

	public BufferedImage img; //�ɻ���ͼƬ
	public int x;//�ɻ����Ƶ���ʼλ��x��
	public int y;//�ɻ����Ƶ���ʼλ��y��
	public int width;//��ʾ�Ŀ��
	public int height;//��ʾ�ĸ߶�
	public int speed=10;//�����ٶ�
	public int flag = 1;//�ɻ���ʾ��ͼƬ���(1,2)
	//����һ����ʱ������
	Timer timer = new Timer();
	
	public Hero()
	{
		//����ָ����ͼƬ
		img = ImageLoader.load("hero"+flag+".png");
		//��ȡͼƬ�Ŀ��
		width = img.getWidth() / 2;
		//��ȡͼƬ�ĸ߶�
		height= img.getHeight() / 2;
		//���÷ɻ���ʾ��x�ᣬy��
		x = 400/2 - width/2;
		y = 650 - height - 40;
		
		//��ʼ�����ӵ������ȶ�ʱ����
		timer.schedule(new ShootTask(), 1000, 200);
	}
	
	//����һ����ʱ���������ӵ�
	class ShootTask extends TimerTask{
		@Override
		public void run() {	
			//�����ӵ�����
			Bullet b = new Bullet(x + width/2, y);
			//���洴�����ӵ�����(���浽����)
			GamePanel.bullets.add(b);
			//�����ӵ��ķ����߳�
			b.start();
		}
	}
	//�ƶ�
	public void moveTo(int pos)
	{
		switch(pos){
		case 37:
			x -= speed;//���ƶ�
			if(x < 0){
				x = 0;//��ֹ���Ƴ����߽�
			}
			break;
		case 38:
			y -= speed;//����
			if(y < 0){
				y = 0;//��ֹ�����ϱ߽�
			}
			break;
		case 39:
			x += speed;//����
			if(x > 400-width){
				x = 400-width;
			}
			break;
		case 40:
			y += speed;//����
			if(y > 650-height-40){
				y = 650-height-40;
			}
			break;
		}
	}
}
