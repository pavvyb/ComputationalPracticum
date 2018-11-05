package sample.nummethods;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import static java.lang.Math.exp;
import static java.lang.Math.log;
import static java.lang.Math.pow;

public class Euler {

    private double maxError = 0;

    public Euler(double x0, double x, double n, double y0) {
        this.x0 = x0;
        X = x;
        N = n;
        this.y0 = y0;
        this.h = (X - x0) / this.N;
        this.series = new XYChart.Series();
        this.errorSeries = new XYChart.Series();
        this.series.setName("Euler Method");
        this.errorSeries.setName("Euler Error");
        setSeries();
    }

    private void setSeries() {
        ObservableList<XYChart.Data> dataset = FXCollections.observableArrayList();
        ObservableList<XYChart.Data> errorsDataset = FXCollections.observableArrayList();

        /**
         * Adding initial value point to the data
         * finding initial error
         * applying Euler's function to initial point
         */
        dataset.add(new XYChart.Data(x0, y0));
        double error = Math.abs(exact(x0) - y0);
        maxError = error;
        errorsDataset.add(new XYChart.Data(x0, error));
        double y = nextY(x0, y0);

        for (double xi = x0 + h; xi <= X; xi += h) {
            error = Math.abs(exact(xi) - y);
            if (error > maxError){
                maxError = error;
            }
            dataset.add(new XYChart.Data(xi, y));
            errorsDataset.add(new XYChart.Data(xi, error));
            y = nextY(xi, y);
        }
        errorSeries.setData(errorsDataset);
        series.setData(dataset);
    }

    /**
     * Given Differential Equation
     * @param y
     * @return
     */
    protected double diffur(double y) {
        return (2*(pow(y,1/2)) + 2*y);
    }


    /**
     * Function to get 'eulerY'
     * @param xPrev
     * @param yPrev
     * @return
     */
    protected double nextY(double xPrev, double yPrev) {
        return (eulerY(xPrev, yPrev));
    }


    /**
     * Finding y according to Euler's Method
     * @param xPrev - previous value of x
     * @param yPrev - previous value of y
     * @return
     */
    protected double eulerY(double xPrev, double yPrev) {
        return yPrev + h * diffur(yPrev);
    }

    /**
     * Exact Solution
     * @param xPrev
     * @return
     */
    protected double exact(double xPrev) {
        double cons = constanta(y0, x0);
        return pow(exp(xPrev+ cons)-1,2);
    }

    /**
     * Helper method to get C (const)
     * @param y0 - input initial conditions
     * @param x0 - input initial conditions
     * @return
     */
    private Double constanta(double y0, double x0){
        return log(1 + pow(y0, 1/2)) - x0;
    }

    public XYChart.Series getSeries() {
        return series;
    }

    public XYChart.Series getErrorSeries() {
        return errorSeries;
    }

    public XYChart.Series getImprovedSeries() {
        return new IEuler(x0, X, N, y0).getSeries();
    }

    public XYChart.Series getImprovedErrorSeries() {
        return new IEuler(x0, X, N, y0).getErrorSeries();
    }

    public XYChart.Series getRungeKuttaSeries() {
        return (new RungeKutta(x0, X, N, y0)).getSeries();
    }

    public XYChart.Series getRungeKuttaErrorSeries() {
        return (new RungeKutta(x0, X, N, y0)).getErrorSeries();
    }

    public double getMaxError() {
        return maxError;
    }

    public double getIEulerError() {
        return (new IEuler(x0, X, N, y0)).getMaxError();
    }

    public double getRKuttaError() {
        return (new RungeKutta(x0, X, N, y0)).getMaxError();
    }

    protected XYChart.Series series;
    protected XYChart.Series errorSeries;
    private double x0;
    private double X;
    private double N;
    private double y0;
    protected double h;

}