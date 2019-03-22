import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class censor {
	static String target, phrase;
	static int index = 1;
	static String before, after;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("censor.in"));
		File file = new File("censor.out");
		PrintWriter out = new PrintWriter(file);
		target = f.readLine();
		phrase = f.readLine();
		calc();
		System.out.println(target);
		out.println(target);
		out.close();
	}

	public static void calc() {
		while (true) {
			index = target.indexOf(phrase, index - (phrase.length() + 1));
			if (index < 0) {
				break;
			}
			before = target.substring(0, index);
			after = target.substring(index + phrase.length());
			target = before + after;
		}

	}

}
