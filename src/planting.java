import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class planting {
	static long num, numType = 1;
	static long[][] connections;
	static long[] fields, numConnections;
	static List<Long> connected = new ArrayList<Long>();

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("planting.in"));
		File file = new File("planting.out");
		PrintWriter out = new PrintWriter(file);
		StringTokenizer st = new StringTokenizer(f.readLine());

		num = Integer.parseInt(st.nextToken());
		connections = new long[(int) num][100];
		numConnections = new long[(int) num];
		fields = new long[(int) num];
		fields[0] = 1;

		for (int i = 0; i < num - 1; i++) {
			st = new StringTokenizer(f.readLine());
			int first = Integer.parseInt(st.nextToken());
			int second = Integer.parseInt(st.nextToken());
			connections[first - 1][(int) numConnections[first - 1]] = second - 1;
			numConnections[first - 1]++;
			connections[second - 1][(int) numConnections[second - 1]] = first - 1;
			numConnections[second - 1]++;
		}

		calcFirst();

		for (int i = 1; i < num; i++) {
			if (fields[i] == 0) {
				calc(i);
				connected.clear();
			}
		}
		for (int i = 1; i < num; i++) {
			if (fields[i] == 0) {
				calc(i);
				connected.clear();
			}
		}

		System.out.println(numType);

		out.println(numType);
		out.close();
	}

	public static void calcFirst() {
		for (int i = 0; i < numConnections[0]; i++) {
			if (fields[(int) connections[0][i]] != 0) {
				numType++;
				fields[(int) connections[0][i]] = numType;
			}

		}
	}

	public static void calc(int curField) {
		long next = 0;
		if (numConnections[curField] <= 0) {
			return;
		}
		for (int i = 0; i < numConnections[curField]; i++) {
			next = connections[curField][i];
			if (fields[(int) next] != 0) {
				nearAd(next, curField);
				if (!connected.contains(fields[(int) next])) {
					connected.add(fields[(int) next]);
				}
			}

		}
		if (connected.size() == 0) {
			curField = 1;
		}
		if (connected.size() == numType) {
			numType++;
			fields[curField] = numType;
		} else {
			for (int i = (int) (numType - 1); i >= 1; i--) {
				if (!connected.contains(i)) {
					fields[curField] = i;
					break;
				}
			}
		}
	}

	public static void nearAd(long nextField, long curField) {
		long next;
		for (int i = 0; i < numConnections[(int) nextField]; i++) {

			next = connections[(int) nextField][i];
			if (next == curField) {
				continue;
			}
			if (fields[(int) next] != 0) {
				if (!connected.contains(fields[(int) next])) {
					connected.add(fields[(int) next]);
				}
			}

		}
	}

}