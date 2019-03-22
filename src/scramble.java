import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class scramble {
	static String[] alpha, back;
	static String[] names;
	static int high, low;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("scramble.in"));
		File file = new File("scramble.out");
		PrintWriter out = new PrintWriter(file);
		StringTokenizer st = new StringTokenizer(f.readLine());
		Object[] dummy;
		int numCows = Integer.parseInt(st.nextToken());
		names = new String[numCows];
		alpha = new String[numCows];
		back = new String[numCows];
		for (int i = 0; i < numCows; i++) {
			names[i] = f.readLine();
			dummy = alpha(names[i]);
			alpha[i] = (dummy[0].toString());
			back[i] = (dummy[1].toString());
		}
		Arrays.sort(alpha);
		Arrays.sort(back);
		// Up alphabet
		for (int i = 0; i < numCows; i++) {
			dummy = alpha(names[i]);
			// high = searchHigh(back, dummy[0].toString());
			high = Arrays.binarySearch(back, dummy[0].toString());
			if (high < 0) {
				high = -(high + 1);
			}
			high++;
			// low = searchLow(alpha, dummy[1].toString());
			low = Arrays.binarySearch(alpha, dummy[1].toString());
			if (low < 0) {
				low = -(low + 1);
			} else {
				low++;
			}
			System.out.println(high + " " + low);
			out.println(high + " " + low);
		}
		out.close();
	}

	public static Object[] alpha(String cow) {
		char[] temp = cow.toCharArray();
		String woc;
		Arrays.sort(temp);
		cow = new String(temp);
		woc = new StringBuilder(new String(temp)).reverse().toString();
		return new Object[] { cow, woc };
	}

	public static int searchHigh(String[] arr, String cow) {
		int index;
		if (Arrays.asList(arr).contains(cow)) {
			index = Arrays.binarySearch(arr, cow) + 1;
		} else {
			index = (-(Arrays.binarySearch(arr, cow) + 1)) + 1;
		}
		return index;
	}

	public static int searchLow(String[] arr, String cow) {
		int index;
		if (Arrays.asList(arr).contains(cow)) {
			index = Arrays.binarySearch(arr, cow) + 1;
		} else {
			index = (-(Arrays.binarySearch(arr, cow) + 1));
		}
		return index;
	}

}
