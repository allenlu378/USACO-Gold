import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
ID: allenlu2
LANG: JAVA
TASK: sort3
*/
public class sort3 {
	static int num, switches = 0, fin = 1000000000;
	static List<Integer> pos1 = new ArrayList<Integer>(), pos2 = new ArrayList<Integer>(),
			pos3 = new ArrayList<Integer>();
	static int[] arr;

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("sort3.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sort3.out")));
		num = in.nextInt();
		arr = new int[num];
		for (int i = 0; i < num; i++) {
			arr[i] = in.nextInt();
			if (arr[i] == 1) {
				pos1.add(i);
			} else if (arr[i] == 2) {
				pos2.add(i);
			} else {
				pos3.add(i);
			}
		}
		// Switch all 1s first
		fin = num1OutPlace(arr.clone(), true);
		// Switch all 2s first

		System.out.println(fin);
		out.print(fin + "\n");
		out.close();

	}

	public static int num1OutPlace(int[] array, boolean first) {
		int num = 0;
		boolean not = true;
		if (first) {
			for (int i = 0; i < pos1.size(); i++) {

				if (array[i] != 1) {
					if (array[i] == 2) {
						array[i] = 1;
						for (int j = num; j < pos1.size(); j++) {
							if (pos1.get(j) >= pos1.size() && pos1.get(j) < pos1.size() + pos2.size()
									&& array[pos1.get(j)] != 2) {
								array[pos1.get(j)] = 2;
								not = false;
								break;
							}

						}
						if (not) {
							for (int j = 0; j < pos1.size(); j++) {
								if (pos1.get(j) >= pos1.size() && array[pos1.get(j)] == 1) {
									array[pos1.get(j)] = 2;
									break;
								}
							}
						}
						not = true;
					}
					if (array[i] == 3) {
						array[i] = 1;
						for (int j = num; j < pos1.size(); j++) {
							if (pos1.get(j) >= pos1.size() + pos2.size() && array[pos1.get(j)] != 3) {
								array[pos1.get(j)] = 3;
								not = false;
								break;
							}
						}
						if (not) {
							for (int j = 0; j < pos1.size(); j++) {
								if (pos1.get(j) >= pos1.size() && array[pos1.get(j)] == 1) {
									array[pos1.get(j)] = 3;
									break;
								}
							}
						}
						not = true;
					}

					num++;

				}
			}
			num += num1OutPlace(array, false);
			return num;
		} else

		{
			for (int i = pos1.size(); i < pos1.size() + pos2.size(); i++) {
				if (array[i] != 2) {
					num++;
				}
			}
		}
		return num;
	}

}
