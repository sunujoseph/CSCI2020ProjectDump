package Lab5;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class Main extends Application{

    private TextField _un;
    private PasswordField _pw;
    private TextField _fn;
    private DatePicker _dp;
    private Button _btn;
    private TextField _email;
    private TextField _pn;


    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Lab 05");

        GridPane gp = new GridPane();
        //Sets the type of padding so it looks good
        gp.setPadding(new Insets(10,10,10,10));





        //Create the scene
        Scene scene = new Scene(gp, 500, 300);

        //Set the scene
        primaryStage.setScene(scene);
        primaryStage.show();

    }



}