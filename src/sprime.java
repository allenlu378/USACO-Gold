
/*
ID: allenlu2
LANG: JAVA
TASK: sprime
*/
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class sprime {
	static int length;
	static String str = "";
	static int[] arr = { 2, 3, 5, 7 };

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("sprime.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sprime.out")));
		length = in.nextInt();
		// Length 1
		for (int num : arr) {
			if (length >= 2) {
				// Length 2
				for (int a = 1; a < 10; a += 2) {
					// Length 3
					if (length >= 3) {
						for (int b = 1; b < 10; b += 2) {
							// Length 4
							if (length >= 4) {
								for (int c = 1; c < 10; c += 2) {
									// Length 5
									if (length >= 5) {
										for (int d = 1; d < 10; d += 2) {
											// Length 6
											if (length >= 6) {
												for (int e = 1; e < 10; e += 2) {
													// Length 7
													if (length >= 7) {
														for (int f = 1; f < 10; f += 2) {
															// Length 8
															if (length >= 8) {
																for (int g = 3; g < 10; g += 6) {

																	str = String.valueOf(num * 10000000 + a * 1000000
																			+ b * 100000 + c * 10000 + d * 1000
																			+ e * 100 + f * 10 + g);
																	for (int x = 0; x < length; x++) {
																		if (!isPrime(Integer.parseInt(str))) {
																			break;
																		}
																		str = str.substring(0, str.length() - 1);
																	}
																	if (str.length() == 0) {
																		System.out.println(num * 10000000 + a * 1000000
																				+ b * 100000 + c * 10000 + d * 1000
																				+ e * 100 + f * 10 + g);
																		out.println(num * 10000000 + a * 1000000
																				+ b * 100000 + c * 10000 + d * 1000
																				+ e * 100 + f * 10 + g);
																	}

																}
															} else {
																str = String.valueOf(num * 1000000 + a * 100000
																		+ b * 10000 + c * 1000 + d * 100 + e * 10 + f);
																for (int x = 0; x < length; x++) {
																	if (!isPrime(Integer.parseInt(str))) {
																		break;
																	}
																	str = str.substring(0, str.length() - 1);
																}
																if (str.length() == 0) {
																	System.out.println(
																			num * 1000000 + a * 100000 + b * 10000
																					+ c * 1000 + d * 100 + e * 10 + f);
																	out.println(num * 1000000 + a * 100000 + b * 10000
																			+ c * 1000 + d * 100 + e * 10 + f);
																}
															}
														}
													} else {
														str = String.valueOf(num * 100000 + a * 10000 + b * 1000
																+ c * 100 + d * 10 + e);
														for (int x = 0; x < length; x++) {
															if (!isPrime(Integer.parseInt(str))) {
																break;
															}
															str = str.substring(0, str.length() - 1);
														}
														if (str.length() == 0) {
															System.out.println(num * 100000 + a * 10000 + b * 1000
																	+ c * 100 + d * 10 + e);
															out.println(num * 100000 + a * 10000 + b * 1000 + c * 100
																	+ d * 10 + e);
														}
													}
												}
											} else {
												str = String.valueOf(num * 10000 + a * 1000 + b * 100 + c * 10 + d);
												for (int x = 0; x < length; x++) {
													if (!isPrime(Integer.parseInt(str))) {
														break;
													}
													str = str.substring(0, str.length() - 1);
												}
												if (str.length() == 0) {
													System.out.println(num * 10000 + a * 1000 + b * 100 + c * 10 + d);
													out.println(num * 10000 + a * 1000 + b * 100 + c * 10 + d);
												}
											}
										}
									} else {
										str = String.valueOf(num * 1000 + a * 100 + b * 10 + c);
										for (int x = 0; x < length; x++) {
											if (!isPrime(Integer.parseInt(str))) {
												break;
											}
											str = str.substring(0, str.length() - 1);
										}
										if (str.length() == 0) {
											System.out.println(num * 1000 + a * 100 + b * 10 + c);
											out.println(num * 1000 + a * 100 + b * 10 + c);
										}
									}
								}
							} else {
								str = String.valueOf(num * 100 + a * 10 + b);
								for (int x = 0; x < length; x++) {
									if (!isPrime(Integer.parseInt(str))) {
										break;
									}
									str = str.substring(0, str.length() - 1);
								}
								if (str.length() == 0) {
									System.out.println(num * 100 + a * 10 + b);
									out.println(num * 100 + a * 10 + b);
								}
							}
						}
					} else {
						str = String.valueOf(num * 10 + a);
						for (int x = 0; x < length; x++) {
							if (!isPrime(Integer.parseInt(str))) {
								break;
							}
							str = str.substring(0, str.length() - 1);
						}
						if (str.length() == 0) {
							System.out.println(num * 10 + a);
							out.println(num * 10 + a);
						}

					}
				}

			} else {
				System.out.println(num);
				out.println(num);
			}
		}
		out.close();
	}

	public static boolean isPrime(int num) {
		for (int i = 3; i * i <= num; i += 2) {
			if (num % i == 0 || num % 2 == 0) {
				return false;
			}
		}
		return true;
	}

}
