package lab07;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.chart.*;
import javafx.scene.layout.HBox;
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
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;



public class Main extends Application{
    public static int numFF = 0, numSM = 0, numST = 0, numT = 0;
    public static double total = 0;

    @FXML
    private Canvas canvas;

    public static void main(String args[]) {
        getData("src/lab07_inclass_monday/weatherwarnings-2015.csv", ",");
        launch(args);
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
                    if(values[i].contains("FLASH FLOOD")){
                        numFF++;
                    }
                    else if(values[i].contains("SEVERE THUNDERSTORM")){
                        numST++;
                    }
                    else if(values[i].contains("SPECIAL MARINE")){
                        numSM++;
                    }
                    else if(values[i].contains("TORNADO")){
                        numT++;
                    }
                    //System.out.print(values[i] + " | ");
                }
                //System.out.print("\n");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        //System.out.println(numFF + " " + numSM + " " + numST + "" + numT);
        total = numFF + numSM + numST + numT;
    }





    @Override public void start(Stage stage) {
        Scene scene = new Scene(new Group());
        Scene sc;
        Group root = new Group();
        stage.setTitle("Lab06");
        stage.setWidth(1000);
        stage.setHeight(500);
        ArrayList shapes = new ArrayList();


        Scene s = new Scene(root, 800, 600, Color.WHITE);

        canvas = new Canvas();

        // broken?
        //canvas.widthProperty().bind(primaryStage.widthProperty());
        //canvas.heightProperty().bind(primaryStage.heightProperty());
        canvas.setHeight(600);
        canvas.setWidth(800);


        //---------------------------------------------------------------------------------------------------
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Flash Flood", numFF),
                        new PieChart.Data("Severe Thunderstorm", numST),
                        new PieChart.Data("Special Marine", numSM),
                        new PieChart.Data("Tornado", numT)

                );
        PieChart chart = new PieChart(pieChartData);

        chart.setLabelLineLength(10);
        chart.setLegendSide(Side.LEFT);
        //---------------------------------------------------------------------------------------------------

        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.DARKCYAN);
        gc.setStroke(Color.DARKCYAN);
        /*
        ArcType.ROUND - pie shaped
        ArcType.CHORD - endpoint connected to startpoint with line
         */

        gc.strokeArc(50, 350, 100, 75, 115.0, 45.0, ArcType.ROUND);
        gc.fillArc(50, 500, 100, 75, 45.0, 115.0, ArcType.ROUND);
        gc.strokeArc(50, 350, 100, 75, 0.0, 45.0, ArcType.ROUND);
        gc.fillArc(50, 500, 100, 75, 0.0, 115.0, ArcType.ROUND);


        Arc arc = new Arc(0, 0, 100, 100, 0, 50);
        shapes.add(arc);

        arc.setFill(Color.BLUE);
        arc.setStroke(Color.BLACK);
        arc.setType(ArcType.ROUND);

        //HBox box = new HBox(gc);
        //box.setAlignment(Pos.CENTER);
       // sc = new Scene(box);
        root.getChildren().add(canvas);




        //((Group) scene.getRoot()).getChildren().add(root);
        //stage.setScene(scene);
        //stage.setScene(sc);
        stage.setScene(s);
        stage.show();
    }










}