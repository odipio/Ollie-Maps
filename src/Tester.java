/*
 * Name: Oliver DiPuccio
 * Assignment: Project04 - Tester Class
 * Due Date: 12/5/2021
 */

public class Tester {

	public static void main(String[] args) {
		Graph.returnAddress = false;
		Graph.useDistCost = true;
		Graph g = new Graph("MapInformation.txt");
		System.out.println(g);
		System.out.println(Dijkstra.findShortestPath(g, "A", "K"));
	}
}
