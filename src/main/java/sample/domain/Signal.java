package sample.domain;


import java.util.Arrays;

public class Signal {
    double[] x;
    double[] y;

    public Signal(double[] x, double[] y) {
        this.x = x;
        this.y = y;
    }

    public Signal() {
    }

    public Signal copyFragment(int from, int to) {
        if (from >= 0 && to > from && y.length >= to) {
            Signal result = new Signal();
            result.setX(Arrays.copyOfRange(x, from, to));
            result.setY(Arrays.copyOfRange(y, from, to));
            return result;
        } else {
            throw new RuntimeException("Could not copy fragment");
        }
    }

    public void setX(double[] x) {
        this.x = x;
    }

    public void setY(double[] y) {
        this.y = y;
    }

    public double[] getX() {
        return x;
    }

    public double[] getY() {
        return y;
    }

    public int length() {
        return y.length;
    }
}
