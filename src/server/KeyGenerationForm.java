package server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import databases.ConnectionProviderS;

public class KeyGenerationForm extends JFrame {

    private JTextField textField1;
    private JTextField textField2;
    private JButton hashButton;
    private JTextField resultField;
    private JButton saveToDbButton;

    public KeyGenerationForm() {
        setTitle("Key Generation Form");
        setSize(500, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create panels
        JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel panel4 = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // Create text fields
        textField1 = new JTextField(20);
        textField2 = new JTextField(20);
        textField1.setPreferredSize(new Dimension(200, 30));
        textField2.setPreferredSize(new Dimension(200, 30));

        // Create button
        hashButton = new JButton("Generate Key");
        saveToDbButton = new JButton("Save");

        // Create result field
        resultField = new JTextField(40);
        resultField.setEditable(false);
        resultField.setPreferredSize(new Dimension(300, 30));

        // Add components to panels
        panel1.add(new JLabel("Username:"));
        panel1.add(textField1);
        panel1.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        panel2.add(new JLabel("System ID:"));
        panel2.add(textField2);
        panel2.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        panel3.add(hashButton);
        panel3.add(resultField);

        panel3.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        panel4.add(saveToDbButton);
        panel4.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        // Set layout for the frame
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // Add panels to the frame
        add(panel1);
        add(panel2);
        add(panel3);
        add(panel4);

        hashButton.setBackground(new Color(0, 0, 255)); // Lightish blue color
        hashButton.setForeground(new Color(255, 255, 255)); // Lightish blue color
        saveToDbButton.setBackground(new Color(255, 153, 0)); // Orange color
        saveToDbButton.setForeground(new Color(255, 255, 255));

        // Add action listener to the button
        hashButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str1 = textField1.getText();
                String str2 = textField2.getText();
                String concatenatedString = str1 + str2;
                String hashedResult = hashString(concatenatedString);
                resultField.setText(hashedResult);

                saveToDatabase(str1, str2, hashedResult);
            }
        });

        // Add action listener to the save to DB button
        saveToDbButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str1 = textField1.getText();
                String str2 = textField2.getText();
                String concatenatedString = str1 + str2;
                String hashedResult = hashString(concatenatedString);
                saveToDatabase(str1, str2, hashedResult);
                JOptionPane.showMessageDialog(KeyGenerationForm.this, "Data saved to database!");
            }
        });
    }

    private String hashString(String input) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = messageDigest.digest(input.getBytes());

            StringBuilder stringBuilder = new StringBuilder();
            for (byte hashByte : hashBytes) {
                stringBuilder.append(String.format("%02x", hashByte));
            }
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void saveToDatabase(String un, String sysId, String key) {
        try {
            Connection con = ConnectionProviderS.getConn();
            String query = "INSERT INTO userdb (Username, SystemID, Hash_key, Subscription) VALUES (?, ?, ?, ?)";

            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, un);
            preparedStatement.setString(2, sysId);
            preparedStatement.setString(3, key);
            preparedStatement.setInt(4, 0);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new KeyGenerationForm().setVisible(true);
            }
        });
    }
}
