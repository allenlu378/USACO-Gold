
/*
ID: allenlu2
LANG: JAVA
TASK: dualpal
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class dualpal {
	static int next, lowest, found = 0, curNum;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("dualpal.in"));
		File file = new File("dualpal.out");
		PrintWriter out = new PrintWriter(file);
		StringTokenizer st = new StringTokenizer(f.readLine());
		next = Integer.parseInt(st.nextToken());
		lowest = Integer.parseInt(st.nextToken());
		curNum = lowest + 1;
		while (found < next) {
			if (curNum == 99) {
				System.out.println("Wait");
			}
			if (checkBase(curNum)) {
				found++;
				System.out.print(curNum + "\n");
				out.print(curNum + "\n");

			}
			curNum++;
		}
		out.close();
	}

	public static boolean checkBase(int dec) {
		int numFound = 0;
		for (int i = 2; i < 11; i++) {
			if (numFound == 2) {
				return true;
			}
			if (checkPalindrome(Integer.toString(Integer.parseInt(String.valueOf(dec), 10), i).toUpperCase())) {
				numFound++;
			}
			if (numFound == 2) {
				return true;
			}
		}
		return false;
	}

	public static boolean checkPalindrome(String number) {
		for (int i = 0; i < number.length() / 2; i++) {
			if (number.charAt(i) != number.charAt(number.length() - 1 - i)) {
				return false;
			}
		}
		return true;
	}

}
