package sample.domain;

import java.util.HashMap;
import java.util.Map;

public class FunctionsRepository {

    public static Map<String, IPhiFunction> repository;

    static {
        repository = new HashMap<>();
        repository.put("gauss",
                new IPhiFunction() {
                    @Override
                    public double call(double x, double a, double m, double sigma) {
                        return a * Math.exp(-((x - m) * (x - m)) / 2 / sigma / sigma);
                    }

                    @Override
                    public boolean calculateNumerically() {
                        return true;
                    }

                    @Override
                    public double[] getI(double sigma) {
                        return null;
                    }

                    @Override
                    public double getLeftEdge() {
                        return -50;
                    }

                    @Override
                    public double getRightEdge() {
                        return +50;
                    }
                }
        );

        repository.put("double-gauss",
                new IPhiFunction() {
                    @Override
                    public double call(double x, double a, double m, double sigma) {
                        return a * Math.exp(-((x + 0.3 - m) * (x + 0.3 - m)) / 2 / sigma / sigma)
                                - 0.8 * (a * Math.exp(-((x - 0.3 - m) * (x - 0.3 - m)) / 2 / sigma / sigma));
                    }

                    @Override
                    public boolean calculateNumerically() {
                        return true;
                    }

                    @Override
                    public double[] getI(double sigma) {
                        return null;
                    }

                    @Override
                    public double getLeftEdge() {
                        return -50;
                    }

                    @Override
                    public double getRightEdge() {
                        return +50;
                    }
                });

        repository.put("triple-gauss",
                new IPhiFunction() {
                    @Override
                    public double call(double x, double a, double m, double sigma) {
                        return +1 * a * Math.exp(-((x + 0 - m) * (x + 0 - m)) / 2 / sigma / sigma)
                                        - 0.9 * (a * Math.exp(-((x - 1 - m) * (x - 1 - m)) / 2 / sigma / sigma))
                                        - 0.9 * (a * Math.exp(-((x + 10 - m) * (x + 10 - m)) / 2 / sigma / sigma));
                    }

                    @Override
                    public boolean calculateNumerically() {
                        return true;
                    }

                    @Override
                    public double[] getI(double sigma) {
                        return null;
                    }

                    @Override
                    public double getLeftEdge() {
                        return -50;
                    }

                    @Override
                    public double getRightEdge() {
                        return +50;
                    }
                });
    }
}
