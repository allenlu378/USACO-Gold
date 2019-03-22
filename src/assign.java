import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

class DataPair implements Comparable<DataPair> {

	private int row, col;

	// constructor
	DataPair() {
		row = 0;
		col = 0;
	}

	DataPair(int s, int e) {
		row = s;
		col = e;
	}

	public int getRow() {
		return (row);
	}

	public int getCol() {
		return (col);
	}

	public int compareTo(DataPair CompPair) {
		return (this.col - CompPair.getCol());
	}

}

public class assign {
	static int numCows, numPairs, numPoss = 0;

	static int[] numBreeds;
	static LinkedList<DataPair> same = new LinkedList<DataPair>(), diff = new LinkedList<DataPair>();
	static LinkedList<Integer> notDone = new LinkedList<Integer>();

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("assign.in"));
		File file = new File("assign.out");
		PrintWriter out = new PrintWriter(file);
		StringTokenizer st = new StringTokenizer(f.readLine());
		char letter;
		int temp1, temp2;
		boolean first = true;
		numCows = Integer.parseInt(st.nextToken());
		numPairs = Integer.parseInt(st.nextToken());
		numBreeds = new int[numCows];
		for (int i = 0; i < numPairs; i++) {
			st = new StringTokenizer(f.readLine());
			letter = st.nextToken().charAt(0);
			temp1 = Integer.parseInt(st.nextToken());
			temp2 = Integer.parseInt(st.nextToken());
			if (letter == 'S') {
				same.add(new DataPair(temp2, temp1));
				continue;

			}
			if (letter == 'D') {
				if (first == false && temp2 == diff.get(0).getRow() && temp1 == diff.get(0).getCol()) {
					continue;
				}
				diff.add(new DataPair(temp2, temp1));
				first = false;

			}
		}
		numBreeds[0] = 1;
		calc(1);
		numPoss *= 3;
		System.out.println(numPoss);
		out.println(numPoss);
		out.close();

	}

	public static void calc(int cowtoChange) {
		if (cowtoChange == numCows) {
			if (check()) {
				numPoss++;
			}
			return;
		}
		for (int i = 1; i <= 3; i++) {
			numBreeds[cowtoChange] = i;
			calc(cowtoChange + 1);
		}

	}

	public static boolean check() {
		for (int i = 0; i < same.size(); i++) {
			if (numBreeds[same.get(i).getRow() - 1] != numBreeds[same.get(i).getCol() - 1]) {
				return false;
			}
		}
		for (int i = 0; i < diff.size(); i++) {
			if (numBreeds[diff.get(i).getRow() - 1] == numBreeds[diff.get(i).getCol() - 1]) {
				return false;
			}
		}
		return true;
	}
}
