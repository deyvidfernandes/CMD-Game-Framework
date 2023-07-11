import java.util.Arrays;
import java.util.Scanner;

public class Main {

	
	public static void main(String[] args) throws InterruptedException {
		Scanner keyboard = new Scanner(System.in);
		Map map1 = new Map(16);
		Player player = new Player(map1, 1, 1);
		int counter = 0;
		boolean action = false;
		String entry = "ms1";
		
		boolean result = true;
		
		while(true) {
	        generateFrame(map1.getSize() * 2, 1);
			System.out.println(map1.draw());
			generateFrame(map1.getSize() * 2, 2);
			System.out.println("\n" + Arrays.toString(player.getPos()));
			breakLines(36 - map1.getSize());
			
			if (!action) {
				entry = keyboard.nextLine();
				actInaRow(counter, action, player, entry);
				action = true;
			} else {
				actInaRow(counter, action, player, entry);
			}
			System.out.println("½");
		}
	}
	
	static void actInaRow(int counter, boolean action, Player player, String entry) throws InterruptedException {
		int char0 = Integer.parseInt(String.valueOf(entry.charAt(2)));
		entry = entry.substring(0, entry.length()-1);
		
		if (counter < char0) {
			counter++;
			player.act(entry);
			Thread.sleep(1000);
		} else {
			counter = 0;
			action = false;
		}
		
	}
	
	static public void breakLines(int num) {
		String product = "";
		for (int l = 0; l <= num + 1; l++) {
			product += "\n";
		}
		System.out.println(product);
	}

	static public void generateFrame(int lenght, int opt) {
		String product = "";
		if (opt == 1) { 
			product += "\t┌";
		} else {
			product += "\t└";
		}
		for (int l = 0; l < lenght + 1; l++) {
			product += "─";
		}
		if (opt == 1) { 
			product += "┐";
		} else {
			product += "┘";
		}
		System.out.println(product);
	}

}
