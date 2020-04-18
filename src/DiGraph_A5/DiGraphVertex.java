package DiGraph_A5;

import java.util.*;


public class DiGraphVertex {
	private long idNum;
	private String label;
	private LinkedList<DiGraphEdge> out_edge = new LinkedList<DiGraphEdge>();
	private LinkedList<DiGraphEdge> in_edge = new LinkedList<DiGraphEdge>();
	
	public DiGraphVertex(long idNum, String label) {
	    this.idNum = idNum;
	    this.label = label;
	  }
	
	public long getIdNum() { return idNum;}
	public String getLabel() { return label;}
	public void addOutEdge(DiGraphEdge out) {
		out_edge.add(out);
	}
	public void addInEdge(DiGraphEdge in) {
		in_edge.add(in);
	}
	public void removeOutEdge(DiGraphEdge out) {
		out_edge.remove(out);
	}
	public void removeInEdge(DiGraphEdge in) {
		in_edge.remove(in);
	}
	public LinkedList<DiGraphEdge> getOutEdges() {return out_edge;}
	public LinkedList<DiGraphEdge> getInEdges() {return in_edge;}
	
}
