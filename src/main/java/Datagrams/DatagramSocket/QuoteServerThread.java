package Datagrams.DatagramSocket;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;

public class QuoteServerThread extends Thread {

    protected DatagramSocket socket = null;
    protected BufferedReader in = null;//Bufferedreader for reading the quotes from the quotes file
    protected boolean moreQuotes = true;//Determinant if there are still some quotes in the quotes file

    public QuoteServerThread() throws IOException {
        this("QuoteSever");
    }

    public QuoteServerThread(String name) throws IOException {
        super(name);

        socket = new DatagramSocket(4445); //The server need to already beconnected to a port, so the package has a destination

        try {
            in = new BufferedReader(new FileReader("one-liners.txt"));
        } catch (FileNotFoundException e) {
            System.err.println("Could not open quote file. Serving time instead.");
        }

    }

    @Override
    public void run() {
        try {

            System.out.println("Waiting for package from Client...");

            byte[] buf = new byte[256];
            DatagramPacket packet = new DatagramPacket(buf, buf.length);//An empty packet is created to recieve the Data from the Client
            socket.receive(packet);//The packet is being recieved from the client

            System.out.println("Package from client recieved");

            String dString ;
            if(in == null){//If the input string is empty meaning the file was not read/found
                dString = new Date().toString();
            }
            else{
                dString = getNextQuote();//Getting the next quote from the file
            }

            buf=dString.getBytes();//Storing that quote in the byte array

            InetAddress address = packet.getAddress();//Getting the address of the client's computer
            int port = packet.getPort();// Getting its transmission port

            //packet=new DatagramPacket(buf, buf.length, address, port);//Creating a new package to send, including the location of the Client

            //One can either make new package or reset the values of the old package
            packet.setData(buf);
            packet.setLength(buf.length);
            packet.setAddress(address);
            packet.setPort(port);


            socket.send(packet);// Sending the package
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected String getNextQuote() {
        String returnValue = null;
        try {
            if ((returnValue = in.readLine()) == null) {
                in.close();
                moreQuotes = false;
                returnValue = "No more quotes. Goodbye.";
            }
        } catch (IOException e) {
            returnValue = "IOException occurred in server.";
        }
        return returnValue;
    }
}
