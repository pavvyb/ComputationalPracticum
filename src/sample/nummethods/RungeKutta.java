package sample.nummethods;

public class RungeKutta extends Euler {

    public RungeKutta(double x0, double x, double n, double y0) {
        super(x0, x, n, y0);
        super.series.setName("Runge-Kutta Method");
        super.errorSeries.setName("Runge-Kutta Error");
    }

    /**
     * Function to get next y for Runge-Kutta Method
     * @param xLast -
     * @param yLast -
     * @return
     */
    @Override
    protected double funcYi(double xLast, double yLast) {
        double k1 = super.h*super.diffur(xLast, yLast);
        double k2 = super.h*super.diffur(xLast+super.h/2, yLast + k1/2);
        double k3 = super.h*super.diffur(xLast+super.h/2, yLast + k2/2);
        double k4 = super.h*super.diffur(xLast+super.h, yLast + k3);
        return (yLast+ (1.0/6.0)*(k1+2*k2+2*k3+k4));
    }
}