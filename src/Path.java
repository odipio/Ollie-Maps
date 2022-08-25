/*
 * Name: Oliver DiPuccio
 * Assignment: Project04 - Path Class
 * Due Date: 12/5/2021
 */

public class Path extends SortedLinkedPriorityQueue<Path> implements Comparable<Path>, PriorityQueueInterface<Path> {
	public String vertex;
	public String path;
	public int cost;
	
	public Path(String vertex, String path, int cost) {
		this.vertex = vertex;
		this.path = path;
		this.cost = cost;
	}

	@Override
	public int compareTo(Path otherState) {
		return cost - otherState.cost;
	}
	
	@Override
	public String toString() {
		String ret = "";
		if(path.isEmpty()) ret = "No path exists!";
		else if(path.length() == 1) ret = "You're already here!";
 		ret = path + " | Cost: " + cost + (Graph.useDistCost ? " miles" : " minutes");
		return ret;
	}
}	
