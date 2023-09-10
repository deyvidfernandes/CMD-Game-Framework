package app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import app.action.CloseAction;
import app.action.CompiledAction;
import app.userInput.ActionInput;
import app.userInput.CompiledCommandDTO;
import app.userInput.InputHandler;
import app.userInput.command.Command;
import app.userInput.command.meta.MetaCommand;
import app.xutility.Xstring;
import app.xutility.exceptions.InvalidUserInput;
import app.xutility.exceptions.Printable;

public class Game {
	
	public enum directionInputs { A, S, W, D }
	
	public enum CloseActionInputs { W, H }
	public enum RangedActionInputs { INSPECT, S }
	
	
	static private GameMap map1 = new GameMap(16);
	static private Player player = new Player(map1, 1, 1);
	static Scanner keyboard = new Scanner(System.in);
	static private Simulation currentSimulation;
	static private int dontBreakLines = 0;
	static private boolean inSimulation = false;
	static private boolean turnAuthorized = true;
	static public boolean lastRenderInSimulation = false;
	static private String userErrorMessage;
	
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
	
	static public void updateLogic() throws InterruptedException {		
		if (inSimulation) {
	        currentSimulation.runTurn();
	    } else {
			try {
				String input = keyboard.nextLine();
		        CompiledCommandDTO command = InputHandler.handle(input);
		        executeCommand(command);
			} catch (InvalidUserInput exc) {
				userErrorMessage = generateErrorMessage(exc);
			}
	    }
	}
	
	private static void executeCommand(CompiledCommandDTO command) {
	    Command.types commandType = command.getType();
		
		if (commandType.equals(Command.types.GAME_COMMAND)) {
			CompiledAction gameCommand = command.getCompiledAction();
			
			if (gameCommand.getTurnsToRun() > 1) {
				startNewSimulation(gameCommand);
			}
			
	        runTurn(gameCommand);
	    } else if (commandType.equals(Command.types.META_COMMAND)) {
	        applyMetaCommand(command.getMetaCommand());
	    }
	}

	private static void applyMetaCommand(MetaCommand metaCommand) {
		// TODO Auto-generated method stub
		
	}

	static public void runTurn(CompiledAction compiledActionInput) {
		player.act(compiledActionInput.getAction(0));
	}
	
	static public void startNewSimulation(CompiledAction compiledAction) {
		currentSimulation = new Simulation(compiledAction);
		currentSimulation.start();
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
