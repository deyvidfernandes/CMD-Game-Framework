package game;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) throws Exception {
		
		Scanner keyboard = new Scanner(System.in);
		
		while(true) {
			Game.draw();
			String entry = keyboard.nextLine();
			Game.updateLogic(entry);
		}
	}
	
	
}
