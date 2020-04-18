package DiGraph_A5;

import java.util.*;

public class DiGraph implements DiGraphInterface {
	LinkedList<Long> graphVert_idNums;
	LinkedList<Long> graphEdge_idNums;
	HashMap<String, DiGraphVertex> graph_vertexs;  
	// in here go all your data and methods for the graph

	public DiGraph() {
		graphVert_idNums = new LinkedList<Long>();
		graphEdge_idNums = new LinkedList<Long>();
		graph_vertexs = new HashMap<String, DiGraphVertex>();
	}

	@Override
	public boolean addNode(long idNum, String label) {
		if(graphVert_idNums.contains(idNum) || idNum < 0) {
			return false;
		}
		if(graph_vertexs.containsKey(label)) {
			return false;
		}
		DiGraphVertex vert = new DiGraphVertex(idNum, label);
		graphVert_idNums.add(idNum);
		graph_vertexs.put(label, vert);
		return true;
	}

	@Override
	public boolean addEdge(long idNum, String sLabel, String dLabel, long weight, String eLabel) {
		if(graphEdge_idNums.contains(idNum) || idNum < 0) {
			return false;
		}
		if(!(graph_vertexs.containsKey(sLabel)) || !(graph_vertexs.containsKey(dLabel))) {
			return false;
		}
		// TODO check if there is already edge between slabel and dlabel
		//if we make it this far: add outEdge and inEdge
		return false;
	}

	@Override
	public boolean delNode(String label) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delEdge(String sLabel, String dLabel) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long numNodes() {
		return graph_vertexs.size();
	}

	@Override
	public long numEdges() {
		// TODO Auto-generated method stub
		return 0;
	}

	// rest of your code to implement the various operations
}