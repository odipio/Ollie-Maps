import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/*
 * Name: Oliver DiPuccio
 * Assignment: Project04 - Graph Class
 * Due Date: 12/5/2021
 */

public class Graph {
	
	//================================================================================= Properties
	public static boolean useDistCost = true;
	public static boolean returnAddress = false;
	
	public HashMap<String, int[]> map;
	
	public static HashMap<String, Vertex> vertexMap;
	public static ArrayList<Vertex> vertices;
	public ArrayList<Edge> edges;
	public ArrayList<String> justEdges;
	public ArrayList<Edge> touchingEdges;
	
	public HashMap<Vertex, ArrayList<Edge>> list;
	
	//================================================================================= Constructor
	public Graph(String fileName) {
		vertexMap = new HashMap<String, Vertex>();
		vertices = new ArrayList<Vertex>(); 
		edges = new ArrayList<Edge>();
		justEdges = new ArrayList<String>();
		map = new HashMap<String, int[]>();
		String[] parts;
		
		
		try(Scanner fin = new Scanner(new File(fileName))) {
			while(fin.hasNextLine()) {
				parts = split(fin);
				if(parts[0].equals("<Nodes>")) {
					fin.nextLine();
					while(true) {
						parts = split(fin);
						if(parts[0].equals("</Nodes>")) break;
						vertexMap.put(parts[0], new Vertex(parts[0], parts[1]));
						vertices.add(new Vertex(parts[0], parts[1]));
					}
				} else if(parts[0].equals("<Edges>")) {
					fin.nextLine();
					while(true) {
						parts = split(fin);
						if(parts[0].equals("</Edges>")) break;
						edges.add(new Edge(
									vertexMap.get(parts[0]),
									vertexMap.get(parts[1]),
									Integer.parseInt(parts[2]),
									Integer.parseInt(parts[3])
								));
					}
				}
				else if(parts[0].equals("<Points>")) {
					fin.nextLine();
					while(true) {
						parts = split(fin);
						if(parts[0].equals("</Points>")) break;
						int[] arr = { 
								Integer.parseInt(parts[1]), 
								Integer.parseInt(parts[2]),
								Integer.parseInt(parts[3]),
								Integer.parseInt(parts[4])};
						map.put(parts[0], arr);
						justEdges.add(parts[0]);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//================================================================================= Methods
	/*
	 * getTouchingEdges(Vertex)
	 * 
	 * A helper method that returns an ArrayList of all the Edges that stem from a given Vertex
	 */
	public ArrayList<Edge> getTouchingEdges(Vertex v) {
		touchingEdges = new ArrayList<Edge>();
		for(Edge e : edges) 
			if(e.fromVertex.equals(v)) touchingEdges.add(e);
		return touchingEdges;
	}
	
	/*
	 * fillMap()
	 * 
	 * A helper method that fills an HashMap with all of the Edges that stem from the Vertex key
	 */
	public void fillMap() {
		list = new HashMap<Vertex, ArrayList<Edge>>();
		for(Vertex v: Graph.vertexMap.values()) 
			list.put(v, this.getTouchingEdges(v));
	}
	
	private String[] split(Scanner fin) {
		return fin.nextLine().split("\t");
	}
	
	/*
	 * getKids(String, Graph)
	 * 
	 * A helper method uses fillMap(Graph) and takes in a String and a Graph and
	 * returns all of the the Edges that stem from the given Vertex in the form of an ArrayList
	 */
	public ArrayList<Edge> getKids(String symbol, Graph g) {
		ArrayList<Edge> ret = new ArrayList<Edge>();
		g.fillMap();
		for(Edge e : g.list.get(Graph.vertexMap.get(symbol))) 
			if(e.fromVertex.symbol.equals(symbol)) ret.add(e);
		return ret;
	}
	
	//================================================================================= Getters
	public Vertex getVertex(String address) {
		for(Vertex v : vertices) 
			if(v.address.equals(address)) return v;
		return null;
	}
	
	//================================================================================= Overridden Methods
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		for(Edge e : edges) s.append(e).append("\n");
		return s.toString();
	}
}
