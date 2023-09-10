package app.userInput.command.game;

import app.action.CompiledAction;
import app.xutility.exceptions.InvalidUserInput;

public class GameCommand {
	
	public static enum types {
		BASIC_ACTION,
		ACTION_SCRIPT,
		COMPLEX_ACTION_SCRIPT
	}

	
	public static GameCommand.types identifyType(String input) {
		if (input.contains("(") || input.contains(")")) {
			return GameCommand.types.COMPLEX_ACTION_SCRIPT;
		} else if (input.contains("-")) {
			return GameCommand.types.ACTION_SCRIPT;
		} else {
			return GameCommand.types.BASIC_ACTION;
		}
	}
	
	public static CompiledAction process(String input) throws InvalidUserInput {
		GameCommand.types gameCommandInputType = GameCommand.identifyType(input);
		GameCommandValidator.validate(input, gameCommandInputType);
		return GameCommandCompiler.compile(input, gameCommandInputType);
	}
	
}
