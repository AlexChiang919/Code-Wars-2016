import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * 
 * @author Alex Chiang
 *
 */
public class prob15 {

	public static void main(String[] args) throws IOException {
		BufferedReader scan;
		try {
			scan = new BufferedReader(new FileReader(prob15.class.getSimpleName() + "-1-in.txt"));
		} catch (FileNotFoundException ex) {
			printF(true, "File not found: %s", ex.getMessage());
			return;
		}
		String line = scan.readLine();
		printLine("Start");
		// front, left, right, back, up, down
		char[][] cube = new char[][] { {'G', 'G', 'G', 'G'}, {'O', 'O', 'O', 'O'}, {'R', 'R', 'R', 'R'}, {'B', 'B', 'B', 'B'}, {'W', 'W', 'W', 'W'}, {'Y', 'Y', 'Y', 'Y'} };
		printFront(cube);
		printLine();
		while (!line.equals(".")) {
			printLine(line);
			if (line.equalsIgnoreCase("F"))
				front(cube);
			else if (line.equalsIgnoreCase("L"))
				left(cube);
			else if (line.equalsIgnoreCase("R"))
				right(cube);
			else if (line.equalsIgnoreCase("B"))
				back(cube);
			else if (line.equalsIgnoreCase("U"))
				up(cube);
			else if (line.equalsIgnoreCase("D"))
				down(cube);
			printFront(cube);
			line = scan.readLine();
			if (!line.equals("."))
				printLine();
		}
		scan.close();
	}

	public static void front(char[][] cube) {
		char old = cube[0][0];
		cube[0][0] = cube[0][1];
		cube[0][1] = cube[0][2];
		cube[0][2] = cube[0][3];
		cube[0][3] = old;
		old = cube[5][0];
		char old1 = cube[5][1];
		cube[5][0] = cube[2][3];
		cube[5][1] = cube[2][0];
		cube[2][0] = cube[4][3];
		cube[2][3] = cube[4][2];
		cube[4][3] = cube[1][2];
		cube[4][2] = cube[1][1];
		cube[1][1] = old;
		cube[1][2] = old1;
	}

	public static void left(char[][] cube) {
		char old = cube[1][0];
		cube[1][0] = cube[1][1];
		cube[1][1] = cube[1][2];
		cube[1][2] = cube[1][3];
		cube[1][3] = old;
		old = cube[5][0];
		char old1 = cube[5][1];
		cube[5][0] = cube[2][3];
		cube[5][1] = cube[2][0];
		cube[2][0] = cube[4][3];
		cube[2][3] = cube[4][2];
		cube[4][3] = cube[1][2];
		cube[4][2] = cube[1][1];
		cube[1][1] = old;
		cube[1][2] = old1;
	}

	public static void right(char[][] cube) {

	}

	public static void back(char[][] cube) {

	}

	public static void up(char[][] cube) {

	}

	public static void down(char[][] cube) {

	}

	public static void printFront(char[][] cube) {
		printLine(cube[0][0] + " " + cube[0][1]);
		printLine(cube[0][2] + " " + cube[0][3]);
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