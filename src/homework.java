
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class homework {
	static int numQ, total = 0, curNumQ, removed;
	static int max = -1;
	static LinkedList<Integer> indexes = new LinkedList<Integer>(), findMin = new LinkedList<Integer>();
	static int[] grades;
	static String gradesStr;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("homework.in"));
		File file = new File("homework.out");
		PrintWriter out = new PrintWriter(file);
		numQ = Integer.parseInt(f.readLine());
		curNumQ = numQ - 2;
		grades = new int[numQ - 1];
		StringTokenizer st = new StringTokenizer(f.readLine());
		int temp;
		for (int i = 0; i < numQ; i++) {

			temp = Integer.parseInt(st.nextToken());
			if (i == 0) {
				continue;
			}
			total += temp;
			findMin.add(temp);
			grades[i - 1] = temp;
		}
		Collections.sort(findMin);
		for (int i = 0; i < numQ - 2; i++) {
			int temp2 = findMin.get(0);
			total -= temp2;
			if (total / curNumQ > max) {
				max = total / curNumQ;
				indexes.clear();
				indexes.add(numQ - curNumQ - 1);
			}
			curNumQ--;
			findMin.removeFirstOccurrence(grades[i]);
			total -= grades[i];
			total += temp2;
		}
		System.out.println("End");
		for (int i = 0; i < indexes.size(); i++) {
			System.out.println(indexes.get(i));
			out.println(indexes.get(i));
		}
		out.close();

	}

}
