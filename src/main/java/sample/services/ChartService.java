package sample.services;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.text.DecimalFormat;
import java.util.ArrayList;

import static sample.common.Helper.log;

public class ChartService {

    LineChart<Number, Number> chart;
    ClickedPoints clickedPoints = new ClickedPoints();
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
        add(name, x, y, false);
    }

    public void add(String name, double[] x, double[] y, boolean clicable) {
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
        if (clicable) {
            series.getNode().setOnMouseClicked(e -> {
                log("Click on chart: " + name);
                log(e.getX(), 2);
                log(chart.getXAxis().getValueForDisplay(e.getX()), 2);
                clickedPoints.push(e.getX());
            });
        }
    }

    class ClickedPoints {
        Double x1, x2;
        int count = 0;

        public void push(double x) {
            if (count == 0) {
                x1 = x;
                count++;
            }
            if (count == 1) {
                x2 = x;
                count++;
            }
            if (count == 2) {
                count = 0;
            }
        }

        public Double getLastX() {
            if (count == 0)
                return x1;
            if (count == 1)
                return x2;
            return null;
        }

        public Double getX1() {
            if (count > 0)
                return x1;
            return null;
        }

        public Double getX2() {
            if (count > 1)
                return x2;
            return null;
        }
    }

}
