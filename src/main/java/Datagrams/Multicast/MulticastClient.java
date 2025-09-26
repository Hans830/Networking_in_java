package Datagrams.Multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;

public class MulticastClient {

    public static void main(String[] args) throws IOException {


        MulticastSocket clientSocket=new MulticastSocket(4446);
        InetAddress group=InetAddress.getByName("WORKGROUP");
        clientSocket.joinGroup(group);

        DatagramPacket packet;
        byte[] buf = new byte[256];
        for(int i=0;i<5;i++){

            packet= new DatagramPacket(buf,buf.length);
            clientSocket.receive(packet);

            System.out.println("Quote: of the Moment: "+new String(packet.getData()));
        }

        clientSocket.leaveGroup(group);
        clientSocket.close();
    }
}
