package comp9103;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;


public class ECB {
	
	private String PHONE_BOOK_FILE;
	private String INSTRUCTION_FILE;
	private String OUTPUT_FILE;
	private String REPORT_FILE;
	
	private PhoneBook phoneBook = new PhoneBook();
	
	public ECB(String phoneBookFile, String instructionFile, String outputFile, String reportFile) {
		this.PHONE_BOOK_FILE = phoneBookFile;
		this.INSTRUCTION_FILE = instructionFile;
		this.OUTPUT_FILE = outputFile;
		this.REPORT_FILE = reportFile;
		File f = new File(this.REPORT_FILE);
		if(f.exists()) {
			f.delete();
		}
	}
	
	
	
	public void readPhoneBookFile() {
		Scanner fileScanner = null;
		
		
		try {
			File file = new File(this.PHONE_BOOK_FILE);

			if(file.exists()) {

				fileScanner = new Scanner(file);
				
				Name name = null;
				DoB birthday = null;
				Phone phone = null;
				Email email = null;
				Address address = null;
				
				
				String tempLine = fileScanner.nextLine();
				
				String tempInfo = ""; //for address , if address has multi line, append rest line to previous line
				
				while(fileScanner.hasNextLine()) {
					
					String[] temp = tempLine.trim().split("\\s");//temp[0] shows what kind of add it is
				
					String infoPart="";
					for(int i = 1; i < temp.length;i++) { // start from 1, append information part together
						infoPart = infoPart + " " + temp[i];
					}
					infoPart = infoPart.trim();

					if(temp[0].equals("name")) {
						
						name = new Name(infoPart);
						if(!name.nameIsValid()) {
							name = null;
						}
						tempLine = fileScanner.nextLine();
						
						
					}else if(temp[0].equals("birthday")) {
						
						birthday = new DoB(infoPart);
						
						if(!birthday.DoBIsValid()) {
							birthday = null;
						}
						
						tempLine = fileScanner.nextLine();
						
						
					}else if(temp[0].equals("phone")) {
						
						phone = new Phone(infoPart);
						phone.removeLeadingZero();
						
						if(!phone.phoneIsValid()) {
							phone = null;
						}
						
						tempLine = fileScanner.nextLine();
						
						
					}else if(temp[0].equals("address")) {

						address = new Address(infoPart);
						tempInfo = infoPart;//if has next line, append to tempInfo
						tempLine = fileScanner.nextLine();

					}else if(temp[0].equals("email")) {
						
						email = new Email(infoPart);

						if(!email.emailIsValid()) {
							email = null;
						}
						
						tempLine = fileScanner.nextLine();

					}else if(tempLine.equals("")) {

						if(!address.addressIsValid()) {
							address = null;
						}
						
						Record record = new Record(name, birthday, phone, email, address);
						
						if(name != null && birthday != null) { //name and birthday are compulsory
							this.phoneBook.add(record);
						}

						
						name = null;
						birthday = null;
						phone = null;
						email = null;
						address = null;
						
						tempLine = fileScanner.nextLine();
						
					}else {
	
						for(int i = 0; i < temp.length;i++) {
							tempInfo = tempInfo + " " + temp[i];
						}

						address = new Address(tempInfo);
						tempLine = fileScanner.nextLine();

					}

				}

				//======= after while loop need to check add one more time to add last record
				
				String[] temp = tempLine.trim().split("\\s");

				String infoPart="";
				for(int i = 1; i < temp.length;i++) {
					infoPart = infoPart + " " + temp[i];
				}
				
				infoPart = infoPart.trim();
				
				if(temp[0].equals("name")) {
					name = new Name(infoPart);
					if(!name.nameIsValid()) {
						name = null;
					}
					
				}else if(temp[0].equals("birthday")) {
					birthday = new DoB(infoPart);
					if(!birthday.DoBIsValid()) {
						birthday = null;
					}
					
				}else if(temp[0].equals("phone")) {
					phone = new Phone(infoPart);
					phone.removeLeadingZero();
					if(!phone.phoneIsValid()) {
						phone = null;
					}
					
				}else if(temp[0].equals("address")) {
					address = new Address(infoPart);
					
				}else if(temp[0].equals("email")) {
					
					email = new Email(infoPart);
					
					if(!email.emailIsValid()) {
						email = null;
					}
				}
				
				if(!address.addressIsValid()) {
					address = null;
				}


				Record record = new Record(name, birthday, phone, email, address);
				
				if(name!=null && birthday!=null) {
					this.phoneBook.add(record);
				}

				name = null;
				birthday = null;
				phone = null;
				email = null;
				address = null;
				
		
				
			}
		}catch(FileNotFoundException e) {
			System.out.println("Phone Book File not found");
		}
	}
	
	public void readInstructionFile() {
		Scanner fileScanner = null;
		try {
			File file = new File(this.INSTRUCTION_FILE);
			
			if(file.exists()) {
				fileScanner  = new Scanner(file);
				
				String keyWord = "";
				String restInfo = "";

				while(fileScanner.hasNextLine()) {
					keyWord = fileScanner.next();
					if(fileScanner.hasNextLine()) {
						restInfo = fileScanner.nextLine();
					}
					
					
					if(keyWord.equals("add")) {
						add(restInfo);
					}else if(keyWord.equals("delete")) {
						delete(restInfo);
					}else if(keyWord.equals("save")) {
						save();
					}else if(keyWord.equals("query")) {
						query(restInfo);
					}

				}
			}
			
		}catch(FileNotFoundException e) {
			System.out.println("Instruction file not found");
		}
	}
	
	
	public void save() {
		PrintWriter outputFile = null;
		
		try {
			outputFile = new PrintWriter(new File(this.OUTPUT_FILE));
			
			ArrayList<Record> phoneBookToSave = this.phoneBook.getRecordsList();
			int counter = 0;
			for(Record record : phoneBookToSave) {
				outputFile.println("name: " + record.getName());
				
				outputFile.println("birthday: " + record.getBirthday());
				
				if(record.getAddress()!="address is null") {
					outputFile.println("address: " + record.getAddress());
					
				}
				if(record.getPhone()!="phone is null") {
					outputFile.println("phone: " + record.getPhone());
					
				}
				if(record.getEmail()!="email is null" && record.getEmail()!=null) {
					outputFile.println("email: " + record.getEmail());
				}

				counter++;
				if(counter < phoneBookToSave.size())
					outputFile.print("\n");
				
				
				
			}
			
			
			
		}catch(FileNotFoundException e){
			System.out.println("Cannot create the output file");
			System.out.println(e.getMessage());
		}finally {
			if (outputFile != null)
        	    outputFile.close();
		}
	}
	
	public void add(String addInfo) {
		
		String[] addParts = addInfo.trim().split(";");
		HashMap<String, String> addHashMap = new HashMap<>();// the key of hashmap is the kind of add, value is the add information
		
		for(String part : addParts) {// create hash map
			part = part.trim();
			String[] temp = part.split(" ");
			String keyPart = temp[0];
			String valuePart = "";
			for(int i = 1; i < temp.length;i++) {
				valuePart = valuePart + " " + temp[i];
			}
			valuePart = valuePart.trim();
			addHashMap.put(keyPart, valuePart);
			
		}
		
		String name = null;
		String birthday = null;
		String phone = null;
		String address = null;
		String email = null;
		
		if(addHashMap.containsKey("name")) {
			name = addHashMap.get("name");
		}
		
		if(addHashMap.containsKey("birthday")) {
			birthday = addHashMap.get("birthday");
		}
		
		if(addHashMap.containsKey("phone")) {
			phone = addHashMap.get("phone");
		}
		
		if(addHashMap.containsKey("address")) {
			address = addHashMap.get("address");
		}
		
		if(addHashMap.containsKey("email")) {
			email = addHashMap.get("email");
		}
		
		
		
		ArrayList<Record> allRecords = this.phoneBook.getRecordsList();
		
		int existFlag = 0; // if existFlag = 1,  means this record is already updated, no need to create again
		
		for(Record record : allRecords) { // check if there is exist record
			if(record.getName().equals(name) && record.getBirthday().equals(birthday)) { //if both name and birthday are same as an exist record, then update this record
				if(phone != null) {
					Phone addPhone = new Phone(phone);
					addPhone.removeLeadingZero();
					record.setPhone(addPhone);
					
				}
				
				if(address != null) {
					record.setAddress(new Address(address));
				}
				
				if(email != null) {
					record.setEmail(new Email(email));
				}
				
				existFlag = 1;// this record is already updated
	
			}
		}
		if(existFlag == 0) { // create new record and add to phonebook

			Phone addPhone = new Phone(phone);
			addPhone.removeLeadingZero();

			Record newRecord = new Record(new Name(name),new DoB(birthday),addPhone,new Email(email), new Address(address));
			this.phoneBook.add(newRecord);
		}
		

		
	}
	
	public void delete(String deleteInfo) {
		String[] deleteParts = deleteInfo.trim().split(";");
		
		if(deleteParts.length == 1) {
			
			String nameToDelete = deleteParts[0];
			
			ArrayList<Record> tempPhoneBook = this.phoneBook.getRecordsList();
			
			
			for(int i = 0; i < tempPhoneBook.size();i++) {
				if(tempPhoneBook.get(i).getName().equals(nameToDelete)) {
					tempPhoneBook.remove(i);
				}
			}
			
			
			phoneBook.setPhoneBook(tempPhoneBook);//assign new phoneBook to this phone Book
			
		}else if(deleteParts.length == 2) {
			deleteParts[1] = deleteParts[1].trim();
			String nameToDelete = deleteParts[0];
			String birthdayToDelete = deleteParts[1];


			String[] allParts = birthdayToDelete.trim().split("-");
			String day = allParts[0];
			String month = allParts[1];
			String year = allParts[2];
			
			if(day.length()<2) {
				day = "0"+day;
			}
			if(month.length()<2) {
				month = "0"+month;
			}
			
			birthdayToDelete = day + "-"+ month+"-"+year;
			
			
			ArrayList<Record> tempPhoneBook = this.phoneBook.getRecordsList();
			
			for(int i = 0; i < tempPhoneBook.size(); i++) {
				if(tempPhoneBook.get(i).getName().equals(nameToDelete) && tempPhoneBook.get(i).getBirthday().equals(birthdayToDelete)){
					tempPhoneBook.remove(i);
				}
			}
			
			
			
		}
	}
	
	public void query(String queryInfo) {
		
		
		String[] queryParts = queryInfo.trim().split(" ");
		String queryBy = queryParts[0]; //query By name, birthday or phone
		
		String restInfo = "";
		
		for(int i = 1; i < queryParts.length;i++) {
			restInfo = restInfo + " " + queryParts[i];
		}
		restInfo = restInfo.trim(); //query value
		
		ArrayList<Record> queryResult = new ArrayList<Record>();
		ArrayList<Record> curPhoneBook = this.phoneBook.getRecordsList();
		
		if(queryBy.equals("name")) {
			for(Record record : curPhoneBook) {
				if(record.getName().equals(restInfo)) {
					
					queryResult.add(record);
				}
			}
			
			CompareBirthday cb = new CompareBirthday();
			Collections.sort(queryResult,cb);
			Collections.reverse(queryResult);
			
			
			
		}else if(queryBy.equals("birthday")) {
			
			for(Record record : curPhoneBook) {
				if(record.getBirthday().equals(restInfo)) {
					queryResult.add(record);
				}
			}
			
			CompareName cn = new CompareName();
			Collections.sort(queryResult,cn);
			
		}else if(queryBy.equals("phone")) {
			for(Record record : curPhoneBook) {
				if(record.getPhone().equals(restInfo)) {
					queryResult.add(record);
				}
			}
			
			CompareName cn = new CompareName();
			Collections.sort(queryResult,cn);
		}
		
		
		
		PrintWriter reportFile = null;
		try {
			
			
			reportFile = new PrintWriter(new FileWriter(this.REPORT_FILE,true));
			
			reportFile.println("====== query " + queryBy + " " + restInfo + " ======");
			for(Record r : queryResult) {
				reportFile.println("name: " + r.getName());
				reportFile.println("birthday: "+r.getBirthday());
				
				if(r.getAddress()!="address is null") {
					reportFile.println("address: "+r.getAddress());
				}
				
				if(r.getPhone()!="phone is null") {
					reportFile.println("phone: "+r.getPhone());
				}
				
				if(r.getEmail()!="email is null") {
					reportFile.println("email: "+r.getEmail());
				}
				reportFile.print("\n");
				
			}
			reportFile.println("====== end of query " + queryBy + " " + restInfo + " ======");
			reportFile.print("\n");
			
		}catch(FileNotFoundException e) {
			System.out.println("Cannot create the report file");
			System.out.println(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(reportFile != null) {
				reportFile.close();
			}
		}
		

		
	}

	
	
	public void show() {
		this.phoneBook.testShow();
	}


}

