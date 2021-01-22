package sample;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;



public class ClientConnection extends Thread {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter pw;
    private BorderPane layout;
    private Stage primaryStage;
    private ObservableList<String> observServList, observClieList; // ObservableList for file names for each Storage
    private int port, clientNum;
    private String hostName;
    private String clientStorageRoot;

    //Client Connection
    //Get Buttons for Client and there Commands
    //Commands deal with both Server and Client Storage
    public ClientConnection(int port, String hostName, Stage stage, int cNum) {
        this.port = port;
        this.hostName = hostName;
        this.primaryStage = stage;
        this.clientNum = cNum;
        this.clientStorageRoot = "clientStorage";
    }

    @Override
    public void run() {
        primaryStage.setTitle("Assignment02 - Client Number: "+clientNum);

        ListView<String> clientList = new ListView<String>(); // ListView for client storage
        clientList.setEditable(true);

        ListView<String> serverList = new ListView<String>(); // ListView for server storage
        serverList.setEditable(true);

        ////////////////////////////////////////BUTTONS////////////////////////////////////////
        GridPane editArea = new GridPane();

        // Upload Button
        Button uploadButton = new Button("Upload");
        uploadButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (clientList.getEditingIndex() != -1) {// returns index of selected list value and if its not nothing
                    System.out.println("Uploading...");
                    uploadCMD(observClieList.get(clientList.getEditingIndex()));
                    System.out.println(" ...Done Uploading");
                }
                updateList(clientList, serverList);
            }
        });
        editArea.add(uploadButton, 0, 0);

        // DOWNLOAD BUTTON
        Button downloadBtn = new Button("Download");
        downloadBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (serverList.getEditingIndex() != -1) { // returns index of selected list value and if its not nothing
                    System.out.println("Downloading...");
                    downloadCMD(observServList.get(serverList.getEditingIndex()));
                    System.out.println(" ...Done Downloading");
                }
                updateList(clientList, serverList);
            }
        });
        editArea.add(downloadBtn, 1, 0);

        // UPDATE BUTTON // Manual update button, runs updateList function
        Button updateBtn = new Button("Update");
        updateBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                updateList(clientList, serverList);
            }
        });
        editArea.add(updateBtn, 2, 0);
        ////////////////////////////////////////END OF BUTTONS////////////////////////////////////////

        updateList(clientList, serverList); // Calls update list upon loading
        SplitPane fileView = new SplitPane(clientList, serverList); // Makes SplitPane dividing the 2 list views
        fileView.setDividerPositions(0.50);

        layout = new BorderPane(); // sets layout
        layout.setTop(editArea);
        layout.setCenter(fileView);
    }

    public synchronized ObservableList<String> sendDIRCmd() {
        try {
            // Initializes Sockets, InputStreams, OutputStreams
            //sends DIR command, get list of files in Server Storage
            Socket socket = new Socket(hostName, port);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); //
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            out.println("DIR");
            out.flush();
            String response;
            response = in.readLine(); // Reads response line by line
            String[] responseParts = response.split(" "); // Splits by spaces them into array
            ObservableList<String> tempList = FXCollections.observableArrayList();// Makes new observable list to store values in
            for (int i = 0; i < responseParts.length; i++) {
                tempList.add(responseParts[i]);
            }
            // Closes the connection
            out.close();
            in.close();
            socket.close();
            return tempList; // returns List
        } catch (IOException e) {}
        return null; // returns null if not
    }

    public void uploadCMD(String fileName) { //sends UPLOAD command
        try {
            // Initializes sockets and in and out streams
            socket = new Socket(hostName, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            pw = new PrintWriter(socket.getOutputStream());
            // Sends request
            pw.println("UPLOAD " + "/" + fileName);
            pw.flush();

            String s = "", line;
            File file = new File(clientStorageRoot, fileName); //Open Local Files
            BufferedReader fin = new BufferedReader(new FileReader(file));// Read Local File
            while ((line = fin.readLine()) != null) {
                s += line;
                s += "\n";
            }
            // Sends local file as String
            pw.print(s);
            pw.flush();
            // Closes streams
            pw.close();
            in.close();
            socket.close();
        } catch (IOException e) {}
    }

    public  void downloadCMD(String fileName) { //sends DOWNLOAD command
        try {
            // Initializes Sockets, InputStreams,OutputStreams
            //Download Command
            Socket socket = new Socket(hostName, port);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            out.println("DOWNLOAD " + "/" + fileName);
            out.flush();
            String tempS;
            File newFile = new File("ClientStorage", fileName);
            if (!newFile.exists()) { // Overwrite Local File
                newFile.createNewFile();
            } else {
                newFile.delete();
                newFile.createNewFile();
            }
            PrintWriter fout = new PrintWriter(newFile);
            while ((tempS = in.readLine()) != null) {
                fout.println(tempS);
            }
            fout.close();
            // close the connection
            out.close();
            in.close();
            socket.close();
        } catch (IOException e) {}
    }

    public ObservableList<String> listFiles() { // Lists Local Files(Client File Storage) ObservableList
        File clientStorage = new File("ClientStorage");
        if (!clientStorage.isDirectory()) { // Create storage if it doesn't exist.
            clientStorage.mkdir();
        }
        ObservableList<String> tempList = FXCollections.observableArrayList();
        File fileList[] = clientStorage.listFiles();
        for (int i = 0; i < fileList.length; i++) {
            tempList.add(fileList[i].getName());
        }
        return tempList;
    }
    // Updates ObservableList and calls listFiles and to DIR COMMAND
    public void updateList(ListView<String> clientList, ListView<String> serverList) {
        System.out.println("Updating...");
        observClieList = listFiles();
        observServList = sendDIRCmd();
        clientList.setItems(observClieList);
        serverList.setItems(observServList);
        System.out.println(" ...Done");
    }

    public BorderPane getLayout() { // Returns layout value used for scene
        return this.layout;
    }
}
