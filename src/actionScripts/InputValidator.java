package actionScripts;

import xutility.exceptions.InvalidUserInput;

public class InputValidator {
	//action-direction-times
	static boolean ValidateBasicAction(String input) throws InvalidUserInput {
			
		if (input.length() > 3) {
			throw new xutility.exceptions.InvalidUserInput("overflow");
		}
	
		//Ternary operations verify if an inputChar is missing in the user input
	
		String actionInput = input.length() > 0 && !String.valueOf(input.charAt(0)).equals(" ") ? String.valueOf(input.charAt(0)) : null;
		String dirInput = input.length() > 1 && !String.valueOf(input.charAt(1)).equals(" ") ? String.valueOf(input.charAt(1)) : null;
		int timesInput;
		//Catch invalid timesInput (not an int)
		try {
			timesInput = input.length() > 2 ? Integer.parseInt(String.valueOf(input.charAt(2))) : 0;
		} catch (NumberFormatException exc) {
			throw new xutility.exceptions.InvalidUserInput(String.valueOf(String.valueOf(input.charAt(2))), "turns", "turns-invalid input");
		}
	
		if (actionInput == null) {	
			throw new xutility.exceptions.InvalidUserInput("none", "action", "action-missing");
		}
		if (dirInput != null) {
			if ( !(dirInput.equals("w") || dirInput.equals("s") || dirInput.equals("d") || dirInput.equals("e") || dirInput.equals("a")) ) {
				throw new xutility.exceptions.InvalidUserInput(dirInput, "direction", "direction-invalid input");
			}
		} else {
			throw new xutility.exceptions.InvalidUserInput("none", "direction", "direction-missing");
		}
		return true;
	}
}
