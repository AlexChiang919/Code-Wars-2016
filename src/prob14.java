import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class prob14 {

	private static final String PROBLEM = "prob14";
	private static final String EXT = "-1-in.txt";
	
	private static HashMap<Character, Integer> shifts = new HashMap<>();
	
	public static void main(String[] args) {
		start();
		Scanner scan;
		try {
			scan = new Scanner(new File(PROBLEM + EXT));
		} catch (FileNotFoundException ex) {
			printF(true, "File not found: %s", ex.getMessage());
			return;
		}
		int times = Integer.parseInt(scan.nextLine());
		while (times-- > 0) {
			String[] split = scan.nextLine().split("(\\.|\\. )");
			for (String s : split) {
				print(decode(s) + ". ");
			}
			printLine();
		}
		scan.close();
	}
	
	private static void start() {
		int shift = 1;
		for (char c = 'A'; c <= 'Z'; c++) {
			shifts.put(c, shift++);
			if (shift >= 6)
				shift = 1;
		}
		shifts.put(' ', 6);
		//printLine(VariableShiftEncoding.shifts);
	}
	
	private static String decode(String s) {
		StringBuilder sb = new StringBuilder("" + s.charAt(0));
		List<Character> chars = toArrayList(s.toCharArray());
		chars.remove(0);
		int current = 0;
		while (chars.size() > 0) {
			int shift = shifts.get(sb.charAt(sb.length() - 1));
			for (int i = 0; i < shift - 1; i++) { 
				current++;
				//printLine(current + " " + chars.size());
				if (current == chars.size()) { 
					current = 0;
				}
			}
			sb.append(chars.remove(current));
			if (current == chars.size()) { 
				current = 0;
			}
			//printLine(sb);
		}
		return sb.toString();
	}
	
	private static List<Character> toArrayList(char[] ch) { 
		ArrayList<Character> out = new ArrayList<>();
		for (char c : ch) { 
			out.add(c);
		}
		return out;
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

}
