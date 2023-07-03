import java.util.Arrays;
import java.util.Scanner;

public class Main {

	
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		Map map1 = new Map(10);
		Character player = new Character(map1, "player", 1, 1);
		
		boolean result = true;
		
		while(true) {
	        if (!result) {
	        	try {
	        	System.out.println(map1.getTile(1, 0).getContent().getName() + "\n");
	        	} catch(java.lang.NullPointerException error) {
		        	System.out.println("aahahah" + "\n");

	    		}
	        }
	        generateFrame(map1.getSize() * 2, 1);
			System.out.println(map1.draw());
			generateFrame(map1.getSize() * 2, 2);
			System.out.println("\n" + Arrays.toString(player.getPos()));
			breakLines(36 - map1.getSize());
			result = player.walk(keyboard.nextLine());
			System.out.println("½");
			
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
