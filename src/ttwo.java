import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/*
ID: allenlu2
LANG: JAVA
TASK: ttwo
*/
public class ttwo {
	static char[][] grid = new char[10][10];
	static int[][] visited = new int[10][10];
	static int dirF = 0, dirC = 0, xF, yF, xC, yC, time = 1;
	static int[][] pastDir = new int[2][1000000];
	static boolean stop = false, moved = false;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("ttwo.in"));
		PrintWriter out = new PrintWriter(new File("ttwo.out"));
		for (int i = 9; i > -1; i--) {
			String line = f.readLine();
			for (int j = 0; j < 10; j++) {
				grid[i][j] = line.charAt(j);
				visited[i][j] = -1;
				if (line.charAt(j) == 'F') {
					xF = j;
					yF = i;
				}
				if (line.charAt(j) == 'C') {
					xC = j;
					yC = i;
				}

			}
		}

		visited[yF][xF] = 0;
		visited[yC][xC] = 0;
		pastDir[0][0] = 0;
		pastDir[0][1] = 0;

		int ans = move();
		System.out.println(ans);
		out.println(ans);
		out.close();
	}

	public static int move() {
		while (true) {

			if (xC == xF && yC == yF) {
				return time - 1;
			}
			oneMin();
			if (time > 160000) {
				return 0;
			}
			time++;
		}

	}

	public static boolean isCycle() {

		if (visited[yF][xF] != -1 && visited[yC][xC] != -1 && visited[yF][xF] == visited[yC][xC]) {
			if (dirF == pastDir[0][visited[yF][xF]] && dirC == pastDir[1][visited[yF][xF]]) {
				return true;
			}
		}

		return false;
	}

	public static void oneMin() {
		switch (dirF) {
		case 0:
			if (isObOrOffGrid(yF + 1, xF)) {
				dirF = (dirF + 1) % 4;
			} else {
				yF++;
				if (time != 1 && isCycle()) {
					stop = true;
					return;
				}
				visited[yF][xF] = time;
			}
			break;
		case 1:
			if (isObOrOffGrid(yF, xF + 1)) {
				dirF = (dirF + 1) % 4;
			} else {
				xF++;
				if (time != 1 && isCycle()) {
					stop = true;
					return;
				}
				visited[yF][xF] = time;

			}
			break;

		case 2:
			if (isObOrOffGrid(yF - 1, xF)) {
				dirF = (dirF + 1) % 4;
			} else {
				yF--;
				if (time != 1 && isCycle()) {
					stop = true;
					return;
				}
				visited[yF][xF] = time;
			}
			break;

		case 3:
			if (isObOrOffGrid(yF, xF - 1)) {
				dirF = (dirF + 1) % 4;
			} else {
				xF--;
				if (time != 1 && isCycle()) {
					stop = true;
					return;
				}
				visited[yF][xF] = time;
			}
			break;

		}
		switch (dirC) {
		case 0:
			if (isObOrOffGrid(yC + 1, xC)) {
				dirC = (dirC + 1) % 4;
			} else {
				yC++;
				if (time != 1 && isCycle()) {
					stop = true;
					return;
				}
				visited[yC][xC] = time;

			}
			break;
		case 1:
			if (isObOrOffGrid(yC, xC + 1)) {
				dirC = (dirC + 1) % 4;
			} else {
				xC++;
				if (time != 1 && isCycle()) {
					stop = true;
					return;
				}
				visited[yC][xC] = time;
			}
			break;

		case 2:
			if (isObOrOffGrid(yC - 1, xC)) {
				dirC = (dirC + 1) % 4;
			} else {
				yC--;
				if (time != 1 && isCycle()) {
					stop = true;
					return;
				}
				visited[yC][xC] = time;
			}
			break;

		case 3:
			if (isObOrOffGrid(yC, xC - 1)) {
				dirC = (dirC + 1) % 4;
			} else {
				xC--;
				if (time != 1 && isCycle()) {
					stop = true;
					return;
				}
				visited[yC][xC] = time;
			}
			break;

		}
	}

	public static boolean isObOrOffGrid(int row, int col) {
		if (row == 10 || row == -1 || col == 10 || col == -1) {
			return true;
		}
		if (grid[row][col] == '*') {
			return true;
		}
		return false;
	}

}
