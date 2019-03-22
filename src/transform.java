
/*
ID: allenlu2
LANG: JAVA
TASK: transform
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class transform {
	static int size, type = 1;
	static int[][] before, after;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("transform.in"));
		File file = new File("transform.out");
		PrintWriter out = new PrintWriter(file);
		StringTokenizer st = new StringTokenizer(f.readLine());
		size = Integer.parseInt(st.nextToken());
		before = new int[size][size];
		for (int i = 0; i < size; i++) {
			st = new StringTokenizer(f.readLine());
			String str = st.nextToken();
			for (int j = 0; j < size; j++) {
				if (str.charAt(j) == '@') {
					before[i][j] = 0;
				} else {
					before[i][j] = 1;
				}
			}
		}
		after = new int[size][size];
		for (int i = 0; i < size; i++) {
			st = new StringTokenizer(f.readLine());
			String str2 = st.nextToken();
			for (int j = 0; j < size; j++) {
				if (str2.charAt(j) == '@') {
					after[i][j] = 0;
				} else {
					after[i][j] = 1;
				}
			}
		}

		if (check90(before)) {
			System.out.println(1);
			out.print(1 + "\n");
		} else if (check180(before)) {
			System.out.println(2);
			out.print(2 + "\n");
		} else if (check270(before)) {
			System.out.println(3);
			out.print(3 + "\n");
		} else if (reflect()) {
			System.out.println(4);
			out.print(4 + "\n");
		} else if (combo()) {
			System.out.println(5);
			out.print(5 + "\n");
		} else if (checkSame()) {
			System.out.println(6);
			out.print(6 + "\n");
		} else {
			System.out.println(7);
			out.print(7 + "\n");
		}

		out.close();

	}

	static public boolean checkSame() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (before[i][j] != after[i][j]) {
					return false;
				}
			}
		}
		return true;
	}

	static public boolean check90(int[][] arr) {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (arr[i][j] != after[j][size - 1 - i]) {
					return false;
				}
			}
		}
		return true;
	}

	static public boolean check180(int[][] arr) {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (arr[i][j] != after[size - 1 - i][size - 1 - j]) {
					return false;
				}
			}
		}
		return true;
	}

	static public boolean check270(int[][] arr) {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (arr[i][j] != after[size - 1 - j][i]) {
					return false;
				}
			}
		}
		return true;
	}

	static public boolean reflect() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (before[i][j] != after[i][size - 1 - j]) {
					return false;
				}
			}
		}
		return true;
	}

	static public boolean combo() {
		int[][] temp = new int[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				temp[i][j] = before[i][size - 1 - j];
			}
		}
		if (check90(temp) || check180(temp) || check270(temp)) {
			return true;
		}
		return false;
	}

}
