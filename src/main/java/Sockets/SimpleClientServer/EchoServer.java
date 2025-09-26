package Sockets.SimpleClientServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class EchoServer {

    public static void main(String[] args) {

        try (ServerSocket serverSocket=new ServerSocket(5555);//initialize the client's socket
             Socket clientSocket=serverSocket.accept();
             PrintWriter out=new PrintWriter(clientSocket.getOutputStream());//Parameterizing the client's socket's output stream
             BufferedReader in=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));// Parameterizing the client's socket's input stream
            // BufferedReader consoleInputStream=new BufferedReader(new InputStreamReader(System.in))// Parameterizing the user's input into the console
        ){

            /* reading information from client, and sending to the Server*/
            String clientInput;
            while((clientInput=in.readLine())!=null){                //Read the inputed line from console
                System.out.println(clientInput);
                out.println(clientInput);                                            //Transferring line by line to the Server
                out.flush();
               // System.out.println("echo :"+ in.readLine());                          // Printing the data goten from the Server (should be the Server Sent)
            }



        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
