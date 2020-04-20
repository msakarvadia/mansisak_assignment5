package DiGraph_A5;
import java.util.*; 

public class ShortestPathComparator implements Comparator<ShortestPathInfo>{

	@Override
	public int compare(ShortestPathInfo o1, ShortestPathInfo o2) {
		if(o1.getTotalWeight()<o2.getTotalWeight()) {
			return 1;
		}
		else{
			return -1;
		}
	}

}
