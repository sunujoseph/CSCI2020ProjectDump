package lab08;

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


public class DataSource extends Application {

    private TextField sidT;
    private TextField fnameT;
    private TextField lnameT;
    private TextField assignmentT;
    private TextField midtermT;
    private TextField finalExamT;
    private Button addB;
    private Label addResult;
    private Button newB;
    private Button openB;
    private Button saveB;
    private Button saveAsB;
    private Button exitB;
    private static String delimiter = ",";
    public static String fileName = "myfile.csv";

    public ObservableList<StudentRecord> marks = FXCollections.observableArrayList();

    public static void main(String[] args) {
        launch(args);

    }



    @Override
    public void start(Stage stage) {

        //ObservableList<StudentRecord> marks = FXCollections.observableArrayList();
        marks = getAllMarks();

        TableView table = new TableView();
        table.setEditable(true);
        StackPane root = new StackPane();
        VBox vbox = new VBox();
        //root.setMaxSize(600,300);


        TableColumn sid = new TableColumn("SID");
        sid.setMinWidth(100);
        sid.setCellValueFactory(new PropertyValueFactory<>("StudentID"));

        TableColumn assignments = new TableColumn("Assignments");
        assignments.setMinWidth(100);
        assignments.setCellValueFactory(new PropertyValueFactory<>("Assignments"));

        TableColumn midterm = new TableColumn("Midterm");
        midterm.setMinWidth(100);
        midterm.setCellValueFactory(new PropertyValueFactory<>("Midterm"));


        TableColumn final_exam = new TableColumn("Final Exam");
        final_exam.setMinWidth(100);
        final_exam.setCellValueFactory(new PropertyValueFactory<>("Finalexam"));

        TableColumn final_mark = new TableColumn("Final Mark");
        final_mark.setMinWidth(100);
        final_mark.setCellValueFactory(new PropertyValueFactory<>("FinalMark"));

        TableColumn letter_grade = new TableColumn("Letter Grade");
        letter_grade.setMinWidth(100);
        letter_grade.setCellValueFactory(new PropertyValueFactory<>("LetterGrade"));





        table.setItems(marks);
        table.getColumns().addAll(sid, assignments, midterm,final_exam,final_mark,letter_grade);

        //Form at the bottom
        //=========================================================================================
        this.sidT = new TextField();
        this.sidT.setPromptText("SID");



        this.assignmentT = new TextField();
        this.assignmentT.setPromptText("Assignment");

        this.midtermT = new TextField();
        this.midtermT.setPromptText("Midterm");

        this.finalExamT = new TextField();
        this.finalExamT.setPromptText("Final Exam");

        this.addB = new Button("Add");
        this.addB.setDefaultButton(true);
       // double as = Double.parseDouble(assignmentT.getText());
        //double mt = Double.parseDouble(midtermT.getText());
        //double fe = Double.parseDouble(finalExamT.getText());
        this.addB.setOnAction(e -> marks.add(new StudentRecord(sidT.getText(),Double.parseDouble(assignmentT.getText()),
                Double.parseDouble(midtermT.getText()),Double.parseDouble(finalExamT.getText()))));

        this.addResult = new Label("");

        this.newB = new Button("New");
        this.newB.setDefaultButton(true);
        this.newB.setOnAction(e ->
                {
                    for (int i = 0; i < table.getItems().size(); i++) {
                        table.getItems().clear();
                    }
                }
        );

        this.openB = new Button("Open");
        this.openB.setDefaultButton(true);
        this.openB.setOnAction(new EventHandler<ActionEvent>() {
            final Label fileLabel = new Label();

            @Override
            public void handle(ActionEvent event) {
                FileChooser fileChooser = new FileChooser();

                // Set extension filter
                FileChooser.ExtensionFilter extFilter =
                        new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
                fileChooser.getExtensionFilters().add(extFilter);



                // Show open file dialog
                File file = fileChooser.showOpenDialog(stage);
                //System.out.println(file.getName());
                String CsvFile = file.getName();
                BufferedReader br = null;

                try {
                    br = new BufferedReader(new FileReader(CsvFile));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                String line;
                try {
                    for (int i = 0; i < table.getItems().size(); i++) {
                        table.getItems().clear();
                    }

                    while ((line = br.readLine()) != null) {
                        String[] fields = line.split(delimiter, -1);
                        List<String> lList = Arrays.asList(line.split(","));

                        marks.add(new StudentRecord(lList.get(0),Double.parseDouble(lList.get(1)),
                                        Double.parseDouble(lList.get(2)),Double.parseDouble(lList.get(3)),
                                        Double.parseDouble(lList.get(4)),lList.get(5)));
                        //for(int i =0; i<lList.size();i++){System.out.println(lList.get(i));}
                        //System.out.println(line);
    
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }


                if (file != null) {
                    fileLabel.setText(file.getPath());
                }
            }
        });

        this.saveB = new Button("Save");
        this.saveB.setDefaultButton(true);
        this.saveB.setOnAction(e ->
            {


                PrintStream out = null;
                int myInt[] = new int[20];
                for (int i = 0; i < 20; i++) {
                    myInt[i] = i;
                }

                try {
                    out = new PrintStream(new FileOutputStream(fileName));
                    int count = 0;

                    for(int i = 0; i < marks.size(); i ++) {

                        out.print(marks.get(i).getStudentID() + delimiter + marks.get(i).getAssignments()
                                + delimiter + marks.get(i).getMidterm() + delimiter + marks.get(i).getFinalexam()
                                + delimiter + marks.get(i).getFinalMark() + delimiter + marks.get(i).getLetterGrade());

                        out.print("\n");
                    }
                } catch (Exception f) {
                    f.printStackTrace();
                } finally {
                    if (out != null) {
                        out.close();
                    }
                }
                System.out.println("We have saved to file..");
                //System.out.println(marks.get(0).getStudentID());



            }


        );





        this.saveAsB = new Button("Save As");
        this.saveAsB.setDefaultButton(true);
        this.saveAsB.setOnAction(e ->
                {

                    TextInputDialog dialog = new TextInputDialog("myfile.csv");
                    dialog.setTitle("Save As");
                    dialog.setHeaderText("ENTER YOUR FILE NAME");
                    dialog.setContentText("Remember to add (*.csv) your file name:");


                    // Traditional way to get the response value.
                    Optional<String> result = dialog.showAndWait();
                    if (result.isPresent()){
                        //System.out.println("Your name: " + result.get());
                        fileName = result.get();
                    }


                    PrintStream out = null;
                    int myInt[] = new int[20];
                    for (int i = 0; i < 20; i++) {
                        myInt[i] = i;
                    }

                    try {
                        out = new PrintStream(new FileOutputStream(fileName));
                        int count = 0;

                        for(int i = 0; i < marks.size(); i ++) {

                            out.print(marks.get(i).getStudentID() + delimiter + marks.get(i).getAssignments()
                                    + delimiter + marks.get(i).getMidterm() + delimiter + marks.get(i).getFinalexam()
                                    + delimiter + marks.get(i).getFinalMark() + delimiter + marks.get(i).getLetterGrade());

                            out.print("\n");
                        }
                    } catch (Exception f) {
                        f.printStackTrace();
                    } finally {
                        if (out != null) {
                            out.close();
                        }
                    }
                    System.out.println("We have saved to file..");
                    //System.out.println(marks.get(0).getStudentID());

                }
        );

        this.exitB = new Button("Exit");
        this.exitB.setDefaultButton(true);
        this.exitB.setOnAction(e ->
                {
                    System.exit(0);
                }
        );

        //Create the form layout
        GridPane bottom = new GridPane();
        bottom.setPadding(new Insets(10));
        bottom.setHgap(10);
        bottom.setVgap(10);

        bottom.add(new Label("SID"), 0, 0);
        bottom.add(sidT, 1,0 );

        bottom.add(new Label("Assignment"), 2, 0);
        bottom.add(assignmentT, 3, 0);

        bottom.add(new Label("Midterm"), 0, 1);
        bottom.add(midtermT, 1, 1);

        bottom.add(new Label("Final Exam"), 2, 1);
        bottom.add(finalExamT, 3, 1);

        bottom.add(addB, 1, 5);
        bottom.add(addResult, 2, 3);

        bottom.add(newB, 1, 6);
        bottom.add(openB, 2, 5);
        bottom.add(saveB, 2, 6);
        bottom.add(saveAsB, 3, 5);
        bottom.add(exitB, 3, 6);

        //=============================================================================================================
        System.out.println(15.0/24.0);
        //layout.setCenter(students);
        //layout.setBottom(bottom);

        //root.setAlignment(table, Pos.TOP_CENTER);
        //root.setAlignment(bottom, Pos.BOTTOM_CENTER);
        //root.getChildren().addAll(table,bottom);

        //vbox.setAlignment(table, Pos.TOP_CENTER);
        vbox.getChildren().addAll(table,bottom);

        stage.setScene(new Scene(vbox, 600, 600));
        //stage.setScene(new Scene(root, 600, 300));

        stage.show();




    }













    public static ObservableList<StudentRecord> getAllMarks() {
        ObservableList<StudentRecord> marks = FXCollections.observableArrayList();
        // Student ID, Assignments, Midterm, Final exam
        marks.add(new StudentRecord("100100100", 75.0f, 68.0f, 54.25f));
        marks.add(new StudentRecord("100100101", 70.0f, 69.25f, 51.5f));
        marks.add(new StudentRecord("100100102", 100.0f, 97.0f, 92.5f));
        marks.add(new StudentRecord("100100103", 90.0f, 88.5f, 68.75f));
        marks.add(new StudentRecord("100100104", 72.25f, 74.75f, 58.25f));
        marks.add(new StudentRecord("100100105", 85.0f, 56.0f, 62.5f));
        marks.add(new StudentRecord("100100106", 70.0f, 66.5f, 61.75f));
        marks.add(new StudentRecord("100100107", 55.0f, 47.0f, 50.5f));
        marks.add(new StudentRecord("100100108", 40.0f, 32.5f, 27.75f));
        marks.add(new StudentRecord("100100109", 82.5f, 77.0f, 74.25f));
        return marks;


    }
}
