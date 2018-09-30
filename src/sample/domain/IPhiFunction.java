package sample.domain;

import static sample.domain.FunctionHelper.calculateN;

public interface IPhiFunction {

    double call(double x, double a, double m, double sigma);

    boolean calculateNumerically();

    double[] getI(double sigma);

    double getLeftEdge();

    double getRightEdge();

    default Signal getSignal(double a, double m, double sigma, double h) {
        int n = calculateN(h, getLeftEdge(), getRightEdge());
        double[] x = new double[n];
        double[] y = new double[n];
        for (int i = 0; i < n; i++) {
            x[i] = getLeftEdge() + h * i;
            y[i] = call(x[i], a, m, sigma);
        }
        return new Signal(x, y);
    }

}
