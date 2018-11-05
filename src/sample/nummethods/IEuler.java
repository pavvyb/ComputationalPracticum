package sample.nummethods;

public class IEuler extends Euler{

    public IEuler(double x0, double x, double n, double y0) {
        super(x0, x, n, y0);
        super.series.setName("Improved Euler Method");
        super.errorSeries.setName("Improved Euler Error");
    }

    /**
     * Find next value of y according to Improved Euler Method
     * uses super (Euler)
     * @param xPrev
     * @param yPrev
     * @return
     */
    @Override
    protected double nextY(double xPrev, double yPrev) {
        return yPrev+super.h*((super.diffur(yPrev)+super.diffur(super.eulerY(xPrev,yPrev)))/2);
    }
}