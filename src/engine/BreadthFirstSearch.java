package engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

import model.Edge;
import model.Graph;
import model.Vertex;

public class BreadthFirstSearch {

	private final List<Vertex> nodes;
	private final List<Edge> edges;
	
	private final Map<Vertex, Boolean> fwdvisited;
	private final Map<Vertex, Boolean> bwdvisited;
	private final Map<Vertex, Vertex> fwdprevious;
	private final Map<Vertex, Vertex> bwdprevious;
	private final Map<Vertex, Integer> distance;//---->not needed
	
	private String search;
	
	private int fwddistance;
	private int bwddistance;
	private int totaldistance;
	
	private final List<Vertex> direction;
	
	public BreadthFirstSearch(Graph graph) {
		this.nodes = new ArrayList<Vertex>(graph.getVertexes());
		this.edges = new ArrayList<Edge>(graph.getEdges());
		fwdvisited = new HashMap<Vertex, Boolean>();
		bwdvisited = new HashMap<Vertex, Boolean>();
		
		fwdprevious = new HashMap<Vertex, Vertex>();
		bwdprevious = new HashMap<Vertex, Vertex>();
		distance = new HashMap<Vertex, Integer>();
		direction = new LinkedList<>();
		
		search = null;
		
		fwddistance = 0;
		bwddistance = 0;
		totaldistance = 0;
	}
	
	public Vertex execute(Vertex source, Vertex destination) throws NullPointerException {
		Queue<Vertex> fwdqueue = new LinkedList();
		Queue<Vertex> bwdqueue = new LinkedList();
		intializeVisited();
		intializeDistance();
		//intializeParent();
		Vertex fwdcurrent = source;
		Vertex bwdcurrent = destination;
		
		fwdvisited.put(fwdcurrent, true);
		distance.put(fwdcurrent, 0);
		fwdqueue.add(fwdcurrent);
		
		bwdvisited.put(bwdcurrent, true);
		distance.put(bwdcurrent, 0);
		bwdqueue.add(bwdcurrent);
		
		Vertex fwdvertex;
		Vertex bwdvertex;
		
		while(!fwdqueue.isEmpty() && !bwdqueue.isEmpty()) {
			if(!fwdqueue.isEmpty()) {
				fwdvertex = fwdqueue.poll();
				if(fwdvertex.equals(bwdcurrent) || bwdqueue.contains(fwdvertex)) {
					search = "Forward";
					return fwdvertex;
				}
				for(Edge edge : edges) {
					Vertex temp = edge.getDestination();
					if(edge.getSource().equals(fwdvertex) && !fwdvisited.get(temp)) {
						fwdvisited.put(temp, true);
						
						distance.put(temp, distance.get(edge.getSource())+1);
						fwdprevious.put(temp, edge.getSource());
						
						fwdqueue.add(temp);
						
					} 
				}
				
			}
			
			if(!bwdqueue.isEmpty()) {
				bwdvertex = bwdqueue.poll();
				if(bwdvertex.equals(fwdcurrent) || fwdqueue.contains(bwdvertex)) {
					search = "Backward";
					return bwdvertex;
				}
				for(Edge edge : edges) {
					Vertex temp = edge.getDestination();
					if(edge.getSource().equals(bwdvertex) && !bwdvisited.get(temp)) {
						bwdvisited.put(temp, true);
						
						distance.put(temp, distance.get(edge.getSource())+1);
						bwdprevious.put(temp, edge.getSource());
						
						bwdqueue.add(temp);
						
					}
				}	
			}		
		}
		return null;
	}

	public Stack<Vertex> pathTo(Vertex v) {
		Stack<Vertex> path = new Stack<Vertex>();
		Stack<Vertex> temppath = new Stack<>();

		Vertex node;
		
		for(node=bwdprevious.get(v); distance.get(node) != 0; node=bwdprevious.get(node)) {
			temppath.add(node);
		}
		temppath.add(node);
		while(!temppath.isEmpty()) {
			path.push(temppath.pop());
			bwddistance++;
		}
		for(node=v; distance.get(node) != 0; node=fwdprevious.get(node)) {
			path.push(node);
			fwddistance++;
		}
		path.push(node);
		fwddistance++;
		totaldistance = fwddistance + bwddistance;
		return path;
	}
	
	public void intializeVisited() {
		for(Vertex node : nodes) {
			fwdvisited.put(node, false);
		}
		for(Vertex node : nodes) {
			bwdvisited.put(node, false);
		}
	}

	public void intializeDistance() {
		for(Vertex node : nodes) {
			distance.put(node, Integer.MAX_VALUE);
		}
	}
	
	
	public List<Vertex> getDirection() {
		return direction;
	}

	public int getFwddistance() {
		return fwddistance;
	}

	public int getBwddistance() {
		return bwddistance;
	}

	public int getTotaldistance() {
		return totaldistance;
	}
	
	public String getSearch() {
		return search;
	}
	
}
