import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
ID: allenlu2
LANG: JAVA
TASK: holstein
*/
public class holstein {
	static int numVit, numFeed, numScoop = 0;
	static int[] vitNeed;
	static List<Integer> feedUsed = new ArrayList<Integer>();
	static int[][] feeds;
	static boolean done = false;

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("holstein.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("holstein.out")));
		numVit = in.nextInt();
		vitNeed = new int[numVit];
		for (int i = 0; i < numVit; i++) {
			vitNeed[i] = in.nextInt();
		}
		numFeed = in.nextInt();
		feeds = new int[numFeed][numVit];
		int[][] combos = new int[numFeed][numVit];

		for (int i = 0; i < numFeed; i++) {
			for (int j = 0; j < numVit; j++) {
				feeds[i][j] = in.nextInt();
			}
		}
		for (int i = 1; i < numVit + 1; i++) {
			combos(0, numFeed - 1, 0, i, combos, out);
			combos = new int[numFeed][numVit];
			if (done) {
				break;
			}
		}
		out.close();

	}

	public static void combos(int start, int end, int index, int size, int[][] data, PrintWriter out) {
		if (done) {
			return;
		}
		if (index == size) {

			calc(data, size, out);
			if (done) {

				return;
			}

			// System.out.println("");
			feedUsed.remove(feedUsed.size() - 1);
			return;
		}
		int temp = 0;
		for (int i = start; i <= end && end - i + 1 >= size - index; i++) {
			temp = i;
			data[index] = feeds[i];
			feedUsed.add(i + 1);
			combos(i + 1, end, index + 1, size, data, out);
		}
		if ((temp <= end && end - temp + 1 >= size - index) && start > 0) {
			feedUsed.remove(feedUsed.size() - 1);
		}
	}

	public static void calc(int[][] data, int size, PrintWriter out) {
		int total = 0;
		for (int i = 0; i < numVit; i++) {
			for (int j = 0; j < numFeed; j++) {
				total += data[j][i];
			}
			if (total < vitNeed[i]) {
				return;
			}
			total = 0;

		}
		done = true;
		System.out.print(size + " ");
		out.print(size + " ");
		for (int i = 0; i < feedUsed.size() - 1; i++) {
			System.out.print(feedUsed.get(i) + " ");
			out.print(feedUsed.get(i) + " ");
		}
		System.out.print(feedUsed.get(feedUsed.size() - 1) + "\n");
		out.print(feedUsed.get(feedUsed.size() - 1) + "\n");
	}

}
