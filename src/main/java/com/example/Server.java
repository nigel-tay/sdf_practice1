package com.example;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server 
{
    
    /**
     @throws IOException
     * Write a program to read a file from a different directory and create a new document containing a summary
     * The summary should contain the following
     * 1. The number of applications in each category
     * 2. State which application has the highest and lowest rating
     * 
     * Program should reside in the server
     * The client will send over the location of the file, and should receive back code 200 if successful
     * You have to use a stream to process the data coming into the server
     */
    public static void main( String[] args ) throws IOException
    {
        // Create a server and socket
        int port = (args.length != 0) ? Integer.parseInt(args[0]) : 3000;

        System.out.println("✨Server listening on port: " + port);

        ServerSocket server = new ServerSocket(port);
        Socket socket = server.accept();
        InputStream is = socket.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(is);
        DataInputStream dis = new DataInputStream(bis);
        String incoming = "";

        try {
            incoming = dis.readUTF();
            while (!incoming.equals("exit")) {
                System.out.println(incoming);
                incoming = dis.readUTF();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            socket.close();
            server.close();
        }
    }
}
