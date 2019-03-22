import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class lightson {
	static int[][] rooms, visited, visited2;
	static int size, numSwitches, roomsVis = 0;
	static List<DataPair3>[][] connections;
	static boolean redo = false;

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("lightson.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("lightson.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		size = Integer.parseInt(st.nextToken());
		numSwitches = Integer.parseInt(st.nextToken());
		rooms = new int[size][size];
		visited = new int[size][size];
		visited2 = new int[size][size];
		connections = new LinkedList[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				connections[i][j] = new LinkedList<DataPair3>();
			}
		}
		for (int i = 0; i < numSwitches; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken()) - 1;
			int y1 = Integer.parseInt(st.nextToken()) - 1;
			int x2 = Integer.parseInt(st.nextToken()) - 1;
			int y2 = Integer.parseInt(st.nextToken()) - 1;
			connections[x1][y1].add(new DataPair3(x2, y2));
		}
		rooms[0][0] = 1;
		bye: dfs(0, 0);
		sec(0, 0);
		if (redo == true)
			roomsVis = 0;
		check();
		System.out.println("End");
		System.out.println(roomsVis);
		pw.println(roomsVis);
		pw.close();

	}

	public static void dfs(int x, int y) {
		if (x < 0 || x == size || y < 0 || y == size || rooms[y][x] == 0 || visited[y][x] == 1) {
			return;
		}
		visited[y][x] = 1;
		if (connections[x][y].isEmpty()) {

		} else {
			while (!connections[x][y].isEmpty()) {
				int first = connections[x][y].get(0).gety(), second = connections[x][y].get(0).getx();
				rooms[first][second] = 1;
				connections[x][y].remove(0);

			}
		}
		dfs(x + 1, y);
		dfs(x - 1, y);
		dfs(x, y + 1);
		dfs(x, y - 1);

	}

	public static void sec(int x, int y) {
		if (x < 0 || x == size || y < 0 || y == size || rooms[y][x] == 0 || visited2[y][x] == 1) {
			return;
		}
		visited2[y][x] = 1;
		if (!connections[x][y].isEmpty()) {
			int first = connections[x][y].get(0).gety(), second = connections[x][y].get(0).getx();
			rooms[first][second] = 1;
			connections[x][y].remove(0);
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					visited2[i][j] = 0;
				}
			}
			dfs(first, second);
			sec(0, 0);

		}
		sec(x + 1, y);
		sec(x - 1, y);
		sec(x, y + 1);
		sec(x, y - 1);
	}

	public static void check() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (visited2[i][j] == 1) {
					roomsVis++;
				}
			}
		}

	}

}

class DataPair3 implements Comparable<DataPair3> {

	private int x, y;

	// constructor
	DataPair3() {
		x = 0;
		y = 0;
	}

	public void setx(int s) {
		x = s;
	}

	public void sety(int e) {
		y = e;
	}

	DataPair3(int s, int e) {
		x = s;
		y = e;
	}

	public int getx() {
		return (x);
	}

	public int gety() {
		return (y);
	}

	@Override
	public int compareTo(DataPair3 arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

}