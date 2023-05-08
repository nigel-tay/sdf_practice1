package com.example;

import java.io.BufferedOutputStream;
import java.io.Console;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main (String[] args) throws UnknownHostException, IOException {
        int port = (args.length != 0) ? Integer.parseInt(args[0]) : 3000;
        Socket sock = new Socket("localhost", port);
        System.out.printf("Client started on %d\n", port);

        OutputStream os = sock.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(os);
        DataOutputStream dos = new DataOutputStream(bos);
        Console cons = System.console();
        String consoleInput = "";

        try {
            while(!(consoleInput.equals("exit"))) {
                consoleInput = cons.readLine();
                dos.writeUTF(consoleInput);
                dos.flush();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            dos.close();
            bos.close();
            os.close();
            sock.close();
        }
        finally {
            dos.close();
            bos.close();
            os.close();
            sock.close();
        }

        
    }
}
