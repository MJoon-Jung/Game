package game;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Game extends Thread{
	private int delay = 20;
	private long pretime;
	private int cnt;
	
	private Image player = new ImageIcon("src\\image\\items\\player3.png").getImage();
	
	private int playerX,playerY;
	private int playerVX,playerVY;
	private Rectangle rect;
	private int playerWidth = player.getWidth(null);
	private int playerHeight = player.getHeight(null);
	private int playerSpeed = 5;
	private int attack = 1;
	
	private boolean up,down,right,left,shooting;
	
	private PlayerAttack playerAttack;
	private ArrayList<PlayerAttack>playerAttackList = new ArrayList<PlayerAttack>();
	
	private Boss boss = new Boss(Main.SCREEN_WIDTH /2 - 75, 5, 1, 100);
	private BossBullet bossBullet;
	private ArrayList<BossBullet> bossBulletList = new ArrayList<>();

	@Override
	public void run() {
		cnt = 0;
		playerX = Main.SCREEN_WIDTH / 2 - playerWidth / 2;
		playerY = Main.SCREEN_HEIGHT - playerHeight * 2;
		while(true) {
			pretime = System.currentTimeMillis();
			if(System.currentTimeMillis() - pretime < delay) {
				try {
					Thread.sleep(delay - System.currentTimeMillis() + pretime);
					keyProcess();
					playerAttackProcess();
					BossAttackProcess();
					BossFire();
					cnt++;
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
		}
	}
	public void gameDraw(Graphics g) {
		playerDraw(g);
		BossDraw(g);
	}
	private void keyProcess() {
		if(up && playerY - playerSpeed > 0) { 
			playerY -= playerSpeed;
			rect.x -= playerSpeed;
		}
		if(down && playerY + playerSpeed + playerHeight < Main.SCREEN_HEIGHT) {
			playerY += playerSpeed;
			rect.y += playerSpeed;
		}
		if(left && playerX - playerSpeed > 0) {
			playerX -= playerSpeed;
			rect.x -= playerSpeed;
		}
		if(right &&playerX + playerWidth + playerSpeed < Main.SCREEN_WIDTH) {
			playerX += playerSpeed;
			rect.x += playerSpeed;
		}
		
		if(shooting && cnt % 5 == 0) {
			playerAttack = new PlayerAttack(playerX-6,playerY-10);
			playerAttackList.add(playerAttack);
		}
		
		if(playerX > boss.x && playerX < boss.x + boss.width && playerY < boss.y  + boss.height && playerY > boss.y) {
			System.out.println("Player death");
		}
	}
	private void playerAttackProcess() {
		for(int i = 0; i < playerAttackList.size(); i++) {
			playerAttack = playerAttackList.get(i);
			playerAttack.fire();
			
			if(playerAttack.y + playerAttack.height < 0) {
				playerAttackList.remove(playerAttack);
			}
			if(playerAttack.x > boss.x && playerAttack.x < boss.x + boss.width && playerAttack.y < boss.y + boss.height - 15 && playerAttack.y > boss.y) {
				playerAttackList.remove(playerAttack);
				boss.hp--;
				
				if(boss.hp <= 0) {
					BossDie();
				}
			}
		}
	}
	public void playerDraw(Graphics g) {
		g.drawImage(player, playerX, playerY, null);
		rect = new Rectangle(playerX + playerWidth / 2 - 10, playerY + playerHeight / 2 - 10, 10, 10);
		
		for(int i = 0; i < playerAttackList.size(); i++) {
			playerAttack = playerAttackList.get(i);
			g.drawImage(playerAttack.image, playerAttack.x, playerAttack.y, null);
		}
		
	}
	public void BossDraw(Graphics g) {
		g.drawImage(boss.image, boss.x, boss.y, null);
		
		for(int i = 0; i < bossBulletList.size(); i++) {
			bossBullet = bossBulletList.get(i);
			g.drawImage(bossBullet.image, bossBullet.x, bossBullet.y, null);
		}
	}
	public void BossAttackProcess() {
		if(cnt % 250 == 0) {
//			for(int j = 0; j < 4; j++) {				
				for (int i = 0;i<360;i+=5) {
					bossBullet = new BossBullet(boss.x + boss.width / 2 - 5,boss.y + boss.height / 2,1,i);
					bossBulletList.add(bossBullet);
					bossBullet = new BossBullet(boss.x + boss.width / 2 - 5,boss.y + boss.height / 2,1,i+4);
					bossBulletList.add(bossBullet);
					bossBullet = new BossBullet(boss.x + boss.width / 2 - 5,boss.y + boss.height / 2,1,i+2);
					bossBulletList.add(bossBullet);
				}
//			}
		}
//		
//		if(cnt % 500 == 0) {
//			for(int i = 0; i < 200; i++) {
//				bossBullet = new BossBullet((int)(Math.random() * (Main.SCREEN_WIDTH - 100)+ 50), (int)(Math.random() * 30) + boss.y + boss.height, 2, 0);
//				bossBulletList.add(bossBullet);
//			}
//		}
//		
		if(cnt % 200 == 199) {		
				for(int i = 0; i <= Main.SCREEN_WIDTH; i += 57) {
					bossBullet = new BossBullet(i, 0, 3, 0);
					bossBulletList.add(bossBullet);
					bossBullet = new BossBullet(i, Main.SCREEN_HEIGHT - 30, 3 , 0);
					bossBulletList.add(bossBullet);
				}
				for(int i = 0; i <= Main.SCREEN_HEIGHT; i += 55) {
					bossBullet = new BossBullet(0, i, 3, 1);
					bossBulletList.add(bossBullet);
					bossBullet = new BossBullet(Main.SCREEN_WIDTH - 30, i, 3 , 1);
					bossBulletList.add(bossBullet);
				}
			}
	}
	public void BossFire() {
		for(int i = 0; i < bossBulletList.size(); i++) {
			bossBullet = bossBulletList.get(i);
			bossBullet.fire();
			
			if(bossBullet.x < 0 || bossBullet.x > Main.SCREEN_WIDTH || bossBullet.y < 0 || bossBullet.y > Main.SCREEN_HEIGHT) {
				bossBulletList.remove(bossBullet);
			}
			if(rect.intersects(bossBullet.rect)) {
				bossBulletList.remove(i);
			}
		}
	}
	public void BossDie() {
		System.out.println("boss dead");
	}
	public void setUp(boolean up) {
		this.up = up;
	}
	public void setDown(boolean down) {
		this.down = down;
	}
	public void setRight(boolean right) {
		this.right = right;
	}
	public void setLeft(boolean left) {
		this.left = left;
	}
	public void setShooting(boolean shooting) {
		this.shooting = shooting;
	}
}
