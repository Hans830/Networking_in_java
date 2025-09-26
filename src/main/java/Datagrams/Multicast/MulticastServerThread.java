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
                byte[] byteData=new byte[256];//container of the Data to be casted

                String castedString=null;

                if(in==null){//if the file didn't open up
                    castedString=new Date().toString();
                }
                else{
                    castedString=getNextQuote();
                }

                byteData=castedString.getBytes();

                InetAddress address = InetAddress.getByName("localhost");//address to the Client the server wants to send to
                DatagramPacket packet;//the packet which contains the data to be sent
                packet=new DatagramPacket(byteData,byteData.length, InetAddress.getByName("localhost"), 4446);
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
