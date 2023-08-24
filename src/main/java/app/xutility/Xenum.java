package app.xutility;

public class Xenum {
	
	static public <T extends Enum<T>> boolean containsStringElement(T[] constants, String element) {
		for (T enumConstant : constants) {
			if ( enumConstant.toString().equals(element.toUpperCase())) {
				return true;
			}
		}
		return false;
	}
	
	static public <T extends Enum<T>> T convertToConstant(T[] constants, String string) {
		for (T enumConstant : constants) {
			if ( enumConstant.toString().equals(string.toUpperCase())) {
				return enumConstant;
			}
		}
		throw new IllegalArgumentException("Tried to convert an invalid string to enumConstant");
	}
}
