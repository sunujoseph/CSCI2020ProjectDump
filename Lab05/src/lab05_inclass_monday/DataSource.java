package lab05_inclass_monday;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataSource {
    public static ObservableList<StudentRecord> getAllStudents() {
        ObservableList<StudentRecord> records = FXCollections.observableArrayList();

        records.add(new StudentRecord("100000000", "Bob", "bob", 10.0f));
        records.add(new StudentRecord("100000001", "Bob1", "bob", 5.0f));
        records.add(new StudentRecord("100000002", "Bob2", "bob",1.0f));
        records.add(new StudentRecord("100000003", "Bob3", "bob", 9.0f));
        records.add(new StudentRecord("100000004", "Bob4", "bob", 11.0f));

        return records;
    }
}
