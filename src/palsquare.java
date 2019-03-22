
/*
ID: allenlu2
LANG: JAVA
TASK: palsquare
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class palsquare {
	static int base;
	static String baseNum;
	static PrintWriter out;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("palsquare.in"));
		File file = new File("palsquare.out");
		out = new PrintWriter(file);
		StringTokenizer st = new StringTokenizer(f.readLine());
		base = Integer.parseInt(st.nextToken());
		calc();
		out.close();
	}

	public static void calc() {
		for (int i = 1; i < 301; i++) {
			if (checkPalindrome(returnNumInBase(i))) {
				System.out.print(origBase(i) + " " + baseNum + "\n");
				out.print(origBase(i) + " " + baseNum + "\n");

			}
		}
	}

	public static String returnNumInBase(int dec) {
		return Integer.toString(Integer.parseInt(String.valueOf(dec * dec), 10), base).toUpperCase();
	}

	public static String origBase(int num) {
		return Integer.toString(Integer.parseInt(String.valueOf(num), 10), base).toUpperCase();
	}

	public static boolean checkPalindrome(String number) {
		for (int i = 0; i < number.length() / 2; i++) {
			if (number.charAt(i) != number.charAt(number.length() - 1 - i)) {
				return false;
			}
		}
		baseNum = number;
		return true;
	}
}
