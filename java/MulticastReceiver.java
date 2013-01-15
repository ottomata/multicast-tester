import java.io.*;

import java.net.SocketException;
import java.net.UnknownHostException;
import java.net.InetAddress;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.MulticastSocket;


/**
 * Hacky class to quickly run a java multicast listener printing received packets to stdout.
 */
public class MulticastReceiver {

  
  private static String usage = "Usage: java MulticastReceiver [multicast-group-ip (Default: 224.1.0.1)] [port (Default: 14454)]";
  
  public static void main(String args[])
  {
    String groupIp = "224.1.0.1";
    int    port    = 14454;
    
    try {
        groupIp = args[1];
        port    = Integer.parseInt(args[2]);
    } catch ( IndexOutOfBoundsException e ) {
      // no op
    }
    
    MulticastSocket socket      = null;
    InetAddress     groupAddress     = null;


    try {
      groupAddress = InetAddress.getByName(groupIp);
    } catch (UnknownHostException e) {
      System.err.println(e.getMessage());
    }

    try {
      socket = new MulticastSocket(port); 
      socket.joinGroup(groupAddress);
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }


    byte[] buf = new byte[256];
    DatagramPacket packet = new DatagramPacket(buf, buf.length);

    System.out.println("Starting multicast receiver in group " + groupIp + " on port " + port);
    while (true) {
      try {
        socket.receive(packet);
      } catch (Exception e) {
        System.err.println(e.getMessage());
      }
      String received = new String(packet.getData(), 0, packet.getLength());
      System.out.print(received);
    }
  }
}