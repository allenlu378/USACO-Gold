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
TASK: runround
*/
public class runround {
	static int num, counter;
	static int[] arr;
	static String str;
	static boolean done = false, changed = false;

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("runround.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("runround.out")));
		num = in.nextInt();

		counter = num + 1;
		while (!done) {
			if (counter == 1246895) {
				System.out.println("Stop");
			}
			arr = new int[String.valueOf(counter).length()];
			str = String.valueOf(counter);
			if (checkDig(str)) {
				isRound(counter, 0);

			}
			changed = false;
			counter++;
		}
		System.out.println(counter - 1);
		out.println(counter - 1);
		out.close();
	}

	public static void isRound(int number, int index) {
		if (changed) {
			return;
		}
		if (arr[index] == 1 && index == 0 && checkFull()) {
			done = true;
			changed = true;
			return;
		}
		if ((arr[index] == 1 && !checkFull()) || (arr[index] == 1 && index != 0 && checkFull())) {
			done = false;
			changed = true;
			return;
		}
		arr[index] = 1;
		isRound(number, (index + Integer.parseInt(str.substring(index, index + 1))) % arr.length);

	}

	public static boolean checkFull() {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == 0) {
				return false;
			}
		}
		return true;
	}

	public static boolean checkDig(String string) {
		List<Character> list = new ArrayList<Character>();
		for (int i = 0; i < string.length(); i++) {
			if (list.contains(string.charAt(i)) || string.charAt(i) == '0') {
				return false;
			}
			list.add(string.charAt(i));
		}
		return true;
	}

}
