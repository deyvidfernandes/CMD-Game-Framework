package game;

import java.util.Arrays;
import java.util.Scanner;

import xutility.exceptions.InvalidUserInput;

public class Game {
	
	static private Map map1 = new Map(16);
	static private Player player = new Player(map1, 1, 1);
	static private Scanner keyboard = new Scanner(System.in);
	
	static private Simulation currentSimulation;
	static private int dontBreakLines = 0;
	static private boolean inSimulation = false;
	static private boolean turnAuthorized = true;
	static private String userErrorMessage;
	static private UserInput userInput;	
	
	static protected Player getPlayer() {
		return player;
	}

	static protected void setInSimulation(boolean value) {
		inSimulation = value;
	}

	static protected void addToDontBreakLines(int value) {
		dontBreakLines = dontBreakLines + value;
	}
	
	
	
	static public void draw() {
        printHeader();
		generateFrame(map1.getSize() * 2, 1);
		System.out.println(map1.draw());
		generateFrame(map1.getSize() * 2, 2);
		System.out.println("\n" + Arrays.toString(player.getPos()));
		breakLines(35 - map1.getSize() - dontBreakLines);
		dontBreakLines = 0;
	}
	
	static private void printHeader() {
		if (!turnAuthorized) {
	        printErrorMessage();
		} else {
			System.out.println("\n");
		}
	}
	static private void printErrorMessage() {
		if (!turnAuthorized) {
			System.out.println(userErrorMessage);
			turnAuthorized = true;
		}
	}
	
	static private String generateErrorMessage(xutility.exceptions.Printable exc) {
		if (exc instanceof xutility.exceptions.InvalidUserInput) {
			
			switch(exc.getId()) {
			case "direction-missing":
				return "É nescessário inserir um valor para o argumento \"direção\"";
			case "direction-invalid input":
				return "\"" + exc.getInvalidInput() + "\" não é um valor válido para a entrada \"direção\"";
			case "action-missing":
				return "É nescessário inserir um valor para a entrada \"ação\"";
			case "turns-invalid input":
				return "\"" + exc.getInvalidInput() + "\" não é um valor válido para a entrada \"turnos\"";
			case "overflow":
				return "O formato a entrada deve possuir no máximo 3 caracteres (ação-direção-vezes)";
			}
			
		}
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
	
	static public void processInput() {
		if (!inSimulation) {
			String input = keyboard.nextLine();
			validateConvertUserInput(input);
			
			if (userInput.getTurnsToRun() > 1) {
				startNewSimulation(userInput.getTurnsToRun());
			}
		}
	}
	
	static public void updateLogic() throws InterruptedException {		
		if (turnAuthorized && inSimulation) {
			currentSimulation.runTurn(userInput);
		} else if (turnAuthorized) {
			runTurn(userInput);
		}
	}
	
	static private void runTurn(UserInput userInput) {
		player.act(userInput);
	}
	
	static public void startNewSimulation(int turnsToRun) {
		currentSimulation = new Simulation(turnsToRun);
		currentSimulation.start();
		inSimulation = true;
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
