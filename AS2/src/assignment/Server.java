package assignment;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.*;
import java.nio.channels.Channel;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Optional;
import java.io.FileOutputStream;
import java.io.PrintStream;



import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private Button newB;
    private Button openB;
    private Button saveB;
    private Button saveAsB;
    private Button exitB;
    private TextField clientIDT;
    private TextField fileT;


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
