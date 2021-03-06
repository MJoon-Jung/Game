import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Enemy {
	Image image = new ImageIcon("D:\\programes\\java_workspace\\ShottingGame\\src\\image\\items\\monster1_1.png").getImage();
//	Image [] image = new Image [4];
//	{
//		image[0] = new ImageIcon("D:\\programes\\java_workspace\\ShottingGame\\src\\image\\items\\monster1_1.png").getImage();
//		image[1] = new ImageIcon("D:\\programes\\java_workspace\\ShottingGame\\src\\image\\items\\monster2_2.png").getImage();
//		image[2] = new ImageIcon("D:\\programes\\java_workspace\\ShottingGame\\src\\image\\items\\monster3_3.png").getImage();
//		image[3] = new ImageIcon("D:\\programes\\java_workspace\\ShottingGame\\src\\image\\items\\monster4_4.png").getImage();
//	}
	int x,y;
	int c,d;
	int width = image.getWidth(null);
	int height = image.getHeight(null);
	
//	int width = image[0].getWidth(null);
//	int height = image[0].getHeight(null);
	int hp = 10;
	
	public Enemy(int x,int y){
		this.x = x;
		this.y = y;
		c = this.x;
		d = this.y;
	}
	public void move() {
		if(this.y < 100) {
			this.y += 3;
			if(this.x < Main.SCREEN_WIDTH / 3) {
				this.x += 2;
			}
			if(this.x > Main.SCREEN_WIDTH * 2 / 3) {
				this.x -= 2;
			}
		}
	}
}
