package comp9103;

import java.util.regex.Pattern;

public class Address {
	
	private String address;
	
	public Address(String address) {
		this.address = address;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean addressIsValid() {
		
		String[] parts = address.trim().split(" ");
		
		return isNumeric(parts[parts.length - 1]);
		
	}
	
	public static boolean isNumeric(String str){ 
	    Pattern pattern = Pattern.compile("[0-9]*"); 
	    return pattern.matcher(str).matches();    
	 } 
	
	

}

