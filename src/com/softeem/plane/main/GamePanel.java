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
 * ��Ϸ�������:������Ϸ��ص�Ԫ��
 * �������ɻ����ӵ�...
 * @author mrchai
 */
public class GamePanel extends JPanel{
	
	BufferedImage bg;//���汳��
	int x = 0;	//��Ⱦ����ʼλ��x��
	int y = 0;	//��Ⱦ����ʼλ��y��
	int width; //��Ⱦ������
	int height; //��Ⱦ����߶�
	
	int speed = 10;//��ʼ�������ٶ�
	java.util.Timer timer = new java.util.Timer();//������ʱ��
	Hero hero = new Hero();
	//�����ӵ����ϣ��洢������Ӣ�ۻ�������ӵ�����
	public static List<Bullet> bullets = new ArrayList<>();
	//�����л�����(�洢���в����ĵз��ɻ�)
	public static List<Enemy> enemys = new ArrayList<Enemy>();
	
	public GamePanel()
	{
		//��ȡ����ͼƬ����
		bg = ImageLoader.load("background.png");
		width = bg.getWidth();//��ȡͼƬ��ʵ���
		height = bg.getHeight();//��ȡͼƬ��ʵ�߶�
		y = 650 - height;//����y���ʼλ��
		//���ò��ַ�ʽΪ���Բ���
		setLayout(null);
		//������ʱ����,����������ÿ��100�����ظ�����һ��
		timer.schedule(new StartTask(), 0, 100);
		//���������л��Ķ�ʱ����ÿ��һ�봴��һ�ܵл���
		timer.schedule(new EnemyTask(), 0,1000);
		//Ϊ��ǰ������ð��������¼�
		addKeyListener(new MoveLis());
		this.setFocusable(true);//���õ�ǰ����ý���
	}
	//��������������
	class MoveLis extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			//��ȡ�����°����İ�����
			int code = e.getKeyCode();
			hero.moveTo(code);
			//�����ػ�
			repaint();
		}
	}
	//�����л�������
	class EnemyTask extends TimerTask{
		@Override
		public void run() {
			//�����л�
			Enemy e = new Enemy();
			enemys.add(e);//�������ĵл�װ�뼯��
			e.start();//���������߳�
		}
	}
	
	//����һ����ʱ����(�����ı䣬�ɻ�����)
	class StartTask extends TimerTask{
		@Override
		public void run() {
			//�޸ķɻ���ʾͼƬ��flagֵ
			if(hero.flag == 1){
				hero.flag = 2;
			}else{
				hero.flag = 1;
			}
			//�޸ķɻ���ʾ��ͼƬ
			hero.img = ImageLoader.load("hero"+hero.flag+".png");
			y += speed;//�ı�y��λ��
			if(y > 0){
				//��ԭͼƬ��λ��Ϊԭʼλ��
				y = 650 - height;
			}
			
			//����Ԫ���ػ�
			repaint();
		}
	}
	
	//������ϷԪ��
	@Override
	public void paint(Graphics g) {
		//���Ʊ���
		g.drawImage(bg, x, y, width, height, null);
		//����Ӣ�ۻ�
		g.drawImage(hero.img, hero.x, hero.y, hero.width, hero.height, null);
		//�����ӵ�(�����ӵ�����)
		for(int i = 0;i<bullets.size();i++)
		{
			Bullet b = bullets.get(i);
			g.drawImage(b.img,b.x,b.y,b.width,b.height, null);
		}
		//���Ƶл�(jdk1.5+ forEach)
		for(int i = 0;i<enemys.size();i++)
		{
			Enemy e = enemys.get(i);
			g.drawImage(e.img, e.x, e.y, e.width, e.height, null);
		}
		//���Ƶ÷����
		g.drawString("�ܷ�:1000", 20, 20);
	}
}
