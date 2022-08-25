
/*
 * Name: Oliver DiPuccio
 * Assignment: Project04 - Vertex Class
 * Due Date: 12/5/2021
 */

public class Vertex {
	
	//================================================================================= Properties
	private static int longestAddress = 1;
	public String symbol;
	public String address;
	
	//================================================================================= Constructor
	public Vertex(String symbol, String address) {
		super();
		this.symbol = symbol;
		this.address = address;
		longestAddress = Math.max(longestAddress, address.length());
	}
	
	//================================================================================= Overridden Methods
	@Override
	public boolean equals(Object otherObject) {
		if(!(otherObject instanceof Vertex)) return false;
		Vertex otherVertex = (Vertex) otherObject;
		return symbol.equals(otherVertex.symbol) && address.equals(otherVertex.address);
		 
	}
	
	@Override
	public String toString() {
		return 	String.format("%-" + (Graph.returnAddress ? longestAddress : 1) + "s",
				Graph.returnAddress ? address : symbol);
	}
}
