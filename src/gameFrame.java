import javax.swing.JFrame;

public class gameFrame extends JFrame{
	gameFrame(){
		gamePanel panel = new gamePanel();
		this.add(new gamePanel());
		this.setTitle("snake");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		this.pack();
		this.setLocationRelativeTo(null);
	}
}
