package game;

public class UserInput {
	private int turnsToRun;
	private String dir;
	private String action;
	
	UserInput(String input) throws xutility.exceptions.InvalidUserInput {
			//Ternary operations verify if an inputChar is missing in the user input

		
			String actionInput = input.length() > 0 && !String.valueOf(input.charAt(0)).equals(" ") ? String.valueOf(input.charAt(0)) : null;
			String dirInput = input.length() > 1 && !String.valueOf(input.charAt(1)).equals(" ") ? String.valueOf(input.charAt(1)) : null;
			int timesInput;
			try {
				timesInput = input.length() > 2 ? Integer.parseInt(String.valueOf(input.charAt(2))) : 0;
			} catch (NumberFormatException exc) {
				throw new xutility.exceptions.InvalidUserInput(String.valueOf(String.valueOf(input.charAt(2))), "times", "times-invalid input");
			}
			
			if (input.length() > 3) {
				throw new xutility.exceptions.InvalidUserInput("overflow");
			}
			if (actionInput != null) {
				this.action = actionInput;	
			} else {
				throw new xutility.exceptions.InvalidUserInput("none", "action", "action-missing");
			}
			if (dirInput != null) {
				if ( !(dirInput.equals("w") || dirInput.equals("s") || dirInput.equals("d") || dirInput.equals("e")) ) {
					throw new xutility.exceptions.InvalidUserInput(dirInput, "direction", "direction-invalid input");
				} else {
					this.dir = dirInput;
				}
			} else {
				throw new xutility.exceptions.InvalidUserInput("none", "direction", "direction-missing");
			}
			if (timesInput == 0) {
				this.turnsToRun = timesInput;
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
