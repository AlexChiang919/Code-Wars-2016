import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class HexagonallyAmazing2 {

	private static final String PROBLEM = "prob18";
	private static final String EXT = "-1-in.txt";

	public static void main(String[] args) {
		Scanner scan;
		try {
			scan = new Scanner(new File(PROBLEM + EXT));
		} catch (FileNotFoundException ex) {
			printF(true, "File not found: %s", ex.getMessage());
			return;
		}
		String[] split = scan.nextLine().split(" ");
		int a = Integer.parseInt(split[0]);
		int b = Integer.parseInt(split[1]);
		char[][] array = new char[a + (a - 1)][b + ((b - 1) * 3)];
		fillArray(array, ' ');
		for (int i = 0; i < (a + (a - 1)); i++) {
			char[] hi = scan.nextLine().toCharArray();
			for (int j = 0; j < hi.length; j++) {
				array[i][j] = hi[j];
			}
		}
		String[] indexI = indexOf(array, '@').split(" ");
		int rI = Integer.parseInt(indexI[0]);
		int cI = Integer.parseInt(indexI[1]);
		drawPath(array, rI, cI + 4, "54333222346554");
		printArray(array);
		scan.close();
	}

	private static void fillArray(char[][] array, char c) {
		for (int a = 0; a < array.length; a++) {
			for (int b = 0; b < array[a].length; b++) {
				array[a][b] = c;
			}
		}
	}

	private static String indexOf(char[][] ch, char c) {
		for (int a = 0; a < ch.length; a++) {
			for (int b = 0; b < ch[a].length; b++) {
				if (ch[a][b] == c)
					return a + " " + b;
			}
		}
		return "-1 -1";
	}
	
	private static List<String> paths = new ArrayList<String>();

	/*
	 * Directions: 
	 *     1   2 
	 *      \ / 
	 *   6---#---3 
	 *      / \ 
	 *     5   4
	 */
	private static String recur(int r, int c) {
		return "";
	}
	
	private static void drawPath(char[][] array, int r, int c, String path) {
		int[] dirs = new int[path.length()];
		for (int i = 0; i < path.length(); i++) { 
			dirs[i] = Integer.parseInt("" + path.charAt(i));
		}
		draw(array, 'A', r, c, dirs, 0);
	}
	
	private static void draw(char[][] array, char letter, int r, int c, int[] dirs, int dirI) { 
		array[r][c] = letter;
		if (dirI >= dirs.length)
			return;
		if (dirs[dirI] == 1) {
			draw(array, (char) (letter + 1), r - 2, c - 2, dirs, dirI + 1);
		} else if (dirs[dirI] == 2) {
			draw(array, (char) (letter + 1), r - 2, c + 2, dirs, dirI + 1);
		} else if (dirs[dirI] == 3) {
			draw(array, (char) (letter + 1), r, c + 4, dirs, dirI + 1);
		} else if (dirs[dirI] == 4) {
			draw(array, (char) (letter + 1), r + 2, c + 2, dirs, dirI + 1);
		} else if (dirs[dirI] == 5) {
			draw(array, (char) (letter + 1), r + 2, c - 2, dirs, dirI + 1);
		} else if (dirs[dirI] == 6) {
			draw(array, (char) (letter + 1), r, c - 4, dirs, dirI + 1);
		}
	}

	public static void print(Object... o) {
		for (Object obj : o) {
			System.out.print(obj);
		}
	}

	public static void printLine(Object... o) {
		if (o.length <= 0) {
			System.out.println();
			return;
		}
		for (Object obj : o) {
			System.out.println(obj);
		}
	}

	public static void printArray(char[][] array) {
		for (char[] a : array) {
			for (char b : a) {
				print(b);
			}
			printLine();
		}
	}

	public static void printF(boolean newLine, String format, Object... o) {
		System.out.printf(format + ((newLine) ? "\n" : ""), o);
	}

}
