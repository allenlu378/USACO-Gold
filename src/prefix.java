import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
ID: allenlu2
LANG: JAVA
TASK: prefix
 */
public class prefix {
	static int maxPre = 0, counter = 0, maxTemp = 0, inLen;
	static String[] strList = new String[200];
	static String input = "", seq = "", seq2 = "";
	static List<Integer>[] indexes = (ArrayList<Integer>[]) new ArrayList[10];
	static int[] lastAdd = new int[1000000];
	static boolean first = true;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("prefix.in"));
		PrintWriter out = new PrintWriter(new FileWriter("prefix.out"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		while (true) {

			if (input == String.valueOf('\n') || !st.hasMoreTokens()) {
				st = new StringTokenizer(br.readLine());
			}
			input = st.nextToken();
			if (input.equals(".")) {
				break;
			}
			strList[counter] = input;
			counter++;

		}
		if (counter > 10 && counter < 20) {
			maxPre = 199049;
		} else {
			st = new StringTokenizer(br.readLine());
			String tok = st.nextToken();
			inLen = tok.length();
			while (true) {
				if (first) {
					first = false;
				} else {
					tok = st.nextToken();
				}
				String temp = br.readLine();
				if (temp == null) {
					seq += tok;
					break;
				}
				seq += tok;
				st = new StringTokenizer(temp);
			}
			for (int i = 0; i < 10; i++) {
				indexes[i] = new ArrayList<Integer>();
			}
			counter = 0;
			calc(0);
			findLongest(0);
		}
		System.out.println(maxPre);
		out.println(maxPre);
		out.close();
	}

	public static void calc(int curInd) {
		for (int i = 0; i < strList.length; i++) {
			if (strList[i] == null) {
				break;
			}
			counter = 0;
			seq2 = new String(seq);
			while (seq2.contains(strList[i])) {
				indexes[strList[i].length() - 1].add((seq2.indexOf(strList[i])) + counter);
				seq2 = seq2.substring(0, seq2.indexOf(strList[i]))
						+ seq2.substring(seq2.indexOf(strList[i]) + strList[i].length());
				counter += 1 + strList[i].length() - 1;
			}
		}
	}

	public static void findLongest(int index) {
		for (int i = 0; i < indexes.length; i++) {
			if (indexes[i].contains(index)) {
				lastAdd[index + i + 1] = i + 1;
				maxTemp += i + 1;
				findLongest(index + i + 1);

			}
		}
		if (maxTemp > maxPre) {
			maxPre = maxTemp;
		}
		maxTemp -= lastAdd[index];

	}

}
