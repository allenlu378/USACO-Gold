import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
ID: allenlu2
LANG: JAVA
TASK: hamming
*/
public class hamming {
	static int num, length, distance, counter = 1;
	static List<String> list = new ArrayList<String>();
	static String str;

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("hamming.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hamming.out")));
		num = in.nextInt();
		length = in.nextInt();
		distance = in.nextInt();
		list.add(corrLen("0"));
		while (list.size() < num) {
			str = Integer.toBinaryString(counter);
			str = corrLen(str);
			if (counter == 11)
				System.out.println("Wait");
			if (checkDist(str)) {

				list.add(String.valueOf(counter));
			}
			counter++;
		}
		for (int i = 0; i < num; i++) {
			if (i == num - 1) {
				System.out.print(Integer.parseInt(list.get(i)) + "\n");
				out.print(Integer.parseInt(list.get(i)) + "\n");
			} else if (i % 10 == 0 && i != 0) {

				System.out.println();
				out.println();
				System.out.print(Integer.parseInt(list.get(i)) + " ");
				out.print(Integer.parseInt(list.get(i)) + " ");
			} else if (i % 10 == 9) {
				System.out.print(Integer.parseInt(list.get(i)));
				out.print(Integer.parseInt(list.get(i)));
			} else {

				System.out.print(Integer.parseInt(list.get(i)) + " ");
				out.print(Integer.parseInt(list.get(i)) + " ");
			}
		}
		out.close();

	}

	public static boolean checkDist(String string) {
		int diff = 0;
		for (int j = 0; j < list.size(); j++) {
			for (int i = 0; i < length; i++) {
				if (string.charAt(i) != corrLen(Integer.toBinaryString(Integer.parseInt(list.get(j)))).charAt(i)) {
					diff++;
				}
			}
			if (diff < distance) {
				return false;
			}
			diff = 0;
		}
		return true;
	}

	public static String corrLen(String string) {
		if (string.length() < length) {
			while (string.length() < length) {
				string = "0" + string;
			}
		}
		return string;
	}

}
