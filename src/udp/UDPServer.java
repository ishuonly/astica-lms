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

    public static int checkVerification(String hashKey, String username, String systemID, String motherboardSN, String cpuID, String macAddress, int subscription) {
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
                    if (subscription == 1) {
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
                        return 0;
                    } else {
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
                        return 1;
                    }
                } else {
                    //user not verified(details don't match)
                    return -1;
                }
            } else {
                // Hash_Key does not exist in the database
                return -1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
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

        int response = checkVerification(hashKey, username, systemID, motherboardSN, cpuID, macAddress, subscription);
        byte[] responseBytes = String.valueOf(response).getBytes();
        InetAddress clientAddress = dp.getAddress();
        int clientPort = dp.getPort();
        DatagramPacket responsePacket = new DatagramPacket(responseBytes, responseBytes.length, clientAddress, clientPort);
        ds.send(responsePacket);
    }

}
