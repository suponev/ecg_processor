package sample;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import sample.domain.FunctionsRepository;

import java.io.IOException;
import java.text.DecimalFormat;


public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        /*/
        stage.setTitle("Line Chart Sample");
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Number of Month");
        final LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);

        lineChart.setTitle("Stock Monitoring, 2010");

        XYChart.Series source = new XYChart.Series();
        source.setName("Исходный сигнал");

        XYChart.Series result = new XYChart.Series();
        result.setName("Результат моделирования");

        int n = 1000;
        double a = -20;
        double b = 20;

        double h = (b - a) / n;

        double[] x = new double[n];
        double[] y = new double[n];

        DecimalFormat df = new DecimalFormat("###.###");

        for (int i = 0; i < n; i++) {
            x[i] = a + h * i;
            y[i] = FunctionsRepository.repository.get("gauss").call(x[i], 1, 0, 3);
            // System.out.println("x =" + x[i] + " y = " + y[i]);
            source.getData().add(new XYChart.Data(x[i], y[i]));
            //System.out.print(df.format(y[i]) + ",");
        }

        GaussPeakProcessor gpp = new GaussPeakProcessor(x, y, FunctionsRepository.repository.get("gauss"));
        gpp.calculate();

        for (int i = 0; i < n; i++) {

            y[i] = gpp.model(x[i]);
            // System.out.println("x =" + x[i] + " y = " + y[i]);
            result.getData().add(new XYChart.Data(x[i], y[i]));
            //System.out.print(df.format(y[i]) + ",");
        }


        Scene scene = new Scene(lineChart, 800, 600);
        lineChart.getData().add(source);
        lineChart.getData().add(result);/*/

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        stage.setTitle("Hello World");
        Scene scene = new Scene(root, 800, 800);
        String css = this.getClass().getResource("chart.style.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
