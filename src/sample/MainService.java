package sample;

import sample.Services.ChartService;
import sample.Services.FileService;
import sample.domain.Signal;

public class MainService {

    Signal signal;
    private FileService fileService;
    private ChartService chartService;


    public MainService(FileService fileService, ChartService chartService) {
        this.fileService = fileService;
        this.chartService = chartService;
    }

    public void selectFile(String fileName) {
        this.signal = fileService.getSignalsMap().get(fileName);
        this.chartService.add("Test",signal.getX(),signal.getY());
    }


}
