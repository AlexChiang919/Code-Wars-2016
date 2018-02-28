import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class prob18 {

	private static final String PROBLEM = "prob18";
	private static final String EXT = "-2-in.txt";

	public static final int[][] D = { { -1, -1, 0, 0, 1, 1 }, { -1, 1, -1, 1, -1, 1 } };

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
		int[][] dis = new int[a + (a - 1)][b + ((b - 1) * 3)];
		fillArray(array, ' ');
		fillArray(dis, 1000000);
		for (int i = 0; i < (a + (a - 1)); i++) {
			char[] hi = scan.nextLine().toCharArray();
			for (int j = 0; j < hi.length; j++) {
				array[i][j] = hi[j];
			}
		}
		int[] start = indexOf(array, '@');
		dis[start[0]][start[1]] = -1;
		bfs(array, dis, start[0], start[1]);
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

	private static void bfs(char[][] maze, int[][] dis, int sr, int sc) {
		dis[sr][sc] = -1;
		int er = -1;
		int ec = -1;
		boolean solved = false;
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(sr);
		q.add(sc);
		while (!q.isEmpty()) {
			int r = q.poll();
			int c = q.poll();
			if (maze[r][c] == '?') {
				solved = true;
				er = r;
				ec = c;
			}
			for (int i = 0; i < 6; i++) {
				int rr = r + D[0][i];
				int cc = c + D[1][i];
				int[] nu = getDirection(D[0][i], D[1][i], r, c);
				int nr = nu[0];
				int nc = nu[1];
				if (!inBounds(maze, rr, cc) || maze[rr][cc] == ' ')
					continue;
				if (dis[r][c] + 1 < dis[nr][nc]) {
					dis[nr][nc] = dis[r][c] + 1;
					q.add(nr);
					q.add(nc);
				}
			}
		}
		if (solved)
			draw(maze, dis, er, ec);
		else
			printLine("Not solvable. :(");
	}

	private static void draw(char[][] maze, int[][] dis, int er, int ec) {
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(er);
		q.add(ec);
		List<Integer> used = new ArrayList<Integer>();
		while (!q.isEmpty()) {
			int r = q.poll();
			int c = q.poll();
			for (int i = 0; i < 6; i++) {
				int rr = r + D[0][i];
				int cc = c + D[1][i];
				int[] nu = getDirection(D[0][i], D[1][i], r, c);
				int nr = nu[0];
				int nc = nu[1];
				if (!inBounds(maze, rr, cc) || maze[rr][cc] == ' ' || used.contains(dis[nr][nc]))
					continue;
				if (dis[nr][nc] == dis[r][c] - 1) {
					used.add(dis[nr][nc]);
					maze[nr][nc] = (char) (('A' + dis[nr][nc]));
					q.add(nr);
					q.add(nc);
				}
			}
		}
		printArray(maze);
	}

	private static boolean inBounds(char[][] maze, int r, int c) {
		return (r >= 0 && r < maze.length) && (c >= 0 && c < maze[r].length);
	}

	private static int[] getDirection(int dr, int dc, int nr, int nc) {
		if (dr == -1 && dc == -1)
			return new int[] { nr - 2, nc - 2 };
		else if (dr == -1 && dc == 1)
			return new int[] { nr - 2, nc + 2 };
		else if (dr == 0 && dc == -1)
			return new int[] { nr, nc - 4 };
		else if (dr == 0 && dc == 1)
			return new int[] { nr, nc + 4 };
		else if (dr == 1 && dc == -1)
			return new int[] { nr + 2, nc - 2 };
		else if (dr == 1 && dc == 1)
			return new int[] { nr + 2, nc + 2 };
		return new int[] { -1, -1 };
	}

	private static int[] indexOf(char[][] ch, char c) {
		for (int a = 0; a < ch.length; a++) {
			for (int b = 0; b < ch[a].length; b++) {
				if (ch[a][b] == c)
					return new int[] { a, b };
			}
		}
		return new int[] { -1, -1 };
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
