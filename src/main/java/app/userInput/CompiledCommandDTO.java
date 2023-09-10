package app.userInput;

import app.action.CompiledAction;
import app.userInput.command.Command;
import app.userInput.command.game.GameCommand;
import app.userInput.command.meta.MetaCommand;

public class CompiledCommandDTO {
	private Command.types type;
	private CompiledAction compiledAction;
	private MetaCommand metaCommand;
	
	public Command.types getType() {
		return type;
	}
	public void setType(Command.types type) {
		this.type = type;
	}
	public CompiledAction getCompiledAction() {
		return compiledAction;
	}
	public void setCompiledAction(CompiledAction gameCommand) {
		this.compiledAction = gameCommand;
	}
	public MetaCommand getMetaCommand() {
		return metaCommand;
	}
	public void setMetaCommand(MetaCommand metaCommand) {
		this.metaCommand = metaCommand;
	}

}
