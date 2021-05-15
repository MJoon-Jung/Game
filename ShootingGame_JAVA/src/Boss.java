import java.awt.Image;

import javax.swing.ImageIcon;

public class Boss {
	
	int x,y;
	String mode = "1";
	
	//타이머를 써서 bullet 계속 호출 중간중간 텀이 0.2초 
	//그리고 각도 1도씩 틀려짐
	
	public Boss(int x,int y) {
		this.x = x;
		this.y = y;
	}
}
