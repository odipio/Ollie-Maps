
/*
 * Name: Oliver DiPuccio
 * Assignment: Project04 - Edge Class
 * Due Date: 12/5/2021
 */

public class Edge {
	
	//================================================================================= Properties
	public Vertex fromVertex;
	public Vertex toVertex;
	public int timeCost;
	public int distCost;
	
	//================================================================================= Constructor
	public Edge(Vertex fromVertex, Vertex toVertex, int timeCost, int distCost) {
		super();
		this.fromVertex = fromVertex;
		this.toVertex = toVertex;
		this.timeCost = timeCost;
		this.distCost = distCost;
	}
	
	//================================================================================= Overridden Methods
	@Override
	public boolean equals(Object otherObject) {
		if(!(otherObject instanceof Edge)) return false;
		Edge otherEdge = (Edge) otherObject;
		return fromVertex.equals(otherEdge.fromVertex) 
				&& toVertex.equals(otherEdge.toVertex)
				&& timeCost == otherEdge.timeCost
				&& distCost == otherEdge.distCost;
		
	}
	
	@Override
	public String toString() {
		return 	String.format("%s -> %s (%d %s)", 
					fromVertex,
					toVertex,
					Graph.useDistCost ? distCost : timeCost,
					Graph.useDistCost ? "miles" : "minutes"
				);
	}
}
