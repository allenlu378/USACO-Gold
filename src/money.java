import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class money {
	static int numCoin, ans = 0, sum = 0, most;
	static int[] coinsUsed;

	public static void main(String[] args) throws java.io.IOException {
		BufferedReader br = new BufferedReader(new FileReader("money.in"));
		PrintWriter out = new PrintWriter(new FileWriter("money.out"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		numCoin = Integer.parseInt(st.nextToken());
		int total = Integer.parseInt(st.nextToken());
		int[] coins = new int[numCoin];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < numCoin; i++) {
			coins[i] = Integer.parseInt(st.nextToken());
		}
		if (1.0 * total / coins[0] % 1 == 0) {
			most = total / coins[0];
		} else {
			most = total / coins[0] + 1;
		}
		coinsUsed = new int[most];
		ans = calc(coins, coins.length - 1, total);
		System.out.println(ans);

	}

	public static int calc(int[] coins, int n, int total) {
		if (total == 0) {
			return 1;
		}
		if (total < 0 || n < 0) {
			return 0;
		}
		int inc = calc(coins, n, total - coins[n]);
		int exc = calc(coins, n - 1, total);
		return inc + exc;
	}

}
