
package databases;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.Statement;
/**
 *
 * @author ASUS
 */
public class ServerTable {
    public static void main(String[] args){
        try{
            Connection con = ConnectionProviderS.getConn();
            Statement st = con.createStatement();
            st.executeUpdate("create table userdb(Username varchar(200), SystemID varchar(200),Hash_Key varchar(300) primary key, MotherboardSN varchar(200), CPU_ID varchar(200), MACAddress varchar(200), Subscription int)");
            JOptionPane.showMessageDialog(null, "Table Created Successfully");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
}
