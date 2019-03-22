import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class hopscotch {
	static int numRows, numCols, highest, poss = 0, start, end;
	static long[][] grid, possArr;
	// static LinkedList<Integer> visited = new LinkedList<Integer>();

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("hopscotch.in"));
		File file = new File("hopscotch.out");
		PrintWriter out = new PrintWriter(file);
		StringTokenizer st = new StringTokenizer(f.readLine());
		numRows = Integer.parseInt(st.nextToken());
		numCols = Integer.parseInt(st.nextToken());
		highest = Integer.parseInt(st.nextToken());
		grid = new long[numCols][numRows];
		possArr = new long[numCols][numRows];
		possArr[numRows - 1][0] = 1;
		for (int i = numCols - 1; i >= 0; i--) {
			st = new StringTokenizer(f.readLine());
			for (int j = 0; j < numRows; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = numRows - 2; i >= 0; i--) {
			for (int j = 1; j < numCols; j++) {
				calc(i, j);
			}
		}
		System.out.println(possArr[0][numCols - 1]);
		out.println(possArr[0][numCols - 1]);
		out.close();
	}

	public static void calc(int row, int col) {
		for (int z = numRows - 1; z >= row + 1; z--) {
			for (int x = 0; x <= col - 1; x++) {
				if (grid[row][col] != grid[z][x]) {
					possArr[row][col] += possArr[z][x];
				}

			}
		}
		possArr[row][col] %= 1000000007;
	}

}
