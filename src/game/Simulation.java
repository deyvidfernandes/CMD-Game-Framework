package game;

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
	public int getSimulationTurnIndex() {
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
	}
	private void incrementSimulationTurnIndex(int increment) {
		this.simulationTurnIndex = simulationTurnIndex + increment;
	}
	
	public void runTurn(UserInput userInput) throws InterruptedException {
		if ( this.getSimulationTurnIndex() < this.getSimulationLength() ) {
			this.incrementSimulationTurnIndex(1);
			correctLineBreaking();
			getPlayer().act(userInput);
			Thread.sleep(1000);
		} else {
			this.end();
		}
	}

	private void correctLineBreaking() {
		if ( this.getSimulationTurnIndex() == this.getSimulationLength() - 1) {
		}
	}
	
}
