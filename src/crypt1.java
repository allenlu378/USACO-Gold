
/*
ID: allenlu2
LANG: JAVA
TASK: crypt1
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class crypt1 {
	static int num, ans = 0;
	static List<Integer> list = new ArrayList<Integer>();

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("crypt1.in"));
		File file = new File("crypt1.out");
		PrintWriter out = new PrintWriter(file);
		StringTokenizer st = new StringTokenizer(f.readLine());
		num = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(f.readLine());

		for (int i = 0; i < num; i++) {
			list.add(Integer.parseInt(st.nextToken()));
		}
		calc();
		System.out.println(ans);
		out.println(ans);
		out.close();

	}

	public static void calc() {
		for (int first = 0; first < num; first++) {
			for (int sec = 0; sec < num; sec++) {
				for (int third = 0; third < num; third++) {
					for (int one = 0; one < num; one++) {
						if (String.valueOf(multiply(list.get(first), list.get(sec), list.get(third), list.get(one)))
								.length() <= 3) {
							if (isDig(multiply(list.get(first), list.get(sec), list.get(third), list.get(one)))) {
								for (int two = 0; two < num; two++) {
									if (String.valueOf(
											multiply(list.get(first), list.get(sec), list.get(third), list.get(two)))
											.length() <= 3) {
										if (isDig(multiply(list.get(first), list.get(sec), list.get(third),
												list.get(two)))) {
											if (String.valueOf(multiply(list.get(first), list.get(sec), list.get(third),
													(list.get(two) * 10 + list.get(one)))).length() <= 4) {
												if (isDig(multiply(list.get(first), list.get(sec), list.get(third),
														(list.get(two) * 10 + list.get(one))))) {
													ans++;
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

	public static int multiply(int first, int sec, int third, int mult) {
		int product;
		product = (100 * first + 10 * sec + third) * mult;
		return product;
	}

	public static boolean isDig(int num) {
		String str = String.valueOf(num);
		for (int i = 0; i < 3; i++) {
			if (!list.contains(str.charAt(i) - 48)) {
				return false;
			}
		}
		return true;

	}

}
