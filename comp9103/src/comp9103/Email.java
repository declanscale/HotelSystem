package comp9103;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Email {
	
	private String email;
	
	public Email(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	public boolean emailIsValid() {
		String REGEX="^\\w+((-\\w+)|(\\.\\w+))*@\\w+(\\.\\w{2,3}){1,3}$";
	    Pattern p = Pattern.compile(REGEX);    
	    Matcher matcher=p.matcher(this.email);    

	    return matcher.matches();

	}
}
