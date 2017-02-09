package com.comp.admin.utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DefaultKaptcha {

	private static final String KAPTCHA_SESSION_KEY = "kaptchaCode";
	
	public static final int WIDTH=80;
	public static final int HEIGHT=30;
	private String checkcode;
	
	public static final char[] CHARS={'2','3','4','5','6','7','8','9','A','B','C','D','E','F','G','H','J','K','L','M','N','P','Q','R','S','T','U','V','W','X','Y','Z'};
	
	public BufferedImage createImage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		BufferedImage image=new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
		
		Graphics g=image.getGraphics();
		
		//1.设置前景色
		setBackGround(g);
		
		//2.设置边框
		setBorder(g);
		
		//3.画干扰线
		drawRandomLine(g);
		
		//4.写随机数
		checkcode = drawRandomNum(g);
		
		//5、用session将验证码保存起来，如进行验证时，会在spring security自定义的登录验证MyUsernamePasswordAuthenticationFilter类里进行验证
		if(request.getSession().getAttribute(KAPTCHA_SESSION_KEY) != ""){
			request.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
			request.getSession().setAttribute(KAPTCHA_SESSION_KEY, checkcode);
		}else{
			request.getSession().setAttribute(KAPTCHA_SESSION_KEY, checkcode);
		}
			
		return image;
		/*//5.图形写给浏览器
		response.setContentType("IMAGE/jpeg");
		
		//浏览器不缓存
		response.setDateHeader("expires", -1);
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("pragma", "no-cache");
		
		ImageIO.write(image, "jpg", response.getOutputStream());*/
				
	}

	private void setBackGround(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, WIDTH, HEIGHT);
	}

	private void setBorder(Graphics g) {
		g.setColor(Color.BLUE);
		g.drawRect(-1, -1, WIDTH,HEIGHT);
		
	}
	
	private void drawRandomLine(Graphics g) {
		g.setColor(Color.GREEN);
		
		for(int i=0;i<5;i++){
			int x1=new Random().nextInt(WIDTH);
			int y1=new Random().nextInt(HEIGHT);
			int x2=new Random().nextInt(WIDTH);
			int y2=new Random().nextInt(HEIGHT);
			g.drawLine(x1, y1, x2, y2);
		}
				
	}
	
	private String drawRandomNum(Graphics g) {
	
		g.setColor(Color.RED);
		g.setFont(new Font("宋体",Font.BOLD,20));
		
		Random random =new Random();
					
		StringBuffer buffer=new StringBuffer();
		
		  for(int i=0;i<4;i++){ //生成六个字符
		   buffer.append(CHARS[random.nextInt(CHARS.length)]);
		  }
		  
		  //System.out.println("checkcode:"+buffer.toString());//测试用
		  g.drawString(buffer.toString(),20,22);
		 	  
		return buffer.toString();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
	
	public String getCheckcode() {
		return checkcode;
	}

	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}

}
