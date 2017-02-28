import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class HexagonallyAmazing2 {

	private static final String PROBLEM = "prob18";
	private static final String EXT = "-1-in.txt";

	private static int[][] d;
	
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
		d = new int[a + (a - 1)][b + ((b - 1) * 3)];
		fillArray(array, ' ');
		fillArray(d, 0);
		for (int i = 0; i < (a + (a - 1)); i++) {
			char[] hi = scan.nextLine().toCharArray();
			for (int j = 0; j < hi.length; j++) {
				array[i][j] = hi[j];
			}
		}
		String[] indexI = indexOf(array, '@').split(" ");
		int rI = Integer.parseInt(indexI[0]);
		int cI = Integer.parseInt(indexI[1]);
		//drawPath(array, rI, cI + 4, "54333222346554");
		initRecur(array, rI, cI);
		//printArray(d);
		scan.close();
	}

	private static void fillArray(char[][] array, char c) {
		for (int a = 0; a < array.length; a++) {
			for (int b = 0; b < array[a].length; b++) {
				array[a][b] = c;
			}
		}
	}
	
	private static void fillArray(int[][] array, int i) {
		for (int a = 0; a < array.length; a++) {
			for (int b = 0; b < array[a].length; b++) {
				array[a][b] = i;
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
	private static void initRecur(char[][] array, int r, int c) {
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		pq.add(r);
		pq.add(c);
		String path = "";
		//d[r][c] = 1;
		a: 
		while (!pq.isEmpty()) {
			int cr = pq.poll();
			int cc = pq.poll();
			for (int i = 1; i <= 6; i++) {
				int nr = getR(cr, i);
				int nc = getC(cc, i);
				
				if (!isInBounds(array, nr, nc))
					continue;
				else if (array[nr][nc] == '?')
					break a;
				if (d[cr][cc] + 1 < d[nr][nc]) {
				//if (d[nr][nc] <= 1) {
					//printLine(true);
					d[nr][nc] = d[cr][cc] + 1;
					path += "" + i;
					//printLine(path);
					array[nr][nc] = 'A';
					printArray(array);
					pq.add(nr);
					pq.add(nc);
				}
			}
		}
		printLine(path);
	}
	
	private static int getR(int r, int dir) { 
		if (dir == 3 || dir == 6)
			return r;
		if (dir == 1 || dir == 2)
			return r - 2;
		if (dir == 5 || dir == 4)
			return r + 2;
		return -1;
	}
	
	private static int getC(int c, int dir) { 
		if (dir == 1 || dir == 5)
			return c - 2;
		if (dir == 3 || dir == 6)
			return c - 4;
		if (dir == 2 || dir == 4)
			return c + 2;
		return -1;
	}
	
	private static boolean isInBounds(char[][] array, int r, int c) {
		return (r >= 0 && r < array.length) && (c >= 0 && c < array[r].length);
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
	
	public static void printArray(int[][] array) {
		for (int[] a : array) {
			for (int b : a) {
				print(b);
			}
			printLine();
		}
	}

	public static void printF(boolean newLine, String format, Object... o) {
		System.out.printf(format + ((newLine) ? "\n" : ""), o);
	}

}
