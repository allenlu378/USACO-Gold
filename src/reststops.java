import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class reststops {
	static int length, john, bess, numStops, stopPos, taste, curMax = 0, posMax = 0, lastPos = 0, diff, arrlen;
	static long tasteMax = 0;
	static int[] tasteArr = new int[100000], dist = new int[100000];

	public static void main(String[] args) throws IOException {

		BufferedReader f = new BufferedReader(new FileReader("reststops.in"));
		File file = new File("reststops.out");
		PrintWriter out = new PrintWriter(file);
		StringTokenizer st = new StringTokenizer(f.readLine());
		length = Integer.parseInt(st.nextToken());
		numStops = Integer.parseInt(st.nextToken());
		john = Integer.parseInt(st.nextToken());
		bess = Integer.parseInt(st.nextToken());
		for (int i = 0; i < numStops; i++) {
			st = new StringTokenizer(f.readLine());
			stopPos = Integer.parseInt(st.nextToken());
			taste = Integer.parseInt(st.nextToken());
			tasteArr[i] = taste;
			dist[i] = stopPos;
		}
		curMax = Arrays.stream(tasteArr).max().getAsInt();
		int index = IntStream.range(0, tasteArr.length).filter(i -> tasteArr[i] == curMax).findFirst().orElse(-1);
		posMax = dist[index];
		tasteMax += (long) ((long) posMax * ((long) john - bess)) * (long) curMax;
		if (index < tasteArr.length) {
			tasteArr = Arrays.copyOfRange(tasteArr, index + 1, tasteArr.length);
			dist = Arrays.copyOfRange(dist, index + 1, dist.length);
			while (true) {
				if (tasteArr.length == 0) {
					break;
				}
				curMax = Arrays.stream(tasteArr).max().getAsInt();
				if (curMax == 0) {
					break;
				}
				lastPos = posMax;
				index = IntStream.range(0, tasteArr.length).filter(i -> tasteArr[i] == curMax).findFirst().orElse(-1);
				posMax = dist[index];
				tasteMax += (long) (posMax - lastPos) * (john - bess) * curMax;
				if (index == tasteArr.length - 1) {
					break;
				}
				tasteArr = Arrays.copyOfRange(tasteArr, index + 1, tasteArr.length);
				dist = Arrays.copyOfRange(dist, index + 1, dist.length);

			}
		}

		System.out.println(tasteMax);
		out.print(tasteMax);
		out.close();
	}

}
