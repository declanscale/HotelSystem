package comp9103;

public class Name {
	
	private String name;
	
	public Name(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public boolean nameIsValid() {
		String tempName = this.name;
		tempName = tempName.replace(" ", "");
		return tempName.matches("[a-zA-Z]+");
	}
	
	

}
