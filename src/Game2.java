import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Game2 extends Thread{
	private int delay = 20;
	private long pretime;
	private int cnt;
	
	private Image player = new ImageIcon("D:\\programes\\java_workspace\\ShottingGame\\src\\image\\items\\player3.png").getImage();
	
	private int playerX,playerY;
	private int playerVX,playerVY;
	private int playerWidth = player.getWidth(null);
	private int playerHeight = player.getHeight(null);
	private int playerSpeed = 10;
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
					enemyAppearProcess();
					enemyMoveProcess();
//					enemyAttackProcess();
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
			for(int j = 0; j < enemyList.size(); j++) {
				enemy = enemyList.get(j);
				if(playerAttack.y < enemy.y + enemy.height && playerAttack.y > enemy.y && playerAttack.x > enemy.x && playerAttack.x < enemy.x + enemy.width) {
					enemyList.remove(enemy);
				}
			}
			
			if(playerAttack.y < -playerAttack.height) playerAttackList.remove(playerAttack);
		}
	}
	public void gameDraw(Graphics g) {
		playerDraw(g);
		enemyDraw(g);
	}
	public void playerDraw(Graphics g) {
		g.drawImage(player, playerX, playerY, null);
		
		for(int i = 0; i < playerAttackList.size(); i++) {
			playerAttack = playerAttackList.get(i);
			g.drawImage(playerAttack.image, playerAttack.x, playerAttack.y, null);
		}
	}
	private void enemyAppearProcess() {
		if(cnt % 200 == 0) {			
			enemy = new Enemy((int)(Math.random()*Main.SCREEN_WIDTH / 3) , -10);
			enemyList.add(enemy);
			enemy = new Enemy((int)(Math.random()*Main.SCREEN_WIDTH * 2 / 3)+Main.SCREEN_WIDTH/3, -10);
			enemyList.add(enemy);
		}
		if(cnt % 500 == 0) {
			Menemy = new M_Enemy(-20,10);
			MenemyList.add(Menemy);
			Menemy = new M_Enemy(Main.SCREEN_WIDTH+20,10);
			MenemyList.add(Menemy);
		}
	}
	private void enemyMoveProcess() {
		for(int i = 0; i < enemyList.size(); i++) {
			enemy = enemyList.get(i);
			enemy.move();
			if(enemy.y >= 100) { 
				enemyAttackProcess();
			}
		}
//		for(int i = 0; i < MenemyList.size(); i++) {
//			Menemy = MenemyList.get(i);
//			Menemy.move();
//		}
	}
	public void enemyDraw(Graphics g) {
		for(int i = 0; i < enemyList.size(); i++) {
			enemy = enemyList.get(i);
			g.drawImage(enemy.image, enemy.x, enemy.y, null);
		}
//		for(int i = 0; i < MenemyList.size(); i++) {
//			
//			Menemy = MenemyList.get(i);
//			g.drawImage(Menemy.image, Menemy.x, Menemy.y, null);
//		}
        for (int i = 0; i < enemyAttackList.size(); i++) {
            enemyAttack = enemyAttackList.get(i);
            g.drawImage(enemyAttack.image, enemyAttack.x, enemyAttack.y, null);
        }
	}
	public void enemyAttackProcess() {
		int dx,dy;
		if (cnt % 100 == 0) {		
			enemyAttack = new EnemyAttack(enemy.x-3 , enemy.y+4);
		 	enemyAttackList.add(enemyAttack);
			playerVX = playerX;
			playerVY = playerY;
        }
        for (int i = 0; i < enemyAttackList.size(); i++) {
            enemyAttack = enemyAttackList.get(i);
            enemyAttack.fire(playerVX,playerVY);
            
            if(enemyAttack.x > playerX && enemyAttack.x < playerX + playerWidth && enemyAttack.y > playerY && enemyAttack.y < playerY + playerHeight) {
            	playerX = Main.SCREEN_WIDTH /2 - playerWidth;
            	playerY = Main.SCREEN_HEIGHT / 2 - playerHeight;
            }
            
            if (enemyAttack.x > playerX & enemyAttack.x < playerX + playerWidth && enemyAttack.y > playerY && enemyAttack.y < playerY + playerHeight) {
//                playerHp -= enemyAttack.attack;
                enemyAttackList.remove(enemyAttack);
            }
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
