package game;

import java.util.Arrays;
import java.util.Scanner;

import xutility.exceptions.InvalidUserInput;

public class Game {
	
	static private  Map map1 = new Map(16);
	static private  Player player = new Player(map1, 1, 1);
	static private  int counter = 0;
	static private  boolean action = false;
	static private String entry = "ms1";
	static private boolean turnAuthorized = true;
	static private String userErrorMessage;
	static private UserInput userInput;

	
	static public void draw() {
        generateFrame(map1.getSize() * 2, 1);
		System.out.println(map1.draw());
		generateFrame(map1.getSize() * 2, 2);
		System.out.println("\n" + Arrays.toString(player.getPos()));
		breakLines(36 - map1.getSize());
	}
	
	static private String generateErrorMessage(Exception exc) {
		return "foo";
	}
	
	static public void validateConvertUserInput(String input) {
		try {
			userInput = new UserInput(input);
		} catch (InvalidUserInput exc) {
			turnAuthorized = false;
			userErrorMessage = generateErrorMessage(exc);
		}
	}	
	
	static public void updateLogic(String input) {
		validateConvertUserInput(input);
		runTurn(userInput);
	}
	
	static public void runTurn(UserInput userInput) {
//		if (!action) {
//			actInaRow(player, entry);
//			action = true;
//		} else {
//			actInaRow(player, entry);
//		}
		player.act(userInput);
	}
	
//	static void actInaRow(Player player, String entry) {
//		int turnsToRun = Integer.parseInt(String.valueOf(entry.charAt(2)));
//		entry = entry.substring(0, entry.length()-1);
//		
//		if (counter < turnsToRun) {
//			counter++;
//			if (!player.act(entry)) {
//				counter = 0;
//				action = false;
//			} else {
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		} else {
//			counter = 0;
//			action = false;
//		}
//		
//	}

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
