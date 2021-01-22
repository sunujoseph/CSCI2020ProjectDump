package assignment;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
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

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
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
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class EchoClient extends Application{

    private Button newB;
    private Button openB;
    private Button saveB;
    private Button saveAsB;
    private Button exitB;
    private TextField clientIDT;
    private TextField fileT;
    public static String StoragePath;
    public static File DIR;
    public static Menu menuFile = new Menu("File");
    public static MenuItem menuUpload = new MenuItem("Upload");
    public static MenuItem menuDownload = new MenuItem("Download");
    public static TableColumn dl = new TableColumn("Local Files");
    public static TableColumn ul = new TableColumn("Server Files");
    public static TextArea textArea = new TextArea();
    public ObservableList<UploadedFiles> uploadFiles = FXCollections.observableArrayList();
    public ObservableList<DownloadedFiles> downloadFiles = FXCollections.observableArrayList();





    public static Stage s;

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
        launch(args);
        System.out.println(streamScanner.nextLine());

        while (!(line = scanner.nextLine()).equals("")) {
            pw.println(line);
            System.out.println(streamScanner.nextLine());
        }

        System.out.println("Disconnecting and quitting...");


    }



    @Override
    public void start(Stage stage) {
        this.s = stage;
        TableView table = new TableView();
        table.setEditable(true);
        StackPane root = new StackPane();
        VBox vbox = new VBox();
        DirectoryChooser directoryChooser = new DirectoryChooser();
        //directoryChooser.setInitialDirectory(DIR);

        /*
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Assignment 1");
        alert.setHeaderText("Please select the following folder: ");
        String s ="Spam";
        alert.setContentText(s);
        alert.showAndWait();
        //Grab Files
        File selectedDirectory = directoryChooser.showDialog(stage);
        String directory = selectedDirectory.getAbsolutePath();
        System.out.println(directory);
        File f = new File(directory);
        File[] files = f.listFiles();

        for(File fl : files)
        {
            downloadFiles.add(new DownloadedFiles(fl,fl.getName()));
        }
            */



        //TableColumn dl = new TableColumn("DownLoaded Files");
        dl.setMinWidth(300);
        //dl.setCellValueFactory(c -> downloadFiles);
        dl.setCellValueFactory(new PropertyValueFactory<>("fileName"));
        //id.setCellValueFactory(new PropertyValueFactory<>("ClientID"));
        //id.setCellValueFactory(c -> clientID);
        //TableColumn ul = new TableColumn("Uploaded Files");
        ul.setMinWidth(300);
        ul.setCellValueFactory(new PropertyValueFactory<>("fileName"));
        MenuBar menuBar = new MenuBar();

        // --- Menu File

        menuFile.getItems().addAll(menuDownload,menuUpload);


        /* DOWNLOAD
        The server will load the text from the text file filename,
        and will immediately send that text to the client and disconnect
         */
        menuDownload.setOnAction(new EventHandler<ActionEvent>() {
            final Label fileLabel = new Label();

            @Override
            public void handle(ActionEvent event) {
                System.out.println("IM IN DOWNLOAD");
                FileChooser fileChooser = new FileChooser();

                // Set extension filter
                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files ", "*.txt", "*.csv", "*.json", "*.java");
                fileChooser.getExtensionFilters().add(extFilter);

                //fileChooser.setInitialDirectory(DIR);

                // Show open file dialog
                File file = fileChooser.showOpenDialog(stage);

                //File testfile = new File(StoragePath);
                //System.out.println(file.getName());
                String FileName = file.getName();
                //BufferedReader br = null;
               // BufferedReader buf = new BufferedReader(new InputStreamReader(file));

                System.out.println("FileName " + FileName);
                downloadFiles.add(new DownloadedFiles(file, FileName));

               // dl.setCellValueFactory(data -> downloadFiles);

               // System.out.println(person.getName());

                StringBuilder sb = new StringBuilder();
                String line = "";


                try {
                    BufferedReader br = new BufferedReader(new FileReader(file));



                    while(line != null){
                        line = br.readLine();
                        sb.append(line).append("\n");
                        System.out.println(line);
                    }


                } catch (FileNotFoundException e){
                   e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                String fileAsString = sb.toString();
                System.out.println("Contents : " + fileAsString);
                textArea.setText(fileAsString);




                System.out.println("AFTER BR");

                System.out.println("After read file");





                if (file != null) {
                    fileLabel.setText(file.getPath());
                }
            }
        });


        /*UPLOAD
        Immediately after the newline after this command will be the contents of a text file
        The server will connect the text from this text file, and save it as a new file filename
        The server will disconnect immediately after saving the text file’s contents
        */
        menuUpload.setOnAction(new EventHandler<ActionEvent>() {
            final Label fileLabel = new Label();

            @Override
            public void handle(ActionEvent event) {
                //System.out.println("TOTOYOYOYOYOY");
                FileChooser fileChooser = new FileChooser();
                // Set extension filter
                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files ", "*.txt", "*.csv", "*.json", "*.java");
                fileChooser.getExtensionFilters().add(extFilter);
                // Show open file dialog
                File file = fileChooser.showOpenDialog(stage);
                //System.out.println(file.getName());
                String fileName = file.getName();
                BufferedReader br = null;




                if (file != null) {
                    fileLabel.setText(file.getPath());
                }
            }
        });



        menuBar.getMenus().addAll(menuFile);
        table.getColumns().addAll(dl,ul);
        vbox.getChildren().addAll(table,menuBar,textArea);
        stage.setScene(new Scene(vbox, 600, 600));
        //stage.setScene(new Scene(root, 600, 300));
        stage.show();
    }









}
