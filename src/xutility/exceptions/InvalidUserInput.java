package xutility.exceptions;

public class InvalidUserInput extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String invalidInput;
	private String charLabel;

	
	public InvalidUserInput(String invalidInput, String charLabel) {
		super();
		this.invalidInput = invalidInput;
		this.charLabel = charLabel;
	}
	
	@Override
	public String toString() {
		if (this.invalidInput.equals("none")) {
			return "Char \"" + charLabel + "\"" + " must have an input value";
		} else {
			return "\"" + this.invalidInput + "\" is not a valid input for \"" + charLabel + "\"" + " char";
		}
	}
	
}
