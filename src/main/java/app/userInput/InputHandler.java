package app.userInput;
import app.userInput.command.Command;
import app.userInput.command.game.GameCommand;
import app.xutility.Xstring;
import app.xutility.exceptions.InvalidUserInput;

public class InputHandler {
	
	public static CompiledCommandDTO handle(String input) throws InvalidUserInput {
		Command.types commandType = identifyCommandType(input);
		CompiledCommandDTO compiledCommand = new CompiledCommandDTO();

		if (commandType.equals(Command.types.META_COMMAND)) {
			
		} else if (commandType.equals(Command.types.GAME_COMMAND)) {
			
			compiledCommand.setType(commandType);
			compiledCommand.setCompiledAction(GameCommand.process(input));
		}
		
		return compiledCommand;
	}
	
	public static Command.types identifyCommandType(String input) {
		if (Xstring.regexFind(input, "^--")) {
			return Command.types.META_COMMAND;
		} else {
			return Command.types.GAME_COMMAND;
		}
	}
	
}
