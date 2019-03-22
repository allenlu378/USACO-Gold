
/*
ID: allenlu2
LANG: JAVA
TASK: combo
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class combo {
	static List<String> used = new ArrayList<String>();
	static int[] john = new int[3], master = new int[3];
	static int total = 0, size;

	public static void main(String[] args) throws IOException {

		BufferedReader f = new BufferedReader(new FileReader("combo.in"));
		File file = new File("combo.out");
		PrintWriter out = new PrintWriter(file);
		StringTokenizer st = new StringTokenizer(f.readLine());
		size = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(f.readLine());
		for (int i = 0; i < 3; i++) {

			john[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(f.readLine());
		for (int i = 0; i < 3; i++) {

			master[i] = Integer.parseInt(st.nextToken());
		}
		put(john[0] - 2, john[1] - 2, john[2] - 2);
		count(master[0] - 2, master[1] - 2, master[2] - 2);
		System.out.println(total);
		out.print(total + "\n");
		out.close();
	}

	public static void put(int num1, int num2, int num3) {
		if (num1 <= 0) {
			num1 = size + num1;
		}
		if (num2 <= 0) {
			num2 = size + num2;
		}
		if (num3 <= 0) {
			num3 = size + num3;
		}
		for (int i = num1; i < num1 + 5; i++) {
			for (int j = num2; j < num2 + 5; j++) {
				for (int k = num3; k < num3 + 5; k++) {
					if (check(String.valueOf(i % size) + j % size + k % size)) {
						total++;
						used.add(String.valueOf(i % size) + j % size + k % size);
					}

				}
			}
		}

	}

	public static void count(int num1, int num2, int num3) {
		if (num1 <= 0) {
			num1 = size + num1;
		}
		if (num2 <= 0) {
			num2 = size + num2;
		}
		if (num3 <= 0) {
			num3 = size + num3;
		}
		for (int i = num1; i < num1 + 5; i++) {
			for (int j = num2; j < num2 + 5; j++) {
				for (int k = num3; k < num3 + 5; k++) {
					if (check(String.valueOf(i % size) + j % size + k % size)) {
						total++;
						used.add(String.valueOf(i % size) + j % size + k % size);
					}

				}
			}
		}

	}

	public static boolean check(String str) {
		if (used.contains(str)) {
			return false;
		}
		return true;
	}

}
