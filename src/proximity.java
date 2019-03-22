import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class proximity {
	static int numCows, diff, maxBreed = -1;
	static int[] cows;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("proximity.in"));
		File file = new File("proximity.out");
		PrintWriter out = new PrintWriter(file);
		StringTokenizer st = new StringTokenizer(f.readLine());
		numCows = Integer.parseInt(st.nextToken());
		diff = Integer.parseInt(st.nextToken());
		cows = new int[numCows];
		for (int i = 0; i < numCows; i++) {
			cows[i] = Integer.parseInt(f.readLine());
		}
		maxIndex();

		System.out.println(maxBreed);
		out.println(maxBreed);
		out.close();
	}

	public static void maxIndex() {
		for (int i = numCows - 1; i > 0; i--) {
			if (cows[i] > maxBreed) {
				for (int j = i - 1; j > -1; j--) {
					if (cows[i] != cows[j]) {
						continue;
					}
					if (i - j > diff) {
						break;
					} else {
						maxBreed = cows[i];
						break;
					}
				}
			}
		}
	}
}
