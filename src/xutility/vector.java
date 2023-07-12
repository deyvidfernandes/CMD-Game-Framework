package xutility;

public class vector {
	static public int[] sumInt(int[] vec1, int[] vec2 ) {
		int[] returnVec = new int[vec1.length];
		for (int i = 0; i < vec1.length; i++) {
			returnVec[i] = vec1[i] + vec2[i]; 
		}
		return returnVec;
	}
}
