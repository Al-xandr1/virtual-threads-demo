package org.example.exM;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    public static void main(String[] args) {

        if (args.length != 1) {
            System.err.println("Usage: java EchoServer <port>");
            System.exit(1);
        }

        int portNumber = Integer.parseInt(args[0]);

        try (ServerSocket serverSocket = new ServerSocket(Integer.parseInt(args[0]))) {
            while (true) {
                // Accept incoming connections
                Socket clientSocket = serverSocket.accept();

                // Start a service thread
                Thread.Builder.OfVirtual ofVirtual = Thread.ofVirtual();
                Thread vt = ofVirtual.start(() -> {
                    try (
                            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
                    ) {
                        String inputLine;
                        while ((inputLine = in.readLine()) != null) {
                            Thread ct = Thread.currentThread();
                            long id = ct.threadId();
                            String name = ct.getName();
                            boolean isVirtual = ct.isVirtual();
                            String strRepresentation = ct.toString();

                            System.out.printf("%d:%s:%s:%s  --  %s%n", id,  name, isVirtual, strRepresentation, inputLine);
                            out.println(inputLine);
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }

        } catch (IOException e) {
            System.out.printf("Exception caught when trying to listen on port %d or listening for a connection%n", portNumber);
            System.out.println(e.getMessage());
        }
    }
}
