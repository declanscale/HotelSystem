package comp9103;

public class Record {
	
	private Name name;
	private DoB birthday;
	private Phone phone;
	private Email email;
	private Address address;
	
	public Record(Name name, DoB birthday) {
		this.name = name;
		this.birthday = birthday;
		this.phone = null;
		this.email = null;
		this.address = null;
	}
	
	public Record(Name name, DoB birthday, Phone phone, Email email, Address address) {
		this.name = name;
		this.birthday = birthday;
		this.phone = phone;
		this.email = email;
		this.address = address;
	}

	
	
	
//========  Getter and Setter =========
	
	public String getName() {
		
		if(name!=null) {
			return name.getName();
		}else {
			return "name is null";
		}
		
		
	}

	public void setName(Name name) {
		this.name = name;
	}

	public String getBirthday() {
		
		if(birthday != null) {
			return birthday.getBirthday();
		}else {
			return "birthday is null";
		}
		
		
	}

	public void setBirthday(DoB birthday) {
		this.birthday = birthday;
	}

	public String getPhone() {
		
		if(phone!=null) {
			return phone.getPhone();
		}else {
			return "phone is null";
		}
		
		
	}

	public void setPhone(Phone phone) {
		this.phone = phone;
	}

	public String getEmail() {
		
		if(email != null) {
			return email.getEmail();
		}else {
			return  "email is null";
		}
		
		
	}

	public void setEmail(Email email) {
		this.email = email;
	}

	public String getAddress() {
		
		if(address != null) {
			return address.getAddress();
		}else {
			return "address is null";
		}
		
		
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	

}
