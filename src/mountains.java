import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

class mountain {
	private long x, y;

	public mountain(long x, long y) {
		this.x = x;
		this.y = y;
	}

	public long getX() {
		return x;
	}

	public long getY() {
		return y;
	}

	public boolean inside(long otherX, long otherY) {
		if (otherY - (otherX - x + y) <= 0) {
			if (otherY - ((-1 * otherX) + x + y) <= 0) {
				return true;
			}
		}
		return false;
	}

}

public class mountains {
	static int num, canSee;
	static mountain[] peaks;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("mountains.in"));
		File file = new File("mountains.out");
		PrintWriter out = new PrintWriter(file);
		StringTokenizer st = new StringTokenizer(f.readLine());
		num = Integer.parseInt(st.nextToken());
		peaks = new mountain[num];
		canSee = num;
		for (int i = 0; i < num; i++) {
			st = new StringTokenizer(f.readLine());
			peaks[i] = new mountain(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		checkInside();
		System.out.println(canSee);
		out.println(canSee);
		out.close();
	}

	public static void checkInside() {
		for (int i = 0; i < num; i++) {
			for (int j = 0; j < num; j++) {
				if (i == j) {
					continue;
				}
				if (peaks[j].inside(peaks[i].getX(), peaks[i].getY())) {
					canSee--;
					break;
				}
			}
		}
	}

}
