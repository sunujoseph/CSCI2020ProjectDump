package lab05_inclass_monday;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sun.util.resources.cldr.lag.CalendarData_lag_TZ;

public class Main extends Application {
    private TableView<StudentRecord> students;
    private TextField sid;
    private TextField fname;
    private TextField lname;
    private Button add;
    private Label addResult;

    public static void main(String[] args) { Application.launch(args); }


    @Override public void start(Stage primaryStage) {
        primaryStage.setTitle("Lab 05 In Lab Example");

        BorderPane layout = new BorderPane();

        //Create the table of student records
        TableColumn<StudentRecord, String> idCol = new TableColumn<>("SID");
        idCol.setPrefWidth(100);
        idCol.setCellValueFactory(new PropertyValueFactory<>("sid"));

        TableColumn<StudentRecord, Float> valCol = new TableColumn<>("Value");
        valCol.setPrefWidth(100);
        valCol.setCellValueFactory(new PropertyValueFactory<>("value"));

        TableColumn<StudentRecord, String> fCol = new TableColumn<>("First Name");
        fCol.setPrefWidth(200);
        fCol.setCellValueFactory(new PropertyValueFactory<>("firstname"));

        TableColumn<StudentRecord, String> lCol = new TableColumn<>("Last Name");
        lCol.setPrefWidth(200);
        lCol.setCellValueFactory(new PropertyValueFactory<>("lastname"));

        this.students = new TableView<>();
        this.students.getColumns().add(idCol);
        this.students.getColumns().add(valCol);
        this.students.getColumns().add(fCol);
        this.students.getColumns().add(lCol);

        //Form at the bottom
        this.sid = new TextField();
        this.sid.setPromptText("SID");

        this.fname = new TextField();
        this.fname.setPromptText("First Name");

        this.lname = new TextField();
        this.lname.setPromptText("Last Name");

        this.add = new Button("Add");
        this.add.setDefaultButton(true);
        this.add.setOnAction(e -> addStudent());

        this.addResult = new Label("");

        //Create the form layout
        GridPane bottom = new GridPane();
        bottom.setPadding(new Insets(10));
        bottom.setHgap(10);
        bottom.setVgap(10);

        bottom.add(new Label("SID"), 0, 0);
        bottom.add(sid, 1,0 );

        bottom.add(new Label("First Name"), 0, 1);
        bottom.add(fname, 1, 1);

        bottom.add(new Label("Last Name"), 2, 1);
        bottom.add(lname, 3, 1);

        bottom.add(add, 1, 3);
        bottom.add(addResult, 2, 3);


        layout.setCenter(students);
        layout.setBottom(bottom);

        Scene scene = new Scene(layout, 600, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
        this.students.setItems(DataSource.getAllStudents());
    }

    public boolean addStudent() {
        //Check if all fields have values
        String id = sid.getText();
        String _fname = fname.getText();
        String _lname = lname.getText();

        if (id.length() == 0 || _fname.length() == 0 || _lname.length() == 0) {
            this.addResult.setText("Empty Field(s)");
            return false;
        }

        //Add the student
        this.students.getItems().add(new StudentRecord(id, _fname, _lname, 0.0f));

        //Clear the fields
        this.sid.setText("");
        this.fname.setText("");
        this.lname.setText("");
        this.addResult.setText("");

        return true;

    }
}
