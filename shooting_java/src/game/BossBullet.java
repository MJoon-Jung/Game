package game;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import org.w3c.dom.css.Rect;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
public class BossBullet{
	int x,y;
	int width,height;
	int type;
	int angle;
	int cnt;
	int c,d;
	double dx = 0;
	double dy = 0;
	double mx,my;
	Rectangle rect;
	Image image;
	public BossBullet(int x,int y,int type,int angle) {
		switch(type){
//			case 1:
//				image = new ImageIcon("src/image/items/bullets/17.png").getImage();
//				this.x = x;
//				this.y = y;
//				this.type = type;
//				this.width = image.getWidth(null);
//				this.height = image.getHeight(null);
//				this.angle = angle;
//				this.dx = x;
//				this.dy = y;
//				break;
			case 1:
				image = new ImageIcon("src/image/items/bullets/17-1.png").getImage();
				this.x =  x;
				this.y = y;
				this.type = type; 
				this.width = image.getWidth(null);
				this.height = image.getHeight(null);
				this.angle = angle;
				this.dx = (int) (DirectionX(angle) * 55) + x;
				this.dy = (int) (DirectionY(angle) * 55) + y;
				rect = new Rectangle(c,d,width,height);
				break;
			case 2:
				image = new ImageIcon("src/image/items/bullets/12-1.png").getImage();
				this.x = x;
				this.y = y;
				this.type = type;
				this.width = image.getWidth(null);
				this.height = image.getHeight(null);
				rect = new Rectangle(c,d,width,height);
				break;
			case 3: //가로 9개 세로 12개  시작점 화면 바깥 반 걸쳐서... 
				image = new ImageIcon("src/image/items/bullets/18-1.png").getImage();
				this.x = x;
				this.y = y;
				this.type = type;
				this.angle = angle;
				this.width = image.getWidth(null);
				this.height = image.getHeight(null);
				c = this.x;
				d = this.y;		
				rect = new Rectangle(c,d,width,height);
		}
	}
	public void fire() {
		switch(type) {
			case 1:
				double xs = DirectionX(angle) + x;
				double ys = DirectionY(angle) + y;
				
				double px = xs - x;
				double py = ys - y;
				
				if(angle%5==4) {
					px = 3 * px;
					py = 3 * py;
				}else if(angle%5==2) {
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
				if(angle <= 90) {		
					this.x = (int)(dx + mx);
					this.y = (int)(dy + my);
				}else if(angle > 90 && angle <= 180) {
					this.x = (int)(dx + mx);
					this.y = (int)(dy + my);
				}else if(angle > 180 && angle <= 270) {
					this.x = (int)(dx + mx);
					this.y = (int)(dy + my);
				}else if(angle > 270) {
					this.x = (int)(dx + mx);
					this.y = (int)(dy + my);
				}
				rect.x = this.x;
				rect.y = this.y;
				cnt++;
				break;
			case 2:
				if(cnt > 50) {					
					this.y += 11;
					rect.x = this.x;
					rect.y = this.y;
				}
				cnt++;
				break;
			case 3:
				if(d == 0 && angle == 0) {
					this.y += 2;
				}else if(d == Main.SCREEN_HEIGHT - 30 && angle == 0) {
					this.y -= 2;
				}
				if(c == 0 && angle == 1) {
					this.x += 2;
				}else if(c == Main.SCREEN_WIDTH - 30 && angle == 1) {
					this.x -= 2;
				}
				rect.x = this.x;
				rect.y = this.y;
				break;
				
		}
	}
	public double DirectionX(int angle) {
		return Math.cos((Math.PI/180)*angle);
	}
	public double DirectionY(int degree) {
		return Math.sin((Math.PI/180)*angle);
	}
}
