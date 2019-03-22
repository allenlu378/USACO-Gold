import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class measurement {
	static Hashtable<Integer, Integer> gOut = new Hashtable<Integer, Integer>();
	static LinkedList<Integer> cowsMax = new LinkedList<Integer>();
	static int numMeasure, initialG, curMax, numChange = 0;
	static String[] measures;
	static int temp;
	static String temp2;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader f = new BufferedReader(new FileReader("measurement.in"));
		File file = new File("measurement.out");
		PrintWriter out = new PrintWriter(file);
		StringTokenizer st = new StringTokenizer(f.readLine());
		numMeasure = Integer.parseInt(st.nextToken());
		curMax = initialG = Integer.parseInt(st.nextToken());
		measures = new String[numMeasure];
		for (int i = 0; i < numMeasure; i++) {
			measures[i] = f.readLine();
			st = new StringTokenizer(measures[i]);
			st.nextToken();
			gOut.put(Integer.parseInt(st.nextToken()), initialG);
		}
		Arrays.sort(measures);
		for (int i = 0; i < numMeasure; i++) {
			st = new StringTokenizer(measures[i]);
			st.nextToken();
			temp = Integer.parseInt(st.nextToken());
			temp2 = st.nextToken();
			if (temp2.indexOf('-') != -1) {
				gOut.put(temp, gOut.get(temp) - Integer.parseInt(temp2.substring(1)));

			}
			if (temp2.indexOf('+') != -1) {
				gOut.put(temp, gOut.get(temp) + Integer.parseInt(temp2.substring(1)));
			}
			if (cowsMax.contains(temp) && gOut.get(temp) < curMax) {
				cowsMax.removeFirstOccurrence(temp);
				if (cowsMax.isEmpty()) {
					LinkedList<Integer> clone = new LinkedList<Integer>();
					clone = (LinkedList<Integer>) gOut.clone();
					Collections.sort(clone);
					curMax = clone.peekLast();
					while (clone.indexOf(curMax) != -1) {
						cowsMax.addLast(clone.indexOf(curMax));
						clone.removeFirstOccurrence(curMax);
					}
					numChange++;
					continue;

				}
			}
			if (gOut.get(temp) == curMax) {
				cowsMax.add(temp);
				numChange++;
			} else if (gOut.get(temp) > curMax) {
				cowsMax.clear();
				cowsMax.add(temp);
				curMax = gOut.get(temp);
				numChange++;
			}

		}
		System.out.println("End");
		System.out.println(numChange);
		out.println(numChange);
		out.close();
	}

}