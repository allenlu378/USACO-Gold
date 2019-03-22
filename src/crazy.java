import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class crazy {
	static int numFences, numCows, maxX = 0, maxY = 0, minX = 1000000, minY = 1000000, answer, filled = 0, count = 1,
			total, x0, y0;
	static int[][] fencePos, grid, cowPos, connections;
	static boolean full = false;

	public static void main(String[] args) throws IOException {

		BufferedReader f = new BufferedReader(new FileReader("crazy.in"));
		File file = new File("crazy.out");
		PrintWriter out = new PrintWriter(file);
		StringTokenizer st = new StringTokenizer(f.readLine());
		numFences = Integer.parseInt(st.nextToken());
		numCows = Integer.parseInt(st.nextToken());
		cowPos = new int[2][numCows];
		fencePos = new int[4][numFences];
		for (int i = 0; i < numFences; i++) {
			StringTokenizer ed = new StringTokenizer(f.readLine());
			for (int z = 0; z < 4; z++) {
				fencePos[z][i] = Integer.parseInt(ed.nextToken());
				if (z % 2 == 0) {
					if (fencePos[z][i] > maxX) {
						maxX = fencePos[z][i];
					}
					if (fencePos[z][i] < minX) {
						minX = fencePos[z][i];
					}
				} else if (z % 2 == 1) {
					if (fencePos[z][i] > maxY) {
						maxY = fencePos[z][i];
					}
					if (fencePos[z][i] < minY) {
						minY = fencePos[z][i];
					}
				}
			}

		}
		for (int i = 0; i < numCows; i++) {
			StringTokenizer ed = new StringTokenizer(f.readLine());
			for (int z = 0; z < 2; z++) {

				cowPos[z][i] = Integer.parseInt(ed.nextToken());
				if (z == 0) {
					if (cowPos[z][i] > maxX) {
						maxX = cowPos[z][i];
					}
					if (cowPos[z][i] < minX) {
						minX = cowPos[z][i];
					}
				} else if (z == 1) {
					if (cowPos[z][i] > maxY) {
						maxY = cowPos[z][i];
					}
					if (cowPos[z][i] < minY) {
						minY = cowPos[z][i];
					}
				}
			}
		}
		for (int i = 0; i < numFences; i++) {
			for (int z = 0; z < 4; z++) {
				if (z % 2 == 0) {
					fencePos[z][i] -= minX;
				} else if (z % 2 == 1) {
					fencePos[z][i] -= minY;
				}
			}
		}
		for (int i = 0; i < numCows; i++) {
			for (int z = 0; z < 2; z++) {
				if (z == 0) {
					cowPos[z][i] -= minX;
				} else if (z == 1) {
					cowPos[z][i] -= minY;
				}
			}
		}
		grid = new int[maxY + 1][maxX + 1];
		total = (maxY + 1) * (maxX + 1);
		for (int i = 0; i < numFences; i++) {
			grid[fencePos[1][i]][fencePos[0][i]] = 1;
			grid[fencePos[3][i]][fencePos[2][i]] = 1;
			fillFence(fencePos[0][i], fencePos[1][i], fencePos[2][i], fencePos[3][i]);
		}
		for (int i = 0; i < numCows; i++) {
			grid[cowPos[1][i]][cowPos[0][i]] = 3;
		}
		findNumFence();
		while (true) {
			if (filled == total) {
				break;
			}
			find0();
			connect(x0, y0, count);
			count++;

		}
		System.out.println("End");

	}

	public static void fillFence(int startX, int startY, int endX, int endY) {
		if (startX == endX) {
			for (int i = Math.min(startY, endY) + 1; i < Math.max(startY, endY); i++) {
				grid[i][startX] = 1;
			}
		}
		if (startY == endY) {
			for (int i = Math.min(startX, endX) + 1; i < Math.max(startX, endX); i++) {
				grid[startY][i] = 1;
			}
		}
	}

	public static void connect(int startX, int startY, int count) {
		if (startX == -1 || startY == -1 || startX == maxX + 1 || startY == maxY + 1) {

		} else {
			filled++;
			connections[startY][startX] = count;
			connect(startX + 1, startY, count);
			connect(startX - 1, startY, count);
			connect(startX, startY + 1, count);
			connect(startX, startY - 1, count);
		}
	}

	public static void findNumFence() {
		for (int i = 0; i < numFences; i++) {
			if (fencePos[0][i] == fencePos[2][i]) {
				total -= Math.abs(fencePos[1][i] - fencePos[3][i]) + 1;
				continue;
			}
			if (fencePos[1][i] == fencePos[3][i]) {
				total -= Math.abs(fencePos[0][i] - fencePos[2][i]) + 1;
			}
		}

	}

	public static void find0() {
		for (int i = 0; i < maxY + 1; i++) {
			for (int z = 0; z < maxX + 1; z++) {
				if (grid[i][z] == 0) {
					x0 = z;
					y0 = i;
					return;
				}
			}
		}
	}

}
