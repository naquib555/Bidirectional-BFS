package model;

public class Edge {

	private final String id;
	private final Vertex source;
	private final Vertex destination;
	
	
	
	
	public Edge(String id, Vertex source, Vertex destination) {
		this.id = id;
		this.source = source;
		this.destination = destination;
	}

	public String getId() {
		return id;
	}
	
	public Vertex getSource() {
		return source;
	}
	
	public Vertex getDestination() {
		return destination;
	}

	

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return source + " " + destination;
	}
	
	
	
}
