/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package client;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import databases.ConnectionProviderC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import hardwareConfigs.MACAddress;
import hardwareConfigs.cpuSN;
import hardwareConfigs.motherboardSN;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class ClientForm extends javax.swing.JFrame {

    /**
     * Creates new form ClientF
     */
    public ClientForm() {
        initComponents();
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

        unLabel.setBackground(new java.awt.Color(255, 255, 255));
        unLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        unLabel.setText("USERNAME :");
        unLabel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        sysIdLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        sysIdLabel.setText("SYSTEM ID:");
        sysIdLabel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        activate.setBackground(new java.awt.Color(0, 255, 0));
        activate.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        activate.setForeground(new java.awt.Color(255, 255, 255));
        activate.setText("Generate Activate License File");
        activate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                activateActionPerformed(evt);
            }
        });

        deactivate.setBackground(new java.awt.Color(255, 0, 0));
        deactivate.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        deactivate.setForeground(new java.awt.Color(255, 255, 255));
        deactivate.setText("Generate Deactivate License File");
        deactivate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deactivateActionPerformed(evt);
            }
        });

        keyLabel.setBackground(new java.awt.Color(255, 102, 102));
        keyLabel.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        keyLabel.setText("KEY");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 255));
        jLabel1.setText("LOGIN FORM");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(unLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sysIdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(keyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(unText, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sysIdText, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(keyText, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(activate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(deactivate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(171, 171, 171)
                        .addComponent(jLabel1)))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(unText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(unLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(sysIdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(sysIdText)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(keyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(keyText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(activate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(deactivate)
                .addGap(22, 22, 22))
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

        saveToJson(un, sysId, key, mac, cpu, motherboard, 1);
        checkDeactivation(un, sysId, key, mac, cpu, motherboard);
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
        saveToJson(un, sysId, key, mac, cpu, motherboard, 0);
        checkActivation(un, sysId, key, mac, cpu, motherboard);
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

    public void saveToJson(String un, String sysId, String key, String mac, String cpu, String motherboard, int subs) {
        String directoryPath2 = "src\\client_lic_files\\";
        JsonObject rowJson = new JsonObject();
        rowJson.addProperty("Username", un);
        rowJson.addProperty("SystemID", sysId);
        rowJson.addProperty("Hash_Key", key);
        rowJson.addProperty("MotherboardSN", motherboard);
        rowJson.addProperty("CPU_ID", cpu);
        rowJson.addProperty("MACAddress", mac);
        rowJson.addProperty("Subscription", subs);

        JsonArray jsonArray = new JsonArray();
        jsonArray.add(rowJson);

        if (subs == 1) {
            try (FileWriter fileWriter = new FileWriter(directoryPath2 + key + "_deactivate.json")) {
                fileWriter.write(jsonArray.toString());
                deactivate.setText("Deactivate License");
                JOptionPane.showMessageDialog(null, "License File Generated");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else if (subs == 0) {
            try (FileWriter fileWriter = new FileWriter(directoryPath2 + key + "_verify.json")) {
                fileWriter.write(jsonArray.toString());
                activate.setText("Activate License");
                JOptionPane.showMessageDialog(null, "License File Generated");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }

    public void checkActivation(String un, String sysId, String key, String mac, String cpu, String motherboard) {
        String directoryPath = "src\\server_lic_files\\";
        String VerifyfileName = key + "_verify.json";
        File vf = new File(directoryPath + VerifyfileName);
//        System.out.println(directoryPath + VerifyfileName);
        if (vf.exists()) {
            JsonElement jsonElement;
            try (FileReader fileReader = new FileReader(directoryPath + VerifyfileName)) {
                jsonElement = JsonParser.parseReader(fileReader);

                if (jsonElement.isJsonArray()) {
                    JsonArray jsonArray = jsonElement.getAsJsonArray();
                    for (JsonElement element : jsonArray) {
                        if (element.isJsonObject()) {
                            JsonObject jsonObject = element.getAsJsonObject();

                            String username = jsonObject.get("Username").getAsString();
                            String sysid = jsonObject.get("SystemID").getAsString();
                            String hashkey = jsonObject.get("Hash_Key").getAsString();
                            String mothersn = jsonObject.get("MotherboardSN").getAsString();
                            String cpuid = jsonObject.get("CPU_ID").getAsString();
                            String macid = jsonObject.get("MACAddress").getAsString();

                            if (username.equals(un) && sysid.equals(sysId) && hashkey.equals(key) && mothersn.equals(motherboard) && cpuid.equals(cpu) && macid.equals(mac)) {

                                String updateQuery = "UPDATE userdb SET Subscription=? WHERE Hash_key=?";

                                try (Connection con = ConnectionProviderC.getConn(); PreparedStatement pstmt = con.prepareStatement(updateQuery);) {
                                    pstmt.setInt(1, 1);
                                    pstmt.setString(2, hashkey);

                                    int rowsAffected = pstmt.executeUpdate();

                                    if (rowsAffected > 0) {
                                        JOptionPane.showMessageDialog(null, "License Activated!");
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Failed to activate license");
                                    }

                                } catch (Exception excep) {
                                    JOptionPane.showMessageDialog(null, excep);
                                }

                            }
                        }

                    }

                } else {
                    System.out.println("Invalid JSON file format. Expected a JSON array.");
                }
                fileReader.close();
                Deletejson(directoryPath, VerifyfileName);

            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void checkDeactivation(String un, String sysId, String key, String mac, String cpu, String motherboard) {
        String directoryPath1 = "src\\server_lic_files\\";
        String fileName = key + "_deactivate.json";
        File df = new File(directoryPath1 + fileName);
//        System.out.println(directoryPath + VerifyfileName);
        if (df.exists()) {
            JsonElement jsonElement;
            try (FileReader fileReader = new FileReader(directoryPath1 + fileName)) {
                jsonElement = JsonParser.parseReader(fileReader);

                if (jsonElement.isJsonArray()) {
                    JsonArray jsonArray = jsonElement.getAsJsonArray();
                    for (JsonElement element : jsonArray) {
                        if (element.isJsonObject()) {
                            JsonObject jsonObject = element.getAsJsonObject();

                            String username = jsonObject.get("Username").getAsString();
                            String sysid = jsonObject.get("SystemID").getAsString();
                            String hashkey = jsonObject.get("Hash_Key").getAsString();
                            String mothersn = jsonObject.get("MotherboardSN").getAsString();
                            String cpuid = jsonObject.get("CPU_ID").getAsString();
                            String macid = jsonObject.get("MACAddress").getAsString();

                            if (username.equals(un) && sysid.equals(sysId) && hashkey.equals(key) && mothersn.equals(motherboard) && cpuid.equals(cpu) && macid.equals(mac)) {

                                try {
                                    Connection con = ConnectionProviderC.getConn();
                                    String updateQuery = "UPDATE userdb SET MotherboardSN=?, CPU_ID=?, MACAddress=?, Subscription = ? WHERE Hash_Key = ?";

                                    PreparedStatement pstmt = con.prepareStatement(updateQuery);
                                    pstmt.setString(1, null);
                                    pstmt.setString(2, null);
                                    pstmt.setString(3, null);
                                    pstmt.setInt(4, 0); // Set Subscription to 0 (deactivated)
                                    pstmt.setString(4, hashkey);

                                    int rowsAffected = pstmt.executeUpdate();

                                    if (rowsAffected > 0) {
                                        Deletejson(directoryPath1, fileName);
                                        JOptionPane.showMessageDialog(null, "License Deactivated!");
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Failed to deactivate the license");
                                    }

                                    pstmt.close();
                                    con.close();
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }

                                JOptionPane.showMessageDialog(null, "License Deactivated!");
                            }
                        }

                    }
                } else {
                    System.out.println("Invalid JSON file format. Expected a JSON array.");
                }

            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }

    public static void Deletejson(String directoryPath, String fileName) {
        String filePath = directoryPath + fileName;

        File jsonFile = new File(filePath);

        if (jsonFile.exists()) {
            try {
                Path path = Paths.get(filePath);
                Files.delete(path);
                System.out.println("JSON file deleted successfully.");
            } catch (IOException e) {
                System.out.println("Error occurred while deleting the JSON file: " + e.getMessage());
            }
        } else {
            System.out.println("JSON file does not exist.");
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
