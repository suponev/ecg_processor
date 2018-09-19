package sample;

import javafx.scene.Node;
import sample.domain.FunctionsRepository;
import sample.services.ChartService;
import sample.services.FileService;
import sample.domain.Signal;

import java.util.Arrays;

public class MainService {


    private Signal fullSignal;
    private Signal workSignal;
    private FileService fileService;
    private ChartService chartService;
    private GaussPeakProcessor gaussPeak;


    public MainService(FileService fileService, ChartService chartService) {
        this.fileService = fileService;
        this.chartService = chartService;
    }

    public void selectRegion(int start, int finish) {

    }

    public void selectFile(String fileName) {
        this.fullSignal = fileService.getSignalsMap().get(fileName);
       // this.chartService.add("Source", fullSignal.getX(), fullSignal.getY());

    }

    public void computeModel(double sigma, double isoline) {
        double[] y = Arrays.copyOf(fullSignal.getY(), fullSignal.getY().length);
        for (int i = 0; i < fullSignal.getY().length; i++) {
            y[i] = y[i] + isoline;
        }
        gaussPeak = new GaussPeakProcessor(
                fullSignal.getX(),
                y,
                FunctionsRepository.repository.get("gauss")
        );
        gaussPeak.setSig(sigma);
         this.chartService.add("Source-Shifted", fullSignal.getX(), y);
        gaussPeak.calcalate();
        double[] model = new double[fullSignal.length()];

        for (int i = 0; i < fullSignal.length(); i++) {
            model[i] = gaussPeak.model(fullSignal.getX()[i]);
        }

        this.chartService.add("Modeled", fullSignal.getX(), model);

    }


}
