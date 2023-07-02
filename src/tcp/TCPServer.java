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
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class TCPServer {
    
//    public void saveToDatabase(String un, String sysId, String key, String mac, String cpu, String motherboard) {
//
//        try {
//            Connection con = ConnectionProviderS.getConn();
//            String query = "INSERT INTO userdb (Username, SystemID, Hash_key, MACAddress, CPU_ID, MotherboardSN, Subscription) VALUES (?, ?, ?, ?, ?, ?, ?)";
//
//            PreparedStatement preparedStatement = con.prepareStatement(query);
//            preparedStatement.setString(1, un);
//            preparedStatement.setString(2, sysId);
//            preparedStatement.setString(3, key);
//            preparedStatement.setString(4, mac);
//            preparedStatement.setString(5, cpu);
//            preparedStatement.setString(6, motherboard);
//            preparedStatement.setInt(7, 0);
//            preparedStatement.executeUpdate();
//            preparedStatement.close();
//            con.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//
//        }
//    }
    
    public static int checkVerification(String hashKey, String username, String systemID,  String motherboardSN, String cpuID, String macAddress, int subscription, int button) {
    try {
            Connection con = ConnectionProviderS.getConn();
            String query = "SELECT Username, SystemID FROM userdb WHERE Hash_Key = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, hashKey);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String storedUsername = resultSet.getString("Username");
                String storedSystemID = resultSet.getString("SystemID");

                if (username.equals(storedUsername) && systemID.equals(storedSystemID)) {
                    if (subscription == 1 && button ==0) {
                        //deactivation code here
                        try {
                            String query1 = "UPDATE userdb SET MotherboardSN=?, CPU_ID=?, MACAddress=?, Subscription = ? WHERE Hash_Key = ?";
                            PreparedStatement preparedStatement = con.prepareStatement(query1);
                            preparedStatement.setString(1, null);
                            preparedStatement.setString(2, null);
                            preparedStatement.setString(3, null);
                            preparedStatement.setInt(4, 0);
                            preparedStatement.setString(5, hashKey);
                            preparedStatement.executeUpdate();
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, e);
                        }
                        return 10 ;
                    } else if(subscription == 0 && button == 1){
                        //activation code here
                        try {
                            String query2 = "UPDATE userdb SET MotherboardSN=?, CPU_ID=?, MACAddress=?, Subscription = ? WHERE Hash_Key = ?";
                            PreparedStatement preparedStatement = con.prepareStatement(query2);
                            preparedStatement.setString(1, motherboardSN);
                            preparedStatement.setString(2, cpuID);
                            preparedStatement.setString(3, macAddress);
                            preparedStatement.setInt(4, 1);
                            preparedStatement.setString(5, hashKey);
                            preparedStatement.executeUpdate();
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, e);
                        }
                        return 01;
                    
                    } else if(subscription == 0 && button ==0){
                        return 00;
                    }
                    else{
                        return 11;
                    }
                }
               else {
                    //user not verified(details don't match)
                    return -1;
                }
            }
                else {
                // Hash_Key does not exist in the database
                return -1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    
    public static void main(String[] args) throws IOException {
    
        
        try {
            ServerSocket ss = new ServerSocket(4999);
           
            
                 Socket s = ss.accept();
            
                System.out.println("client connected");
            
                // Create an input stream to receive data
                InputStream inputStream =s.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                OutputStream OutputStream = s.getOutputStream();
                PrintWriter sWrite = new PrintWriter(OutputStream, true);
                // Read the string from the client
                String str = reader.readLine();
                //json logic hereeadLine()) != null){
                // Parse JSON
                Gson gson = new Gson();
                JsonObject jsonObject = gson.fromJson(str, JsonObject.class);
                String username = jsonObject.get("Username").getAsString();
                String systemID = jsonObject.get("SystemID").getAsString();
                String hashKey = jsonObject.get("Hash_Key").getAsString();
                String motherboardSN = jsonObject.get("MotherboardSN").getAsString();
                String cpuID = jsonObject.get("CPU_ID").getAsString();
                String macAddress = jsonObject.get("MACAddress").getAsString();
                int subscription = jsonObject.get("Subscription").getAsInt();
                int button=jsonObject.get("Button").getAsInt();
                
                int response = checkVerification(hashKey, username, systemID,motherboardSN, cpuID, macAddress, subscription,button);
                 System.out.println(response);
                String responseStr=Integer.toString(response);
                sWrite.println(responseStr);
                System.out.println(responseStr);
            
                
                 // Close the connection
                 s.close();
                 ss.close();
            
           
        
       
}  catch (IOException e) {
            e.printStackTrace();
        }
    }

}





    

        