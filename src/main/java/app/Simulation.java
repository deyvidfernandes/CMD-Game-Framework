package app;

import app.action.CloseAction;
import app.action.CompiledAction;
import app.action.UnitaryAction;

public class Simulation {
	private int length;
	private int turnIndex = 0;
	private CompiledAction actionScript;
	
	Simulation(CompiledAction compiledAction) {
		this.length = compiledAction.getTurnsToRun();
		this.actionScript = compiledAction;
	}

	public void end() {
		Game.setInSimulation(false);
	}
	
	public void runTurn() throws InterruptedException {
		if (this.turnIndex < this.length) {
			correctLineBreaking();
			CompiledAction turnBasicAction = new CompiledAction(actionScript.getAction(turnIndex));
			this.turnIndex++;
			Game.runTurn(turnBasicAction);
			Thread.sleep(800);
		} else {
			this.end();
		}
	}

	private void correctLineBreaking() {
		if ( this.turnIndex == this.length - 1) {
		}
	}

	public void start() {
		Game.setInSimulation(true);
	}
	
}
