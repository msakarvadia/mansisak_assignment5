package DiGraph_A5;

import java.util.*;

import org.apache.commons.lang.RandomStringUtils;

public class DiGraphPlayground {

	public static void main(String[] args) {

		// thorough testing is your responsibility
		//
		// you may wish to create methods like
		// -- print
		// -- sort
		// -- random fill
		// -- etc.
		// in order to convince yourself your code is producing
		// the correct behavior
		exTest();
		//efTest();
	}

	public static void exTest() {
		DiGraph d = new DiGraph();
		/*
		 * d.addNode(1, "f"); d.addNode(3, "s"); d.addNode(7, "t"); d.addNode(0, "fo");
		 * d.addNode(4, "fi"); d.addNode(6, "si"); d.addEdge(0, "f", "s", 0, null);
		 * d.addEdge(1, "f", "si", 0, null); d.addEdge(2, "s", "t", 0, null);
		 * d.addEdge(3, "fo", "fi", 0, null); d.addEdge(4, "fi", "si", 0, null);
		 */

		for (int i = 0; i < 500000; i++) {
			String str1 = getAlphaNumericString(5);
			String str2 = getAlphaNumericString(5);

			d.addNode(i, str1);
			d.addNode(i * 50, str2);
			d.addEdge(i, str1, str2, 0, str1);
			// System.out.println(str);

		}
		d.addNode(3, "a");
		d.addNode(7, "t");
		d.addNode(0, "fo");
		d.addNode(4, "fi");
		d.addNode(6, "si");
		d.addEdge(0, "f", "s", 0, null);
		d.addEdge(1, "f", "si", 0, null);
		d.addEdge(2, "s", "t", 0, null);
		d.addEdge(3, "fo", "fi", 0, null);
		d.addEdge(4, "fi", "si", 0, null);
		// d.delEdge("fi", "si");
		System.out.println("numEdges: " + d.numEdges());
		System.out.println("numNodes: " + d.numNodes());
	}

	public static void efTest() {
		DiGraph d = new DiGraph();
		int x = 1000000; // change number
		String[] arr = new String[x];

		for (int i = 0; i < x; i++) {
			arr[i] = RandomStringUtils.randomAlphanumeric(5); // just gives us a random label
			if (i == 0) {
				d.addNode(50000000, "0"); // needed solid start node
			}
			d.addNode(i, arr[i]);

			if (i % 2 == 0) {
				d.addEdge(i, "0", arr[i], i, "");
			}
			d.addEdge(i, arr[i], arr[i % (arr.length - 1)], 1, "");
			// d.addEdge(i,arr[i], arr[i%(arr.length-2)],1, "");
		}

		System.out.println("start");
		long start = System.currentTimeMillis();

		ShortestPathInfo[] t = d.shortestPath("0");
		// This prints each statement of ShortInfo
		// for (int i = 0; i < t.length; i++) {
		// System.out.println(t[i]);
		// }
		System.out.println("done");
		long end = System.currentTimeMillis();
		long elapsedTime = end - start;
		System.out.println(elapsedTime); // total time in milliseconds
	}

	static String getAlphaNumericString(int n) {

		// lower limit for LowerCase Letters
		int lowerLimit = 97;

		// lower limit for LowerCase Letters
		int upperLimit = 122;

		Random random = new Random();

		// Create a StringBuffer to store the result
		StringBuffer r = new StringBuffer(n);

		for (int i = 0; i < n; i++) {

			// take a random value between 97 and 122
			int nextRandomChar = lowerLimit + (int) (random.nextFloat() * (upperLimit - lowerLimit + 1));

			// append a character at the end of bs
			r.append((char) nextRandomChar);
		}

		// return the resultant string
		return r.toString();
	}

}