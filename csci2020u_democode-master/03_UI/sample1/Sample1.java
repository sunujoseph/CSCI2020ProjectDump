import javafx.scene.*;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.input.*;
import javafx.scene.image.*;
import javafx.collections.*;
import javafx.event.*;

import java.io.*;
import java.net.*;

public class Sample1 extends Application {
  private BorderPane layout;
  private TableView<Student> table;
  private TextField sidField, fnameField;

  public void start(Stage primaryStage) throws Exception {
    primaryStage.setTitle("JavaFX Sample 1");

    // create our menu

    // file menu
    Menu fileMenu = new Menu("File");
    MenuItem newMenuItem = new MenuItem("New", imageFile("images/new.png"));
    newMenuItem.setAccelerator(KeyCombination.keyCombination("Ctrl+N"));
    fileMenu.getItems().add(newMenuItem);
    fileMenu.getItems().add(new SeparatorMenuItem());
    fileMenu.getItems().add(new MenuItem("Open...", imageFile("images/open.png")));
    fileMenu.getItems().add(new SeparatorMenuItem());
    fileMenu.getItems().add(new MenuItem("Save", imageFile("images/save.png")));
    fileMenu.getItems().add(new MenuItem("Save As...", imageFile("images/save_as.png")));
    fileMenu.getItems().add(new SeparatorMenuItem());
    MenuItem exitMenuItem = new MenuItem("Exit", imageFile("images/exit.png"));
    fileMenu.getItems().add(exitMenuItem);
    exitMenuItem.setAccelerator(KeyCombination.keyCombination("Ctrl+Q"));
    exitMenuItem.setOnAction( e -> System.exit(0) );

    // edit menu
    Menu editMenu = new Menu("Edit");
    editMenu.getItems().add(new MenuItem("Cut", imageFile("images/cut.png")));
    editMenu.getItems().add(new MenuItem("Copy", imageFile("images/copy.png")));
    editMenu.getItems().add(new MenuItem("Paste", imageFile("images/paste.png")));

    // help menu
    Menu helpMenu = new Menu("Help");
    helpMenu.getItems().add(new MenuItem("About...", imageFile("images/about.png")));
    helpMenu.getItems().add(new SeparatorMenuItem());
    helpMenu.getItems().add(new MenuItem("Help...", imageFile("images/help.png")));

    MenuBar menuBar = new MenuBar();
    menuBar.getMenus().add(fileMenu);
    menuBar.getMenus().add(editMenu);
    menuBar.getMenus().add(helpMenu);

    // main table
    table = new TableView<>();
    table.setItems(DataSource.getAllStudents());
    table.setEditable(true);

    // sid column
    TableColumn<Student,Integer> sidColumn = null;
    sidColumn = new TableColumn<>("SID");
    sidColumn.setMinWidth(100);
    sidColumn.setCellValueFactory(new PropertyValueFactory<>("sid"));

    TableColumn<Student,String> firstNameColumn = null;
    firstNameColumn = new TableColumn<>("First Name");
    firstNameColumn.setMinWidth(200);
    firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));

    TableColumn<Student,String> lastNameColumn = null;
    lastNameColumn = new TableColumn<>("Last Name");
    lastNameColumn.setMinWidth(200);
    lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
    lastNameColumn.setCellFactory(TextFieldTableCell.<Student>forTableColumn());
    lastNameColumn.setOnEditCommit((CellEditEvent<Student, String> event) -> {
      // TODO: Push changes to the database
      int index = event.getTablePosition().getRow();
      Student student = event.getTableView().getItems().get(index);
      student.setLastName(event.getNewValue());
    });

    TableColumn<Student,Double> gpaColumn = null;
    gpaColumn = new TableColumn<>("GPA");
    gpaColumn.setMinWidth(100);
    gpaColumn.setCellValueFactory(new PropertyValueFactory<>("gpa"));

    table.getColumns().add(sidColumn);
    table.getColumns().add(firstNameColumn);
    table.getColumns().add(lastNameColumn);
    table.getColumns().add(gpaColumn);

    GridPane editArea = new GridPane();
    editArea.setPadding(new Insets(10, 10, 10, 10));
    editArea.setVgap(10);
    editArea.setHgap(10);

    Label sidLabel = new Label("SID:");
    editArea.add(sidLabel, 0, 0);
    sidField = new TextField();
    sidField.setPromptText("100200300");
    editArea.add(sidField, 1, 0);

    Label fnameLabel = new Label("First name:");
    editArea.add(fnameLabel, 0, 1);
    fnameField = new TextField();
    fnameField.setPromptText("Barb");
    editArea.add(fnameField, 1, 1);

    Button addButton = new Button("Add");
    editArea.add(addButton, 1, 4);
    addButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        // collect the data from the UI for our new student
        int sid = Integer.parseInt(sidField.getText());
        String firstName = fnameField.getText();
        // TODO:  Add the other fields' data also
        Student newStudent = new Student(sid, firstName, "", 0.0);

        // add the data to our data source
        table.getItems().add(newStudent);

        // clear the text fields (UX)
        sidField.setText("");
        fnameField.setText("");
      }
    });

    // initialize the border pane
    layout = new BorderPane();
    // Place UI elements
    layout.setTop(menuBar);
    layout.setCenter(table);
    layout.setBottom(editArea);

    Scene scene = new Scene(layout, 600, 600);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  private ImageView imageFile(String filename) {
      return new ImageView(new Image("file:"+filename));
  }

  public static void main(String[] args) {
    launch(args);
  }
}
