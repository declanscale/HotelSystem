package comp9103;

public class Comp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String phoneBookFile = args[0];
		String instructionFile = args[1];
		String outputFile = args[2];
		String reportFile = args[3];
		
		ECB myECB  = new ECB(phoneBookFile, instructionFile, outputFile, reportFile);
		myECB.readPhoneBookFile();
		myECB.readInstructionFile();
		//myECB.show();
		

	}

}
