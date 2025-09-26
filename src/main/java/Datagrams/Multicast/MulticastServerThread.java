package Datagrams.Multicast;

import Datagrams.DatagramSocket.QuoteServerThread;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.security.PublicKey;
import java.util.Date;

public class MulticastServerThread extends QuoteServerThread {


    public MulticastServerThread() throws IOException {
        this("MulticastServer");
    }

    public MulticastServerThread(String name) throws IOException {
        super(name);
    }

    @Override
    public void run() {

        while(moreQuotes){
            try{
                byte[] byteData=new byte[256];//container of the Data to be cast

                String castedString=null;

                if(in==null){//if the file didn't open up
                    byteData=new Date().toString().getBytes();
                }
                else{
                    //castedString=getNextQuote();//The nex
                    byteData=getNextQuote().getBytes();
                }

                //byteData=castedString.getBytes();
                /* For a multi data cast, the Server has to know where to send the packets, in other words where the Clients
                will be listening from. All Clients must be listening from the same network or group through a particular port
                 */

                InetAddress clientsAddress = InetAddress.getByName("192.168.1.1");//address to the Client the server wants to send to
                DatagramPacket packet;//the packet which contains the data to be sent

                /*The packet to be sent is created and bound to the address and port it is destined for

                 */
                packet=new DatagramPacket(byteData,byteData.length, clientsAddress , 4446);
                socket.send(packet);
                try {
                    sleep((long)(Math.random()*5000));
                }catch(InterruptedException ie){

                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }



    }
}
