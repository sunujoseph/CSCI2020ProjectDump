package lab07_inclass_monday;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.chart.*;
import javafx.scene.paint.Color;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main {
    public static void main(String args[]) {
        getData("src/lab07_inclass_monday/weatherwarnings-2015.csv", ",");
    }
    
    public static void getData(String path, String delimiter) {
        //List<String> srcList = new ArrayList<>();
        // Special Case
        if (path == null || path.length() == 0) {
            return;
        }
        if(delimiter == null || delimiter.length() == 0) {
            delimiter = ",";
        }
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            //Read the next line until end of file
            for (String line; (line = br.readLine()) != null;) {
                //Parse the line
                String[] values = line.split(delimiter);
                for(int i = 0; i < values.length; i++) {
                    System.out.print(values[i] + " | ");
                }
                System.out.print("\n");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
