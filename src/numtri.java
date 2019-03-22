import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

/*
ID: allenlu2
LANG: JAVA
TASK: numtri
*/
public class numtri {
	static int numRow;
	static int[] best, oldBest;

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("numtri.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("numtri.out")));
		numRow = in.nextInt();
		best = new int[numRow];
		oldBest = new int[numRow];
		// Solv
		for (int row = 0; row < numRow; row++) {

			for (int col = 0; col < row + 1; col++) {
				int num = in.nextInt();
				if (row == 0) {
					best[0] = num;
					break;
				}

				// Left most
				if (col == 0) {
					best[0] = oldBest[0] + num;

					continue;
				}

				// Middle
				best[col] = Math.max(oldBest[col], oldBest[col - 1]) + num;

			}
			oldBest = Arrays.copyOf(best, best.length);

		}
		Arrays.sort(best);
		System.out.println(best[numRow - 1]);
		out.println(best[numRow - 1]);
		out.close();

	}

}
