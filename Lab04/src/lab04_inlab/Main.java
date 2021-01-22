package lab04_inlab;

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

        primaryStage.setTitle("Lab 04");

        GridPane gp = new GridPane();
        //Sets the type of padding so it looks good
        gp.setPadding(new Insets(10,10,10,10));

        Label usernameLabel = new Label("Username:");
        _un = new TextField();
        _un.setPromptText("Username ");
        gp.add(usernameLabel, 0, 0);
        gp.add(_un, 1, 0);


        Label pwLabel = new Label("Password:");
        _pw = new PasswordField();
        _pw.setPromptText("Password");
        gp.add(pwLabel, 0, 1);
        gp.add(_pw, 1, 1);

        Label fullnameLabel = new Label("Full Name:");
        _fn = new TextField();
        _fn.setPromptText("Full Name ");
        gp.add(fullnameLabel, 0, 2);
        gp.add(_fn, 1, 2);

        //name error
        Label fullnameerrorLabel = new Label("");
        gp.add(fullnameerrorLabel, 2, 2);


        Label emailLabel = new Label("Email:");
        _email = new TextField();
        _email.setPromptText("Email ");
        gp.add(emailLabel, 0, 3);
        gp.add(_email, 1, 3);

        //email error
        Label emailerrorLabel = new Label("");
        gp.add(emailerrorLabel, 2, 3);

        Label phonenumberLabel = new Label("Phone Number:");
        _pn = new TextField();
        _pn.setPromptText("Phone Number ");
        gp.add(phonenumberLabel, 0, 4);
        gp.add(_pn, 1, 4);

        //phone error
        Label phoneerrorLabel = new Label("");
        gp.add(phoneerrorLabel, 2, 4);


        Label dateLabel = new Label("Date: ");
        _dp = new DatePicker();
        gp.add(dateLabel, 0, 5);
        gp.add(_dp, 1, 5);


        _btn = new Button("Register");
        _btn.setDefaultButton(true);
        gp.add(_btn, 0, 6);



        _btn.setOnAction(new EventHandler<ActionEvent>() {
                              @Override
                              public void handle(ActionEvent event) {
                                  //Print to console from our button
                                  System.out.println("Username: " + _un.getText());
                                  System.out.println("Password: " + _pw.getText());
                                  System.out.println("Full Name: " + _fn.getText());
                                  System.out.println("Email: " + _email.getText());
                                  System.out.println("Phone Number: " + _pn.getText());
                                  System.out.println("Date: " + _dp.getValue());



                                  //Clear previous inputs
                                  _un.clear();
                                  _pw.clear();
                                  _fn.clear();
                                  _email.clear();
                                  _pn.clear();
                                  _dp.setValue(null);
                              }
                         }
        );

        //Create the scene
        Scene scene = new Scene(gp, 500, 300);

        //Set the scene
        primaryStage.setScene(scene);
        primaryStage.show();

    }



}
