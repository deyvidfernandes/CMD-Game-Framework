package game;

public class UserInput {
	private int turnsToRun;
	private String dir;
	private String action;
	
	UserInput(String input) throws xutility.exceptions.InvalidUserInput {
			if (input.length() > 0) {
				this.action = String.valueOf(input.charAt(0));	
			} else {
				throw new xutility.exceptions.InvalidUserInput("none", "action");
			}
			if (input.length() > 1) {
				this.dir = String.valueOf(input.charAt(1));
			} else {
				throw new xutility.exceptions.InvalidUserInput("none", "direction");
			}
			if (input.length() > 2) {
				this.turnsToRun = Integer.parseInt(String.valueOf(input.charAt(2)));
			} else {
				this.turnsToRun = 1; // In the absence of the third char, just one turn will be runned
			}
			
	}
	
	public int getTurnsToRun() {
		return turnsToRun;
	}
	public void setTurnsToRun(int turnsToRun) {
		this.turnsToRun = turnsToRun;
	}
	public String getDir() {
		return dir;
	}
	public void setDir(String dir) {
		this.dir = dir;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
}
