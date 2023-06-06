
package databases;
import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author ASUS
 */
public class ConnectionProvider {
    public static Connection getConn(){
        try{
           Class.forName("com.mysql.jdbc.Driver");
           Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy?useSSL=false","root","code_ishag");
           return con;
        }
        catch(Exception e){
        System.out.println(e);
        return null;
        }
    }
}
