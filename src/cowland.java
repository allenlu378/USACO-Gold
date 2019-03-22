import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

class Graph1 {
	private int V; // No. of vertices
	private LinkedList<Integer> adj[], path; // Adjacency Lists
	int[] pred;
	private Stack<Integer> stack = new Stack<Integer>();

	// Constructor
	Graph1(int v) {
		V = v;
		pred = new int[V];
		adj = new LinkedList[v];
		path = new LinkedList<Integer>();
		for (int i = 0; i < v; i++)
			adj[i] = new LinkedList();
	}

	// Function to add an edge into the graph
	void addEdge(int v, int w) {
		adj[v].add(w);
		adj[w].add(v);
	}

	void clear() {
		pred = new int[V];
		path = new LinkedList<Integer>();
	}

	LinkedList<Integer> DFS(int src, int dest, int numV) {
		stack.push(src);
		while (!stack.isEmpty()) {
			int u = stack.pop();
			path.add(u);
			if (u == dest) {
				return path;
			}
			for (int i = 0; i < adj[u].size(); i++) {
				stack.push(adj[u].get(i));
			}

		}
		return path;
	}

	void BFS(int src, int dest, int numV) {
		LinkedList<Integer> queue = new LinkedList<Integer>();
		boolean[] visited = new boolean[numV];
		for (int i = 0; i < numV; i++) {
			visited[i] = false;
			pred[i] = -1;
		}
		visited[src] = true;
		queue.add(src);
		while (!queue.isEmpty()) {
			int u = queue.getFirst();
			queue.removeFirst();
			for (int i = 0; i < adj[u].size(); i++) {
				int temp = adj[u].get(i);
				if (visited[temp] == false) {
					visited[temp] = true;
					pred[temp] = u;
					queue.add(temp);

					// We stop BFS when we find
					// destination.
					if (temp == dest)
						return;
				}
			}
		}
	}

	LinkedList<Integer> print(int src, int dest, int numV) {
		BFS(src, dest, numV);
		LinkedList<Integer> path = new LinkedList<Integer>();
		int crawl = dest;
		path.add(crawl);
		while (pred[crawl] != -1) {
			path.add(pred[crawl]);
			crawl = pred[crawl];
		}
		return path;
	}
}

public class cowland {
	static int numRide, numQ, path1, path2;
	static int[] enjoyment;
	static Graph1 g;
	static LinkedList<Integer> pathR = new LinkedList<Integer>();

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("cowland.in"));
		File file = new File("cowland.out");
		PrintWriter out = new PrintWriter(file);
		StringTokenizer st = new StringTokenizer(f.readLine());
		numRide = Integer.parseInt(st.nextToken());
		g = new Graph1(numRide);
		numQ = Integer.parseInt(st.nextToken());
		enjoyment = new int[numRide];
		st = new StringTokenizer(f.readLine());
		for (int i = 0; i < numRide; i++) {
			enjoyment[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < (numRide - 1) * 2; i += 2) {
			st = new StringTokenizer(f.readLine());
			path1 = Integer.parseInt(st.nextToken());
			path2 = Integer.parseInt(st.nextToken());
			g.addEdge(path1 - 1, path2 - 1);
		}
		System.out.println("Stop");
		for (int i = 0; i < numQ; i++) {
			st = new StringTokenizer(f.readLine());
			if (Integer.parseInt(st.nextToken()) == 1) {
				int index = Integer.parseInt(st.nextToken());
				enjoyment[index - 1] = Integer.parseInt(st.nextToken());
			} else {
				pathR = g.print(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, numRide);
				// pathR = g.DFS(Integer.parseInt(st.nextToken()) - 1,
				// Integer.parseInt(st.nextToken()) - 1, numRide);

				Collections.reverse(pathR);
				out.println(xor(pathR));

				g.clear();
			}
		}
		out.close();
	}

	public static int xor(LinkedList<Integer> pathT) {
		int num3 = enjoyment[pathT.getFirst()] ^ enjoyment[pathT.get(1)];
		int index = 2;
		if (pathT.size() == 2) {
			return num3;
		}
		for (int j = 2; j < pathT.size(); j++) {
			num3 = num3 ^ enjoyment[pathT.get(index)];

			if (index == pathT.size() - 1) {
				return num3;
			} else {
				index++;
			}
		}
		return 0;
	}

}
