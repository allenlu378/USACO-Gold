import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class perimeter {
	static int[][] grid, visited;
	static int size, largestArea = 0, smallestPerim = 1000000000, curArea = 0, curPerim = 0, connections = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("perimeter.in"));
		File file = new File("perimeter.out");
		PrintWriter out = new PrintWriter(file);
		StringTokenizer st = new StringTokenizer(f.readLine());
		size = Integer.parseInt(st.nextToken());
		grid = new int[size][size];
		visited = new int[size][size];
		for (int i = size - 1; i >= 0; i--) {
			st = new StringTokenizer(f.readLine());
			String token = st.nextToken();
			for (int j = 0; j < size; j++) {

				if (token.charAt(j) == '.') {
					grid[i][j] = 0;
				} else {
					grid[i][j] = 1;
				}
			}
		}
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (grid[i][j] == 1) {
					fill(i, j);
					curPerim = curArea * 4 - connections + 1;
					if (curArea > largestArea) {
						largestArea = curArea;
						smallestPerim = curPerim;
					} else if (curArea == largestArea && curPerim < smallestPerim) {
						largestArea = curArea;
						smallestPerim = curPerim;
					}
					curArea = 0;
					curPerim = 0;
					connections = 0;
				}

			}
		}

		System.out.println(largestArea + " " + smallestPerim);
		out.println(largestArea + " " + smallestPerim);
		out.close();
	}

	public static void fill(int row, int col) {
		if (row < 0 || col < 0 || row == size || col == size || grid[row][col] == 0) {
			return;
		}
		connections++;
		if (visited[row][col] == 1) {
			return;
		}
		visited[row][col] = 1;
		curArea++;

		fill(row + 1, col);
		fill(row - 1, col);
		fill(row, col + 1);
		fill(row, col - 1);

	}

}
