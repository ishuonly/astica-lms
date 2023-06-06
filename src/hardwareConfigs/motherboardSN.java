// Java code to get the system
// motherboard serial number on linux

// importing the libraries
import java.io.*;

class motherboardSN {
	static String getLinuxMotherBoardSerialNumber()
	{

		// command to be executed on the terminal
		String command
			= "sudo dmidecode -s baseboard-serial-number";

		// variable to store the Serial Number
		String serialNumber = null;

		// try block
		try {

			// declaring the process to run the command
			Process SerialNumberProcess
				= Runtime.getRuntime().exec(command);

			// getting the input stream using
			// InputStreamReader using Serial Number Process
			InputStreamReader ISR = new InputStreamReader(
				SerialNumberProcess.getInputStream());

			// declaring the Buffered Reader
			BufferedReader br = new BufferedReader(ISR);

			// reading the serial number using
			// Buffered Reader
			serialNumber = br.readLine().trim();

			// waiting for the system to return
			// the serial number
			SerialNumberProcess.waitFor();

			// closing the Buffered Reader
			br.close();
		}

		// catch block
		catch (Exception e) {

			// printing the exception
			e.printStackTrace();

			// giving the serial number the value null
			serialNumber = null;
		}

		// returning the serial number
		return serialNumber;
	}

	// Driver Code
	public static void main(String[] args)
	{

		// printing and calling the method which
		// returns the Serial Number
		System.out.println(
			getLinuxMotherBoardSerialNumber());
	}
}

