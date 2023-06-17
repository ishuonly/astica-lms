package server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import databases.ConnectionProviderS;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;

public class JsonFile {

    public static void main(String[] args) {
        
//        try{
//           Connection con = ConnectionProviderS.getConn(); 
//        }
//        
//        catch{(SQLException e) {
//            e.printStackTrace();
//        }
        
        // Assuming you have retrieved the row data into variables
        String value1 = "Example value 1";
        String value2 = "Example value 2";

        // Create a Gson object with optional settings
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
       

        // Create an instance of the RowData class and populate it with the row data
        RowData rowData = new RowData(value1, value2);
        // Initialize the remaining fields as needed

        // Serialize the Java object to JSON
        String json = gson.toJson(rowData);

        // Save the JSON to a file
        try {
            FileWriter writer = new FileWriter("output.json");
            writer.write(json);
            writer.close();
            System.out.println("JSON file exported successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
        static class RowData {
        private String column1;
        private String column2;

        public RowData(String column1, String column2) {
            this.column1 = column1;
            this.column2 = column2;
        }

        // Getters and setters (if needed) for column1 and column2
    }
}
