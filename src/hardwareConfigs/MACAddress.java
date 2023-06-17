package hardwareConfigs;
// Java program to access all the MAC addresses of the
// localhost machine
  
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
public class MACAddress {
    public static void main(String[] args) throws Exception
    {
        // instantiate the MACAddress class
        MACAddress obj = new MACAddress();
        
        // call the getMAC() method on the current object
        // passing the localhost address as the parameter
        obj.getMAC();
    }
  
    // method to get the MAC addresses of the
    // localhost machine
    public static String getMAC()
    {
        try {
            
            // create an Enumeration of type
            // NetworkInterface and store the values
            // returned by
            // NetworkInterface.getNetworkInterfaces()
            // method
            Enumeration<NetworkInterface> networks
                = NetworkInterface.getNetworkInterfaces();
            
            // for every network in the networks Enumeration
            while (networks.hasMoreElements()) {
                NetworkInterface network
                    = networks.nextElement();
                
                // call getHardwareAddress() method on each
                // network and store the returned value in a
                // byte array
                byte[] mac = network.getHardwareAddress();
  
                if (mac != null) {
                    
                    // convert the obtained byte array into
                    // a printable String
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < mac.length; i++) {
                        sb.append(String.format(
                            "%02X%s", mac[i],
                            (i < mac.length - 1) ? "-"
                                                 : ""));
                    }
                    
                    // print the final String containing the
                    // MAC Address
                    return(sb.toString());
                }
            }
        }
        catch (SocketException e) {
            e.printStackTrace();
        }
        return null;
    }
}
