package sample.domain;

import java.util.function.Predicate;

public class FunctionHelper {


    /**
     * Calculates the values of the moments of the required order of the function.
     *
     * @param x
     * @param y
     * @param order
     * @return
     */
    public static double calculateMu(double[] x, double[] y, int order) {

        double integral = 0;
        double h = Math.abs(Math.abs(x[1]) - Math.abs(x[0]));

        for (int i = 0; i < y.length - 1; i++) {
            integral += (y[i] * Math.pow(x[i], order) + y[i + 1] * Math.pow(x[i + 1], order)) / 2 * h;
        }

        return integral;
    }

    public static int calculateN(double h, double leftEdge, double rightEdge) {
        return (int) ((rightEdge - leftEdge) / h);
    }


}
