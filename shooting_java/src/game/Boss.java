package game;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Boss {
	int x,y;
	int width,height;
	int type;
	int hp;
	Image image;
	
	public Boss(int x,int y,int type, int hp) {
		switch(type) {
			case 1:
				this.image = new ImageIcon("src/image/boss.png").getImage();
				this.x =x ;
				this.y =y;
				this.type= type;
				this.hp = hp;
				this.width = image.getWidth(null);
				this.height= image.getHeight(null);
				break;
		}
	}
}
