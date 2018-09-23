package sample.domain;


public interface IPhiFunction {

    double call(double x, double a, double m, double sigma);

    boolean calculateNumerically();

    double[] getI(double sigma);

    double getLeftEdge();

    double getRightEdge();

}
