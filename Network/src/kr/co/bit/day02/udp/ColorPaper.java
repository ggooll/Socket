package kr.co.bit.day02.udp;

import java.util.Scanner;

public class ColorPaper {

	public static byte[][] matrix;
	public static int white = 0;
	public static int blue = 0;

	public static void divide(int x, int y, int len) {
		byte color = matrix[x][y];

		if (len != 1) {
			for (int i = x; i < x + len; i++) {
				for (int j = y; j < y + len; j++) {
					if (matrix[i][j] != color) {
						int newlen = len / 2;
						divide(x, y, newlen);
						divide(x, y + newlen, newlen);
						divide(x + newlen, y, newlen);
						divide(x + newlen, y + newlen, newlen);
						return;
					}
				}
			}
		}

		if (color == 1)
			blue++;
		else
			white++;
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int size = scan.nextInt();
		matrix = new byte[size][size];

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				matrix[i][j] = scan.nextByte();
			}
		}
		divide(0, 0, size);
		System.out.printf("%d\n%d", white, blue);
		scan.close();
	}
}
