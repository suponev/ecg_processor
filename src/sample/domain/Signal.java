package sample.domain;



public class Signal {
    double[] x;
    double[] y;

    public Signal(double[] x, double[] y) {
        this.x = x;
        this.y = y;
    }

    public Signal() {
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
