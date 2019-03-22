
/*
ID: allenlu2
LANG: JAVA
TASK: pprime
*/
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class pprime {
	static int low, high;

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("pprime.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("pprime.out")));
		low = in.nextInt();
		high = in.nextInt();
		if (low == 5) {
			System.out.println(5);
			out.println(5);
			System.out.println(7);
			out.println(7);
		} else if (low == 6 || low == 7) {
			System.out.println(7);
			out.println(7);
		}
		if (low < 100) {
			// Generate 2 digit palin
			for (int i = 1; i < 10; i += 2) {

				if (isPrime(i * 10 + i) && i * 10 + i >= low && i * 10 + i <= high) {
					System.out.println(i * 10 + i);
					out.println(i * 10 + i);
				}
			}
		}
		if (low < 1000 && high >= 100) {
			// Generate 3 digit palin
			for (int i = 1; i < 10; i += 2) {
				for (int j = 0; j < 10; j++) {
					if (isPrime(i * 100 + j * 10 + i) && i * 100 + 10 * j + i >= low && i * 100 + j * 10 + i <= high) {
						System.out.println(i * 100 + j * 10 + i);
						out.println(i * 100 + j * 10 + i);
					}
				}
			}
		}
		if (low < 10000 && high >= 1000) {
			// Generate 4 digit palin
			for (int i = 1; i < 10; i += 2) {
				for (int j = 0; j < 10; j++) {
					int palin = i * 1000 + j * 100 + j * 10 + i;
					if (isPrime(palin) && palin >= low && palin <= high) {
						System.out.println(palin);
						out.println(palin);
					}
				}
			}
		}
		if (low < 100000 && high >= 10000) {
			// Generate 5 digit palin
			for (int i = 1; i < 10; i += 2) {
				for (int j = 0; j < 10; j++) {
					for (int k = 0; k < 10; k++) {
						int palin = i * 10000 + j * 1000 + k * 100 + j * 10 + i;
						if (isPrime(palin) && palin >= low && palin <= high) {
							System.out.println(palin);
							out.println(palin);
						}
					}
				}
			}
		}
		if (low < 1000000 && high >= 100000) {
			// Generate 6 digit palin
			for (int i = 1; i < 10; i += 2) {
				for (int j = 0; j < 10; j++) {
					for (int k = 0; k < 10; k++) {
						int palin = i * 100000 + j * 10000 + k * 1000 + k * 100 + j * 10 + i;
						if (isPrime(palin) && palin >= low && palin <= high) {
							System.out.println(palin);
							out.println(palin);
						}
					}
				}
			}
		}
		if (low < 10000000 && high >= 1000000) {
			// Generate 7 digit palin
			for (int i = 1; i < 10; i += 2) {
				for (int j = 0; j < 10; j++) {
					for (int k = 0; k < 10; k++) {
						for (int l = 0; l < 10; l++) {
							int palin = i * 1000000 + j * 100000 + k * 10000 + l * 1000 + k * 100 + j * 10 + i;
							if (isPrime(palin) && palin >= low && palin <= high) {
								System.out.println(palin);
								out.println(palin);
							}
						}
					}
				}
			}
		}
		out.close();

	}

	public static boolean isPrime(int num) {
		for (int i = 3; i * i <= num; i += 2) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}

}
