import java.awt.Image;

import javax.swing.ImageIcon;

public class BossBullet {
	Image image = new ImageIcon("D:\\programes\\java_workspace\\ShottingGame\\src\\image\\items\\bullets\\14.png").getImage();
	
	int x,y;
	int width = image.getWidth(null);
	int height = image.getHeight(null);
	int degree;
	int cnt;
	double dx = 0;
	double dy = 0;
	double mx;
	double my; 
	public BossBullet(int x, int y,int degree) {
		this.degree = degree;
		this.x = x;
		this.y = y;
		this.dx = x;
		this.dy = y;
		
	}
	public void fire() {
		double xs = DirectionX(degree) + x;
		double ys = DirectionY(degree) + y;
		
		double px = xs - x;
		double py = ys - y;
//		this.mx += 2 * px / py;
		if(degree%5==4) {
			px = 3 * px;
			py = 3 * py;
		}else if(degree%5==2) {
			px = 2.5 * px;
			py = 2.5 * py;
		}else{
			px = 2 * px;
			py = 2 * py;
		}
		if(cnt<10) {
			this.mx += 2 * px;
			this.my += 2 * py;
		}else if(cnt<20) {
			this.mx += 1.75 * px;
			this.my += 1.75 * py;
		}else {
			this.mx += 1.5 * px;
			this.my += 1.5 * py;
		}
//		this.x = (int)(dx + mx);
//		this.y = (int)(dy + my);
		if(degree <= 90) {		
			this.x = (int)(dx + mx);
			this.y = (int)(dy + my);
		}else if(degree > 90 && degree <= 180) {
			this.x = (int)(dx + mx);
			this.y = (int)(dy + my);
		}else if(degree > 180 && degree <= 270) {
			this.x = (int)(dx + mx);
			this.y = (int)(dy + my);
		}else if(degree > 270) {
			System.out.println("asdf");
			this.x = (int)(dx + mx);
			this.y = (int)(dy + my);
		}
		cnt++;
	}
	/*
	public void fire() {
		double xs = DirectionX(degree) + x;
		double ys = DirectionY(degree) + y;
		
		double px = xs - x;
		double py = ys - y;
//		this.mx += 2 * px / py;
		if(cnt<10) {
			this.mx += 4 * px;
			this.my += 4 * py;
		}else if(cnt<20) {
			this.mx += 3 * px;
			this.my += 3 * py;
		}else {
			this.mx += 2 * px;
			this.my += 2 * py;
		}
//		this.x = (int)(dx + mx);
//		this.y = (int)(dy + my);
		if(degree <= 90) {		
			this.x = (int)(dx + mx);
			this.y = (int)(dy + my);
		}else if(degree > 90 && degree <= 180) {
			this.x = (int)(dx + mx);
			this.y = (int)(dy + my);
		}else if(degree > 180 && degree <= 270) {
			this.x = (int)(dx + mx);
			this.y = (int)(dy + my);
		}else if(degree > 270) {
			System.out.println("asdf");
			this.x = (int)(dx + mx);
			this.y = (int)(dy + my);
		}
		cnt++;
	}
	*/
//	public void fire() {
//		//xs, ys >  x,y가 중심인 원의  어떠한 점
//		double xs = DirectionX(degree) + x;
//		double ys = DirectionY(degree) + y;
//		
//		//x,y 와  점(x',y') 뺀 값
//		double px = xs - x;
//		double py = ys - y;
//		this.mx += 2 * px;
//		this.my += 2* py;
//		this.x = (int)(dx + mx); 
//		this.y = (int)(dy + my);
//	}
	public double DirectionX(int degree) {
		return Math.cos((Math.PI/180)*degree);
	}
	public double DirectionY(int degree) {
		return Math.sin((Math.PI/180)*degree);
	}
}
