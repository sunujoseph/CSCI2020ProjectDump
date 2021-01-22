package lab10_tuesdaydemo_multithread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(20500)) {
            System.out.println("Waiting for users to connect...");

            while (true) {
                Socket socket = serverSocket.accept();
                Runnable r = new ServerThread(socket);
                Thread t = new Thread(r);
                t.start();
            }
        }
    }
}
