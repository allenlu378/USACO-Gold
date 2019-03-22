
/*
ID: allenlu2
LANG: JAVA
TASK: skidesign
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class skidesign {
	static int numHill, costFin = 1000000000, costTemp = 0;
	static int[] heights;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("skidesign.in"));
		File file = new File("skidesign.out");
		PrintWriter out = new PrintWriter(file);
		StringTokenizer st = new StringTokenizer(f.readLine());
		numHill = Integer.parseInt(st.nextToken());
		heights = new int[numHill];
		for (int i = 0; i < numHill; i++) {
			st = new StringTokenizer(f.readLine());
			heights[i] = Integer.parseInt(st.nextToken());
		}
		calc();
		System.out.println(costFin);
		out.println(costFin);
		out.close();
	}

	public static void calc() {
		int high = -1, low = 101, diff, lowLim, highLim;
		for (int i = 0; i < numHill; i++) {
			if (heights[i] > high) {
				high = heights[i];
			}
			if (heights[i] < low) {
				low = heights[i];
			}
		}
		diff = high - low;
		int[] costs = new int[diff + 1];
		if (diff > 17) {
			for (int i = 0; i < diff + 1; i++) {

				lowLim = low + i;
				highLim = lowLim + 17;
				checkBelow(lowLim);
				checkAbove(highLim);
				if (costTemp < costFin) {
					costFin = costTemp;
				}
				costTemp = 0;
			}
		} else {
			costFin = 0;
		}

	}

	public static void checkBelow(int num) {
		for (int i = 0; i < numHill; i++) {
			if (heights[i] < num) {
				costTemp += Math.pow(num - heights[i], 2);

			}
		}
	}

	public static void checkAbove(int num) {
		for (int i = 0; i < numHill; i++) {
			if (heights[i] > num) {
				costTemp += Math.pow(heights[i] - num, 2);
			}
		}
	}

}
