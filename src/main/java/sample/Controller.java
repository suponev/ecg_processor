package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.*;
import sample.common.ParamCellFactory;
import sample.services.ChartService;
import sample.services.FileService;

import static sample.common.Helper.log;


public class Controller {

    @FXML()
    TextField sigmaInput = new TextField();
    @FXML()
    TextField isolineInput = new TextField();
    @FXML()
    Button applyButton = new Button();
    @FXML()
    ChoiceBox selectFiles = new ChoiceBox();

    public static final ObservableList data = FXCollections.observableArrayList();

    final NumberAxis xAxis = new NumberAxis();
    final NumberAxis yAxis = new NumberAxis();
    @FXML()
    LineChart<Number, Number> chart = new LineChart(xAxis, yAxis);
    @FXML()
    LineChart<Number, Number> chartTwo = new LineChart(xAxis, yAxis);
    @FXML
    ListView<String> paramList = new ListView<String>(data);
    private FileService fileService;
    private ChartService chartService;
    private ChartService secondChartService;
    private MainService mainService;


    @FXML
    void initialize() {

        paramList.setCellFactory(new ParamCellFactory());

        paramList.setItems(data);
        fileService = new FileService();
        chartService = new ChartService(chart);
        secondChartService = new ChartService(chartTwo);
        mainService = new MainService(fileService, chartService, secondChartService);

        selectFiles.setItems(FXCollections.observableArrayList(fileService.getSignalsMap().keySet()));
        selectFiles.getSelectionModel().selectedItemProperty().addListener((a) -> {
            log("Select signal file : ");
        });
        log("Scene initialized.");
    }


    public Controller() {

    }

    public void selectSignalFile() {

    }

    public void clickApply() {
        mainService.selectFile((String) selectFiles.getSelectionModel().selectedItemProperty().getValue());
        double sig = Double.parseDouble(sigmaInput.getText());
        double isoline = Double.parseDouble(isolineInput.getText());
        log("Sigma and Isoline " + sig + "," + isoline);
        mainService.computeModel(sig, isoline);
        log("Clic!!");
    }

    public void clickReset() {
        mainService.selectFile((String) selectFiles.getSelectionModel().selectedItemProperty().getValue());
        chartService.clear();
    }
}
