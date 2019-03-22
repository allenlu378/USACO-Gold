import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

class DataPair4 implements Comparable<DataPair4> {

	private int row, col;

	// constructor
	DataPair4() {
		row = 0;
		col = 0;
	}

	DataPair4(int s, int e) {
		row = s;
		col = e;
	}

	public int getRow() {
		return (row);
	}

	public int getCol() {
		return (col);
	}

	public int compareTo(DataPair4 CompPair) {
		return (this.col - CompPair.getCol());
	}

}

public class lifeguards {
	static int numCows, max, maxTemp, max2 = 0;
	static LinkedList<DataPair4> time = new LinkedList<DataPair4>();
	static int[] start, end;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("lifeguards.in"));
		File file = new File("lifeguards.out");
		PrintWriter out = new PrintWriter(file);
		numCows = Integer.parseInt(f.readLine());
		start = new int[numCows];
		end = new int[numCows];
		for (int i = 0; i < numCows; i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			start[i] = Integer.parseInt(st.nextToken());
			end[i] = Integer.parseInt(st.nextToken());

		}
		max = time.size();
		for (int i = 0; i < numCows; i++) {
			maxTemp = max;
			for (int j = start[i]; j < end[i]; j++) {
				if (maxTemp == max2) {
					break;
				}
				if (1 == 1) {
					maxTemp--;
				}

			}
			if (maxTemp > max2) {
				max2 = maxTemp;
			}
		}
		System.out.println(max2);
		out.println(max2);
		out.close();
	}

}
