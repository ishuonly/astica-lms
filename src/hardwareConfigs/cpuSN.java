package hardwareConfigs;
// Java Program to get CPU Serial Number of Windows Machine
// using FileWriter class
 
// Importing required classes
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
 
// Main class
// WindowsCpuSerialNumber
public class cpuSN {
 
    // Method 1
    // To get CPU serial number
    public static String getWindowsCPU_SerialNumber()
    {
 
        // Initially declaring and initializing an empty
        // string
        String result = "";
 
        // Try block to check for exceptions
        try {
 
            // Creating an object of File class
            File file
                = File.createTempFile("realhowto", ".vbs");
 
            // Deleting file while exiting
            file.deleteOnExit();
 
            // Creating an object of FileWriter class to
            // write on
            FileWriter fw = new java.io.FileWriter(file);
 
            // Remember the command
            String vbs1
                = "Set objWMIService = GetObject(\"winmgmts:\\\\.\\root\\cimv2\")\n"
                  + "Set colItems = objWMIService.ExecQuery _ \n"
                  + "   (\"Select * from Win32_Processor\") \n"
                  + "For Each objItem in colItems \n"
                  + "    Wscript.Echo objItem.ProcessorId \n"
                  + "    exit for  ' do the first cpu only! \n"
                  + "Next \n";
 
            // Writing on file
            fw.write(vbs1);
 
            // Closing all file connections to
            // release memory spaces
            fw.close();
 
            Process p = Runtime.getRuntime().exec(
                "cscript //NoLogo " + file.getPath());
 
            BufferedReader input = new BufferedReader(
                new InputStreamReader(p.getInputStream()));
 
            String line;
 
            while ((line = input.readLine()) != null) {
                result += line;
            }
 
            input.close();
        }
 
        // Catch block to handle the exceptions
        catch (Exception E) {
 
            // Print the exception along with the message
            System.err.println("Windows CPU Exp : "
                               + E.getMessage());
        }
 
        return result.trim();
    }
 
    // Method 2
    // Main driver method
    public static void main(String[] args)
    {
 
        String cpuSerialNumber
            = getWindowsCPU_SerialNumber();
 
        // Calling the method1 to retrieve CPU serial number
        // and printing the same
        System.out.println(
            "CPU Serial Number of my Windows Machine: "
            + cpuSerialNumber);
    }
}
