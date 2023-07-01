
package udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {
    public static void main(String[] args) throws Exception {
        DatagramSocket ds = new DatagramSocket();
        
        String i = "{\"name\": \"John\", \"age\": 30}";
        byte[] b = i.getBytes();
        InetAddress is = InetAddress.getLocalHost();
        DatagramPacket dp = new DatagramPacket(b,b.length,is,9999);
        ds.send(dp);
        
        System.out.println("JSON data sent over UDP.");
        
        
        byte[] b1 = new byte[1024];
        DatagramPacket dp1 = new DatagramPacket(b1,b1.length);
        ds.receive(dp1);
        
        String str = new String(dp1.getData());
        System.out.println("result is: "+ str);
    }
}

