package sample;

class Matrix
{
    public static double det(double a1, double a2, double a3, double b1, double b2, double b3 , double c1 , double c2,double c3 )
    {
        double det = a1 * b2 * c3 + a2 * b3 * c1 + b1 * c2 * a3 - a3 * b2 * c1 - b1 * a2* c3 - a1 * c2 * b3;
        return det;
    }
}
