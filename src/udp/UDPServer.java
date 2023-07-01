
package udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {
    public static void main(String a[]) throws Exception{
        DatagramSocket ds = new DatagramSocket(9999);
        System.out.println("UDP server started. Waiting for incoming data...");
        byte[] b1 = new byte[1024];
        DatagramPacket dp = new DatagramPacket(b1,b1.length);
        ds.receive(dp);
        String str = new String(dp.getData());
//        int num = Integer.parseInt(str.trim());
//        int result = num*num;
        byte[] b2 = str.getBytes();
        InetAddress ia = InetAddress.getLocalHost();
        DatagramPacket dp1 = new DatagramPacket(b2,b2.length,ia,dp.getPort());
        ds.send(dp1);
    }
    
}