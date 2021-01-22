package lab06;


import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.chart.*;

public class Main extends Application{


    //Code Listing 1: Sample Data for the Bar Chart
    private static double[] avgHousingPricesByYear = {
            247381.0,264171.4,287715.3,294736.1,
            308431.4,322635.9,340253.0,363153.7
    };
    private static double[] avgCommercialPricesByYear = {
            1121585.3,1219479.5,1246354.2,1295364.8,
            1335932.6,1472362.0,1583521.9,1613246.3
    };

    //Code Listing 2: Sample Data for the Pie Chart
    private static String[] ageGroups = {
            "18-25", "26-35", "36-45", "46-55", "56-65", "65+"
    };
    private static int[] purchasesByAgeGroup = {
            648, 1021, 2453, 3173, 1868, 2247
    };
    private static Color[] pieColours = {
            Color.AQUA, Color.GOLD, Color.DARKORANGE,
            Color.DARKSALMON, Color.LAWNGREEN, Color.PLUM
    };

    public static void main(String[] args) {
        launch(args);

    }


    @Override
    public void start(Stage stage) {
        stage.setTitle("Lab 06");

       // BarChart<String,Number> bc = new BarChart<String,Number>(xAxis,yAxis);

        //Bar Graph Code
        XYChart.Series barGraph = new XYChart.Series();
        //for (int i = 0; i <  avgHousingPricesByYear.length; i++) {
      //  barGraph.getData().addAll(avgHousingPricesByYear);
      //  barGraph.getData().addAll(avgCommercialPricesByYear);

        //}
       // for (int i = 0; i < avgCommercialPricesByYear.length; i++) {

        //}







    }


}
