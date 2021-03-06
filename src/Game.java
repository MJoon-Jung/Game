import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Game extends Thread{
	private int delay = 20;
	private long pretime;
	private int cnt;
	
	private Image player = new ImageIcon("D:\\programes\\java_workspace\\ShottingGame\\src\\image\\items\\player3.png").getImage();
	
	private int playerX,playerY;
	private int playerVX,playerVY;
	private int playerWidth = player.getWidth(null);
	private int playerHeight = player.getHeight(null);
	private int playerSpeed = 5;
	private int playerHP = 30;
	
	private boolean up,down,right,left,shooting;
	
	private PlayerAttack playerAttack;
	private ArrayList<PlayerAttack>playerAttackList = new ArrayList<PlayerAttack>();
	
	private Enemy enemy;
	private ArrayList<Enemy>enemyList = new ArrayList<Enemy>();
//	private int EnemyImageIdx = (int)(Math.random() * enemy.image.length);
	
	private boolean isFireAllow = false;
	private EnemyAttack enemyAttack;
	private ArrayList <EnemyAttack> enemyAttackList = new ArrayList<EnemyAttack>();
	
	private M_Enemy Menemy;
	private ArrayList<M_Enemy>MenemyList = new ArrayList<M_Enemy>();
	
	private Image boss_image = new ImageIcon("D:\\programes\\java_workspace\\ShottingGame\\src\\image\\items\\boss1.png").getImage();
	private int boss_width = boss_image.getWidth(null);
	private int boss_height = boss_image.getHeight(null);
	private BossBullet bossBullet;
	private Boss boss = new Boss(Main.SCREEN_WIDTH / 2 - boss_width / 2, 20);
	private ArrayList<BossBullet>BossBulletList = new ArrayList<>();
	private int degree = 130;
	private int degree2 = 0;
	private boolean DirDegree = true;
	private boolean DirDegree2 = true;
	
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
					BossAttackProcess();
					playerAttackProcess();
					cnt++;
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
		}
	}
	private void keyProcess() {
		if(up && playerY - playerSpeed > 0) playerY -= playerSpeed;
		if(down && playerY + playerSpeed + playerHeight < Main.SCREEN_HEIGHT) playerY += playerSpeed;
		if(left && playerX - playerSpeed > 0) playerX -= playerSpeed;
		if(right &&playerX + playerWidth + playerSpeed < Main.SCREEN_WIDTH) playerX += playerSpeed;
		
		if(shooting && cnt % 5 == 0) {
			playerAttack = new PlayerAttack(playerX-6,playerY-10);
			playerAttackList.add(playerAttack);
		}
	}
	private void playerAttackProcess() {
		for(int i = 0; i < playerAttackList.size(); i++) {
			playerAttack = playerAttackList.get(i);
			playerAttack.fire();
		}
	}
	public void gameDraw(Graphics g) {
		playerDraw(g);
//		enemyDraw(g);
		BossDraw(g);
	}
	public void playerDraw(Graphics g) {
		g.drawImage(player, playerX, playerY, null);
		
		for(int i = 0; i < playerAttackList.size(); i++) {
			playerAttack = playerAttackList.get(i);
			g.drawImage(playerAttack.image, playerAttack.x, playerAttack.y, null);
		}
	}
//	private void BossAppearProcess() {
//		
//	}
	public void BossDraw(Graphics g) {
		g.drawImage(boss_image, boss.x, boss.y, null);
		
		for(int i = 0; i < BossBulletList.size(); i++) {
			bossBullet = BossBulletList.get(i);
			g.drawImage(bossBullet.image, bossBullet.x, bossBullet.y, null);
		}
	}
	public void BossAttackProcess() {
		/*
		 * boss.mode 1,2,3,4 존재
		 * 1. 
		 * 2. cos 각도당 cnt 텀을 두고 발사
		 * 3.
		 * 4.
		 */
		
		
//		if(cnt % 20 == 0) {
//			for (int i = 0;i<360;i+=5) {
//				bossBullet = new BossBullet(boss.x + boss_width/2 , boss.y + boss_height + 10,i);
//				BossBulletList.add(bossBullet);
//			}
//		}
		if(cnt % 90 == 0) {
			for (int i = 0;i<360;i+=5) {
				bossBullet = new BossBullet(boss.x + boss_width/2 , boss.y + boss_height + 10,i);
				BossBulletList.add(bossBullet);
				bossBullet = new BossBullet(boss.x + boss_width/2 , boss.y + boss_height + 10,i+4);
				BossBulletList.add(bossBullet);
				bossBullet = new BossBullet(boss.x + boss_width/2 , boss.y + boss_height + 10,i+2);
				BossBulletList.add(bossBullet);
			}
		}
		/*if(cnt % Integer.MAX_VALUE == 0) {			
//			if(cnt % 6 == 5) {
////			System.out.println(degree);
//				bossBullet = new BossBullet(boss.x + boss_width/2 , boss.y + boss_height + 10,degree2);
//				BossBulletList.add(bossBullet);
//				if(DirDegree2) degree2 -= 5;
//				else if(!DirDegree2) degree2 += 5;
//				
//				if(degree2 < 58) DirDegree2 =false;
//				else if(degree2 > 138) DirDegree2 =true;
//			}
			if(cnt % 6 == 0) {
//			System.out.println(degree);
				bossBullet = new BossBullet(boss.x + boss_width/2 , boss.y + boss_height + 10,degree);
				BossBulletList.add(bossBullet);
				if(DirDegree) degree -= 5;
				else if(!DirDegree) degree += 5;
				
				if(degree < 50) DirDegree =false;
				else if(degree > 130) DirDegree =true;
			}
		}*/
		for(int i = 0; i < BossBulletList.size(); i++) {
			bossBullet = BossBulletList.get(i);
			bossBullet.fire();
		}
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
