
package databases;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.Statement;
/**
 *
 * @author ASUS
 */
public class LoginTable {
    public static void main(String[] args){
        try{
            Connection con = ConnectionProviderL.getConn();
            Statement st = con.createStatement();
            st.executeUpdate("create table userdb(id int primary key AUTO_INCREMENT, username varchar(50) NOT NULL, password varchar(50) NOT NULL)");
            JOptionPane.showMessageDialog(null, "Table Created Successfully");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
}

