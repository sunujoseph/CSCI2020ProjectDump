package Lab09;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.*;
import java.text.SimpleDateFormat;


public class main extends Application {


    /*
    * String date = "13-08-2016";
      String[] values = date.split("-");
      int day = Integer.parseInt(values[0]);
      int month = Integer.parseInt(values[1]);
      int year = Integer.parseInt(values[2]);
    */


    private static final String apiKey = "https://www.alphavantage.co/query?function=TIME_SERIES_MONTHLY&symbol=AAPL&apikey=ONZ9SN8990XVME6L";
    private static final String apiKey2 = "https://www.alphavantage.co/query?function=TIME_SERIES_MONTHLY&symbol=QQQ&apikey=ONZ9SN8990XVME6L";
    public static ObservableList<APIData> data = FXCollections.observableArrayList();
    public static ObservableList<APIData> data2 = FXCollections.observableArrayList();


    public static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        //current position
        int cp;
        while ((cp = rd.read()) != -1){
            sb.append((char) cp);
        }
        return sb.toString();
    }
    public static JsonObject readJsonFromUrl(String url) throws IOException, JsonParseException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JsonParser parser = new JsonParser();
            JsonObject json = parser.parse(jsonText).getAsJsonObject();
            return json;
        } finally {
            is.close();
        }
    }


    public static void main(String args[]) throws IOException, JsonParseException {
        JsonObject json = readJsonFromUrl(apiKey);
        JsonObject json2 = readJsonFromUrl(apiKey2);


        JsonObject values = json.get("Monthly Time Series").getAsJsonObject();
        JsonObject values2 = json2.get("Monthly Time Series").getAsJsonObject();


        for (Map.Entry<String, JsonElement> entry : values.entrySet()) {
            String date = entry.getKey();
            float closePrice = entry.getValue().getAsJsonObject().get("4. close").getAsFloat();

            //System.out.printf("%s : $%.2f \n", date, closePrice);

            data.add(new APIData(date,closePrice));

        }

        for (Map.Entry<String, JsonElement> entry : values2.entrySet()) {
            String date = entry.getKey();
            float closePrice = entry.getValue().getAsJsonObject().get("4. close").getAsFloat();

            //System.out.printf("%s : $%.2f \n", date, closePrice);

            data2.add(new APIData(date,closePrice));

        }


        launch(args);

    }


    @Override
    public void start(Stage stage) {
        Path path = new Path();

        //Creating a Group object
        Group root = new Group(path);

        //Creating a scene object
        Scene scene = new Scene(root, 800, 700);

       // ArrayList<LineTo> Line = null;
        ObservableList<LineTo> line = FXCollections.observableArrayList();
        ObservableList<LineTo> line2 = FXCollections.observableArrayList();

        MoveTo move = new MoveTo(0,0);



        for(int i = 0; i < data.size(); i++){
            line.add(i,new LineTo(i*10,data.get(i).getClosePrice()));
            //System.out.println("Date: " + data.get(i).getDate() + "ClosePrice: " + data.get(i).getClosePrice());
        }
        for(int i = 0; i < data.size(); i++){
            line2.add(i,new LineTo(i*10,data2.get(i).getClosePrice()));
            //System.out.println("Date: " + data.get(i).getDate() + "ClosePrice: " + data.get(i).getClosePrice());
        }

        path.getElements().add(move);
        path.getElements().addAll(line);
        path.getElements().addAll(line2);

        //final NumberAxis xAxis = new NumberAxis();
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
       // xAxis.setLabel("Number of Month");
        //creating the chart
        final LineChart<String,Number> lineChart = new LineChart<String,Number>(xAxis,yAxis);

        lineChart.setTitle("Lab 09");
        //defining a series
        XYChart.Series series = new XYChart.Series();
        XYChart.Series series2 = new XYChart.Series();

       // series.setName("Data1");
       // series2.setName("Data2");

        //populating the series with data
        /*
        series.getData().add(new XYChart.Data(1, 23));
        series.getData().add(new XYChart.Data(2, 14));
        series.getData().add(new XYChart.Data(3, 15));
        series.getData().add(new XYChart.Data(4, 24));
        series.getData().add(new XYChart.Data(5, 34));
        series.getData().add(new XYChart.Data(6, 36));
        series.getData().add(new XYChart.Data(7, 22));
        series.getData().add(new XYChart.Data(8, 45));
        series.getData().add(new XYChart.Data(9, 43));
        series.getData().add(new XYChart.Data(10, 17));
        series.getData().add(new XYChart.Data(11, 29));
        series.getData().add(new XYChart.Data(12, 25));
        */
        for(int i = 0; i < data.size(); i++){
            series.getData().add(new XYChart.Data(data.get(i).getDate(),(data.get(i).getClosePrice())));
            //System.out.println("Date: " + data.get(i).getDate() + "ClosePrice: " + data.get(i).getClosePrice());
        }

        for(int j = 0; j < data2.size(); j++){
            series2.getData().add(new XYChart.Data(data2.get(j).getDate(), (data2.get(j).getClosePrice())));
        }

        //series.getData().addAll(data);
        Scene scene2  = new Scene(lineChart,800,600);
        lineChart.getData().addAll(series,series2);
















        //Setting title to the Stage
        stage.setTitle("Lab09");

        //Adding scene to the stage
        stage.setScene(scene2);
        stage.show();


    }




    public void drawLinePoint(){



    }



}
