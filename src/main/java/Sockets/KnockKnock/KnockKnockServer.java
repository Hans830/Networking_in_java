package Sockets.KnockKnock;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class KnockKnockServer {

    public static void main(String[] args) {


        System.out.println("Waiting for the client...");

        try (ServerSocket serverSocket = new ServerSocket(4444);
             Socket communicationSocket = serverSocket.accept();
             BufferedReader serverInput = new BufferedReader(new InputStreamReader(communicationSocket.getInputStream()));
             PrintWriter serverOutput = new PrintWriter(new OutputStreamWriter(communicationSocket.getOutputStream()), true)
        ) {
            System.out.println("Client is connected.");

            String inputLine, outputLine;
            KnockKnockProtocol kkp = new KnockKnockProtocol();//keeps track of the conversation
            outputLine = kkp.processInput(null);//Initiating conversation by finding out the reply for a null input
            serverOutput.println(outputLine);//Sending the initial greeting

            while (
                    (inputLine = serverInput.readLine())
                            != null) {//if the inputLine is not null after having have read the server input

                if (inputLine.equalsIgnoreCase("Bye!")) {
                    break;
                }
                outputLine = kkp.processInput(inputLine);//finding out what output to send
                serverOutput.println(outputLine);//sending respond to the client

            }
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }
    }
}
