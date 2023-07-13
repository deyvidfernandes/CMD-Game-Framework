package game;

public class UserInput {
	private int turnsToRun;
	private String dir;
	private String action;
	
	UserInput(String input) {
		this.turnsToRun = Integer.parseInt(String.valueOf(input.charAt(2)));
		this.dir = String.valueOf(input.charAt(1));
		this.action = String.valueOf(input.charAt(0));
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
