import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * 
 * @author Alex Chiang
 *
 */
public class prob08 {

	public static void main(String[] args) throws IOException {
		BufferedReader scan;
		try {
			scan = new BufferedReader(new FileReader(prob08.class.getSimpleName() + "-1-in.txt"));
		} catch (FileNotFoundException ex) {
			printF(true, "File not found: %s", ex.getMessage());
			return;
		}
		int T = Integer.parseInt(scan.readLine());
		while (T-- > 0) {
			String word = scan.readLine();
			StringBuilder sb = new StringBuilder("");
			for (int i = 0; i < word.length(); i++) {
				sb.append(word.charAt(i));
				printLine(getSpaces(word.length() - sb.length()) + sb.toString());
			}
			for (int i = 0; i < word.length() - 1; i++) {
				sb.deleteCharAt(0);
				printLine(sb.toString());
			}
			printLine();
		}
		scan.close();
	}
	
	public static String getSpaces(int i) {
		String spaces = "";
		for (int a = 0; a < i; a++) {
			spaces += " ";
		}
		return spaces;
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