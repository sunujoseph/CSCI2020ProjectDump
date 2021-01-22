package sample;

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
import javafx.scene.chart.*;
import javafx.scene.paint.Color;
import javafx.scene.Group;

public class Main extends Application {
    private static double[] avgHousingPricesByYear = {
            247381.0 , 264171.4 , 287715.3 , 294736.1,
            308431.4 , 322635.9 , 340253.0 ,363153.7
    };
    private static double[] avgCommercialPricesByYear = {
            1121585.3 , 1219479.5 , 1246354.2 , 1295364.8,
            1335932.6 , 1472362.0 , 1583521.9 , 1613246.3
    };
//
    private static String[] ageGroups = {
            "18-25", "26-35", "36-45", "46-55", "56-65", "65+"
    };
    private static int[] purchasesByAgeGroup = {
            648, 1021, 2453, 3173, 1868, 2247
    };
    private static Color[] pieColours = {
            Color.AQUA, Color.GOLD, Color.DARKORANGE, Color.DARKSALMON, Color.LAWNGREEN, Color.PLUM
    };

    @Override
    public void start(Stage stage) throws Exception{
        stage.setTitle("Lab 06");
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String,Number> bc = new BarChart<>(xAxis,yAxis);


        XYChart.Series barChart1 = new XYChart.Series();

        barChart1.getData().add(new XYChart.Data("2000",247381));
        barChart1.getData().add(new XYChart.Data("2001",264171.4));
        barChart1.getData().add(new XYChart.Data("2002",287715.3));
        barChart1.getData().add(new XYChart.Data("2003",294736.1));
        barChart1.getData().add(new XYChart.Data("2004",308431.4));
        barChart1.getData().add(new XYChart.Data("2005",322635.9));
        barChart1.getData().add(new XYChart.Data("2006",340253));
        barChart1.getData().add(new XYChart.Data("2007",363153.7));

        XYChart.Series barChart2 = new XYChart.Series();

        barChart2.getData().add(new XYChart.Data("2000",1121585.3));
        barChart2.getData().add(new XYChart.Data("2001",1219479.5));
        barChart2.getData().add(new XYChart.Data("2002",1246354.2));
        barChart2.getData().add(new XYChart.Data("2003",1295364.8));
        barChart2.getData().add(new XYChart.Data("2004",1335932.6));
        barChart2.getData().add(new XYChart.Data("2005",1472362.0));
        barChart2.getData().add(new XYChart.Data("2006",1583521.9));
        barChart2.getData().add(new XYChart.Data("2007",1613246.3));
        bc.getData().addAll(barChart1, barChart2);



        PieChart pi=new PieChart();
        pi.getData().add(new PieChart.Data("18-25",648));
        pi.getData().add(new PieChart.Data("26-35",1021));
        pi.getData().add(new PieChart.Data("36-45",2453));
        pi.getData().add(new PieChart.Data("46-55",3173));
        pi.getData().add(new PieChart.Data("56-65",1868));
        pi.getData().add(new PieChart.Data("65+",2247));

        FlowPane root=new FlowPane();
        root.getChildren().addAll(bc,pi);
        Scene scene  = new Scene(root,1000,1000);
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
