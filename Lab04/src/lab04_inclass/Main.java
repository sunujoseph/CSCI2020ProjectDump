package lab04_inclass;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class Main extends Application {

    //Our variables for the simple application
    private TextField _text1;
    private PasswordField _pw1;
    private TextField _fn1;
    private DatePicker _dp1;
    private Button _btn1;


    //Text area we use as an effective console ish
    private TextArea _ta;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Lab 04 in Lab Code");

        GridPane gp = new GridPane();
        //Sets the type of padding so it looks good
        gp.setPadding(new Insets(10,10,10,10));


        //Set our variables
        Label textLabel = new Label("Username:");
        _text1 = new TextField();
        _text1.setPromptText("I am a prompt text for Text1 ");

        //Label nameLabel = new Label("Full Name:");
        //_fn1 = new TextField();
        //_fn1.setPromptText("I am a prompt text for Text1 ");

        gp.add(textLabel, 0, 0);
        gp.add(_text1, 1, 0);

        Label pwLabel = new Label("Password:");
        _pw1 = new PasswordField();
        gp.add(pwLabel, 0, 1);
        gp.add(_pw1, 1, 1);

        Label dateLabel = new Label("Label for date picker");
        _dp1 = new DatePicker();
        gp.add(dateLabel, 0, 2);
        gp.add(_dp1, 1, 2);

        Label btnLabel = new Label("Submit");
        _btn1 = new Button("Hello!");
        //This set default button makes it so if there's one button on the form, we can press enter to press it
        _btn1.setDefaultButton(true);
        gp.add(btnLabel, 0, 3);
        gp.add(_btn1, 0, 4);


        _ta = new TextArea();
        //This makes our TextArea readonly.
        _ta.setDisable(true);
        //Remember when playing with the TextArea we need to manually add new lines!
        _ta.appendText("Appended text will show here:\n");
        gp.add(_ta, 0, 5, 3, 5);

        //This is out button event handler. We will work with what happens here
        _btn1.setOnAction(new EventHandler<ActionEvent>() {
                              @Override
                              public void handle(ActionEvent event) {
                                  //Print to console from our button
                                  System.out.println("Hello from button!");

                                  //Append to our TextArea with our button
                                  _ta.appendText(_text1.getText() + "\n" + _pw1.getText() + "\n"
                                          + _dp1.getValue() + "\n");

                                  //Clear previous inputs
                                  _text1.clear();
                                  _pw1.clear();
                                  _dp1.setValue(null);
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