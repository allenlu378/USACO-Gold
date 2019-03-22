import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
ID: allenlu2
LANG: JAVA
TASK: ariprog
 */
public class ariprog2 {
	static int len, lim, limR;
	static List<Integer> first = new ArrayList<Integer>(), diff = new ArrayList<Integer>(),
			sq = new ArrayList<Integer>(), diffSq = new ArrayList<Integer>();

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("ariprog.in"));
		File file = new File("ariprog.out");
		PrintWriter out = new PrintWriter(file);
		StringTokenizer st = new StringTokenizer(f.readLine());
		len = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(f.readLine());
		limR = Integer.parseInt(st.nextToken());
		lim = (int) (Math.pow(limR, 2) * 2);

		findSq(lim);
		solve();

		print(out);

	}

	public static void findSq(int high) {

		for (int checkNum = 0; checkNum <= high; checkNum++) {
			for (int i = 0; i <= limR; i++) {
				if (Math.sqrt(checkNum - Math.pow(i, 2)) % 1 == 0 && Math.sqrt(checkNum - Math.pow(i, 2)) <= limR) {
					sq.add(checkNum);
					break;
				}
			}

		}

	}

	public static void solve() {
		int temp = 1;
		for (int firstV = 0; firstV < sq.size() - 4; firstV++) {
			for (int diffV = 1; diffV < lim / (len - 1) + 1; diffV += temp) {
			}
		}
	}

	public static void print(PrintWriter out) {
		if (first.isEmpty()) {
			out.println("NONE");
		} else {
			// bubbleSort();
			for (int i = 0; i < first.size(); i++) {
				System.out.println(first.get(i) + " " + diff.get(i));
				out.println(first.get(i) + " " + diff.get(i));
			}
		}
		out.close();
	}

}
