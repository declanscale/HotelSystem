package comp9103;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DoB {
	
	private String birthday;
	
	public DoB(String birthday) {
		String[] allParts = birthday.trim().split("-");
		String day = allParts[0];
		String month = allParts[1];
		String year = allParts[2];
		
		if(day.length()<2) {
			day = "0"+day;
		}
		if(month.length()<2) {
			month = "0"+month;
		}
		
		this.birthday = day + "-"+ month+"-"+year;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
	public boolean DoBIsValid() {
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		
		boolean isValid = true;
		
		try {
			Date date = format.parse(this.birthday);
			String[] parts = this.birthday.trim().split("-");
			
			if(Integer.parseInt(parts[2])> 2020) {
				isValid = false;
			}
			int month = Integer.parseInt(parts[1]);
			int day = Integer.parseInt(parts[0]);
			if(month == 2 || month == 4 || month ==6 || month == 9 || month ==11) {
				if(day > 30) {
					isValid = false;
				}
			}
		}catch(ParseException e) {
			
			isValid = false;
			
		}
		
		return isValid;
	}

	
}
