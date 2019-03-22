import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.StringTokenizer;

public class cowrace {
	static int numBMoves, numEMoves, speed, time, count = 0, index = 1, numChanges = 0;
	static Hashtable<Integer, Integer> bPos, ePos;
	static char ahead = 'n';

	public static void main(String[] args) throws IOException {

		BufferedReader f = new BufferedReader(new FileReader("cowrace.in"));
		File file = new File("cowrace.out");
		PrintWriter out = new PrintWriter(file);
		StringTokenizer st = new StringTokenizer(f.readLine());
		numBMoves = Integer.parseInt(st.nextToken());
		bPos = new Hashtable<Integer, Integer>();
		numEMoves = Integer.parseInt(st.nextToken());
		ePos = new Hashtable<Integer, Integer>();
		bPos.put(0, 0);
		for (int i = 0; i < numBMoves; i++) {
			StringTokenizer ed = new StringTokenizer(f.readLine());
			speed = Integer.parseInt(ed.nextToken());
			time = Integer.parseInt(ed.nextToken());
			while (count < time) {
				bPos.put(index, bPos.get(index - 1) + speed);
				index++;
				count++;
			}
			count = 0;
		}
		count = 0;
		index = 1;
		ePos.put(0, 0);
		for (int i = 0; i < numEMoves; i++) {
			StringTokenizer ed = new StringTokenizer(f.readLine());
			speed = Integer.parseInt(ed.nextToken());
			time = Integer.parseInt(ed.nextToken());
			while (count < time) {
				ePos.put(index, ePos.get(index - 1) + speed);
				index++;
				count++;
			}
			count = 0;
		}
		compare();
		System.out.println(numChanges);
		out.println(numChanges);
		out.close();

	}

	public static void compare() {
		for (int i = 0; i < bPos.size(); i++) {
			if (bPos.get(i) > ePos.get(i) && ahead == 'n') {
				ahead = 'b';
				continue;
			}
			if (bPos.get(i) < ePos.get(i) && ahead == 'n') {
				ahead = 'e';
				continue;
			}
			if (bPos.get(i) > ePos.get(i) && ahead == 'e') {
				numChanges++;
				ahead = 'b';
				continue;
			}
			if (bPos.get(i) < ePos.get(i) && ahead == 'b') {
				numChanges++;
				ahead = 'e';
				continue;
			}
		}
	}

}
