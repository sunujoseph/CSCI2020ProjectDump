package lab10_monday_demo;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException {
        String serverAdd = JOptionPane.showInputDialog(
                "Enter IP Address (port: 20500): ");

        System.out.println("Attempting to connect...");
        Socket socket = new Socket(serverAdd, 20500);
        System.out.println("Successfully connected!");
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        out.println("Echo Server");
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String answer = input.readLine();
        System.out.println("Incoming Resp: " + answer);
    }
}
