package sample;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;


public class ClientConnectionServer extends Thread{
    private ServerSocket serverSocket;
    private int port;

    public ClientConnectionServer(int port) throws IOException {
        this.port = port;
        serverSocket = new ServerSocket(port);
    }

    public void handleClientRequests() throws IOException {
        try {
            int i = 0;
            // Vector of threads for Multithreading
            // Display Client(s) Status with the Server
            Vector<Thread> handlerThread = new Vector<Thread>();
            while (!serverSocket.isClosed()) {
                    System.out.println("ClientConnectionServer listening on port " + port);
                    Socket socket = serverSocket.accept();
                    System.out.println("Client Found...");
                    handlerThread.add(i, new Thread(new ClientConnectionHandler(socket))); //
                    handlerThread.get(i).start(); // NEW THREAD is made
                    i++;
            }
        } catch (IOException e) {}
    }

    public void closeThread() { //Close Thread
        try {
            serverSocket.close();
        } catch (IOException e) {
        }
    }

    //Run Client Connection Server
    @Override
    public void run() {
        try {
            this.handleClientRequests();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
