
/*
ID: allenlu2
LANG: JAVA
TASK: wormhole
 */
import java.io.IOException;

public class wormhole {

	public static void main(String[] args) throws IOException {

	}

	public static int holeRow(int[] arr) {
		int numHole = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == 1) {
				numHole++;
			}
		}
		return numHole;
	}

	public static int factorial(int num) {
		int total = 1;
		for (int i = 0; i <= num; i++) {
			total *= i;
		}
		return total;
	}

	public static int calc(int num) {
		int total;

	}

}
