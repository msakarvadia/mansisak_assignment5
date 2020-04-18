package DiGraph_A5;


public class DiGraphEdge {
	private long idNum;
	private String slabel;
	private String dlabel;
	private long weight;
	private String elabel;
	
	public DiGraphEdge(long idNum, String sLabel, String dLabel, long weight, String eLabel) {
	    this.idNum = idNum;
	    this.slabel = sLabel;
	    this.dlabel = dLabel;
	    this.weight = weight;
	    this.elabel = eLabel;
	  }
	public long getIdNum() { return idNum;}
	public String getSLabel() {return slabel;}
	public String getDLabel() {return dlabel;}
	public long getWeight() {return weight;}
	public String getELabel() {return elabel;}
	
}
