import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

class DataPair2 implements Comparable<DataPair2> {

	private int row, col;

	// constructor
	DataPair2() {
		row = 0;
		col = 0;
	}

	public void setRow(int s) {
		row = s;
	}

	public void setCol(int e) {
		col = e;
	}

	DataPair2(int s, int e) {
		row = s;
		col = e;
	}

	public int getRow() {
		return (row);
	}

	public int getCol() {
		return (col);
	}

	public static Object[] bubbleSort(DataPair2[] intArray, int[] other) {
		int n = intArray.length;
		int temp = 0, temp2 = 0;
		int temp3 = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 1; j < (n - i); j++) {

				if (intArray[j - 1].getRow() > intArray[j].getRow()) {
					// swap the elements!
					temp = intArray[j - 1].getRow();
					temp2 = intArray[j - 1].getCol();
					temp3 = other[j - 1];
					intArray[j - 1].setRow(intArray[j].getRow());
					intArray[j - 1].setCol(intArray[j].getCol());
					other[j - 1] = other[j];
					intArray[j].setRow(temp);
					intArray[j].setCol(temp2);
					other[j] = temp3;
				} else if (intArray[j - 1].getRow() == intArray[j].getRow()
						&& intArray[j - 1].getCol() > intArray[j].getCol()) {
					// swap the elements!
					temp = intArray[j - 1].getRow();
					temp2 = intArray[j - 1].getCol();
					temp3 = other[j - 1];
					intArray[j - 1].setRow(intArray[j].getRow());
					intArray[j - 1].setCol(intArray[j].getCol());
					other[j - 1] = other[j];
					intArray[j].setRow(temp);
					intArray[j].setCol(temp2);
					other[j] = temp3;

				}
			}
		}
		return new Object[] { intArray, other };
	}

	@Override
	public int compareTo(DataPair2 o) {
		// TODO Auto-generated method stub
		return 0;
	}

}

public class stampede {
	static int numCows, cowsSeen = 0, minStart = 1000000000, prevY;
	static LinkedList<Integer> cows = new LinkedList<Integer>(), inEnds = new LinkedList<Integer>();
	static long maxEnd = 0;
	static int[] xCoords, yCoords;
	static DataPair2[] startEnd;
	static int[] time, indexes;
	static boolean seeCow = false;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("stampede.in"));
		File file = new File("stampede.out");
		PrintWriter out = new PrintWriter(file);
		Object[] dummy;
		numCows = Integer.parseInt(f.readLine());
		xCoords = new int[numCows];
		yCoords = new int[numCows];
		startEnd = new DataPair2[numCows];
		time = new int[numCows];
		indexes = new int[numCows];
		for (int i = 0; i < numCows; i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			xCoords[i] = x;
			yCoords[i] = y;
			time[i] = Integer.parseInt(st.nextToken());
			indexes[i] = i;
			startEnd[i] = new DataPair2();
		}
		findStart();
		DataPair2.bubbleSort(startEnd, indexes);
		// Arrays.sort(end0);
		calc();
		System.out.println(cowsSeen);
		out.println(cowsSeen);
		out.close();
	}

	public static void findStart() {
		for (int i = 0; i < numCows; i++) {
			startEnd[i].setRow((int) Math.abs((time[i]) * (0 - (xCoords[i] + 1))));
			startEnd[i].setCol(startEnd[i].getRow() + time[i]);
			if (startEnd[i].getRow() < minStart) {
				minStart = startEnd[i].getRow();
			}
			if (startEnd[i].getCol() > maxEnd) {
				maxEnd = startEnd[i].getCol();
			}
		}
	}

	public static void calc() {
		int sCount = 0, eCount = 0;
		for (int i = minStart; i < maxEnd; i++) {
			if (i == startEnd[sCount].getRow()) {
				cows.add(yCoords[indexes[sCount]]);
				inEnds.add(startEnd[sCount].getCol());
				sCount++;
			}
			if (i == startEnd[eCount].getCol()) {
				// cows.add(yCoords[indexes[sCount]]);
				inEnds.remove((Object) startEnd[eCount].getCol());
				eCount++;
			}
		}
	}

}
