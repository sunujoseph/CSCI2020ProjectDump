package lab10_tuesdaydemo_multithread;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class EchoClient {



    public static void main(String[] args) throws IOException {
        String screenName   = "User 1";
        String host         = "127.0.0.1";
        int port            = 20500;

        Socket socket = new Socket(host, port);
        InputStream io = socket.getInputStream();
        OutputStream os = socket.getOutputStream();
        PrintWriter pw = new PrintWriter(os, true);

        Scanner scanner = new Scanner(System.in);
        Scanner streamScanner = new Scanner(io);
        String line;
        System.out.println("Connected to " + host + ":" + port);
        System.out.println(streamScanner.nextLine());

        while (!(line = scanner.nextLine()).equals("")) {
            pw.println(line);
            System.out.println(streamScanner.nextLine());
        }

        System.out.println("Disconnecting and quitting...");

    }




}
