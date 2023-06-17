
package databases;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.Statement;
/**
 *
 * @author ASUS
 */
public class ClientTable {
    public static void main(String[] args){
        try{
            Connection con = ConnectionProviderC.getConn();
            Statement st = con.createStatement();
            st.executeUpdate("create table userdb(Username varchar(200), SystemID varchar(200),Hash_Key varchar(300) primary key, MotherboardSN varchar(200), CPU_ID varchar(200), MACAddress varchar(200), Subscription int)");
//            st.executeUpdate("insert into appuser(userRole,name,dob,mobileNumber,email,username,password,address) values('Admin','Admin','21-11-2002','0000111122','ikshita02@xyz.com','admin','admin','India')");
            JOptionPane.showMessageDialog(null, "Table Created Successfully");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
}
