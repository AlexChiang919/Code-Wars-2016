import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

@SuppressWarnings({"unchecked", "rawtypes"})
public class HexagonallyAmazing {

	private static final String PROBLEM = "prob18";
	private static final String EXT = "-1-in.txt";

	private static ArrayList<String> shorts = new ArrayList<String>();

	private static int V; // No. of vertices
	private static LinkedList<Integer> adj[]; // Adjacency Lists
	
	
	public static void main(String[] args) {
		 V = 1;
	        adj = new LinkedList[V];
	        for (int i=0; i< V; ++i)
	            adj[i] = new LinkedList();
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
		printArray(array);
		scan.close();
	}

	

	// Constructor
	
	// Function to add an edge into the graph
	void addEdge(int v, int w) {
		adj[v].add(w);
	}

	// prints BFS traversal from a given source s
	void BFS(int s) {
		// Mark all the vertices as not visited(By default
		// set as false)
		boolean visited[] = new boolean[V];

		// Create a queue for BFS
		LinkedList<Integer> queue = new LinkedList<Integer>();

		// Mark the current node as visited and enqueue it
		visited[s] = true;
		queue.add(s);

		while (queue.size() != 0) {
			// Dequeue a vertex from queue and print it
			s = queue.poll();
			System.out.print(s + " ");

			// Get all adjacent vertices of the dequeued vertex s
			// If a adjacent has not been visited, then mark it
			// visited and enqueue it
			Iterator<Integer> i = adj[s].listIterator();
			while (i.hasNext()) {
				int n = i.next();
				if (!visited[n]) {
					visited[n] = true;
					queue.add(n);
				}
			}
		}
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

	private static void initRecursion(int r1, int c1, int r2, int c2) {
		shorts.add(recur(r1, c1, r2, c2, 1));
	}

	/*
	 * Directions: 1 2 \ / 6---#--- 3 / \ 5 4
	 */
	private static String recur(int r, int c, int rF, int cF, int dir) { 
		return "";
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
