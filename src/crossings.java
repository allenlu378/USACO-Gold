import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class crossings {
	static int numCows, numSafe = 0;
	static int[] lower, upper, safe;
	static boolean isSafe = false;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("crossings.in"));
		File file = new File("crossings.out");
		PrintWriter out = new PrintWriter(file);
		numCows = Integer.parseInt(f.readLine());
		lower = new int[numCows];
		upper = new int[numCows];
		safe = new int[numCows];
		for (int i = 0; i < numCows; i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			lower[i] = Integer.parseInt(st.nextToken());
			upper[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < numCows - 2; i++) {
			if (safe[i] == 0) {
				check(i);
			}

		}
		for (int i = 0; i < numCows; i++) {
			if (safe[i] == 0) {
				numSafe++;
			}
		}
		System.out.println(numSafe);
		out.print(numSafe);
		out.close();

	}

	public static void check(int start) {
		for (int i = 0; i < numCows; i++) {
			if (lower[start] < lower[i] && upper[start] > upper[i]) {
				safe[start] = 1;
				safe[i] = 1;
				continue;
			} else if (lower[start] > lower[i] && upper[start] < upper[i]) {
				safe[start] = 1;
				safe[i] = 1;
				continue;
			} else {
			}

		}
	}

}
