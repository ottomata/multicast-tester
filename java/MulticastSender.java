import java.io.*;

import java.net.SocketException;
import java.net.UnknownHostException;
import java.net.InetAddress;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.MulticastSocket;

import java.util.Scanner; 

/**
 * Hacky class to quickly run a java multicast listener printing received packets to stdout.
 */
public class MulticastSender {
  public static void main(String args[]) {
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
      socket = new MulticastSocket(); 
      socket.joinGroup(groupAddress);
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
    
    Scanner scanner = new Scanner(System.in);
    byte buf[] = new byte[256];
    String line = null;
    while (scanner.hasNext()) {
      line = scanner.nextLine() + "\n";
      buf = line.getBytes();
      DatagramPacket packet = new DatagramPacket(buf, buf.length, groupAddress, port);

      try {
        socket.send(packet);
      } catch (Exception e) {
        System.err.println(e.getMessage());
      }
    }
    socket.close();
  }
}