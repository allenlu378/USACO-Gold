
/*
ID: allenlu2
LANG: JAVA
TASK: milk3
*/
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class milk3 {
	static int[] caps, values;
	static List<Integer> ans = new ArrayList<Integer>();
	static List<int[]> visited = new ArrayList<int[]>();

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("milk3.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk3.out")));
		caps = new int[] { in.nextInt(), in.nextInt(), in.nextInt() };
		values = new int[] { 0, 0, caps[2] };
		ans.add(values[2]);
		solve(0, 0, values[2]);
		print(out);
	}

	public static boolean isInList(final List<int[]> list, final int[] candidate) {

		return list.stream().anyMatch(a -> Arrays.equals(a, candidate));
		// ^-- or you may want to use .parallelStream() here instead
	}

	public static void solve(int a, int b, int c) {
		if (isInList(visited, new int[] { a, b, c })) {
			return;
		}
		visited.add(new int[] { a, b, c });
		if (a == 0 && !ans.contains(c)) {
			ans.add(c);
		}
		if (a < caps[0]) {
			// Pour c to a
			if (c >= caps[0] - a) {
				solve(caps[0], b, c - (caps[0] - a));
			} else {
				solve(a + c, b, 0);
			}
			// Pour b to a
			if (b >= caps[0] - a) {
				solve(caps[0], b - (caps[0] - a), c);
			} else {
				solve(a + b, 0, c);
			}

		}
		if (c < caps[2]) {
			// Pour b to c
			if (b >= caps[2] - c) {
				solve(a, b - (caps[2] - c), caps[2]);
			} else {
				solve(a, 0, b + c);
			}
			// Pour a to c
			if (a >= caps[2] - c) {
				solve(a - (caps[2] - c), b, caps[2]);
			} else {
				solve(0, b, a + c);
			}
		}

		if (b < caps[1]) {
			// Pour a to b
			if (a >= caps[1] - b) {
				solve(a - (caps[1] - b), caps[1], c);
			} else {
				solve(0, a + b, c);
			}
			// Pour c to b
			if (c >= caps[1] - b) {
				solve(a, caps[1], c - (caps[1] - b));
			} else {
				solve(a, b + c, 0);
			}

		}

	}

	public static void print(PrintWriter out) {
		Collections.sort(ans);
		for (int i = 0; i < ans.size(); i++) {
			if (i == ans.size() - 1) {
				System.out.print(ans.get(i));
				out.println(ans.get(i));
				break;
			}
			System.out.print(ans.get(i) + " ");
			out.print(ans.get(i) + " ");
		}
		out.close();
	}

}
