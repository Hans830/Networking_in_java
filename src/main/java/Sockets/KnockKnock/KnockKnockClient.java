package Sockets.KnockKnock;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;

public class KnockKnockClient {


    public static void main(String[] args) {


        try (Socket kkClientSocket = new Socket("localhost", 4444);
             BufferedReader clientInput = new BufferedReader(new InputStreamReader(kkClientSocket.getInputStream()));
             PrintWriter clientOutput = new PrintWriter(new OutputStreamWriter(kkClientSocket.getOutputStream()), true);
             BufferedReader consoleStream=new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Connected to server:");
            String fromServer, usersMessage;
          //  ArrayList<String> toServer = new ArrayList<>(Arrays.asList("Who's there?", "Dexter who?", "Groan."));

            while ((fromServer = clientInput.readLine()) != null) {//if and while the server sends messages to the client which are not null
                System.out.println("Server: " + fromServer);//Print out the message received from the server

                if (fromServer.equalsIgnoreCase("bye!")) {//if the server Sent a "bye!" as message we end the communication
                    break;
                }
                System.out.print("Client: ");
                usersMessage = consoleStream.readLine();//We read in the message the user has sent to the client through the console

                if (usersMessage != null) {//if the message is not empty
                    clientOutput.println(usersMessage);//We send the message to the Server else ???
                }

                //System.out.println("Client: "+toServer.getFirst());

            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }


    }
