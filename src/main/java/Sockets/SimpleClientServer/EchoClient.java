package Sockets.SimpleClientServer;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class EchoClient {


    public static void main(String[] args) {

        String textToBeEchoed= "this text was sent by the Client";

        try (Socket clientSocket=new Socket("192.168.1.2",5555);//initialize the client's socket
             PrintWriter out=new PrintWriter(clientSocket.getOutputStream());//Parameterizing the client's socket's output stream
             BufferedReader in=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));// Parameterizing the client's socket's input stream
             BufferedReader consoleInputStream=new BufferedReader(new InputStreamReader(System.in))// Parameterizing the user's input into the console
        ){

            /* reading information from console, and sending to the Server*/
            String consoleInput;
            while((consoleInput=consoleInputStream.readLine())!=null){                //Read the inputed line from console
                out.println(consoleInput);                                            //Transferring line by line to the Server
                out.flush();
                System.out.println("echo :"+ in.readLine());                          // Printing the data goten from the Server (should be the Server Sent)
            }



        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
