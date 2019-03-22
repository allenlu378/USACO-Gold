import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;

/*
TASK: convention
LANG: JAVA
ID: allenlu2
 */
public class convention {
	static StreamTokenizer input;

	public static void main(String[] args) throws IOException {
		String prob = "convention";
		input = new StreamTokenizer(new BufferedReader(new FileReader(prob + ".in")));
		PrintWriter out = new PrintWriter(new FileWriter(prob + ".out"));
		int numCow = nextInt(), numBus = nextInt(), busSize = nextInt(), max = 0;
		int[] arrivalTime = new int[numBus * busSize];
		int[][] busOrder = new int[numBus][busSize];
		for (int i = 0; i < numCow; i++) {
			arrivalTime[i] = nextInt();
		}

		Arrays.sort(arrivalTime);
		for (int i = 0; i < numBus; i++) {
			for (int j = 0; j < busSize; j++) {
				busOrder[i][j] = arrivalTime[i * busSize + j];
			}
			if (busOrder[i][busSize - 1] - busOrder[i][0] > max) {
				max = busOrder[i][busSize - 1] - busOrder[i][0];
			}
		}
		System.out.println("Stop");
		System.out.println(max);
		out.println(max);
		out.close();

		/*
		 * int[] weightTimes = new int[noOfCustomers]; int customersServed = 0; int
		 * currentTime = 0; while (customersServed < noOfCustomers) { int minWeightTime
		 * = Integer.MAX_VALUE; int customerIndex = Integer.MIN_VALUE; int
		 * preparationTime = 0; for (int j = 0; j < noOfCustomers; j++) { if
		 * (arrivalTime[j] <= currentTime) { int weightTime = (currentTime -
		 * arrivalTime[j] + orderPreparationTime[j]); if (weightTime < minWeightTime) {
		 * minWeightTime = weightTime; customerIndex = j; preparationTime =
		 * orderPreparationTime[j]; } } } if (customerIndex != Integer.MIN_VALUE) {
		 * weightTimes[customerIndex] = minWeightTime; arrivalTime[customerIndex] =
		 * Integer.MAX_VALUE; customersServed++; currentTime += preparationTime; } }
		 * 
		 * int totalWeightTime = 0; for (int weightTime : weightTimes) { totalWeightTime
		 * += weightTime; } System.out.println((int) totalWeightTime / noOfCustomers);
		 */

	}

	static int nextInt() throws IOException {
		input.nextToken();
		return (int) input.nval;
	}

}
