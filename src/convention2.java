import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.List;

/*
TASK: convention2
LANG: JAVA
ID: allenlu2
 */
class Tuple {
	private int time, sen, arr;

	public Tuple() {
		arr = 0;
		time = 0;
		sen = 0;
	}

	public Tuple(int tim, int seni, int arri) {
		time = tim;
		sen = seni;
		arr = arri;
	}

	int getTim() {
		return time;
	}

	int getSen() {
		return sen;
	}

	int getArr() {
		return arr;
	}
}

public class convention2 {
	static StreamTokenizer input;
	static List<Tuple> line = new ArrayList<Tuple>();

	static int maxWait = 0, time = 0, maxTime, startIndex = 0;
	static List<Tuple> list = new ArrayList<Tuple>();

	public static void main(String[] args) throws IOException {
		String prob = "convention2";
		input = new StreamTokenizer(new BufferedReader(new FileReader(prob + ".in")));

		PrintWriter out = new PrintWriter(new FileWriter(prob + ".out"));
		int numCow = nextInt(), cowPassed = 0, indexCowNotEat = 1;

		int[] wait = new int[numCow];
		int[][] times = new int[numCow][3];

		for (int i = 0; i < numCow; i++) {
			times[i][0] = nextInt();
			times[i][1] = i + 1;
			times[i][2] = nextInt();
		}
		int time = times[0][0] + times[0][2];
		java.util.Arrays.sort(times,
				java.util.Comparator.<int[]>comparingDouble(a -> a[0]).thenComparingDouble(a -> a[1]));
		time = times[0][0];
		while (true) {
			int minSen = numCow + 1;
			for (int i = startIndex; i < numCow; i++) {
				if (times[i][0] <= time) {
					list.add(new Tuple(times[i][1], times[i][2], times[i][0]));
					if (times[i][1] < minSen) {
						minSen = times[i][1];
					} else {
						startIndex = i;
					}

				}
			}
			int curWait = 0;
			if (list.size() > 0) {
				Tuple cur = list.get(0);
				if (cur.getSen() == minSen) {
					if (time < cur.getArr()) {
						time = cur.getArr();
						curWait = 0;
					} else {
						curWait = time - cur.getArr();
						time = time + cur.getTim();

					}
					list.remove(cur);
				}

				if (curWait > maxWait) {
					maxWait = curWait;
				}

			}
			if (startIndex >= numCow) {
				break;
			}

		}
		System.out.print("Stop");

	}

	static int nextInt() throws IOException {
		input.nextToken();
		return (int) input.nval;
	}

	public static Tuple findMSenior() {
		int mSenior = 10000000;
		Tuple next = new Tuple();
		for (int i = 0; i < line.size(); i++) {
			if (line.get(i).getSen() < mSenior) {
				mSenior = line.get(i).getSen();
			}
		}
		line.remove(next);
		return next;
	}

}
