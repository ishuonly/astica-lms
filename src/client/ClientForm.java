package client;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import databases.ConnectionProviderC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import hardwareConfigs.MACAddress;
import hardwareConfigs.cpuSN;
import hardwareConfigs.motherboardSN;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.sql.ResultSet;

public class ClientForm extends javax.swing.JFrame {

    public ClientForm() {
        initComponents();
        ImageIcon icon = new ImageIcon("src/Images/half-logo-icon.png");
        setIconImage(icon.getImage());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        unLabel = new javax.swing.JLabel();
        sysIdLabel = new javax.swing.JLabel();
        unText = new javax.swing.JTextField();
        sysIdText = new javax.swing.JTextField();
        activate = new javax.swing.JButton();
        deactivate = new javax.swing.JButton();
        keyLabel = new javax.swing.JLabel();
        keyText = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("License Activation/Deactivation");
        setBackground(new java.awt.Color(255, 255, 255));

        unLabel.setBackground(new java.awt.Color(255, 255, 255));
        unLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        unLabel.setForeground(new java.awt.Color(0, 0, 0));
        unLabel.setText("USERNAME :");

        sysIdLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        sysIdLabel.setForeground(new java.awt.Color(0, 0, 0));
        sysIdLabel.setText("SYSTEM ID:");

        unText.setForeground(new java.awt.Color(0, 0, 0));

        sysIdText.setForeground(new java.awt.Color(0, 0, 0));

        activate.setBackground(new java.awt.Color(1, 12, 128));
        activate.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        activate.setForeground(new java.awt.Color(255, 255, 255));
        activate.setText("Activate License");
        activate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                activateActionPerformed(evt);
            }
        });

        deactivate.setBackground(new java.awt.Color(1, 12, 128));
        deactivate.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        deactivate.setForeground(new java.awt.Color(255, 255, 255));
        deactivate.setText("Deactivate License");
        deactivate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deactivateActionPerformed(evt);
            }
        });

        keyLabel.setBackground(new java.awt.Color(255, 102, 102));
        keyLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        keyLabel.setForeground(new java.awt.Color(0, 0, 0));
        keyLabel.setText("KEY:");

        keyText.setForeground(new java.awt.Color(0, 0, 0));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Activate/Deactivate License");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(143, 143, 143)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(unLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sysIdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(keyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(keyText)
                            .addComponent(unText)
                            .addComponent(sysIdText, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(activate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(deactivate, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(76, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(unLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(sysIdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(keyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(unText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(sysIdText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(keyText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(activate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(deactivate)
                .addGap(37, 37, 37))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void deactivateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deactivateActionPerformed
        // TODO add your handling code here:
        String un = unText.getText();
        String sysId = sysIdText.getText();
        String key = keyText.getText();
        String mac = MACAddress.getMAC();
        String cpu = cpuSN.getWindowsCPU_SerialNumber();
        String motherboard = motherboardSN.getmotherboardSN();
        
        try {
            Connection con = ConnectionProviderC.getConn();
            String querya = "SELECT Subscription FROM userdb WHERE Hash_Key = ?";
            PreparedStatement statement = con.prepareStatement(querya);
            statement.setString(1, key);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int storedSubs = resultSet.getInt("Subscription");

                if (storedSubs == 0) {
                    JOptionPane.showMessageDialog(null, "License already deactivated!");
                } else {
                    udpClient(un, sysId, key, mac, cpu, motherboard, 1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        
    }//GEN-LAST:event_deactivateActionPerformed

    private void activateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_activateActionPerformed
        // TODO add your handling code here:
        String un = unText.getText();
        String sysId = sysIdText.getText();
        String key = keyText.getText();
        String mac = MACAddress.getMAC();
        String cpu = cpuSN.getWindowsCPU_SerialNumber();
        String motherboard = motherboardSN.getmotherboardSN();
        
        saveToDatabase(un, sysId, key, mac, cpu, motherboard);

        try {
            Connection con = ConnectionProviderC.getConn();
            String querya = "SELECT Subscription FROM userdb WHERE Hash_Key = ?";
            PreparedStatement statement = con.prepareStatement(querya);
            statement.setString(1, key);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int storedSubs = resultSet.getInt("Subscription");

                if (storedSubs == 1) {
                    JOptionPane.showMessageDialog(null, "License already activated!");
                } else {                    
                    udpClient(un, sysId, key, mac, cpu, motherboard, 0);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_activateActionPerformed
    public void saveToDatabase(String un, String sysId, String key, String mac, String cpu, String motherboard) {

        try {
            Connection con = ConnectionProviderC.getConn();
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

    public void udpClient(String un, String sysId, String key, String mac, String cpu, String motherboard, int subs) {
        JsonObject rowJson = new JsonObject();
        rowJson.addProperty("Username", un);
        rowJson.addProperty("SystemID", sysId);
        rowJson.addProperty("Hash_Key", key);
        rowJson.addProperty("MotherboardSN", motherboard);
        rowJson.addProperty("CPU_ID", cpu);
        rowJson.addProperty("MACAddress", mac);
        rowJson.addProperty("Subscription", subs);

        Gson gson = new Gson();
        String jsonString = gson.toJson(rowJson);

        try {
            // Convert JSON data to byte array
            byte[] sendData = jsonString.getBytes();

            // Destination IP address and port number
            InetAddress serverAddress = InetAddress.getLocalHost();
            int serverPort = 9999;

            // Create UDP socket
            try (DatagramSocket socket = new DatagramSocket()) {
                // Create datagram packet with the JSON data
                DatagramPacket packet = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);

                // Send the datagram packet
                socket.send(packet);

                System.out.println("JSON data sent over UDP.");

                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

                // Receive the response from the server
                socket.receive(receivePacket);

                String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
                int subsVal = Integer.parseInt(response.trim());
                if (subsVal == 1) {
                    String updateQuery = "UPDATE userdb SET Subscription=? WHERE Hash_key=?";

                    try (Connection con = ConnectionProviderC.getConn(); PreparedStatement pstmt = con.prepareStatement(updateQuery);) {
                        pstmt.setInt(1, 1);
                        pstmt.setString(2, key);

                        int rowsAffected = pstmt.executeUpdate();

                        if (rowsAffected > 0) {
                            JOptionPane.showMessageDialog(null, "License Activated Successfully!");
                        } else {
                            JOptionPane.showMessageDialog(null, "Failed to activate license");
                        }

                    } catch (Exception excep) {
                        JOptionPane.showMessageDialog(null, excep);
                    }
                } else if (subsVal == 0) {
                    try {
                        Connection con = ConnectionProviderC.getConn();
                        String updateQuery = "UPDATE userdb SET MotherboardSN=?, CPU_ID=?, MACAddress=?, Subscription = ? WHERE Hash_Key = ?";

                        PreparedStatement pstmt = con.prepareStatement(updateQuery);
                        pstmt.setString(1, null);
                        pstmt.setString(2, null);
                        pstmt.setString(3, null);
                        pstmt.setInt(4, 0); // Set Subscription to 0 (deactivated)
                        pstmt.setString(5, key);

                        int rowsAffected = pstmt.executeUpdate();

                        if (rowsAffected > 0) {
                            JOptionPane.showMessageDialog(null, "License Deactivated Successfully!");
                        } else {
                            JOptionPane.showMessageDialog(null, "Failed to deactivate the license");
                        }

                        pstmt.close();
                        con.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "User not registered");
                }
                System.out.println("Server response: " + response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ClientForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClientForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClientForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClientForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton activate;
    private javax.swing.JButton deactivate;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel keyLabel;
    private javax.swing.JTextField keyText;
    private javax.swing.JLabel sysIdLabel;
    private javax.swing.JTextField sysIdText;
    private javax.swing.JLabel unLabel;
    private javax.swing.JTextField unText;
    // End of variables declaration//GEN-END:variables
}
