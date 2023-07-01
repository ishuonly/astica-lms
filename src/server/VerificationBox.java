/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package server;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import databases.ConnectionProviderS;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.FileReader;
import java.io.FileWriter;

/**
 *
 * @author ASUS
 */
public class VerificationBox extends javax.swing.JFrame {

    /**
     * Creates new form VerificationBox
     */
    public VerificationBox() {
        initComponents();
        setTitle("Verification/Deactivation");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        show_user();
        verifyButton.setEnabled(false);
        deactivateButton.setEnabled(false);
    }

    public ArrayList<User> userdb() {
        ArrayList<User> usersdb = new ArrayList<>();

        try {
            Connection con = ConnectionProviderS.getConn();
            String query = "SELECT * FROM userdb";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            User user;
            while (rs.next()) {
                user = new User(rs.getString("Username"), rs.getString("SystemID"), rs.getString("Hash_Key"), rs.getString("MotherboardSN"), rs.getString("CPU_ID"), rs.getString("MACAddress"), rs.getInt("Subscription"));
                usersdb.add(user);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return usersdb;
    }

    public void show_user() {

        ArrayList<User> list = userdb();
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        Object[] row = new Object[3];
        int i;
        for (i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getUsername();
            row[1] = list.get(i).getSystemID();
            row[2] = list.get(i).getHash_Key();

            String directoryPath = "src\\client_lic_files\\";
            String VerifyfileName = list.get(i).getHash_Key() + "_verify.json";
            File vf = new File(directoryPath + VerifyfileName);
            String DeactivatefileName = list.get(i).getHash_Key() + "_deactivate.json";
            File df = new File(directoryPath + DeactivatefileName);

            if (vf.exists() || df.exists()) {
                model.addRow(row);

                if (vf.exists()) {
                    jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                        @Override
                        public void valueChanged(ListSelectionEvent event) {
                            if (!event.getValueIsAdjusting()) {
                                int selectedRow = jTable1.getSelectedRow();
                                if (selectedRow != -1) {
                                    verifyButton.setEnabled(true);
                                }
                            }
                        }
                    });

                    ActionListener vlistener = new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
//                            JOptionPane.showMessageDialog(null,i);
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
                                            String mac = jsonObject.get("MACAddress").getAsString();

                                            int selectedRow = jTable1.getSelectedRow();
                                            String susername = jTable1.getValueAt(selectedRow, 0).toString();
                                            String ssysid = jTable1.getValueAt(selectedRow, 1).toString();
                                            String shashkey = jTable1.getValueAt(selectedRow, 2).toString();

                                            if (username.equals(susername) && sysid.equals(ssysid) && hashkey.equals(shashkey)) {

                                                String updateQuery = "UPDATE userdb SET MotherboardSN=?, CPU_ID=?, MACAddress=?, Subscription=? WHERE Hash_key=?";

                                                try (Connection con = ConnectionProviderS.getConn(); PreparedStatement pstmt = con.prepareStatement(updateQuery);) {
                                                    pstmt.setString(1, mothersn);
                                                    pstmt.setString(2, cpuid);
                                                    pstmt.setString(3, mac);
                                                    pstmt.setInt(4, 1);
                                                    pstmt.setString(5, hashkey);

                                                    int rowsAffected = pstmt.executeUpdate();

                                                    if (rowsAffected > 0) {
                                                        JOptionPane.showMessageDialog(null, "Database row updated successfully");
                                                    } else {
                                                        JOptionPane.showMessageDialog(null, "Failed to update the database row");
                                                    }

                                                } catch (Exception excep) {
                                                    JOptionPane.showMessageDialog(null, excep);
                                                }

                                                JOptionPane.showMessageDialog(null, "Verified and License Activated!");

                                                int v = 1;
                                                if (v == 1) {
                                                    String directoryPath2 = "src\\server_lic_files\\";
                                                    JsonObject rowJson = new JsonObject();
                                                    rowJson.addProperty("Username", username);
                                                    rowJson.addProperty("SystemID", sysid);
                                                    rowJson.addProperty("Hash_Key", hashkey);
                                                    rowJson.addProperty("MotherboardSN", mothersn);
                                                    rowJson.addProperty("CPU_ID", cpuid);
                                                    rowJson.addProperty("MACAddress", mac);
                                                    rowJson.addProperty("Subscription", 1);

                                                    JsonArray jsonArray1 = new JsonArray();
                                                    jsonArray1.add(rowJson);

                                                    try (FileWriter fileWriter = new FileWriter(directoryPath2 + hashkey + "_verify.json")) {
                                                        fileWriter.write(jsonArray1.toString());

                                                    } catch (IOException ex) {
                                                        ex.printStackTrace();
                                                    }

                                                }

                                            }
                                        }

                                    }
                                } else {
                                    System.out.println("Invalid JSON file format. Expected a JSON array.");
                                }
                                // Close the FileReader to release the file resources
                                fileReader.close();

                                // Call the function to delete the JSON file
                                Deletejson(directoryPath,VerifyfileName);
                                verifyButton.setEnabled(false);
                            } catch (IOException exception) {
                                exception.printStackTrace();
                            }

                        }

                    };
                    verifyButton.addActionListener(vlistener);
                }
                if (df.exists()) {
                    jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                        @Override
                        public void valueChanged(ListSelectionEvent event) {
                            if (!event.getValueIsAdjusting()) {
                                int selectedRow = jTable1.getSelectedRow();
                                if (selectedRow != -1) {
                                    deactivateButton.setEnabled(true);
                                }
                            }
                        }
                    });

                    ActionListener dlistener = new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            try {
                                int selectedRow = jTable1.getSelectedRow();
                                if (selectedRow != -1) {
                                    String hashKey = (String) jTable1.getValueAt(selectedRow, 2);
                                    updateSubscription(hashKey, 0);
                                    JOptionPane.showMessageDialog(null, "License deactivated successfully!");
                                }
                            } catch (Exception execp) {
                                JOptionPane.showMessageDialog(null, execp);
                            }
                        }

                        public void updateSubscription(String hashKey, int subscription) {
                            try {
                                Connection con = ConnectionProviderS.getConn();
                                String query = "UPDATE userdb SET MotherboardSN=?, CPU_ID=?, MACAddress=?, Subscription = ? WHERE Hash_Key = ?";
                                PreparedStatement preparedStatement = con.prepareStatement(query);
                                preparedStatement.setString(1,null);
                                preparedStatement.setString(2, null);
                                preparedStatement.setString(3, null);
                                preparedStatement.setInt(4, subscription);
                                preparedStatement.setString(5, hashKey);
                                preparedStatement.executeUpdate();
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(null, e);
                            }
                            Deletejson(directoryPath,DeactivatefileName);
                            deactivateButton.setEnabled(false);
                        }
                    };
          
                    deactivateButton.addActionListener(dlistener);

                }

            }

        }

    }

    public static void Deletejson(String directoryPath , String fileName) {
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
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        verifyButton = new javax.swing.JButton();
        deactivateButton = new javax.swing.JButton();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Username", "SystemID", "Hash Key"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        verifyButton.setText("Verify");

        deactivateButton.setText("Deactivate");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 579, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(verifyButton)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addComponent(deactivateButton)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(129, 129, 129)
                .addComponent(verifyButton)
                .addGap(28, 28, 28)
                .addComponent(deactivateButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(VerificationBox.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VerificationBox.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VerificationBox.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VerificationBox.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VerificationBox().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JButton deactivateButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    protected javax.swing.JButton verifyButton;
    // End of variables declaration//GEN-END:variables
}
