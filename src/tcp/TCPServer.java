/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tcp;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import databases.ConnectionProviderS;
import java.net.*;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.net.InetAddress;

/**
 *
 * @author HP
 */
public class TCPServer {
    
    public void saveToDatabase(String un, String sysId, String key, String mac, String cpu, String motherboard) {

        try {
            Connection con = ConnectionProviderS.getConn();
            String query = "INSERT INTO userdb (Username, SystemID, Hash_key, MACAddress, CPU_ID, MotherboardSN, Subscription) VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, un);
            preparedStatement.setString(2, sysId);
            preparedStatement.setString(3, key);
            preparedStatement.setString(4, mac);
            preparedStatement.setString(5, cpu);
            preparedStatement.setString(6, motherboard);
            preparedStatement.setInt(7, 0);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
    
    public static int checkVerification(String hashKey, String username, String systemID, int subscription) {
        if (subscription == 1) {
            
            return 0;
        } else {
            return 1;
        }
    }

    private static boolean checkHashKeyInDatabase(String hashKey) {

        try {
            Connection con = ConnectionProviderS.getConn();
            String query = "SELECT COUNT(*) FROM userdb WHERE Hash_Key = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, hashKey);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    
    public static void main(String[] args) throws IOException {
    
        ServerSocket ss= new ServerSocket(4999);
    Socket s = ss.accept();
    
    System.out.println("client connected");
    
    
            // Accept client connections
            

            // Create an input stream to receive data
            InputStream inputStream =s.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            OutputStream OutputStream = s.getOutputStream();

            // Read the string from the client
            String str = reader.readLine();
            PrintWriter sWrite = new PrintWriter(OutputStream, true); 
           

          
//

//    //json logic here
//        // Parse JSON
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(str, JsonObject.class);
        String username = jsonObject.get("Username").getAsString();
        String systemID = jsonObject.get("SystemID").getAsString();
        String hashKey = jsonObject.get("Hash_Key").getAsString();
        String motherboardSN = jsonObject.get("MotherboardSN").getAsString();
        String cpuID = jsonObject.get("CPU_ID").getAsString();
        String macAddress = jsonObject.get("MACAddress").getAsString();
        int subscription = jsonObject.get("Subscription").getAsInt();

      // Check if Hash_Key exists in the database
      boolean hashKeyExists = checkHashKeyInDatabase(hashKey);

        if (hashKeyExists) {
            int response = checkVerification(hashKey, username, systemID, subscription);
            String responseStr=Integer.toString(response);
            sWrite.println(responseStr);
        } else {
            int response = -1;
            String responseStr=Integer.toString(response);

            sWrite.println(responseStr);
        }

      // Close the connection
            s.close();
            ss.close();
       
}

}







    

        