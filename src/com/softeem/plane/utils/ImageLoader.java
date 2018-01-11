package com.softeem.plane.utils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

/**
 * 图片素材加载工具类
 * @author mrchai
 */
public class ImageLoader {
	
	public static BufferedImage load(String img){
		//根据指定图片的资源获取URL对象
		URL url = ImageLoader.class.getResource("/image/"+img);
		try {
			return ImageIO.read(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		System.out.println(ImageLoader.load("bg.png"));
	}
	
}
