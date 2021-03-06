import java.awt.Image;

import javax.swing.ImageIcon;

public class M_Enemy {
	Image image = new ImageIcon("D:\\programes\\java_workspace\\ShottingGame\\src\\image\\items\\boss1.png").getImage();
	int x,y;
	int width = image.getWidth(null);
	int height = image.getHeight(null);
	M_Enemy(int x,int y){
		this.x = x;
		this.y =y ;
	}
	public void move() {
		if(y <100) {
			if(x < 100) {
				this.x += 3;
			}
			else if(x > 203) {
				this.x -= 3;
			}
			this.y += 3;
		}
	}
}
