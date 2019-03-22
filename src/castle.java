import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
ID: allenlu2
LANG: JAVA
TASK: castle
*/
public class castle {
	static List[][] grid;
	static int numCol, numRow, index = 0, numRoom = 0, roomSize = 0, largestRoom = 0, startRow, startCol;
	static int[][] visited;
	static boolean first = true;
	static String wall, direct = "N";
	static List[] roomNums = new ArrayList[2500];

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("castle.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("castle.out")));
		numCol = in.nextInt();
		numRow = in.nextInt();
		grid = new ArrayList[numRow][numCol];
		visited = new int[numRow][numCol];
		for (int row = 0; row < numRow; row++) {
			for (int col = 0; col < numCol; col++) {
				findWalls(row, col, in.nextInt());
			}
		}
		for (int row = 0; row < numRow; row++) {
			for (int col = 0; col < numCol; col++) {
				first = true;
				if (visited[row][col] == 1) {

					continue;
				}
				roomNums[index] = new ArrayList();
				findNumRoom(row, col);
				index++;
			}
		}

		visited = new int[numRow][numCol];
		System.out.println(numRoom);
		System.out.println(largestRoom);
		out.println(numRoom);
		out.println(largestRoom);
		// Remove walls
		roomSize = 0;
		largestRoom = 0;
		for (int col = 0; col < numCol; col++) {
			for (int row = numRow - 1; row >= 0; row--) {
				first = true;
				if (visited[row][col] == 1) {
					continue;
				}
				r2(row, col);
			}
		}

		System.out.println(largestRoom);
		System.out.println(wall);
		out.println(largestRoom);
		out.println(wall);
		out.close();
	}

	public static void r2(int row, int col) {
		if (row != 0) {
			if (!grid[row][col].contains(row * numCol + col + 1 - numCol)) {
				for (int i = 0; i < roomNums.length; i++) {
					if (roomNums[i] == null) {

					} else if (roomNums[i].contains(row * numCol + col + 1)
							|| roomNums[i].contains(row * numCol + col + 1 - numCol)) {
						roomSize += roomNums[i].size();
					}

				}
				if (roomSize > largestRoom) {
					largestRoom = roomSize;
					wall = (row + 1) + " " + (col + 1) + " N";
				}
				roomSize = 0;
			}
		}
		if (col != numCol - 1) {
			if (!grid[row][col].contains(row * numCol + col + 1 + 1)) {
				for (int i = 0; i < roomNums.length; i++) {
					if (roomNums[i] == null) {

					} else if (roomNums[i].contains(row * numCol + col + 1)
							|| roomNums[i].contains(row * numCol + col + 1 + 1)) {
						roomSize += roomNums[i].size();
					}
				}
				if (roomSize > largestRoom) {
					largestRoom = roomSize;
					wall = (row + 1) + " " + (col + 1) + " E";
				}
				roomSize = 0;
			}
		}
	}

	public static void findNumRoom(int row, int col) {
		if (first) {
			startRow = row;
			startCol = col;
			first = false;
		}

		if (row < 0 || row >= numRow || col < 0 || col >= numCol) {
			return;
		}
		if (visited[row][col] == 1) {
			return;
		}
		roomNums[index].add(row * numCol + col + 1);
		roomSize++;
		visited[row][col] = 1;
		for (int i = 0; i < grid[row][col].size(); i++) {
			if ((int) grid[row][col].get(i) % numCol == 0) {
				findNumRoom(((int) grid[row][col].get(i)) / (numCol) - 1, numCol - 1);
			} else {
				findNumRoom((int) (grid[row][col].get(i)) / (numCol),
						(((int) grid[row][col].get(i) - (numCol * ((int) grid[row][col].get(i) / (numCol)))) - 1)
								% numCol);
			}
		}
		if (row == startRow && col == startCol) {
			first = true;
			numRoom++;
			if (roomSize > largestRoom) {
				largestRoom = roomSize;
			}
			roomSize = 0;
		}

	}

	public static void findWalls(int row, int col, int value) {
		grid[row][col] = new ArrayList<Integer>();
		switch (value) {
		case 0:
			eWall(row, col);
			sWall(row, col);
			nWall(row, col);
			wWall(row, col);
			break;
		case 1:
			eWall(row, col);
			sWall(row, col);
			nWall(row, col);
			break;
		case 2:
			sWall(row, col);
			eWall(row, col);
			wWall(row, col);
			break;
		case 3:
			eWall(row, col);
			sWall(row, col);
			break;
		case 4:
			wWall(row, col);
			nWall(row, col);
			sWall(row, col);
			break;
		case 5:
			nWall(row, col);
			sWall(row, col);
			break;
		case 6:
			sWall(row, col);
			wWall(row, col);
			break;
		case 7:
			sWall(row, col);
			break;
		case 8:
			nWall(row, col);
			eWall(row, col);
			wWall(row, col);
			break;
		case 9:
			eWall(row, col);
			nWall(row, col);
			break;
		case 10:
			eWall(row, col);
			wWall(row, col);
			break;
		case 11:
			eWall(row, col);
			break;
		case 12:
			nWall(row, col);
			wWall(row, col);
			break;
		case 13:
			nWall(row, col);
			break;
		case 14:
			wWall(row, col);
			break;
		case 15:
			break;
		}
	}

	public static void nWall(int row, int col) {
		if (row != 0) {
			grid[row][col].add((row - 1) * numCol + col + 1);

		}
	}

	public static void wWall(int row, int col) {
		if (col != 0) {
			grid[row][col].add(row * numCol + col);
		}
	}

	public static void eWall(int row, int col) {
		if (col != numCol - 1) {
			grid[row][col].add((row) * numCol + col + 2);
		}
	}

	public static void sWall(int row, int col) {
		if (row != numRow - 1) {
			grid[row][col].add((row + 1) * numCol + col + 1);
		}
	}

}
