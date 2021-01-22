package sample;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    static int port = 20500; // Port Number
    static String hostName = "127.0.0.1"; // Return host address



    @Override
    public void start(Stage primaryStage) throws Exception {


        // Number of Clients
        //Change number to change the number of clients
        //Allows Multiple Clients to file Share Each Other
        int numberOfClients = 1;


        // Initializes Server Thread and Start Server
        ClientConnectionServer server = new ClientConnectionServer(port);
        server.start();

        //Stage and Scene for as Multiple clients running
        //Based on numberOfClients, will determine the number of clients
        Stage[] clientStages = new Stage[numberOfClients];
        ClientConnection clientClass[] = new ClientConnection[numberOfClients];
        Scene scene[] = new Scene[numberOfClients];

        // Initializes and starts Client Thread

        for (int i = 0; i < numberOfClients; i++) {
            clientStages[i] = new Stage();
            clientClass[i] = new ClientConnection(port, hostName, clientStages[i], i + 1);
            clientClass[i].start();
            clientClass[i].join();
            scene[i] = new Scene(clientClass[i].getLayout(), 600, 600);
            clientStages[i].setScene(scene[i]);
            clientStages[i].show();
            //
            // Checks that all clients are closed before close the server thread to avoid errors
            clientStages[i].setOnCloseRequest(e -> {
                boolean closeFlag = true;
                for (int j = 0; j < numberOfClients; j++) {
                    if (clientClass[j].isAlive()) {
                        closeFlag = false;
                    }
                }
                if (closeFlag == true) {
                    server.closeThread(); // Closes server thread
                }
            });
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}
