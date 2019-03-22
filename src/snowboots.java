import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class snowboots {
	static int numTiles, numBoots, curTile = 0, dis = 0;
	static int[] depth, step, tiles;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("snowboots.in"));
		File file = new File("snowboots.out");
		PrintWriter out = new PrintWriter(file);
		StringTokenizer st = new StringTokenizer(f.readLine());
		numTiles = Integer.parseInt(st.nextToken());
		numBoots = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(f.readLine());
		depth = new int[numBoots];
		step = new int[numBoots];
		tiles = new int[numTiles];
		for (int i = 0; i < numTiles; i++) {
			tiles[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < numBoots; i++) {
			st = new StringTokenizer(f.readLine());
			depth[i] = Integer.parseInt(st.nextToken());
			step[i] = Integer.parseInt(st.nextToken());
		}
		boolean usable, done = false;

		for (int i = 0; i < numBoots; i++) {

			usable = false;
			int start = Math.min(curTile + step[i], numTiles - 1);
			for (int j = start; j > curTile; j--) {

				if (depth[i] >= tiles[j]) {

					usable = true;
					curTile = j;
					break;
				}

			}
			if (curTile >= numTiles - 1) {
				done = true;
			}
			if (done) {
				break;
			}
			if (usable) {
				i--;
			} else {
				dis++;
			}
		}
		System.out.println(dis);
		out.print(dis);
		out.close();
	}
}
