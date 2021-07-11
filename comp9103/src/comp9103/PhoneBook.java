package comp9103;

import java.util.ArrayList;

public class PhoneBook {
	
	private ArrayList<Record> phoneBook;
	
	public PhoneBook() {
		this.phoneBook = new ArrayList<Record>();
	}
	
	public void add(Record record) {
		this.phoneBook.add(record);
		
	}
	
	public void delete(Name name) {
		
	}
	
	public void testShow() {
		for(Record r : phoneBook) {
			if(r.getName()!=null)
			System.out.println(r.getName());
			if(r.getBirthday()!=null)
			System.out.println(r.getBirthday());
			if(r.getPhone()!=null)
			System.out.println(r.getPhone());
			if(r.getEmail()!=null)
			System.out.println(r.getEmail());
			if(r.getAddress()!=null)
			System.out.println(r.getAddress());
			System.out.println("=================");
		}
	}
	
	public ArrayList<Record> getRecordsList(){
		return this.phoneBook;
	}


	public void setPhoneBook(ArrayList<Record> phoneBook) {
		this.phoneBook = phoneBook;
	}
	
	
	

}
