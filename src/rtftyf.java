import java.util.*;
public class rtftyf {
	public static void main(String[] args) {
		for(int i = 120;i>=60;i--) {
			System.out.println("X: "+DirectionX(i)+", Y:"+DirectionY(i));
		}
		
	}
	public static double DirectionX(int degree) {
		return Math.cos((Math.PI/180)*degree);
	}
	public static double DirectionY(int degree) {
		return Math.sin((Math.PI/180)*degree);
	}
}
