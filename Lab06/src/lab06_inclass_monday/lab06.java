package lab06_inclass_monday;
/*
    Code -stolen- used from Devon McGrath. Thanks <3 your code is always better 
    and cleaner than mine
*/

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class lab06 extends Application {

    /* Bar Chart Data */
    private static double[] avgHousePricesByYear = {
            247381.0,264171.4,287715.3,294736.1,
            308431.4,322635.9,340253.0,363153.7
    };

    private static double[] avgCommercialPricesByYear = {
            1121585.3,1219479.5,1246354.2,1295364.8,
            1335932.6,1472362.0,1583521.9,1613246.3
    };


    private Canvas canvas;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root, 800, 425, Color.WHITE);

        this.canvas = new Canvas();
        this.canvas.widthProperty().bind(primaryStage.widthProperty());
        this.canvas.heightProperty().bind(primaryStage.heightProperty());

        root.getChildren().add(canvas);

        primaryStage.setTitle("Lab 06 In Class Bar Graph");
        primaryStage.setScene(scene);
        primaryStage.show();

        //Draw the Graphics
        drawBarChart(50, 50, 325, 325, new Series(avgHousePricesByYear, Color.RED),
                new Series(avgCommercialPricesByYear, Color.BLUE));

    }

    private void drawBarChart(int ox, int oy, int w, int h, Series... series) {
        GraphicsContext g = canvas.getGraphicsContext2D();

        // Get the max and min values of all the series
        double max = Double.NEGATIVE_INFINITY, min = 0;
        int maxLen = 0;

        for (Series s : series) {
            if (s.values.length > maxLen) {
                maxLen = s.values.length;
            }
            for (double x : s.values) {
                if (x > max) {
                    max = x;
                }
                if (x < min) {
                    min = x;
                }
            }
        }

        // Start drawing each series

        double spacing = 10;
        double jump =(w - 2*spacing)/(series.length*(maxLen +2));

        for (int i = 0; i < series.length; i++) {
            g.setFill(series[i].colour);
            double[] arr = series[i].values;
            double x = ox + jump*i + spacing/2;

            // Draw the elements in the series
            for(int j = 0; j < arr.length; j++) {
                double height = ((arr[j] - min) / (max - min)) * h;
                g.strokeRect(x, oy + h - height, jump, height);
                g.fillRect(x, oy + h - height, jump, height);
                x += jump * series.length + spacing;
            }
        }
        g.strokeRect(ox, oy, w, h);
    }

    public static class Series {
        public double[] values;
        public Color colour;
        public String label;

        public Series() {
            this.values = new double[0];
            this.colour = Color.WHITE;
        }

        public Series(double[] values, Color colour) {
            this.values = values;
            this.colour = colour;
        }

        public Series(double[] values, Color color, String label) {
            this.values = values;
            this.colour = colour;
            this.label = label;
        }

        public double[] getValues() {
            return values;
        }
        public void setValues(double[] values) {
            this.values = values;
        }

        public Color getColour() {
            return colour;
        }
        public void setColour(Color colour) {
            this.colour = colour;
        }

        public String getLabel() {
            return label;
        }
        public void setLabel(String label) {
            this.label = label;
        }
    }
}
