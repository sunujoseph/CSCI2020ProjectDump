package Lab05;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class DataSource extends Application {



    public static void main(String[] args) {
        launch(args);

    }



    @Override
    public void start(Stage stage) {

        ObservableList<StudentRecord> marks = FXCollections.observableArrayList();
        marks = getAllMarks();

        TableView table = new TableView();
        table.setEditable(true);

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

        StackPane root = new StackPane();
        root.getChildren().add(table);
        stage.setScene(new Scene(root, 600, 300));
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
