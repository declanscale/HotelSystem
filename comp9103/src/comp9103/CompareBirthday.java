package comp9103;

import java.util.Comparator;

public class CompareBirthday implements Comparator<Record>{

	@Override
	public int compare(Record r1, Record r2) { //used for sort birthday
		// TODO Auto-generated method stub
		return r1.getBirthday().compareTo(r2.getBirthday());
	}
	
}