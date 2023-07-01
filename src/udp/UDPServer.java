package udp;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import databases.ConnectionProviderS;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class UDPServer {
    
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

    public static int checkVerification(int subscription) {
        if(subscription==1){
            return 0;
        }
        else{
            return 1;
        }
    }

    public static void main(String a[]) throws Exception {
        DatagramSocket ds = new DatagramSocket(9999);
        System.out.println("UDP server started. Waiting for incoming data...");
        byte[] b1 = new byte[1024];
        DatagramPacket dp = new DatagramPacket(b1, b1.length);
        ds.receive(dp);
        String str = new String(dp.getData(), 0, dp.getLength());
        //json logic here
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

        // Print retrieved information
        System.out.println("Username: " + username);
        System.out.println("SystemID: " + systemID);
        System.out.println("Hash_Key: " + hashKey);
        System.out.println("MotherboardSN: " + motherboardSN);
        System.out.println("CPU_ID: " + cpuID);
        System.out.println("MACAddress: " + macAddress);
        System.out.println("Subscription: " + subscription);
        
        

        // Prepare response
        int response = checkVerification(subscription);
        byte[] responseBytes = String.valueOf(response).getBytes();
        InetAddress clientAddress = dp.getAddress();
        int clientPort = dp.getPort();
        DatagramPacket responsePacket = new DatagramPacket(responseBytes, responseBytes.length, clientAddress, clientPort);
        ds.send(responsePacket);
    }

}
