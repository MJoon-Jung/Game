package game;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class App extends JFrame{
	private Image bufferImage;
	private Graphics screenGraphic;

	private Image mainScreen = new ImageIcon("src\\image\\example2.png").getImage();
	private Image loadingScreen = new ImageIcon("src\\image\\loading2.png").getImage();
	private Image gameScreen = new ImageIcon("src\\image\\background2.png").getImage();

	private boolean isMainScreen,isloadingScreen,isGameScreen;

	public static Game game = new Game();
	public App() {
		setTitle("Shooting");
		setUndecorated(true);
		setSize(Main.SCREEN_WIDTH,Main.SCREEN_HEIGHT);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(null);
		setVisible(true);

		init();
		addKeyListener(new keyListener());
	}
	private void init() {
		isMainScreen  = true;
		isloadingScreen = false;
		isGameScreen = false;

	}
	private void gameStart() {
		isMainScreen = false;
		isloadingScreen = true;

		Timer loadingTimer = new Timer();
		TimerTask loadingTask = new TimerTask() {

			@Override
			public void run() {
				isloadingScreen = false;
				isGameScreen = true;
				game.start();
			}

		};
		loadingTimer.schedule(loadingTask, 3000);

	}
	public void paint(Graphics g) {
		bufferImage = createImage(Main.SCREEN_WIDTH,Main.SCREEN_HEIGHT);
		screenGraphic = bufferImage.getGraphics();
		screenDraw(screenGraphic);
		g.drawImage(bufferImage, 0, 0, null);
	}
	public void screenDraw(Graphics g) {
		if(isMainScreen) {			
			g.drawImage(mainScreen, 0, 0, null);
		}
		if(isloadingScreen) {
			g.drawImage(loadingScreen, 0, 0, null);
		}
		if(isGameScreen) {
			g.drawImage(gameScreen, 0, 0, null);
			game.gameDraw(g);
		}
		this.repaint();
	}
	class keyListener extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_UP:
				game.setUp(true);
				break;
			case KeyEvent.VK_DOWN:
				game.setDown(true);
				break;
			case KeyEvent.VK_RIGHT:
				game.setRight(true);
				break;
			case KeyEvent.VK_LEFT:
				game.setLeft(true);
				break;
			case KeyEvent.VK_SPACE:
				game.setShooting(true);
				break;
			case KeyEvent.VK_ENTER:
				if(isMainScreen) {
					gameStart();
				}
				break;
			case KeyEvent.VK_ESCAPE:
				System.exit(0);
				break;

			}
		}	
		@Override
		public void keyReleased(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_UP:
				game.setUp(false);
				break;
			case KeyEvent.VK_DOWN:
				game.setDown(false);
				break;
			case KeyEvent.VK_RIGHT:
				game.setRight(false);
				break;
			case KeyEvent.VK_LEFT:
				game.setLeft(false);
				break;
			case KeyEvent.VK_SPACE:
				game.setShooting(false);
				break;
			}
		}
	}
}
