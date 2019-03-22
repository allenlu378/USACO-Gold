import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class censor2 {
	// input string
	static String FullStr;
	// pattern to match
	static String Pattern;
	// string after censorship
	static String ResultStr;
	// output debug info, if true, output the debug info
	static boolean Verbose = false;
	static int[] PatternAry;
	static String MatchMethod = "KMP";

	public static void main(String[] args) {
		Solver();
	}

	// constructor
	censor2(String Method) {
		MatchMethod = Method;
	};

	censor2() {
		MatchMethod = "JavaBuiltIn";
	};

	private static int Matching(String Text, String Pattern) {
		int Midx;
		if (MatchMethod == "")
			display("no Algorithm is not set, use Java Built in\n");
		else
			display("Match Algo: " + MatchMethod + "\n");
		switch (MatchMethod) {
		case "KMP":
			Midx = KMPStringMatch(Text, Pattern);
			break;
		case "BruteForce":
			Midx = BruteForce(Text, Pattern);
			break;
		case "JavaBuiltIn":
			Midx = Text.indexOf(Pattern);
		default:
			Midx = Text.indexOf(Pattern);
		}
		;

		return (Midx);

	}

	private static int BruteForce(String Text, String Pattern) {
		int Midx = -1;
		int i, j, m;
		for (i = 0; i < Text.length() - Pattern.length(); i++) {
			for (j = 0; j < Pattern.length(); j++) {
				if (Pattern.charAt(j) != Text.charAt(i + j)) {
					// if break, restart
					j = 0;
					break;
				}
				;
			}

			// check if match
			if (j >= Pattern.length()) {
				Midx = i;
				display("find match at index: " + Midx + "\n");
				break;
			}
		}
		return (Midx);
	};

	private static void display(String Str) {
		if (Verbose)
			System.out.println(Str);
	};

	public static void Solver() {
		display("start input\n");
		try {
			ProbInput();
		} catch (IOException ex) {
			ex.getStackTrace();
		}
		display("finish input\n");

		display("start problem solver\n");
		ProbSolver();
		display("finish solving the proble\n");

		try {
			ProbOutput();
		} catch (FileNotFoundException ex) {
			ex.getStackTrace();
		}
		display("done");
	}

	private static void ProbInput() throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("censor.in"));
		FullStr = f.readLine();
		Pattern = f.readLine();
		display("full string:" + FullStr + "\n");
		display("pattern:" + Pattern + "\n");

		// allocate the pattern array
		PatternAry = new int[Pattern.length()];
	}

	private static void ProbOutput() throws FileNotFoundException {
		// File f = new File("censor.out");
		File file = new File("censor.out");
		PrintWriter out = new PrintWriter(file);
		System.out.println(ResultStr);
		out.println(ResultStr);
		out.close();
		// display("result: " + ResultStr + "\n");

	};

	private static void patternArray(String Pattern, int Size, int[] Ary) {
		int prefix_len = -1; // length of the previous longest prefix suffix
		int i = 0;
		Ary[i] = prefix_len;
		// i++;prefix_len++;
		// calculate Ary[i] for i = 1, size -1
		while (i < Size) {
			// if( Pattern.charAt(prefix_len) == Pattern.charAt(i))
			// {
			// Ary[ i ] = prefix_len;
			// }
			// else{
			// Ary[ i ] = prefix_len;
			// };
			while (prefix_len >= 0 && Pattern.charAt(i) != Pattern.charAt(prefix_len)) {
				prefix_len = Ary[prefix_len];
			}
			i++;
			prefix_len++;
			// if use following special case, it will make first letter to be -1
			// if( prefix_len == 0 && i<Size &&
			// Pattern.charAt(prefix_len)==Pattern.charAt(i))
			// {
			// Ary[ i] = Ary[ prefix_len];
			// }
			// else
			// {
			Ary[i] = prefix_len;
			// };
		}
		;
	}

	// this function find the pattern Pat in String Txt and return the first index
	// if no pattern in txt, returns -1, otherwise return the index of all
	// occurrance
	private static int KMPStringMatch(String Txt, String Pat) {
		int Matchidx = -1;

		int M = Pat.length();
		int N = Txt.length();

		int i = 0; // index in txt
		int j = 0; // index in Pat

		// pre calculate the pattern array
		int[] lps = new int[M + 1];
		patternArray(Pat, M, lps);

		while (i < N) {
			if (Pat.charAt(j) == Txt.charAt(i)) {
				// if match, check next one
				i++;
				j++;
			} else {
				// mismatch after j matches
				// match lps[0]...lps[ j-1]
				// characters,
				// i -= lps[j];
				if (lps[j] == -1) {
					// if first letter is mismatch, need to move to next in s and reset j
					i++;
					j = 0;
				} else {
					// if not first letter is mismatch, i is already moved, just update d
					j = lps[j];
				}
			}

			if (j == M) {
				// find a match
				System.out.println("found pattern at index:" + i + "\n");
				Matchidx = i - j; // in txt, start from i-j, find a match string
				j = lps[j - 1];
				break;
			}

		}
		;
		return (Matchidx);
	}

	private static void ProbSolver() {
		// search the pattern and remove from string
		ResultStr = FullStr;
		int Midx;

		display("start searching\n");
		while ((Midx = Matching(ResultStr, Pattern)) != -1) {
			display("full test: " + ResultStr + "\n");
			display("Patter: " + Pattern + "\n");
			display("match at: " + Midx + "\n");
			if (Midx == 0) {
				// match at begining
				ResultStr = ResultStr.substring(Midx + Pattern.length(), ResultStr.length());
			} else
				ResultStr = ResultStr.substring(0, Midx)
						+ ResultStr.substring(Midx + Pattern.length(), ResultStr.length());

			display("New Str: " + ResultStr + "\n");
		}
	};
}