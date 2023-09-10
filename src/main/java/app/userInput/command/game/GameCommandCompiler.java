package app.userInput.command.game;

import java.util.ArrayList;
import java.util.Arrays;

import app.action.CloseAction;
import app.action.CompiledAction;
import app.action.UnitaryAction;
import app.xutility.Xarray;
import app.xutility.Xstring;
import app.xutility.exceptions.InvalidUserInput;

public class GameCommandCompiler {
	private static CloseAction processBasicAction(String input) {
		String action = String.valueOf(input.charAt(0));
		String direction = String.valueOf(input.charAt(1));
		
		return new CloseAction(direction, action);
	}
	
	private static CompiledAction compileBasicAction(String input) {
		CompiledAction compiledAction;
		String action = String.valueOf(input.charAt(0));
		String direction = String.valueOf(input.charAt(1));
		
		if (input.length() < 3) {
			compiledAction = new CompiledAction(new CloseAction(direction, action));
		} else {
			compiledAction = compileActionScript(multiplyBasicAction(input));
			
		}
		
		return compiledAction;
	}
	
	private static ArrayList<String> multiplyActionsInScript(String[] basicActionArray) {
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
	
	private static ArrayList<String> multiplyBasicAction(String basicActionString) {
		ArrayList<String> productActionScript = new ArrayList<String>(); 
		int repeater = Integer.parseInt(basicActionString.substring(2));
		String basicActionBody = basicActionString.substring(0, 2);
		for (int i = 0; i < repeater; i++) {
			productActionScript.add(basicActionBody);
		}
		return productActionScript;
	}
	
	private static CompiledAction compileActionScript(String actionScript) {
		ArrayList<UnitaryAction> compiledBasicActionList = new ArrayList<UnitaryAction>();
		String[] basicActionArray = actionScript.split("-");
		basicActionArray = Xarray.trimStringArray(basicActionArray);
		ArrayList<String> basicActionList = multiplyActionsInScript(basicActionArray);
		for (String basicActionString : basicActionList) {
			CloseAction basicAction = processBasicAction(basicActionString);
			compiledBasicActionList.add(basicAction);
		}

		return new CompiledAction(compiledBasicActionList);
	}
	
	private static CompiledAction compileActionScript(ArrayList<String> basicActionList) {
		ArrayList<UnitaryAction> compiledBasicActionList = new ArrayList<UnitaryAction>();
		for (String basicActionString : basicActionList) {
			UnitaryAction basicAction = processBasicAction(basicActionString);
			compiledBasicActionList.add(basicAction);
		}
		
		return new CompiledAction(compiledBasicActionList);
	}
	
	private static String translateComplexActionScript_to_actionScript(String complexActionScript) {
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
	
	public static CompiledAction compile(String input, GameCommand.types gameCommandInputType) throws InvalidUserInput {
		switch(gameCommandInputType) {
		case BASIC_ACTION:
			return compileBasicAction(input);
		case ACTION_SCRIPT:
			return compileActionScript(input);
		case COMPLEX_ACTION_SCRIPT:
			String actionScript = translateComplexActionScript_to_actionScript(input);
			return compileActionScript(actionScript);
		default:
			throw new Error();
		}
	}
}
