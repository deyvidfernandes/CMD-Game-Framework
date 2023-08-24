package app.input;

import java.util.ArrayList;
import java.util.Arrays;

import app.Game;
import app.xutility.Xstring;
import app.xutility.Xarray;
import app.xutility.Xenum;
import app.xutility.exceptions.InvalidUserInput;

public class ActionInput {
	
	enum actionInputTypes {
		BASIC_ACTION,
		ACTION_SCRIPT,
		COMPLEX_ACTION_SCRIPT
	}
	
	private static boolean validateBasicActionInput(String input) throws InvalidUserInput {
		
		try {
			String char1 = String.valueOf(input.charAt(0));
			if (!Xenum.containsStringElement(Game.actionInputs.values(), char1)) {
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
	
	private static actionInputTypes identifyActionInputType(String input) {
		if (input.contains("(") || input.contains(")")) {
			return actionInputTypes.COMPLEX_ACTION_SCRIPT;
		} else if (input.contains("-")) {
			return actionInputTypes.ACTION_SCRIPT;
		} else {
			return actionInputTypes.BASIC_ACTION;
		}
	}
	
	private static BasicAction processBasicAction(String input) {
		String action = String.valueOf(input.charAt(0));
		String direction = String.valueOf(input.charAt(1));
		
		return new BasicAction(direction, action);
	}
	
	private static CompiledAction compileBasicAction(String input) {
		CompiledAction compiledAction;
		String action = String.valueOf(input.charAt(0));
		String direction = String.valueOf(input.charAt(1));
		
		if (input.length() < 3) {
			compiledAction = new CompiledAction(new BasicAction(direction, action));
		} else {
			compiledAction = compileActionScript(multiplyBasicAction(input));
			
		}
		
		return compiledAction;
	}
	
	public static ArrayList<String> multiplyActionsInScript(String[] basicActionArray) {
		ArrayList<String> basicActionList = new ArrayList<String>(Arrays.asList(basicActionArray));
		for (int index = 0; index < basicActionList.size(); index++) {
			String basicAction = basicActionList.get(index);
			
			if (basicAction.length() > 2) {
				basicActionList.remove(index);
				basicActionList.addAll(index, multiplyBasicAction(basicAction));
			}
			
		}
		return basicActionList;
	}
	
	public static ArrayList<String> multiplyBasicAction(String basicActionString) {
		ArrayList<String> productActionScript = new ArrayList<String>(); 
		int repeater = Integer.parseInt(basicActionString.substring(2));
		String basicActionBody = basicActionString.substring(0, 2);
		for (int i = 0; i < repeater; i++) {
			productActionScript.add(basicActionBody);
		}
		return productActionScript;
	}
	
	private static CompiledAction compileActionScript(String actionScript) {
		ArrayList<BasicAction> compiledBasicActionList = new ArrayList<BasicAction>();
		String[] basicActionArray = actionScript.split("-");
		basicActionArray = Xarray.trimStringArray(basicActionArray);
		ArrayList<String> basicActionList = multiplyActionsInScript(basicActionArray);
		for (String basicActionString : basicActionList) {
			BasicAction basicAction = processBasicAction(basicActionString);
			compiledBasicActionList.add(basicAction);
		}

		return new CompiledAction(compiledBasicActionList);
	}
	
	private static CompiledAction compileActionScript(ArrayList<String> basicActionList) {
		ArrayList<BasicAction> compiledBasicActionList = new ArrayList<BasicAction>();
		for (String basicActionString : basicActionList) {
			BasicAction basicAction = processBasicAction(basicActionString);
			compiledBasicActionList.add(basicAction);
		}
		
		return new CompiledAction(compiledBasicActionList);
	}
	
	public static String translateComplexActionScript_to_actionScript(String complexActionScript) {
//		2(ws2-hd3-wa3)-9(hs-hd2-wd-3(wd1-wa2)-hw2)
		String compiledActionScript_inProcess = new String(complexActionScript);
		
		while(compiledActionScript_inProcess.contains(")")) {
			int scriptRepeater;
			String scriptRepeaterWithoutComma;
			int actionGroupEnd = compiledActionScript_inProcess.indexOf(")");
			String beginningToActionGroupEnd = compiledActionScript_inProcess.substring(0, actionGroupEnd);
			int actionGroupStart = beginningToActionGroupEnd.lastIndexOf("(");
			String actionInside = beginningToActionGroupEnd.substring(actionGroupStart + 1);
			String scriptRepeaterWithComma = Xstring.firstMatchOf_regex(beginningToActionGroupEnd, "\\d+\\(");
			
			if (beginningToActionGroupEnd.lastIndexOf(scriptRepeaterWithComma) + scriptRepeaterWithComma.length() - 1 < actionGroupStart) {
				scriptRepeaterWithComma = "";
			}

			if (scriptRepeaterWithComma.equals("")) {
				scriptRepeater = 1; 
				scriptRepeaterWithoutComma = "";
			} else {
				scriptRepeaterWithoutComma = scriptRepeaterWithComma.substring(0, scriptRepeaterWithComma.length() - 1);
				scriptRepeater = Integer.parseInt(scriptRepeaterWithoutComma);
			}
			System.out.println("-" + scriptRepeater);
			System.out.println();
			String compiledActionGroup = "";
			for (int i = 0; i < scriptRepeater; i++) {
				compiledActionGroup += actionInside;
				System.out.println("-" + i);
				if (!(i == scriptRepeater - 1)) {
					compiledActionGroup += "-";
				}
			}
			
			String actionGroupEnd_left = compiledActionScript_inProcess.substring(0, actionGroupStart - scriptRepeaterWithoutComma.length());
			String actionGroupEnd_right = compiledActionScript_inProcess.substring(actionGroupEnd + 1);
			compiledActionScript_inProcess = actionGroupEnd_left + compiledActionGroup + actionGroupEnd_right;
		}
		return compiledActionScript_inProcess;
	}
	
	public static CompiledAction compile(String input) throws InvalidUserInput {
		actionInputTypes actionInputType = identifyActionInputType(input);
		switch(actionInputType) {
		case BASIC_ACTION:
			validateBasicActionInput(input);
			return compileBasicAction(input);
		case ACTION_SCRIPT:
			validateActionScriptInput(input);
			return compileActionScript(input);
		case COMPLEX_ACTION_SCRIPT:
			validateActionScriptInput(input);
			String actionScript = translateComplexActionScript_to_actionScript(input);
			return compileActionScript(actionScript);
		default:
			throw new Error();
		}
	}
	
}
