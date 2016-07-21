package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;

import engine.BreadthFirstSearch;
import model.Edge;
import model.Graph;
import model.Vertex;

public class testbfs {

	private static List<Vertex> nodes;
	private static List<Edge> arcs;
	//private static Map<Integer, String> brokenEdge;
	//private static int totalBrokenEdge;
	
	private static void testExecute() throws IOException, NullPointerException{
		
		nodes = new ArrayList<Vertex>();
		arcs = new ArrayList<Edge>();
		//brokenEdge = new HashMap<Integer, String>();
		BufferedReader br = new BufferedReader(new FileReader("mapsafe.txt"));
		
		List<Vertex> location = new ArrayList<Vertex>();
		
		int totalVertices = Integer.parseInt(br.readLine());
		int totalEdges = Integer.parseInt(br.readLine());
		
		location.add(new Vertex("0", "Arad"));
		location.add(new Vertex("1", "Zerind"));
		location.add(new Vertex("2", "Sibiu"));
		location.add(new Vertex("3", "Timisoara"));
		location.add(new Vertex("4", "Odarea"));
		location.add(new Vertex("5", "Rimneu Vileea"));
		location.add(new Vertex("6", "Lagoj"));
		location.add(new Vertex("7", "Mehadia"));
		location.add(new Vertex("8", "Dobreta"));
		location.add(new Vertex("9", "Craiova"));
		location.add(new Vertex("10", "Petesti"));
		location.add(new Vertex("11", "Bucharest"));
		location.add(new Vertex("12", "Giurgui"));
		location.add(new Vertex("13", "Fagaras"));
		location.add(new Vertex("14", "Urzieeni"));
		location.add(new Vertex("15", "Eforie"));
		location.add(new Vertex("16", "Hirsova"));
		location.add(new Vertex("17", "Vaslui"));
		location.add(new Vertex("18", "Iasi"));
		location.add(new Vertex("19", "Neamt"));
		
		for(Vertex loc : location) {
			nodes.add(loc);
		}
		
		
		
		
		String line;
		StringTokenizer st;
		
		for (int i = 0; i < totalEdges; i++) {
			line = br.readLine();
			st = new StringTokenizer(line);
			String source = st.nextToken();
			String destination = st.nextToken();
			//String distance = st.nextToken();
			String lane = source + " " + destination;
			
			
			addLane(lane, Integer.parseInt(source), Integer.parseInt(destination));
		}
		
		Graph graph = new Graph(nodes, arcs);
		BreadthFirstSearch bfs = new BreadthFirstSearch(graph);
		Vertex converge = bfs.execute(nodes.get(0), nodes.get(14));
		//bfs.printPath(new Stack<Vertex>(), nodes.get(0), nodes.get(11));
		//List<Vertex> path = bfs.getDirection();
		printPath(bfs, converge);
		
		System.out.println("Length: "+ bfs.getTotaldistance());
		
		if(bfs.getSearch().equals("Forward")) {
			System.out.println("Direction: Forward City:" + converge+ " #Roads: " + bfs.getFwddistance());
		} else {
			System.out.println("Direction: Backward City:" + converge+ " #Roads: " + bfs.getBwddistance());
		}
	}
	
	private static void printPath(BreadthFirstSearch bfs, Vertex converge) {
		Stack<Vertex> path = bfs.pathTo(converge);
		System.out.print("Route: ");
		while(!path.isEmpty()) {
			System.out.print(path.pop());
			if(!path.isEmpty()) {
				System.out.print("->");
			}
		}
		System.out.println();
	}
	
	private static void addLane(String laneId, int sourceLocationNumber, int destinationLocationNumber) {
		Edge lane = new Edge(laneId, nodes.get(sourceLocationNumber), nodes.get(destinationLocationNumber));
		arcs.add(lane);
	}
	
	public static void main(String[] args) throws IOException {
		testExecute();
		
	}
	
	

}
