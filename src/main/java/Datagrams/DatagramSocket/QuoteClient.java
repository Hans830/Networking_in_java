package Datagrams.DatagramSocket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class QuoteClient {


    public static void main(String[] args) throws IOException {
        String nameOfHost="192.168.1.2";//The name of the computer on which the server is run

        DatagramSocket socket=new DatagramSocket();//Creates a DatagramSocket which is bound to any available port on this computer

        /*-- Recap
        - The Clients DatagramSocket has been created, its transmitting out of any free port on this computer
        - We know the name of the Host of the server on this network and we'll use it to get its internet address
        - We have to know throught which port it is transmitting on its computer in order to send our package to that port
         */


        byte[] bytesToBeSent= new byte[256];
        InetAddress address=InetAddress.getByName(nameOfHost);//getting the internet address of the host with the name "nameOfHost"

        /* When creating a package which the Client will send we add the Data we want to send
        the size of the data and the address of the server recieving it
        The first package we send is just meant to share the location of the client such that the Server can send a Package to that location
         */
        DatagramPacket packet = new DatagramPacket(bytesToBeSent,bytesToBeSent.length,address,4445);//Creating a Datagram packet destined
        // for the address of the Server at its port


        socket.send(packet);//Packet is sent (the address and port were already given, so it knows where to go), this initial packet transfer is empty and is simply a
        // request to the server for information. From this transfer the server can find out the address and the port number of this client

        System.out.println("Sending packet to Server.");

        packet =new DatagramPacket(bytesToBeSent, bytesToBeSent.length);//A new Datagram packet is created, purpose is recieving the info sent by the server
        socket.receive(packet);
        System.out.println("Received packet from Server.");

        String recieved= new String(packet.getData(),0,packet.getLength());
        System.out.println("Quote of the Moment: " + recieved);

        socket.close();
    }
}
