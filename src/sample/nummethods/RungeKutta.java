package sample.nummethods;

public class RungeKutta extends Euler {

    public RungeKutta(double x0, double x, double n, double y0) {
        super(x0, x, n, y0);
        super.series.setName("Runge-Kutta Method");
        super.errorSeries.setName("Runge-Kutta Error");
    }

    /**
     * Function to get next y for Runge-Kutta Method
     * uses super (Euler)
     * @param xPrev - previous value of x
     * @param yPrev - previous value of y
     * @return
     */
    @Override
    protected double nextY(double xPrev, double yPrev) {
        double k1 = super.h*super.diffur(yPrev);
        double k2 = super.h*super.diffur(yPrev + k1/2);
        double k3 = super.h*super.diffur(yPrev + k2/2);
        double k4 = super.h*super.diffur(yPrev + k3);
        return (yPrev+ (k1+2*k2+2*k3+k4)*(1.0/6.0));
    }
}