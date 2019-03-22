
/*
ID: allenlu2
LANG: JAVA
TASK: namenum
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class namenum {
	static long num, index = -1;
	static String[] valid = new String[4531];
	static String str;
	static PrintWriter out;
	static boolean any = false;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("namenum.in"));
		BufferedReader fe = new BufferedReader(new FileReader("dict.txt"));
		File file = new File("namenum.out");
		out = new PrintWriter(file);
		StringTokenizer st = new StringTokenizer(f.readLine());
		for (int i = 0; i < 4531; i++) {
			valid[i] = fe.readLine();
		}
		num = Long.parseLong(st.nextToken());
		str = String.valueOf(num);
		make(index, "");
		if (!any) {
			System.out.println("NONE\n");
			out.print("NONE\n");
		}
		out.close();
	}

	public static void make(long digit, String name) {

		index++;
		if (name.length() == str.length()) {
			if (Arrays.asList(valid).contains(name.toUpperCase())) {
				any = true;
				System.out.print(name + "\n");
				out.print(name + "\n");
			}
			index--;

			name = name.substring(0, name.length() - 1);
			return;
		}

		digit = str.charAt((int) index) - '0';
		switch ((int) digit) {
		case 2:

			make(digit, name + 'A');
			make(digit, name + 'B');
			make(digit, name + 'C');
			break;
		case 3:
			make(digit, name + 'D');
			make(digit, name + 'E');
			// make(digit, name + 'F');
			break;
		case 4:
			make(digit, name + 'G');
			make(digit, name + 'H');
			make(digit, name + 'I');
			break;
		case 5:
			make(digit, name + 'J');
			make(digit, name + 'K');
			make(digit, name + 'L');
			break;
		case 6:
			make(digit, name + 'M');
			make(digit, name + 'N');
			make(digit, name + 'O');
			break;
		case 7:
			make(digit, name + 'P');
			make(digit, name + 'R');
			make(digit, name + 'S');
			break;
		case 8:
			make(digit, name + 'T');
			make(digit, name + 'U');
			// make(digit, name + 'V');
			break;
		case 9:
			make(digit, name + 'W');
			make(digit, name + 'X');
			make(digit, name + 'Y');
			break;
		}
		index--;

	}

}
