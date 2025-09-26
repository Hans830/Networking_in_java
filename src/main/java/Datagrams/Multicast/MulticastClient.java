package Datagrams.Multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;

public class MulticastClient {

    public static void main(String[] args) throws IOException {


        MulticastSocket clientSocket=new MulticastSocket(4446);
        InetAddress group=InetAddress.getByName("239.0.0.1");
        clientSocket.joinGroup(group);
        System.out.println("Joint ClientGroup");
        DatagramPacket packet;
        byte[] buf = new byte[256];
        for(int i=0;i<5;i++){


            packet= new DatagramPacket(buf,buf.length);
            System.out.println("Recieving packet.");
            clientSocket.receive(packet);
            System.out.println("Packet recieved.");

            System.out.println("Quote: of the Moment: "+new String(packet.getData()));
        }

        clientSocket.leaveGroup(group);
        clientSocket.close();
    }
}
