
/*
ID: allenlu2
LANG: JAVA
TASK: barn1
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class barn1 {
	static int maxBoards, numStalls, numCows, min;
	static int[] stalls, stallsCovered, numbers;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("barn1.in"));
		File file = new File("barn1.out");
		PrintWriter out = new PrintWriter(file);
		StringTokenizer st = new StringTokenizer(f.readLine());
		maxBoards = Integer.parseInt(st.nextToken());
		numStalls = Integer.parseInt(st.nextToken());
		min = numStalls;
		numCows = Integer.parseInt(st.nextToken());
		stalls = new int[numStalls];
		stallsCovered = new int[numStalls];
		numbers = new int[numCows];
		for (int i = 0; i < numCows; i++) {
			int temp = Integer.parseInt(f.readLine());
			stalls[temp - 1] = 1;
			numbers[i] = temp;
		}
		Arrays.sort(numbers);
		if (maxBoards >= numCows) {
			min = numCows;
		} else {
			for (int i = 0; i < numbers[0]; i++) {
				stallsCovered[i] = 1;
			}
			for (int i = numStalls - 1; i > numbers[numbers.length - 1]; i--) {
				stallsCovered[i] = 1;
			}
			calc();

			min = min - numbers[0] + 1;
			min = min - (numStalls - numbers[numbers.length - 1]);
		}
		System.out.println(min + "\n");
		out.print(min + "\n");
		out.close();

	}

	public static void calc() {
		int maxNone = 0, maxNoneTemp = 0, start = 0, startF = 0, end = 0;
		boolean str = true;
		for (int i = 1; i < maxBoards; i++) {
			for (int j = 0; j < numStalls; j++) {
				if (stallsCovered[j] == 1) {

				} else {
					if (stalls[j] == 1) {

						if (maxNoneTemp > maxNone) {
							maxNone = maxNoneTemp;
							maxNoneTemp = 0;
							end = j;

							startF = start;
						}
						str = true;
						maxNoneTemp = 0;
					} else {
						if (str) {
							str = false;
							start = j;
						}
						maxNoneTemp++;
					}
				}
			}
			min -= maxNone;
			maxNone = maxNoneTemp = 0;
			for (int j = startF; j < end + 1; j++) {
				stallsCovered[j] = 1;
			}
		}
	}

}
