import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.TreeMap;

/*
ID: allenlu2
LANG: JAVA
TASK: preface
*/
class RomanNumber {

	private final static TreeMap<Integer, String> map = new TreeMap<Integer, String>();

	static {

	}

	public final static String toRoman(int number) {
		TreeMap<Integer, String> map = new TreeMap<Integer, String>();
		map.put(1000, "M");
		map.put(900, "CM");
		map.put(500, "D");
		map.put(400, "CD");
		map.put(100, "C");
		map.put(90, "XC");
		map.put(50, "L");
		map.put(40, "XL");
		map.put(10, "X");
		map.put(9, "IX");
		map.put(5, "V");
		map.put(4, "IV");
		map.put(1, "I");
		int l = map.floorKey(number);
		if (number == l) {
			return map.get(number);
		}
		return map.get(l) + toRoman(number - l);
	}

}

public class preface {
	static int[] totals = new int[7];
	static int num;

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("preface.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("preface.out")));
		num = in.nextInt();
		for (int i = 1; i <= num; i++) {
			String temp = RomanNumber.toRoman(i);
			count(temp);
		}
		print(out);
		out.close();

	}

	public static void print(PrintWriter out) {
		int num = 0;
		for (int i = 6; i >= 0; i--) {
			if (totals[i] != 0) {
				num = i;
				break;
			}
		}
		for (int i = 0; i <= num; i++) {
			switch (i) {
			case 0:
				System.out.println("I " + totals[i]);
				out.println("I " + totals[i]);
				break;
			case 1:
				System.out.println("V " + totals[i]);
				out.println("V " + totals[i]);
				break;
			case 2:
				System.out.println("X " + totals[i]);
				out.println("X " + totals[i]);
				break;
			case 3:
				System.out.println("L " + totals[i]);
				out.println("L " + totals[i]);
				break;
			case 4:
				System.out.println("C " + totals[i]);
				out.println("C " + totals[i]);
				break;
			case 5:
				System.out.println("D " + totals[i]);
				out.println("D " + totals[i]);
				break;
			case 6:
				System.out.println("M " + totals[i]);
				out.println("M " + totals[i]);
				break;
			}
		}
	}

	public static void count(String str) {
		for (int i = 0; i < str.length(); i++) {
			switch (str.charAt(i)) {
			case 'I':
				totals[0]++;
				break;
			case 'V':
				totals[1]++;
				break;
			case 'X':
				totals[2]++;
				break;
			case 'L':
				totals[3]++;
				break;
			case 'C':
				totals[4]++;
				break;
			case 'D':
				totals[5]++;
				break;
			case 'M':
				totals[6]++;
				break;

			}
		}
	}

}
