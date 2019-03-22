
/*
ID: allenlu2
LANG: JAVA
TASK: milk2
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class milk2 {
	static int numF, longMilk = 0, longNone = 0, start, end, min = 1000001, max = -1;
	static int[] times = new int[1000000];
	static boolean lastEmpty = true;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader f = new BufferedReader(new FileReader("milk2.in"));
		File file = new File("milk2.out");
		PrintWriter out = new PrintWriter(file);
		StringTokenizer st = new StringTokenizer(f.readLine());
		numF = Integer.parseInt(st.nextToken());
		for (int i = 0; i < numF; i++) {
			st = new StringTokenizer(f.readLine());
			start = Integer.parseInt(st.nextToken());
			if (start < min) {
				min = start;
			}
			end = Integer.parseInt(st.nextToken());
			if (end > max) {
				max = end;
			}
			for (int j = start; j < end; j++) {
				times[j] = 1;
			}
		}
		int tempMilk = 0, tempNone = 0;
		for (int i = min; i < max; i++) {
			if (times[i] == 1 && lastEmpty == true) {
				if (tempNone > longNone) {
					longNone = tempNone;
				}
				tempNone = 0;
				tempMilk = 1;
				lastEmpty = false;
				continue;
			}
			if (times[i] == 1 && lastEmpty == false) {
				tempMilk++;
				lastEmpty = false;
				continue;
			}
			// 0
			if (times[i] == 0 && lastEmpty == false) {
				if (tempMilk > longMilk) {
					longMilk = tempMilk;
				}
				tempMilk = 0;
				tempNone = 1;
				lastEmpty = true;
				continue;
			}
			if (times[i] == 0 && lastEmpty == true) {
				tempNone++;
				lastEmpty = true;
				continue;
			}

		}
		if (tempNone > longNone) {
			longNone = tempNone;
		}
		if (tempMilk > longMilk) {
			longMilk = tempMilk;
		}
		System.out.println(longMilk + " " + longNone);
		out.print(longMilk + " " + longNone + "\n");
		out.close();
	}

}
