import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class relay {
	static int[] messengers, numTimes;
	static int numCows, numNotLoopy = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("relay.in"));
		File file = new File("relay.out");
		PrintWriter out = new PrintWriter(file);
		numCows = Integer.parseInt(f.readLine());
		messengers = new int[numCows];
		numTimes = new int[numCows];
		for (int i = 0; i < numCows; i++) {
			messengers[i] = Integer.parseInt(f.readLine());
		}
		for (int i = 0; i < numCows; i++) {
			countLoopy(i);
			for (int z = 0; z < numCows; z++) {
				numTimes[z] = 0;
			}
		}
		System.out.println(numNotLoopy);
		out.print(numNotLoopy);
		out.close();
	}

	public static void countLoopy(int startIndex) {
		if (startIndex > messengers.length || messengers[startIndex] == 0) {
			numNotLoopy++;
			return;
		}
		numTimes[startIndex]++;
		if (numTimes[startIndex] == 2) {
			return;
		}
		countLoopy(messengers[startIndex] - 1);
	}

}
