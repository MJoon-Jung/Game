import javax.swing.JFrame;

public class gameFrame extends JFrame{
	gameFrame(){
		GamePanelv panel = new GamePanelv();
		this.add(new GamePanelv());
		this.setTitle("snake");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		this.pack();
		this.setLocationRelativeTo(null);
	}
}
