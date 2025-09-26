package Datagrams.DatagramSocket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class QuoteClient {


    public static void main(String[] args) throws IOException {
        String nameOfHost="192.168.1.2";

        DatagramSocket socket=new DatagramSocket();//Creates a DatagramSocket which is bound to any available port on the

        byte[] bytesToBeSent= new byte[256];
        InetAddress address=InetAddress.getByName(nameOfHost);//getting the internet address of the host with the name "nameOfHost"
        DatagramPacket packet = new DatagramPacket(bytesToBeSent,bytesToBeSent.length,address,4445);//Creating a Datagram packet destined
        // for the address of the Server at its port
        socket.send(packet);//Packet is sent to address and port (4445 in this case), this initial packet transfer is empty and is simply a
        // request to the server for information. From this transfer the server can find out the address and the port number of this client

        packet =new DatagramPacket(bytesToBeSent, bytesToBeSent.length);//
        socket.receive(packet);

        String recieved= new String(packet.getData(),0,packet.getLength());
        System.out.println("Quote of the Moment: " + recieved);

        socket.close();
    }
}
