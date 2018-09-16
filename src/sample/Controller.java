package sample;

import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import sample.Services.ChartService;
import sample.Services.FileService;

import javax.swing.event.ChangeEvent;

import static sample.common.Helper.log;


public class Controller {

    @FXML()
    Button applyButton = new Button();
    @FXML()
    ChoiceBox selectFiles = new ChoiceBox();

    final NumberAxis xAxis = new NumberAxis();
    final NumberAxis yAxis = new NumberAxis();
    @FXML()
    LineChart<Number, Number> chart = new LineChart(xAxis, yAxis);

    private FileService fileService;
    private ChartService chartService;
    private MainService mainService;


    @FXML
    void initialize() {
        fileService = new FileService();
        chartService = new ChartService(chart);
        mainService = new MainService(fileService, chartService);

        selectFiles.setItems(FXCollections.observableArrayList(fileService.getSignalsMap().keySet()));
        selectFiles.getSelectionModel().selectedItemProperty().addListener((a) -> {
            log("Select signal file : " );
        });
        log("Scene initialized.");
    }


    public Controller() {

    }

    public void selectSignalFile() {

    }

    public void clickApply() {
        mainService.selectFile((String) selectFiles.getSelectionModel().selectedItemProperty().getValue());
        log("Clic!!");
    }
}
