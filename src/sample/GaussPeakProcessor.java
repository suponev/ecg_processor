package sample;

import sample.domain.FunctionHelper;
import sample.domain.IPhiFunction;

public class GaussPeakProcessor {

    private IPhiFunction phi;

    private double sig = 5;

    private double[] x;
    private double[] y;

    private double[] mu;
    private double[] b;
    private double[] i;
    private double[] a;
    private double[] m;

    private int c = 6;


    public double model(double x) {
        return phi.call(x, a[0], m[0], sig)
                + phi.call(x, a[1], m[1], sig)
                + phi.call(x, a[2], m[2], sig);
    }

    public GaussPeakProcessor(double[] x, double[] y, IPhiFunction phi) {
        mu = new double[c];
        b = new double[c];
        i = new double[c];
        this.phi = phi;

        this.x = x;
        this.y = y;
    }

    public void calcalate() {
        computeMu();
        computeI();
        computeB();
        computeM();
        computeA();
    }

    void computeM() {
        m = new double[3];
        double det = b[2] * b[2] * b[2] + b[1] * b[1] * b[4] + b[3] * b[3] * b[0] - b[0] * b[2] * b[4] - 2 * b[1] * b[2] * b[3];
        double det1 = b[3] * b[2] * b[2] + b[1] * b[1] * b[5] + b[4] * b[3] * b[0] - b[0] * b[2] * b[5] - b[4] * b[1] * b[2] - b[1] * b[3] * b[3];
        double det2 = b[2] * b[2] * b[4] + b[3] * b[5] * b[0] + b[3] * b[1] * b[4] - b[0] * b[4] * b[4] - b[5] * b[2] * b[1] - b[3] * b[3] * b[2];
        double det3 = b[2] * b[2] * b[5] + b[1] * b[4] * b[4] + b[3] * b[3] * b[3] - b[3] * b[2] * b[4] - b[3] * b[4] * b[2] - b[3] * b[1] * b[5];
        double c2 = det1 / det;
        double c1 = det2 / det;
        double c0 = det3 / det;
        double bb = -c2;
        double c = -c1;
        double d = -c0;

        double p = c2;
        double q = c1;
        double r = c0;
        double p1 = c - bb * bb / 3;
        double q1 = (2 * bb * bb * bb / 27) - (bb * c / 3) + d;
        double Q = (Math.pow((p1 / 3.0), 3)) + Math.pow((q1 / 2.0), 2);

        double phi;

        double rho = Math.sqrt((q1 / 2) * (q1 / 2) + Math.abs(Q));
        if (q1 < 0) {
            phi = Math.atan(-2 * Math.sqrt(Math.abs(Q)) / q1);
        } else {
            if (q1 > 0) {
                phi = Math.PI - Math.atan(2 * Math.sqrt(Math.abs(Q)) / q1);
            } else {
                phi = Math.PI / 2;
            }
        }
        m[1] = 2 * Math.exp(Math.log(rho) / 3) * Math.cos(phi / 3) + p / 3;
        m[2] = 2 * Math.exp(Math.log(rho) / 3) * Math.cos(phi / 3 + 2 * Math.PI / 3) + p / 3;
        m[0] = 2 * Math.exp(Math.log(rho) / 3) * Math.cos(phi / 3 + 4 * Math.PI / 3) + p / 3;
        System.out.println("m's :  " + m[0] + " " + m[1] + " " + m[2] + " ");

    }

    double[] computeA() {
        a = new double[m.length];
        if (b.length == 4) {
            a[0] = (b[1] - b[0] * m[1]) / (m[0] - m[1]);
            a[1] = b[0] - a[0];
        } else {

            double detA = Matrix.det(1, 1, 1, m[0], m[1], m[2], m[0] * m[0], m[1] * m[1], m[2] * m[2]);
            a[0] = Matrix.det(b[0], 1, 1, b[1], m[1], m[2], b[2], m[1] * m[1], m[2] * m[2]) / detA;
            a[1] = Matrix.det(1, b[0], 1, m[0], b[1], m[2], m[0] * m[0], b[2], m[2] * m[2]) / detA;
            a[2] = Matrix.det(1, 1, b[0], m[0], m[1], b[1], m[0] * m[0], m[1] * m[1], b[2]) / detA;

        }
        return a;
    }

    void computeB() {
        b[0] = mu[0] / i[0];

        b[1] = (mu[1] - i[1] * b[0]) / i[0];

        b[2] = (mu[2] - i[2] * b[0] + 2 * i[1] * b[1]) / i[0];

        b[3] = (mu[3] - i[3] * b[0] + 3 * i[2] * b[1] + 3 * i[1] * b[2]) / i[0];

        b[4] = (mu[4] - i[4] * b[0] + 4 * i[3] * b[1] + 6 * i[2] * b[2] + 4 * i[1] * b[3]) / i[0];

        b[5] = (mu[5] + i[5] * b[0] + 5 * i[4] * b[1] + 10 * i[3] * b[2] + 10 * i[2] * b[3] + 5 * i[1] * b[4]) / i[0];
    }

    void computeI() {
        double[] yModeling = new double[x.length];
        for (int i = 0; i < y.length - 1; i++) {
            yModeling[i] = phi.call(x[i], 1, 0, sig);
        }
        for (int i = 0; i < c; i++) {
            this.i[i] = FunctionHelper.calculateMu(this.x, yModeling, i);
        }
    }

    void computeMu() {
        for (int order = 0; order < c; order++) {
            mu[order] = FunctionHelper.calculateMu(x, y, order);
        }
    }


}
