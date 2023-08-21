package app;

import app.input.BasicAction;
import app.input.CompiledAction;

public class Simulation extends Game {
	private boolean simulationRunning = false;
	private int simulationLength;
	private int simulationTurnIndex = 0;
	
	Simulation(int simulationLength) {
		this.simulationRunning = false;
		this.simulationLength = simulationLength;
	}
	
	public int getSimulationLength() {
		return this.simulationLength;
	}
	private void setSimulationLength(int simulationLength) {
		this.simulationLength = simulationLength;
	}
	public int getTurnIndex() {
		return simulationTurnIndex;
	}
	private void setSimulationTurnIndex(int simulationTurnIndex) {
		this.simulationTurnIndex = simulationTurnIndex;
	}
	private void setSimulationRunning(boolean simulationRunning) {
		this.simulationRunning = simulationRunning;
	}

	public boolean isSimulationRunning() {
		return this.simulationRunning;
	}
	public void start() {
		this.setSimulationRunning(true);
	}
	public void end() {
		this.setSimulationRunning(false);
		super.setInSimulation(false);
		lastRenderInSimulation = true;
	}
	
	private void incrementSimulationTurnIndex(int increment) {
		this.simulationTurnIndex = simulationTurnIndex + increment;
	}
	
	public void runTurn(CompiledAction compiledAction) throws InterruptedException {
		if ( this.getTurnIndex() < this.getSimulationLength() ) {
			correctLineBreaking();
			BasicAction turnBasicAction = compiledAction.getAction(this.getTurnIndex());
			this.incrementSimulationTurnIndex(1);
			getPlayer().act(turnBasicAction);
			Thread.sleep(800);
		} else {
			this.end();
		}
	}

	private void correctLineBreaking() {
		if ( this.getTurnIndex() == this.getSimulationLength() - 1) {
		}
	}
	
}
