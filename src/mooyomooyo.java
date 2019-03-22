import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class mooyomooyo {
	static int[][] permBoard, tempBoard, visited;
	static int height, max, numFilled = 0;
	static boolean isAnyLeft = false;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("mooyomooyo.in"));
		PrintWriter out = new PrintWriter(new FileWriter("mooyomooyo.out"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		height = Integer.parseInt(st.nextToken());
		max = Integer.parseInt(st.nextToken());
		permBoard = new int[height][10];
		tempBoard = new int[height][10];
		visited = new int[height][10];
		for (int i = height - 1; i > -1; i--) {
			String temp = br.readLine();
			for (int j = 0; j < 10; j++) {
				permBoard[i][j] = temp.charAt(j) - 48;
			}
		}
		fillTemp();
		while (true) {
			for (int row = 0; row < height; row++) {
				for (int col = 0; col < 10; col++) {
					if (permBoard[row][col] != 0) {
						fill(row, col, tempBoard[row][col]);
						if (numFilled >= max) {
							fillPerm();
							isAnyLeft = true;
						} else {
							fillTemp();
						}
						visited = new int[height][10];
						numFilled = 0;
					}
				}
			}
			if (isAnyLeft == false) {
				break;
			}
			isAnyLeft = false;
			drop();
			fillTemp();
		}
		for (int row = height - 1; row > -1; row--) {
			for (int col = 0; col < 10; col++) {
				System.out.print(permBoard[row][col]);
				out.print(permBoard[row][col]);
			}
			System.out.println();
			out.println();
		}
		out.close();

	}

	public static void fillPerm() {
		for (int row2 = 0; row2 < height; row2++) {
			for (int col2 = 0; col2 < 10; col2++) {
				permBoard[row2][col2] = tempBoard[row2][col2];
			}
		}
	}

	public static void fillTemp() {
		for (int row2 = 0; row2 < height; row2++) {
			for (int col2 = 0; col2 < 10; col2++) {
				tempBoard[row2][col2] = permBoard[row2][col2];
			}
		}
	}

	public static void fill(int row, int col, int val) {
		if (row == height || row == -1 || col == 10 || col == -1) {
			return;
		}
		if (visited[row][col] == 1 || tempBoard[row][col] != val) {
			return;
		}
		visited[row][col] = 1;
		tempBoard[row][col] = 0;
		numFilled++;
		fill(row + 1, col, val);
		fill(row, col + 1, val);
		fill(row - 1, col, val);
		fill(row, col - 1, val);
	}

	public static void drop() {
		int[] newCol;
		for (int col = 0; col < 10; col++) {
			newCol = dropCol(getColumn(permBoard, col));
			for (int row = 0; row < height; row++) {
				permBoard[row][col] = newCol[row];
			}
		}
	}

	public static int[] getColumn(int[][] matrix, int column) {
		return IntStream.range(0, matrix.length).map(i -> matrix[i][column]).toArray();
	}

	public static int[] dropCol(int[] col) {
		int count = 0;
		int[] newCol = new int[height];
		for (int row = 0; row < height; row++) {
			if (col[row] != 0) {
				newCol[count] = col[row];
				count++;
			}
		}
		return newCol;
	}

}
