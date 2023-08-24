package app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import app.input.ActionInput;
import app.input.BasicAction;
import app.input.CompiledAction;
import app.xutility.Xstring;
import app.xutility.exceptions.InvalidUserInput;
import app.xutility.exceptions.Printable;

public class Game {
	
	static public String[] directionValidInputs = {"a", "s", "w", "d"};
	static public String[] actionValidInputs = {"w", "h"};
	
	static private Map map1 = new Map(16);
	static private Player player = new Player(map1, 1, 1);
	static private Scanner keyboard = new Scanner(System.in);
	
	static private Simulation currentSimulation;
	static private int dontBreakLines = 0;
	static private boolean inSimulation = false;
	static private boolean turnAuthorized = true;
	static public boolean lastRenderInSimulation = false;
	static private String userErrorMessage;
	static private CompiledAction compiledActionInput;	
	
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
		if (!lastRenderInSimulation) {
	        printHeader();
			generateFrame(map1.getSize() * 2, 1);
			System.out.println(map1.draw());
			generateFrame(map1.getSize() * 2, 2);
			breakLines(35 - map1.getSize() - dontBreakLines);
			dontBreakLines = 0;
		} else {
			lastRenderInSimulation = false;
		}
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
	
	static private String generateErrorMessage(Printable exc) {
		if (exc instanceof InvalidUserInput) {
			
			switch(exc.getId()) {
			case "direction-missing":
				return "É nescessário inserir um valor para o argumento \"direção\"";
			case "direction-invalid input":
				return "\"" + exc.getInvalidInput() + "\" não é um valor válido para a entrada \"direção\"";
			case "action-missing":
				return "É nescessário inserir um valor para a entrada \"ação\"";
			case "action-invalid input":
				return "\"" + exc.getInvalidInput() + "\" não é um valor válido para a entrada \"ação\"";
			case "times-invalid input":
				return "\"" + exc.getInvalidInput() + "\" não é um valor válido para a entrada \"turnos\"";
			case "syntaxError-parentheses placed wrong":
				return "Há parênteses soltos na entrada";
			case "syntaxError-double separators":
				return "Há separadores duplicados (--) na entrada";
			case "scriptRepeater-invalid input":
				return "Foi inserido um valor inválido no repetidor do grupo de ações";
			case "syntaxError-missing separator":
				return "Você esqueceu de colocar um separador";
			case "syntaxError-empty action group":
				return "Há um grupo de ações vazio";
			default:
				return exc.getId();
			}
			
		}
		return "foo";

	}
	
	static public void validateConvertUserInput(String input) {
//		try {
//			//userInput = new UserInput(input);
//		} catch (InvalidUserInput exc) {
//			turnAuthorized = false;
//			userErrorMessage = generateErrorMessage(exc);
//		}
	}	
	
	static public void processInput() {
		if (!inSimulation) {
			try {
				compiledActionInput = ActionInput.compile(keyboard.nextLine());
				turnAuthorized = true;
				if (compiledActionInput.getTurnsToRun() > 1 && turnAuthorized) {
					startNewSimulation(compiledActionInput.getTurnsToRun());
				}
			} catch (InvalidUserInput exc) {
				turnAuthorized = false;
				userErrorMessage = generateErrorMessage(exc);
			}
		}
	}
	
	static public void updateLogic() throws InterruptedException {		
		if (turnAuthorized && inSimulation) {
			currentSimulation.runTurn(compiledActionInput);
		} else if (turnAuthorized) {
			runTurn(compiledActionInput);
		}
	}
	
	static private void runTurn(CompiledAction compiledActionInput) {
		player.act(compiledActionInput.getAction(0));
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
