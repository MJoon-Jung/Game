import java.awt.Image;

import javax.swing.ImageIcon;

public class EnemyAttack {
	Image image = new ImageIcon("D:\\programes\\java_workspace\\ShottingGame\\src\\image\\items\\bullets\\enemy_bullet.png").getImage();
	int x,y;
	int c,d;
	int width = image.getWidth(null);
	int height = image.getHeight(null);
	int attack = 5;
	
	public EnemyAttack(int x,int y) {
		this.x = x;
		this.y = y;
		c = this.x;
		d = this.y;
	}
	public void fire(int xs,int ys) {
		int px = xs - c;	
		int py = ys- d;
		this.x += 4 * px / py;
		if(py < 0) {			
			this.y -= 4;
		}
		if(py > 0) {
			this.y += 4;
		}
	}
}
