package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{





        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) throws Exception{

        URL obj;
        String summaryFilename = "summary.csv";
        String url = "http://csundergrad.science.uoit.ca/courses/csci2020u/data_mtle/lodging_houses.csv";
        //public LodgingHouse lh;

        obj = new URL(url);
        //URLConnection yc = obj.openConnection();
        //BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
        //FileOutputStream fos = new FileOutputStream(summaryFilename);
        //fos = in;

       // String inputLine;
       // while ((inputLine = in.readLine()) != null)
            //System.out.println(inputLine);
       // in.close();



        // Your code to do the following goes here:
        // 1. Load the data from 'url' into a list of LodgingHouse objects
        obj = new URL(url);


        ReadableByteChannel rbc = Channels.newChannel(obj.openStream());

        FileOutputStream fos = new FileOutputStream(summaryFilename);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);


        launch(args);
    }
}
