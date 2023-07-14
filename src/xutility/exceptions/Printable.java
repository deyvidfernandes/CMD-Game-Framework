package xutility.exceptions;

public abstract class Printable extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String getId() {
		return "foo";
	}
	
	public String getInvalidInput() {
		return "foo";
	}
}
