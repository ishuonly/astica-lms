
package databases;
import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author ASUS
 */
public class ConnectionProviderL {
    public static Connection getConn(){
        try{
           Class.forName("com.mysql.cj.jdbc.Driver");


           Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/logindb?zeroDateTimeBehavior=CONVERT_TO_NULL", "root", "code_ishag");

           return con;
        }
        catch(Exception e){
        System.out.println(e);
        return null;
        }
    }
}

