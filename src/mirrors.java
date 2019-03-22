import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class mirrors {
	static int[] fenceX, fenceY;
	static String[] direction;
	static int index, maxX, minX, minY, maxY, numFences;
	static int[][] numBounce;
	static int[] tempArr, tempArr1, fenceXOrig, fenceYOrig;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("mirrors.in"));
		File file = new File("mirrors.out");
		PrintWriter out = new PrintWriter(file);
		StringTokenizer st = new StringTokenizer(f.readLine());
		String temp;

		numFences = Integer.parseInt(st.nextToken());
		int barnX = Integer.parseInt(st.nextToken()), barnY = Integer.parseInt(st.nextToken()), count = 0, homeX = 0,
				homeY = 0;
		int[][] grid;
		fenceX = new int[numFences];
		fenceY = new int[numFences];
		fenceXOrig = new int[numFences];
		fenceYOrig = new int[numFences];
		direction = new String[numFences];
		for (int i = 0; i < numFences; i++) {
			st = new StringTokenizer(f.readLine());
			fenceX[i] = Integer.parseInt(st.nextToken());
			fenceY[i] = Integer.parseInt(st.nextToken());
			fenceXOrig[i] = fenceX[i];
			fenceYOrig[i] = fenceY[i];
			direction[i] = st.nextToken();
		}
		tempArr = fenceX.clone();
		Arrays.sort(tempArr);
		maxX = tempArr[tempArr.length - 1];
		minX = tempArr[0];
		tempArr1 = fenceY.clone();
		Arrays.sort(tempArr1);
		maxY = tempArr1[tempArr1.length - 1];
		minY = tempArr1[0];
		if (barnX > maxX) {
			maxX = barnX;
		}
		if (barnX < minX) {
			minX = barnX;
		}
		if (barnY > maxY) {
			maxY = barnY;
		}
		if (barnY < minY) {
			minY = barnY;
		}
		for (int i = 0; i < numFences; i++) {
			fenceX[i] = fenceX[i] - minX;
			fenceY[i] = fenceY[i] - minY;
			fenceXOrig[i] = fenceXOrig[i] - minX;
			fenceYOrig[i] = fenceYOrig[i] - minY;

		}
		barnX -= minX;
		barnY -= minY;
		maxX -= minX;
		maxY -= minY;
		homeX = 0 - minX;
		homeY = 0 - minY;

		numBounce = new int[maxY - minY + 1][maxX - minX + 1];
		bubbleSort(fenceY, fenceX, direction);
		grid = new int[maxY + 1][maxX + 1];

		for (int i = 0; i < maxY + 1; i++) {
			for (int j = 0; j < maxX + 1; j++) {
				if (count == numFences) {
					break;
				} else if (i == barnY && j == barnX) {
					grid[i][j] = 2;
				} else if (i == (fenceY[count]) && j == (fenceX[count])) {
					if (direction[count].charAt(0) == '/') {
						grid[i][j] = 4;
					} else {
						grid[i][j] = 3;
					}
					count++;
				} else {
					grid[i][j] = 0;
				}
			}
		}
		grid[homeY][homeX] = 1;
		index = calc(homeX, homeY, grid, barnX, barnY, out);
		out.print(index);
		System.out.println(index);
		out.close();
		// System.out.println("End");

	}

	public static int calc(int homeX, int homeY, int[][] grid, int barnX, int barnY, PrintWriter out) {
		String last = "r";
		boolean stop = false, good = false;
		for (int i = 0; i < numFences; i++) {
			int[][] hit = new int[maxY + 1][maxX + 1];
			int curPosX = homeX, curPosY = homeY;
			while (!stop) {
				if (curPosX == -1 || curPosY == -1 || curPosX == maxX + 1 || curPosY == maxY + 1) {
					stop = true;
					continue;
				}
				if (curPosX == barnX && curPosY == barnY) {
					good = true;
					break;
				}
				if (last == "r") {
					if (grid[curPosY][curPosX] == 3) {
						if (hit[curPosY][curPosX] == 2) {
							break;
						}
						hit[curPosY][curPosX]++;
						curPosY--;
						last = "d";

						continue;
					}
					if (grid[curPosY][curPosX] == 4) {
						if (hit[curPosY][curPosX] == 2) {
							break;
						}
						hit[curPosY][curPosX]++;
						curPosY++;
						last = "u";

						continue;
					}
					curPosX++;
					continue;
				}
				if (last == "l") {
					if (grid[curPosY][curPosX] == 3) {
						if (hit[curPosY][curPosX] == 2) {
							break;
						}
						hit[curPosY][curPosX]++;
						curPosY++;
						last = "u";

						continue;
					}
					if (grid[curPosY][curPosX] == 4) {
						if (hit[curPosY][curPosX] == 2) {
							break;
						}
						hit[curPosY][curPosX]++;
						curPosY--;
						last = "d";

						continue;
					}
					curPosX--;
					continue;
				}
				if (last == "u") {
					if (grid[curPosY][curPosX] == 3) {
						if (hit[curPosY][curPosX] == 2) {
							break;
						}
						hit[curPosY][curPosX]++;
						curPosX--;
						last = "l";

						continue;
					}
					if (grid[curPosY][curPosX] == 4) {
						if (hit[curPosY][curPosX] == 2) {
							break;
						}
						hit[curPosY][curPosX]++;
						curPosX++;
						last = "r";

						continue;
					}
					curPosY++;
					continue;
				}
				if (last == "d") {
					if (grid[curPosY][curPosX] == 3) {
						if (hit[curPosY][curPosX] == 2) {
							break;
						}
						hit[curPosY][curPosX]++;
						curPosX++;
						last = "r";

						continue;
					}
					if (grid[curPosY][curPosX] == 4) {
						if (hit[curPosY][curPosX] == 2) {
							break;
						}
						hit[curPosY][curPosX]++;
						curPosX--;
						last = "l";

						continue;
					}
					curPosY--;
					continue;
				}

			}
			if (good == true) {
				if (i == 0) {
					return 0;
				}
				for (int z = 0; z < numFences; z++) {
					if (fenceX[i - 1] == fenceXOrig[z] && fenceY[i - 1] == fenceYOrig[z]) {
						return z + 1;
					}
				}
			}
			if (i != 0) {
				if (grid[fenceY[i - 1]][fenceX[i - 1]] == 3) {
					grid[fenceY[i - 1]][fenceX[i - 1]] = 4;
				} else if (grid[fenceY[i - 1]][fenceX[i - 1]] == 4) {
					grid[fenceY[i - 1]][fenceX[i - 1]] = 3;
				}
			}
			if (grid[fenceY[i]][fenceX[i]] == 3) {
				grid[fenceY[i]][fenceX[i]] = 4;
			} else if (grid[fenceY[i]][fenceX[i]] == 4) {
				grid[fenceY[i]][fenceX[i]] = 3;
			}
			stop = false;
			last = "r";
		}
		return -1;
	}

	private static Object[] bubbleSort(int[] intArray, int[] other, String[] a) {
		int n = intArray.length;
		int temp = 0, temp2 = 0;
		String temp3;
		for (int i = 0; i < n; i++) {
			for (int j = 1; j < (n - i); j++) {

				if (intArray[j - 1] > intArray[j]) {
					// swap the elements!
					temp = intArray[j - 1];
					temp2 = other[j - 1];
					temp3 = a[j - 1];
					intArray[j - 1] = intArray[j];
					other[j - 1] = other[j];
					a[j - 1] = a[j];
					intArray[j] = temp;
					other[j] = temp2;
					a[j] = temp3;

				}
				if (intArray[j - 1] == intArray[j]) {
					if (other[j - 1] > other[j]) {
						// swap the elements!
						temp = intArray[j - 1];
						temp2 = other[j - 1];
						temp3 = a[j - 1];
						intArray[j - 1] = intArray[j];
						other[j - 1] = other[j];
						a[j - 1] = a[j];
						intArray[j] = temp;
						other[j] = temp2;
						a[j] = temp3;
					}
				}
			}
		}
		return new Object[] { intArray, other, a };
	}
}
