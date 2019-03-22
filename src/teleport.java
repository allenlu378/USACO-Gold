import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class teleport {
	static int numPiles, max = 0;
	static int[] bef, after;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("teleport.in"));
		File file = new File("teleport.out");
		PrintWriter out = new PrintWriter(file);
		StringTokenizer st = new StringTokenizer(f.readLine());
		numPiles = Integer.parseInt(st.nextToken());
		after = new int[numPiles];
		bef = new int[numPiles];
		for (int i = 0; i < numPiles; i++) {
			st = new StringTokenizer(f.readLine());
			bef[i] = Integer.parseInt(st.nextToken());
			after[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = -100000; i < 10000000; i++) {
			if (after[i] - bef[i] >= (bef[i] + (after[i] - i))) {
				max += after[i] - bef[i];
			}
		}
		System.out.print(max);
		out.print(max);
		out.close();
	}

}
