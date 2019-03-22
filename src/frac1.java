
/*
ID: allenlu2
LANG: JAVA
TASK: frac1
*/
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Fraction {

	private int num, den;
	static double ratio;

	public Fraction() {
		super();
		num = 1;
		den = 1;

		// TODO Auto-generated constructor stub
	}

	public Fraction(int num, int den) {
		super();
		this.num = num;
		this.den = den;

	}

	public int getNum() {
		return num;
	}

	public int getDen() {
		return den;
	}

	public double getRatio() {
		return num * 1.0 / den;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public void setDen(int den) {
		this.den = den;
	}

	public Fraction add(Fraction second) {
		Fraction third = new Fraction();
		third.num = this.num * second.den + this.den * second.num;
		third.den = this.den * second.den;
		return third;
	}

	public double findratio(double n, double d) {
		ratio = 0;

		ratio = n / d;

		return ratio;
	}

	@Override
	public String toString() {
		return num + "/" + den;
	}
}

public class frac1 {
	static List<Fraction> list = new ArrayList<Fraction>();
	static int num, counter = 2;

	public static void main(String[] args) throws IOException {

		Scanner in = new Scanner(new File("frac1.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("frac1.out")));
		num = in.nextInt();
		list.add(new Fraction(0, 1));
		list.add(new Fraction(1, 1));
		for (int denom = 2; denom <= num; denom++) {
			for (int numer = 1; numer < denom; numer++) {
				if (rPrime(numer, denom)) {
					addFrac(numer, denom);
				}
			}
		}
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
			out.println(list.get(i));
		}
		out.close();

	}

	public static void addFrac(int numer, int denom) {
		int i = 0;
		while (new Fraction(numer, denom).getRatio() > list.get(i).getRatio()) {
			i++;
		}
		list.add(i, new Fraction(numer, denom));
	}

	private static int gcd(int a, int b) {
		int t;
		while (b != 0) {
			t = a;
			a = b;
			b = t % b;
		}
		return a;
	}

	private static boolean rPrime(int a, int b) {
		return gcd(a, b) == 1;
	}

}
