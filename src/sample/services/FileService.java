package sample.services;

import sample.common.TxtFileFilter;
import sample.domain.Signal;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.DoubleStream;

import static sample.common.Helper.log;


public class FileService {

    private final String PATH_TO_SIGNALS = "data/signals";
    private final String PATH_TO_SETTINGS = "data/settings";

    private File signalsDirectory;
    private File settingsFile;

    private HashMap<String, Signal> signalsMap;

    public FileService() {
        signalsDirectory = new File(PATH_TO_SIGNALS);
        settingsFile = new File(PATH_TO_SETTINGS);
        loadSignals();
    }

    private void loadSignals() {
        signalsMap = new HashMap<>();
        log("Load signals :");
        Arrays.stream(signalsDirectory.listFiles(new TxtFileFilter())).forEach(f -> {
            log(f.getName(), 2);
            Path path = Paths.get(PATH_TO_SIGNALS, f.getName());
            List<String> lines = new ArrayList<>();
            try {
                lines = Files.readAllLines(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Signal result = new Signal();
            double[] readY = lines.stream().mapToDouble(l -> Double.parseDouble(l)).toArray();
            result.setY(readY);
            result.setX(DoubleStream.iterate(1, n -> n + 1).limit(readY.length).toArray());
            signalsMap.put(f.getName(), result);
        });
        log("Load signals completed:");
    }


    private List<File> listFilesForFolder(final File folder) {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                System.out.println(fileEntry.getName());
            }
        }
        return null;
    }

    public HashMap<String, Signal> getSignalsMap() {
        return signalsMap;
    }

    public void setSignalsMap(HashMap<String, Signal> signalsMap) {
        this.signalsMap = signalsMap;
    }
}
