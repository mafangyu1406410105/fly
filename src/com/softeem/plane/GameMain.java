package com.softeem.plane;

import javax.swing.*;

import com.softeem.plane.main.GamePanel;

public class GameMain extends JFrame{
	//�����������󴴽�ʱ���һЩ��ʼ��������
	public GameMain()
	{
		//����Ĭ�ϲ��ַ�ʽ
		//this.setLayout(null);
		//����Ϸ�����뵽������
		this.add(new GamePanel());
		//���ô������
		this.setTitle("ȫ���ɻ�");
		//���ô����С
		this.setSize(400, 650);
		//��ֹ��ק�ı��С
		this.setResizable(false);
		//���ô�����ʾ����Ļ���м�
		this.setLocationRelativeTo(null);
		//���õ�����ر�ʱֹͣJVM
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//��ʾ����
		this.setVisible(true);
	}
	
	//�����������
	public static void main(String[] args) {
		new GameMain();//�����������
	}
}
