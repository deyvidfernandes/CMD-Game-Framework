package app.userInput.command.game;

import app.Game;
import app.action.CompiledAction;
import app.xutility.Xarray;
import app.xutility.Xenum;
import app.xutility.Xstring;
import app.xutility.exceptions.InvalidUserInput;

public class GameCommandValidator {
	
	private static boolean validateBasicActionInput(String input) throws InvalidUserInput {
		try {
			String char1 = String.valueOf(input.charAt(0));
			if (!Xenum.containsStringElement(Game.CloseActionInputs.values(), char1)) {
				throw new InvalidUserInput(char1, "action", "action-invalid input");
			}
		} catch (IndexOutOfBoundsException exc) {
			throw new InvalidUserInput("none", "action", "action-missing");
		}
		
		try {
			String char2 = String.valueOf(input.charAt(1));
			if (!Xenum.containsStringElement(Game.directionInputs.values(), char2)) {
				throw new InvalidUserInput(char2, "direction", "direction-invalid input");
			}
		} catch (IndexOutOfBoundsException exc) {
			throw new InvalidUserInput("none", "direction", "direction-missing");
		}
		
		if (input.length() > 2) {
			String timesInput = input.substring(2);
			try {
				int timesInput_int = Integer.parseInt(timesInput);
				if (timesInput_int == 0) {
					throw new InvalidUserInput(timesInput, "times", "times-invalid input");
				}
			} catch(NumberFormatException exc) {
				throw new InvalidUserInput(timesInput, "times", "times-invalid input");
			} 
		}
		return true;
	}
	
	private static boolean validateActionScriptInput(String input) throws InvalidUserInput {
		String[] basicActionInputArray;
		String patternForFirstScriptRepeater = "[^-\\(\\d]+\\d*\\(|^0+\\(|-0+\\(";
		
		if ( ( Xstring.countOccurrencesOf(input, "(") - Xstring.countOccurrencesOf(input, ")") ) != 0) {
			throw new InvalidUserInput("syntaxError-parentheses placed wrong");
		}
		
		if ( input.contains("--") ) {
			throw new InvalidUserInput("syntaxError-double separators");
		}
		if (input.contains(")(")) {
			throw new InvalidUserInput("syntaxError-missing separator");
		}
		if ( Xstring.regexFind(input, patternForFirstScriptRepeater)) {
			throw new InvalidUserInput("none", "scriptRepeater", "scriptRepeater-invalid input");
		}

		if ( input.contains("()") ) {
			throw new InvalidUserInput("syntaxError-empty action group");
		}
		
		if ( input.contains("(-") || input.contains("-)")) {
			throw new InvalidUserInput("snytaxError");
		}
		
		basicActionInputArray = input.split("\\)-\\(|\\-\\(|\\)-\\d+\\(|-\\d+\\(|\\d+\\(|\\)-|-|\\)|\\(");
		basicActionInputArray = Xarray.trimStringArray(basicActionInputArray);
		for (String basicActionInput : basicActionInputArray) {
			validateBasicActionInput(basicActionInput);
		}
		
		return true;
	}
	
	public static void validate(String input, GameCommand.types gameCommandInputType) throws InvalidUserInput {
		switch(gameCommandInputType) {
		case BASIC_ACTION:
			validateBasicActionInput(input);
			break;
		case ACTION_SCRIPT:
			validateActionScriptInput(input);
			break;
		case COMPLEX_ACTION_SCRIPT:
			validateActionScriptInput(input);
			break;
		default:
			throw new Error();
		}
	}
	
}
