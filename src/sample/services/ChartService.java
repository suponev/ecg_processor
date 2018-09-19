package sample.services;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.input.MouseEvent;

import java.beans.EventHandler;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class ChartService {
    LineChart<Number, Number> chart;

    DecimalFormat df = new DecimalFormat("###.###");

    public ChartService(LineChart<Number, Number> chart) {
        this.chart = chart;
        chart.getXAxis().setLabel("Время мс");
        chart.getYAxis().setLabel("Напряжение м'В");
        chart.setTitle("Фрагмент ЭКГ");
        chart.setCreateSymbols(false);


    }

    public void clear() {
        chart.getData().clear();
    }

    public void add(String name, double[] x, double[] y) {
        if (x.length != y.length) {
            throw new RuntimeException("Could not create chart data");
        }
        XYChart.Series series = new XYChart.Series();
        series.setName(name);
        ArrayList<XYChart.Data> data = new ArrayList<>();
        for (int i = 0; i < x.length; i++) {
            XYChart.Data xy = new XYChart.Data(x[i], y[i]);
            data.add(xy);
        }
        series.getData().addAll(data);

        chart.getData().add(series);
        series.getNode().setOnMouseClicked(e -> {

            System.out.println(chart.getXAxis().getValueForDisplay(e.getX()));
        });
    }


}
