
/*
ID: allenlu2
LANG:JAVA 
TASK:lamps
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class lamps {

	public static String[] finalSetup = new String[1000];
	public static int finalSetupCount = 0;

	public static void but1(boolean[] b) {
		for (int i = 0; i < b.length; i++) {
			b[i] = !b[i];
		}
	}

	public static void but2(boolean[] b) {
		for (int i = 1; i < b.length; i += 2) {
			b[i] = !b[i];
		}
	}

	public static void but3(boolean[] b) {
		for (int i = 0; i < b.length; i += 2) {
			b[i] = !b[i];
		}
	}

	public static void but4(boolean[] b) {
		for (int i = 0; i < b.length; i += 3) {
			b[i] = !b[i];
		}
	}

	public static void loop(int butCount, boolean[] lamps, int[] finalLamps) {
		if (butCount == 0) {
			boolean pass = true;
			for (int i = 0; i < finalLamps.length; i++) {
				if ((finalLamps[i] == 1 && !lamps[i]) || (finalLamps[i] == 2 && lamps[i])) {
					pass = false;
					break;
				}
			}
			if (pass) {
				boolean contains = false;
				String s = "";
				for (int i = 0; i < lamps.length; i++) {
					if (lamps[i]) {
						s += "1";
					} else {
						s += "0";
					}
				}
				for (int i = 0; i < finalSetupCount; i++) {
					if (finalSetup[i].equals(s)) {
						contains = true;
					}
				}
				if (!contains) {
					finalSetup[finalSetupCount++] = s;
				}
			}
		} else {
			/*
			 * The shortest switch combinations to cover all others switch combinations. /*
			 * > No switching > 1 > 3 > 2 > 1 2 1 4 > 1 2 4 > 1 4 > 4
			 */
			butCount = butCount - 1;
			loop(0, lamps, finalLamps);
			but1(lamps);
			loop(butCount % 2, lamps, finalLamps);
			but1(lamps);
			but2(lamps);
			loop(butCount % 2, lamps, finalLamps);
			but2(lamps);
			but3(lamps);
			loop(butCount % 2, lamps, finalLamps);
			but3(lamps);
			but4(lamps);
			loop(butCount % 2, lamps, finalLamps);
			but4(lamps);
			if (butCount >= 2) {
				but1(lamps);
				but4(lamps);
				loop(butCount % 2, lamps, finalLamps);
				but4(lamps);
				but1(lamps);
			}
			if (butCount >= 3) {
				but1(lamps);
				but2(lamps);
				but4(lamps);
				loop(butCount % 3, lamps, finalLamps);
				but4(lamps);
				but2(lamps);
				but1(lamps);
			}
			if (butCount >= 4) {
				but1(lamps);
				but2(lamps);
				but1(lamps);
				but4(lamps);
				loop(butCount % 4, lamps, finalLamps);
				but4(lamps);
				but1(lamps);
				but2(lamps);
				but1(lamps);
			}
		}
	}

	public static void main(String[] zzzz) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("lamps.in"));
		int N = Integer.parseInt(br.readLine());
		boolean[] lamps = new boolean[N];
		for (int i = 0; i < N; i++) {
			lamps[i] = true;
		}
		int[] finalL = new int[N];
		int C = Integer.parseInt(br.readLine());
		String[] onL = br.readLine().split(" ");
		for (int i = 0; i < onL.length - 1; i++) {
			finalL[Integer.parseInt(onL[i]) - 1] = 1;
		}
		String[] offL = br.readLine().split(" ");
		for (int i = 0; i < offL.length - 1; i++) {
			finalL[Integer.parseInt(offL[i]) - 1] = 2;
		}
		loop(C, lamps, finalL);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("lamps.out")));
		if (finalSetupCount == 0) {
			pw.println("IMPOSSIBLE");
		} else {
			Arrays.sort(finalSetup, 0, finalSetupCount);
			for (int i = 0; i < finalSetupCount; i++) {
				pw.println(finalSetup[i]);
			}
		}
		pw.close();
	}
}