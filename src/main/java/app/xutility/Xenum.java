package app.xutility;

public class Xenum {
	static public <T extends Enum<T>> boolean containsElement(T[] inputEnum, Object element) {
		for (int i = 0; i < inputEnum.length; i++) {
			if ( inputEnum[i].equals(element)) {
				return true;
			}
		}
		return false;
	}
}
