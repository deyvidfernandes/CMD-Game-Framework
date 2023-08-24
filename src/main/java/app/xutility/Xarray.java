package app.xutility;

public class Xarray {
	static public int[] sumInt(int[] vec1, int[] vec2 ) {
		int[] returnVec = new int[vec1.length];
		for (int i = 0; i < vec1.length; i++) {
			returnVec[i] = vec1[i] + vec2[i]; 
		}
		return returnVec;
	}
	
	static public boolean arrayContainsElement(Object[] vector, Object element) {
		for (int i = 0; i < vector.length; i++) {
			if ( vector[i].equals(element)) {
				return true;
			}
		}
		return false;
	}

	static public String[] subStringArray(String[] array, int startIndex, int endIndex) {
		String[] newArray;
		
		int newArrayLength = array.length - startIndex - (array.length - endIndex); 
		
		newArray = new String[newArrayLength];
		
		for (int i = 0; i < newArrayLength; i++) {
			newArray[i] = array[i + startIndex];
		}
		return newArray;
	}
	static public String[] trimStringArray(String[] array) {
		int blankElements = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i].equals("")) {
				blankElements++;
			}
		}
		String[] newArray = new String[array.length - blankElements];
		int internIndex = 0;
		for (int i = 0; i < array.length; i++) {
			if (!array[i].equals("")) {
				newArray[internIndex] = array[i];
				internIndex++;
			}
		}
		return newArray;
	}

	static public int[] subIntArray(int[] array, int startIndex, int endIndex) {
		int[] newArray;
		endIndex+= 1;
		int newArrayLength = array.length - startIndex - (array.length - endIndex); 
		
		newArray = new int[newArrayLength];
		
		for (int i = 0; i < newArrayLength; i++) {
			newArray[i] = array[i + startIndex];
		}
		return newArray;
	}
}
