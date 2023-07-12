package xutility.exceptions;

public class invalidMethodInput extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String methodName;
	private String invalidInput;
	
	public invalidMethodInput(String methodName, Object invalidInput) {
		super();
		this.invalidInput = String.valueOf(invalidInput);
		this.methodName = methodName;
	}
	
	@Override
	public String toString() {
		return "\"" + this.invalidInput + "\" is not a valid input for \"" + methodName + "\"";
	}
	
}
