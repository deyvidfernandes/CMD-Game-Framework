package xutility.exceptions;

public class InvalidUserInput extends Printable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String invalidInput;
	private String charLabel;
	private String id;
	
	public InvalidUserInput(String invalidInput, String charLabel, String id) {
		super();
		this.invalidInput = invalidInput;
		this.charLabel = charLabel;
		this.id = id;
	}
	public InvalidUserInput(String id) {
		super();
		this.id = id;
	}
	
	public String getCharLabel() {
		return this.charLabel;
	}
	
	@Override
	public String getInvalidInput() {
		return this.invalidInput;
	}

	@Override
	public String getId() {
		return this.id;
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
