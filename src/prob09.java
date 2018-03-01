import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * 
 * @author Alex Chiang
 *
 */
public class prob09 {

	public static void main(String[] args) throws IOException {
		BufferedReader scan;
		try {
			scan = new BufferedReader(new FileReader(prob09.class.getSimpleName() + "-1-in.txt"));
		} catch (FileNotFoundException ex) {
			printF(true, "File not found: %s", ex.getMessage());
			return;
		}
		String line = scan.readLine();
		while (!line.equals("0 0 0")) {
			StringTokenizer st = new StringTokenizer(line);
			int l = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			int sa = sa(l, w, h);
			int vol = volume(l, w, h);
			if (vol - sa > sa) {
				printF(true, "A %dx%dx%d block is LESS than Perfect.", l, w, h);
			} else if (vol - sa < sa) {
				printF(true, "A %dx%dx%d block is MORE than Perfect.", l, w, h);
			} else {
				printF(true, "A %dx%dx%d block is PERFECT.", l, w, h);
			}
			line = scan.readLine();
		}
		scan.close();
	}
	
	public static int volume(int l, int w, int h) {
		return l * w * h;
	}
	
	public static int sa(int l, int w, int h) {
		return (2 * l * h) + (2 * l * w) + (2 * w * h);
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