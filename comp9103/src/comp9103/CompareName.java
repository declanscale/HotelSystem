package comp9103;

import java.util.Comparator;

public class CompareName implements Comparator<Record>{
	
	@Override
	public int compare(Record r1, Record r2) { //used for sort name
		// TODO Auto-generated method stub
		return r1.getName().compareTo(r2.getName());
	}
	
	

}

