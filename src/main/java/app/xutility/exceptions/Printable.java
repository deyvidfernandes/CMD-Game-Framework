package app.xutility.exceptions;

public abstract class Printable extends Exception {
	private static final long serialVersionUID = 7211449901126743729L;

	public String getId() {
		return "foo";
	}
	
	public String getInvalidInput() {
		return "foo";
	}
}
