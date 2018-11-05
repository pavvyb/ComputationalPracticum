package sample.nummethods;

public class IEuler extends Euler{

    public IEuler(double x0, double x, double n, double y0) {
        super(x0, x, n, y0);
        super.series.setName("Improved Euler Method");
        super.errorSeries.setName("Improved Euler Error");
    }

    /**
     * Find next value of y according to Improved Euler Method
     * @param xPrev
     * @param yPrev
     * @return
     */
    @Override
    protected double funcYi(double xPrev, double yPrev) {
        return yPrev+super.h*((super.diffur(xPrev,yPrev)+super.diffur(xPrev+super.h, super.eulerY(xPrev,yPrev)))/2);
    }
}