import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * @author Alex Chiang
 *
 */
public class prob10 {

	public static void main(String[] args) throws IOException {
		BufferedReader scan;
		try {
			scan = new BufferedReader(new FileReader(prob10.class.getSimpleName() + "-1-in.txt"));
		} catch (FileNotFoundException ex) {
			printF(true, "File not found: %s", ex.getMessage());
			return;
		}
		String config = scan.readLine();
		while (!config.equalsIgnoreCase("=========")) {
			char[][] board = new char[3][3];
			board[0] = config.substring(0, 3).toCharArray();
			board[1] = config.substring(3, 6).toCharArray();
			board[2] = config.substring(6, 9).toCharArray();
			int result = result(board);
			if (result == 1) {
				printLine("Player X won.");
			} else if (result == 2) {
				printLine("Player O won.");
			} else {
				printLine("There was a tie.");
			}
			printArray(board);
			printLine();
			config = scan.readLine();
		}
		scan.close();
	}

	/**
	 * 
	 * @param board
	 * @return 1 for X, 2 for O, 3 for tie.
	 */
	public static int result(char board[][]) {
		if (checkRows(board, 'X'))
			return 1;
		else if (checkColumns(board, 'X'))
			return 1;
		else if (checkDiagonals(board, 'X'))
			return 1;
		else if (checkRows(board, 'O'))
			return 2;
		else if (checkColumns(board, 'O'))
			return 2;
		else if (checkDiagonals(board, 'O'))
			return 2;
		else
			return 3;
	}

	public static boolean checkRows(char[][] array, char ch) {
		Queue<Integer> q = new LinkedList<>();
		for (int r = 0; r < 3; r++) {
			boolean row = true;
			for (int c = 0; c < 3; c++) {
				if (array[r][c] != ch) {
					row = false;
				} else {
					array[r][c] = '$';
					q.add(r);
					q.add(c);
				}
			}
			if (row) {
				q.clear();
				return true;
			} else {
				while (!q.isEmpty()) {
					int rr = q.poll();
					int cc = q.poll();
					array[rr][cc] = ch;
				}
			}
		}
		return false;
	}

	public static boolean checkColumns(char[][] array, char ch) {
		Queue<Integer> q = new LinkedList<>();
		for (int c = 0; c < 3; c++) {
			boolean col = true;
			for (int r = 0; r < 3; r++) {
				if (array[r][c] != ch) {
					col = false;
				} else {
					array[r][c] = '$';
					q.add(r);
					q.add(c);
				}
			}
			if (col) {
				q.clear();
				return true;
			} else {
				while (!q.isEmpty()) {
					int rr = q.poll();
					int cc = q.poll();
					array[rr][cc] = ch;
				}
			}
		}
		return false;
	}

	public static boolean checkDiagonals(char[][] array, char ch) {
		Queue<Integer> q = new LinkedList<>();
		boolean a = true;
		for (int i = 0; i < 3; i++) {
			if (array[i][i] != ch) {
				a = false;
			} else {
				array[i][i] = '$';
				q.add(i);
				q.add(i);
			}
		}
		if (a) {
			q.clear();
			return true;
		} else {
			while (!q.isEmpty()) {
				int r = q.poll();
				int c = q.poll();
				array[r][c] = ch;
			}
		}
		boolean b = true;
		for (int i = 0; i < 3; i++) {
			if (array[i][2 - i] != ch) {
				b = false;
			} else {
				array[i][2 - i] = '$';
				q.add(i);
				q.add(2 - i);
			}
		}
		if (b) {
			q.clear();
			return true;
		} else {
			while (!q.isEmpty()) {
				int r = q.poll();
				int c = q.poll();
				array[r][c] = ch;
			}
		}
		return false;
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

	public static void printF(boolean newLine, String format, Object... o) {
		System.out.printf(format + ((newLine) ? "\n" : ""), o);
	}

	public static void printArray(char[][] array) {
		for (char[] ch : array) {
			for (char c : ch)
				print(c);
			printLine();
		}
	}

}