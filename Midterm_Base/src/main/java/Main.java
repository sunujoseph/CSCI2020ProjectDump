import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {
    public Button prev;
    public Button next;
    public TextField objectid;
    public TextField streetname;
    public TextField propertyclass;
    public TextField shapearea;
    public TextField shapelenght;
    public LodgingHouse LH;
    public Controller c;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("display_records.fxml"));
        BorderPane layout = new BorderPane();


        this.objectid = new TextField();
        this.objectid.setPromptText("Object ID:");

        this.streetname = new TextField();
        this.streetname.setPromptText("Street Name");

        this.propertyclass = new TextField();
        this.propertyclass.setPromptText("Property CLass");

        this.shapearea = new TextField();
        this.shapearea.setPromptText("Shape Area");

        this.shapelenght = new TextField();
        this.shapelenght.setPromptText("Shape Length");



        this.prev = new Button("Previous");
        this.prev.setDefaultButton(true);
        this.prev.setOnAction(e -> {
            try {
                c.prev();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });


        this.next = new Button("Next");
        this.next.setDefaultButton(true);
        this.next.setOnAction(e -> {
            try {
                c.next();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        //this.addResult = new Label("");


        GridPane bottom = new GridPane();

        bottom.setPadding(new Insets(10));
        bottom.setHgap(10);
        bottom.setVgap(10);

        bottom.add(new Label("SID"), 0, 0);
        bottom.add(objectid, 1,0 );

        bottom.add(new Label("First Name"), 0, 1);
        bottom.add(streetname, 1, 1);

        bottom.add(new Label("Last Name"), 2, 1);
        bottom.add(propertyclass, 3, 1);

        bottom.add(prev, 1, 3);
        //bottom.add(addResult, 2, 3);

        bottom.add(next, 1, 3);
        //bottom.add(addResult, 2, 3);

        primaryStage.setTitle("Lodging Houses");
        primaryStage.setScene(new Scene(root, 600, 300));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
