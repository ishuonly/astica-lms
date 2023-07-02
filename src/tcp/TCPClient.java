/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tcp;

import java.io.*;
import java.net.*;


/**
 *
 * @author HP
 */
public class TCPClient {
    public static void main(String[] args) throws IOException{
        try{
        Socket s = new Socket("localhost",4999);
        
        // Create an output stream to send data
            OutputStream outputStream = s.getOutputStream();
            PrintWriter writer = new PrintWriter(outputStream, true);

            // Send the string to the server
            String message = "Hello, server!";
            writer.println(message);

            // Close the connection
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
//        InputStream clientInputStream = s.getInputStream();
//    OutputStream clientOutputStream = s.getOutputStream();
//   
//BufferedReader clientReader = new BufferedReader(new InputStreamReader(clientInputStream));
//
//PrintWriter clientWriter = new PrintWriter(clientOutputStream, true); 

    }
            
            
    
}
