import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 * @author Alex Chiang
 *
 */
public class prob07 {

	public static void main(String[] args) throws IOException {
		BufferedReader scan;
		try {
			scan = new BufferedReader(new FileReader(prob07.class.getSimpleName() + "-1-in.txt"));
		} catch (FileNotFoundException ex) {
			printF(true, "File not found: %s", ex.getMessage());
			return;
		}
		int T = Integer.parseInt(scan.readLine());
		while (T-- > 0) {
			String[] words = scan.readLine().split("\\s+");
			String s = "";
			int ss = Integer.MAX_VALUE;
			for (String w : words) {
				if (w.length() < ss) {
					s = w;
					ss = w.length();
				}
			}
			List<Character> common = new ArrayList<>();
			for (int i = 0; i < s.length(); i++) {
				char c = s.charAt(i);
				boolean has = true;
				for (int a = 0; a < words.length; a++) {
					String word = words[a];
					StringBuilder sb = new StringBuilder(word);
					if (!word.contains("" + c)) {
						has = false;
					} else {
						sb.deleteCharAt(word.indexOf(c));
					}
					words[a] = sb.toString();
				}
				if (has)
					common.add(c);
			}
			Collections.sort(common);
			for (char c : common)
				print(c);
			printLine();
		}
		scan.close();
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