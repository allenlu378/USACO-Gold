
/*
ID: allenlu2
LANG: JAVA
TASK: milk
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class milk {
	static int numMilk, numFarm, cost = 0;
	static int[] units, price;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("milk.in"));
		File file = new File("milk.out");
		PrintWriter out = new PrintWriter(file);
		StringTokenizer st = new StringTokenizer(f.readLine());
		numMilk = Integer.parseInt(st.nextToken());
		numFarm = Integer.parseInt(st.nextToken());
		units = new int[numFarm];
		price = new int[numFarm];
		for (int i = 0; i < numFarm; i++) {
			st = new StringTokenizer(f.readLine());
			price[i] = Integer.parseInt(st.nextToken());
			units[i] = Integer.parseInt(st.nextToken());
		}
		Object[] dummy = bubbleSort(price, units);
		price = (int[]) dummy[0];
		units = (int[]) dummy[1];
		calc();
		System.out.println(cost + "\n");
		out.print(cost + "\n");
		out.close();
	}

	public static void calc() {
		for (int i = 0; i < numFarm; i++) {
			if (numMilk <= units[i]) {
				cost += numMilk * price[i];
				break;
			}
			numMilk -= units[i];
			cost += price[i] * units[i];

		}

	}

	private static Object[] bubbleSort(int[] intArray, int[] other) {
		int n = intArray.length;
		int temp = 0, temp2 = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 1; j < (n - i); j++) {

				if (intArray[j - 1] > intArray[j]) {
					// swap the elements!
					temp = intArray[j - 1];
					temp2 = other[j - 1];
					intArray[j - 1] = intArray[j];
					other[j - 1] = other[j];
					intArray[j] = temp;
					other[j] = temp2;
				}
			}
		}
		return new Object[] { intArray, other };
	}

}
