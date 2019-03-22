import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.LinkedList;

public class homework2 {
	static int numQ, total, curMin;
	static String grades;
	static String[] gradesReady;
	static int curNumQ = 1;
	static Double max = -1.0;
	static LinkedList<Integer> index = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("homework.in"));
		File file = new File("homework.out");
		PrintWriter out = new PrintWriter(file);
		numQ = Integer.parseInt(f.readLine());
		grades = f.readLine();
		gradesReady = grades.split(" ");
		curMin = Integer.parseInt(gradesReady[gradesReady.length - 1]);
		total = Integer.parseInt(gradesReady[gradesReady.length - 1]);
		for (int i = gradesReady.length - 2; i >= 1; i--) {
			total += Integer.parseInt(gradesReady[i]);
			if (Integer.parseInt(gradesReady[i]) < curMin) {
				curMin = Integer.parseInt(gradesReady[i]);
			}
			total -= curMin;
			if (1.0 * total / curNumQ == max) {
				index.add(numQ - curNumQ - 1);
			} else if (1.0 * total / curNumQ > max) {
				max = 1.0 * total / curNumQ;
				index.clear();
				index.add(numQ - curNumQ - 1);
			}
			curNumQ++;
			total += curMin;

		}
		Collections.sort(index);
		System.out.println("End:");
		for (int i = 0; i < index.size(); i++) {
			if (index.get(i) != 0) {
				System.out.println(index.get(i));
				out.println(index.get(i));
			}
		}
		out.close();

	}

}
