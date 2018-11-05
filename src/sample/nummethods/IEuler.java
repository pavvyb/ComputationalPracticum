package sample.nummethods;

public class IEuler extends Euler{

    public IEuler(double x0, double x, double n, double y0) {
        super(x0, x, n, y0);
        super.series.setName("Improved Euler Method");
        super.errorSeries.setName("Improved Euler Error");
    }

    //Get y_(i+1) by Improved Euler Method
    @Override
    protected double funcYi(double xLast, double yLast) {
        return yLast+super.h*((super.diffur(xLast,yLast)+super.diffur(xLast+super.h, super.eulerY(xLast,yLast)))/2);
    }
}