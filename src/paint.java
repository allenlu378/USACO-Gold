import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.StringTokenizer;

public class paint {
	static int answer = 0, pos = 0;
	static Hashtable<Integer, Integer> moves;
	static int[] num, turnPos, turnPosReal;
	static int temp, numTurns = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("paint.in"));
		File file = new File("paint.out");
		PrintWriter out = new PrintWriter(file);
		StringTokenizer st = new StringTokenizer(f.readLine());
		int numMoves = Integer.parseInt(st.nextToken()), temp, count = 1, min = 1000000000, max = -1000000000;
		moves = new Hashtable<Integer, Integer>(numMoves);
		moves.put(0, 0);
		String let;
		boolean right = false;
		turnPos = new int[numMoves];
		for (int i = 0; i < numMoves; i++) {
			StringTokenizer e = new StringTokenizer(f.readLine());
			temp = Integer.parseInt(e.nextToken()) + count;
			let = e.nextToken();
			while (count < temp) {
				if (let.equals("L")) {
					if (right == true && i != 0) {
						numTurns++;
						turnPos[numTurns - 1] = pos;
					}
					right = false;
					pos--;
					moves.put(count, pos);
					if (pos < min) {
						min = pos;
					}
					if (pos > max) {
						max = pos;
					}
				} else {
					if (right == false && i != 0) {
						numTurns++;
						turnPos[numTurns - 1] = pos;
					}
					right = true;
					pos++;
					moves.put(count, pos);
					if (pos < min) {
						min = pos;
					}
					if (pos > max) {
						max = pos;
					}
				}
				count++;

			}

		}
		if (max < 0) {
			max = 0;
		}
		if (min > 0) {
			min = 0;
		}
		turnPosReal = new int[numTurns];
		for (int i = 0; i < numTurns; i++) {
			turnPosReal[i] = turnPos[i];
		}
		count = 0;
		num = new int[max - min + 2];
		for (int z = 0; z < moves.size(); z++) {
			num[moves.get(z) - min]++;
			if (count < numTurns) {
				if (moves.get(z) == turnPosReal[count]) {
					count++;
					num[moves.get(z) - min]++;
				}
			}
		}
		int inRow = 0;
		for (int z = 0; z < num.length; z++) {
			if (num[z] >= 2) {
				inRow++;
			} else {
				if (inRow == 0) {

				} else {
					answer += (inRow - 1);
					inRow = 0;
				}
			}
		}
		System.out.println("Size " + moves.size());
		System.out.println(answer);
		out.print(answer);
		out.close();

	}

}
