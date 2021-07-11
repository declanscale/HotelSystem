package comp9103;

public class Phone {
	
	private String phone;
	
	public Phone(String phone) {
		this.phone = phone;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public void removeLeadingZero() {
		if(this.phone !=null)
		this.phone = phone.replaceFirst("^0*", "");
	}
	
	public boolean phoneIsValid() {
		return this.phone.matches("[0-9]+");
	}

	
}
