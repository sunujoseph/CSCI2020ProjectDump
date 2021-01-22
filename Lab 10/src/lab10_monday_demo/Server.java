package lab10_monday_demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static ServerSocket serverSocket;
    private static int port = 20500;
    public static void main(String[] args) throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("Starting up server on port: " + port);
        try {
            while (true) {
                Socket socket = serverSocket.accept();
                try {
                    System.out.println("We have a connection!");
                    BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String answer = input.readLine();
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    out.println(answer);
                } finally {
                    socket.close();
                }
            }
        } finally {
            serverSocket.close();
        }
    }
}
