import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class shuffle {
	static int numCows;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("shuffle.in"));
		File file = new File("shuffle.out");
		PrintWriter out = new PrintWriter(file);
		numCows = Integer.parseInt(f.readLine());
		StringTokenizer st = new StringTokenizer(f.readLine());
	}

}
