import java.util.ArrayList;

/*
 * Name: Oliver DiPuccio
 * Assignment: Project04 - Dijkstra Class
 * Due Date: 12/5/2021
 */

public class Dijkstra {
	
	//================================================================================= Properties
	public static int totalCost;
	
	//================================================================================= Methods
	/*
	 * findShortestPath(Graph, String, String)
	 * 
	 * This method uses Dijkstra's algorithm to find the shortest path from
	 * two vertices. There are 3 "curr" variables and Objects used to keep track of the 
	 * a cost, Path, or String that is being pointed to, as well as an empty Path that will be
	 * returned if there is no path found. An ArrayList of Edges as well as one of Strings are used
	 * to keep track of the Edges that touch a Vertex and to keep track of the Vertices that have been
	 * visited, respectively. A PriorityQueue of Paths is used in a while loop to add and remove Paths
	 * organized by total cost thanks to the PriorityQueue. If the next Vertex is the end Vertex then the 
	 * loop terminates and the Path is returned.
	 */
	public static Path findShortestPath(Graph g, String a, String b) {
		int currCost;
	
		//Path to be returned if a path does not exist
		Path emptyPath = new Path(a, "", 0);
		
		String currPath;
		String nextPath;
		Path nextEntry;
		Path nextState;
		
		//ArrayList of edges that stem from a chosen vertex
		ArrayList<Edge> edgeList;
		
		//ArrayList to keep track of visited vertices
		ArrayList<String> traveled = new ArrayList<String>();
		
		//ArrayList to organize and loop through generated paths
		SortedLinkedPriorityQueue<Path> pathList = new SortedLinkedPriorityQueue<Path>(); 
		
		pathList.add(new Path(a, a, 0));		
		
		while(!pathList.isEmpty()) {
			nextEntry = pathList.remove();
			edgeList = g.getKids(nextEntry.vertex, g);
			if((traveled.contains(nextEntry.vertex))) continue;
			traveled.add(nextEntry.vertex);
			if(nextEntry.vertex.equals(b)) return nextEntry;
			else {
				currCost = nextEntry.cost;
				currPath = nextEntry.path;
				for(Edge e : edgeList) {
					totalCost = currCost + (Graph.useDistCost ? e.distCost : e.timeCost);
					nextPath = currPath + e.toVertex;
					nextState = new Path(e.toVertex.symbol, nextPath, totalCost);
					pathList.add(nextState);
				}
			}	
		}
		return emptyPath;
	}
}
