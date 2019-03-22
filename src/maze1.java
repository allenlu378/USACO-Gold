
/*
ID: allenlu2
LANG: JAVA
TASK: maze1
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class maze1 {

	private int w;
	private int h;
	private String[] map;

	public int solve(int w, int h, String[] map) {
		this.w = w;
		this.h = h;
		this.map = map;
		int n = w * h;

		int[] dists = new int[n];
		for (int i = 0; i < n; i++) {
			dists[i] = Integer.MAX_VALUE;
		}

		for (int i = 0; i < n; i++) {
			if (hasExit(i)) {
				int[] dist = dijkstra(i);
				for (int j = 0; j < n; j++) {
					dists[j] = Math.min(dist[j], dists[j]);
				}
			}
		}

		int res = 0;
		for (int i = 0; i < n; i++) {
			res = Math.max(res, dists[i]);
		}

		return res + 1;
	}

	private boolean hasExit(int checking) {
		// convert to 2D point
		int x = checking / w;
		int y = checking % w;

		if (x == h - 1 && map[2 * x + 2].charAt(2 * y + 1) == ' ') {
			return true;
		}
		if (y == w - 1 && map[2 * x + 1].charAt(2 * y + 2) == ' ') {
			return true;
		}
		if (y == 0 && map[2 * x + 1].charAt(2 * y) == ' ') {
			return true;
		}
		if (x == 0 && map[2 * x].charAt(2 * y + 1) == ' ') {
			return true;
		}
		return false;
	}

	private int[] dijkstra(int source) {
		int n = w * h;
		int[] distance = new int[n];
		for (int i = 0; i < n; i++) {
			distance[i] = Integer.MAX_VALUE;
		}
		distance[source] = 0;
		boolean[] visited = new boolean[n];
		int visitedNodes = 0;

		while (visitedNodes < n) {
			int checking = minDist(distance, visited);
			for (int v : neighbors(checking)) {
				// distance(checking,v) = 1
				distance[v] = Math.min(distance[v], distance[checking] + 1);
			}
			visited[checking] = true;
			visitedNodes++;
		}

		return distance;
	}

	private ArrayList<Integer> neighbors(int checking) {
		ArrayList<Integer> res = new ArrayList<Integer>();

		// convert to 2D point
		int x = checking / w;
		int y = checking % w;

		if (x < h - 1 && map[2 * x + 2].charAt(2 * y + 1) == ' ') {
			res.add(checking + w);
		}
		if (y < w - 1 && map[2 * x + 1].charAt(2 * y + 2) == ' ') {
			res.add(checking + 1);
		}
		if (y > 0 && map[2 * x + 1].charAt(2 * y) == ' ') {
			res.add(checking - 1);
		}
		if (x > 0 && map[2 * x].charAt(2 * y + 1) == ' ') {
			res.add(checking - w);
		}

		return res;
	}

	private int minDist(int[] distance, boolean[] visited) {
		assert (distance.length == visited.length);
		int res = -1;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < distance.length; i++) {
			if (visited[i])
				continue;
			if (min > distance[i]) {
				min = distance[i];
				res = i;
			}
		}
		return res;
	}

	public static void main(String[] args) throws IOException {
		String problemName = "maze1";
		BufferedReader f = new BufferedReader(new FileReader(problemName + ".in"));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int w = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());

		String[] map = new String[2 * h + 1];

		for (int i = 0; i < 2 * h + 1; i++) {
			map[i] = f.readLine();
		}

		int res = (new maze1()).solve(w, h, map);

		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(problemName + ".out")));
		out.println(res);
		out.close(); // close the output file
		System.exit(0); // don't omit this!
	}

}