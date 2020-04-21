package DiGraph_A5;

import java.util.*;

public class DiGraph implements DiGraphInterface {
	HashMap<Long, String> graphEdge_idNums;
	HashMap<String, DiGraphVertex> graph_vertexs;
	HashMap<Long, DiGraphVertex> graphVert_idNums;
	private LinkedList<String> vertex_names = new LinkedList<String>();
	int num_of_edges;
	// in here go all your data and methods for the graph

	public DiGraph() {
		graphVert_idNums = new HashMap<Long, DiGraphVertex>();
		graphEdge_idNums = new HashMap<Long, String>();
		graph_vertexs = new HashMap<String, DiGraphVertex>();
		num_of_edges = 0;
	}

	@Override
	public boolean addNode(long idNum, String label) {
		if (graphVert_idNums.containsKey(idNum) || idNum < 0) {
			return false;
		}
		if (graph_vertexs.containsKey(label)) {
			return false;
		}
		DiGraphVertex vert = new DiGraphVertex(idNum, label);
		graphVert_idNums.put(idNum, vert);
		graph_vertexs.put(label, vert);
		vertex_names.add(label);
		return true;
	}

	@Override
	public boolean addEdge(long idNum, String sLabel, String dLabel, long weight, String eLabel) {
		if (graphEdge_idNums.containsKey(idNum) || idNum < 0) {
			return false;
		}
		if (!(graph_vertexs.containsKey(sLabel)) || !(graph_vertexs.containsKey(dLabel))) {
			return false;
		}
		// checks if there is already edge between slabel and dlabel
		LinkedList<DiGraphEdge> out = graph_vertexs.get(sLabel).getOutEdges();
		LinkedList<DiGraphEdge> in = graph_vertexs.get(dLabel).getInEdges();
		for (int i = 0; i < out.size(); i++) {
			if (in.contains(out.get(i))) {
				return false;
			}
		}
		// if we make it this far: add outEdge and inEdge
		graphEdge_idNums.put(idNum, eLabel);
		DiGraphEdge edge = new DiGraphEdge(idNum, sLabel, dLabel, weight, eLabel);
		graph_vertexs.get(sLabel).addOutEdge(edge);
		graph_vertexs.get(dLabel).addInEdge(edge);
		num_of_edges++;
		return true;
	}

	@Override
	public boolean delNode(String label) {
		if (!(graph_vertexs.containsKey(label))) {
			return false;
		}
		LinkedList<DiGraphEdge> out = graph_vertexs.get(label).getOutEdges();
		LinkedList<DiGraphEdge> in = graph_vertexs.get(label).getInEdges();
		for (int i = 0; i < out.size(); i++) {
			delEdge(out.get(i).getSLabel(), out.get(i).getDLabel());
		}
		for (int i = 0; i < in.size(); i++) {
			delEdge(in.get(i).getSLabel(), in.get(i).getDLabel());
		}
		graphVert_idNums.remove(graph_vertexs.get(label).getIdNum());
		graph_vertexs.remove(label);
		vertex_names.remove(label);
		return true;
	}

	@Override
	public boolean delEdge(String sLabel, String dLabel) {
		if (!(graph_vertexs.containsKey(sLabel)) || !(graph_vertexs.containsKey(dLabel))) {
			return false;
		}
		LinkedList<DiGraphEdge> source = graph_vertexs.get(sLabel).getOutEdges();
		LinkedList<DiGraphEdge> dest = graph_vertexs.get(dLabel).getInEdges();
		// For loops checks to see if edge exists and if it does it removes it!
		for (int i = 0; i < source.size(); i++) {
			DiGraphEdge rem = source.get(i);
			if (dest.contains(source.get(i))) {
				graph_vertexs.get(dLabel).removeInEdge(rem);
				graph_vertexs.get(sLabel).removeOutEdge(rem);
				graphEdge_idNums.remove(rem.getIdNum());
				num_of_edges--;
				return true;
			}
		}
		return false;
	}

	@Override
	public long numNodes() {
		return graph_vertexs.size();
	}

	@Override
	public long numEdges() {
		return num_of_edges;
	}

	@Override
	public ShortestPathInfo[] shortestPath(String label) {
		ShortestPathInfo[] paths = new ShortestPathInfo[(int) this.numNodes()];
		HashMap<String, Integer> dest_dist = new HashMap<String, Integer>();
		// Initialize the above hashmap with each destination vertex (including src
		// itself) w/ super high distances from src
		for (int i = 0; i < vertex_names.size(); i++) {
			dest_dist.put(vertex_names.get(i), Integer.MAX_VALUE);
		}
		// create pq and add src to it w/ dist of zero
		PriorityQueue<Map.Entry<String, Integer>> queue = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
		queue.offer(new AbstractMap.SimpleEntry<>(label, 0));
		// update dest_best_path for src w/ zero as distance
		dest_dist.remove(label);
		dest_dist.put(label, 0);
		while (!queue.isEmpty()) {
			Map.Entry<String, Integer> u = queue.remove();
			LinkedList<DiGraphEdge> out = graph_vertexs.get(u.getKey()).getOutEdges();
			for (int i = 0; i < out.size(); i++) {
				DiGraphEdge e = out.get(i);
				Integer newDist = (int) (dest_dist.get(e.getSLabel()) + e.getWeight());
				if (dest_dist.get(e.getDLabel()) > newDist) {
					{
						dest_dist.remove(e.getDLabel());
						dest_dist.put(e.getDLabel(), newDist);
						queue.offer(new AbstractMap.SimpleEntry<>(e.getDLabel(), newDist));

					}
				}

			}
		}

		// iterate thru hashmap and add all values to the paths variable
		for (int i = 0; i < vertex_names.size(); i++) {
			String name = vertex_names.get(i);
			Integer distance = dest_dist.get(name);
			if (distance == Integer.MAX_VALUE) {
				paths[i] = new ShortestPathInfo(name, -1);
			} else {
				paths[i] = new ShortestPathInfo(name, distance);
			}

		}
		return paths;

	}
}
