import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class superbull {
	static int numTeams;
	static long max = 0;
	static int[] ID, dist;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader f = new BufferedReader(new FileReader("superbull.in"));
		File file = new File("superbull.out");
		PrintWriter out = new PrintWriter(file);
		numTeams = Integer.parseInt(f.readLine());
		ID = new int[numTeams];
		dist = new int[numTeams];
		visited = new boolean[numTeams];
		for (int i = 0; i < numTeams; i++) {
			ID[i] = Integer.parseInt(f.readLine());
			visited[i] = false;
		}
		for (int i = 0; i < numTeams; i++) {

			int farthest = -1;
			for (int j = 0; j < numTeams; j++) {
				if (visited[j]) {
					continue;
				}
				if (farthest == -1 || dist[j] > dist[farthest]) {
					farthest = j;
				}
			}
			visited[farthest] = true;
			max += dist[farthest];
			for (int k = 0; k < numTeams; k++) {
				dist[k] = Math.max(dist[k], ID[k] ^ ID[farthest]);
			}

		}
		System.out.println(max);
		out.println(max);
		out.close();
	}

}
