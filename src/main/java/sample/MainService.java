package sample;

import sample.domain.FunctionsRepository;
import sample.domain.IPhiFunction;
import sample.services.ChartService;
import sample.services.FileService;
import sample.domain.Signal;

import java.util.Arrays;

public class MainService {


    private Signal fullSignal;
    private Signal workSignal;
    private FileService fileService;
    private ChartService chartService;
    private ChartService secondChartService;
    private GaussPeakProcessor gaussPeak;
    private IPhiFunction phi = FunctionsRepository.repository.get("triple-gauss");


    public MainService(FileService fileService,
                       ChartService chartService,
                       ChartService secondChartService) {
        this.fileService = fileService;
        this.chartService = chartService;
        this.secondChartService = secondChartService;
    }

    public void selectRegion(int from, int to) {
        this.workSignal = fullSignal.copyFragment(from, to);
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

        gaussPeak = new GaussPeakProcessor(fullSignal.getX(), y, phi);
        gaussPeak.setChartService(secondChartService);
        gaussPeak.setSigma(sigma);
        this.chartService.add("Source-Shifted", fullSignal.getX(), y, true);
        gaussPeak.calculate();
        double[] model = new double[fullSignal.length()];

        for (int i = 0; i < fullSignal.length(); i++) {
            model[i] = gaussPeak.model(fullSignal.getX()[i]);
        }

        this.chartService.add("Modeled", fullSignal.getX(), model);

    }

    private void printPhi(ChartService chartService) {
        chartService.clear();

        //chartService.add("phi",);
    }


}
