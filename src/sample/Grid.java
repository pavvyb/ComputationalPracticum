package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import sample.nummethods.Euler;
import sample.nummethods.Exact;

public class Grid {

    private double x0;
    private double y0;
    private double X;
    private double N;
    private double c;
    private double n0;
    private double eN;

    /**
     * Getting the set of points for Graphs
     * XYChart.Series seriesFunction; - Set of points for Exact Solution Graph
     * XYChart.Series seriesEuler; - Set of points for Euler Method Graph
     * XYChart.Series seriesEulerError; - Set of points for Euler method error graph
     * XYChart.Series seriesEulerErrorN; - Set of points for Euler method error graph depending on the N
     * XYChart.Series seriesImprovedEuler; - Set of points for Improved Euler method
     * XYChart.Series seriesImprovedEulerError; - Set of points for Improved Euler method error graph
     * XYChart.Series seriesImprovedEulerErrorN; - Set of points for Improved Euler method error graph depending on the N
     * XYChart.Series seriesRungeKutta;  - Set of points for Runge-Kutta method
     * XYChart.Series seriesRungeKuttaError; - Set of points for Runge-Kutta method error graph
     * XYChart.Series seriesRungeKuttaErrorN; - Set of points for Runge-Kutta method error graph depending on the
     */
    private XYChart.Series seriesFunction;
    private XYChart.Series seriesEuler;
    private XYChart.Series seriesEulerError;
    private XYChart.Series seriesEulerErrorN;
    private XYChart.Series seriesImprovedEuler;
    private XYChart.Series seriesImprovedEulerError;
    private XYChart.Series seriesImprovedEulerErrorN;
    private XYChart.Series seriesRungeKutta;
    private XYChart.Series seriesRungeKuttaError;
    private XYChart.Series seriesRungeKuttaErrorN;


    public Grid(double x0, double X, double y0, double N, double n0, double eN) {
        this.seriesFunction = new Exact(x0, y0, X, N).getSeries();
        Euler euler = new Euler(x0, X, N, y0);
        this.seriesEuler = euler.getSeries();
        this.seriesEulerError = euler.getErrorSeries();
        this.seriesImprovedEuler = euler.getImprovedSeries();
        this.seriesImprovedEulerError = euler.getImprovedErrorSeries();
        this.seriesRungeKutta = euler.getRungeKuttaSeries();
        this.seriesRungeKuttaError = euler.getRungeKuttaErrorSeries();
        errorAnalyzeSeries(x0, X, y0, N, n0, eN);
    }

    private void errorAnalyzeSeries(double x0, double X, double y0, double N, double n0, double eN) {
        ObservableList<XYChart.Data> dataEEu = FXCollections.observableArrayList();
        ObservableList<XYChart.Data> dataEIEu = FXCollections.observableArrayList();
        ObservableList<XYChart.Data> dataERK = FXCollections.observableArrayList();
        this.seriesEulerErrorN = new XYChart.Series();
        this.seriesImprovedEulerErrorN = new XYChart.Series();
        this.seriesRungeKuttaErrorN = new XYChart.Series();
        for (double i = n0; i < eN; i++) {
            Euler euler = new Euler(x0, X, i, y0);
            dataEEu.add(new XYChart.Data(i, euler.getMaxError()));
            dataEIEu.add(new XYChart.Data(i, euler.getIEulerError()));
            dataERK.add(new XYChart.Data(i, euler.getRKuttaError()));
        }
        this.seriesEulerErrorN.setData(dataEEu);
        this.seriesEulerErrorN.setName("Euler Error");
        this.seriesImprovedEulerErrorN.setData(dataEIEu);
        this.seriesImprovedEulerErrorN.setName("Improved Euler Error");
        this.seriesRungeKuttaErrorN.setData(dataERK);
        this.seriesRungeKuttaErrorN.setName("Runge-Kutta Error");

    }


    public XYChart.Series getFuncSeries() {
        return seriesFunction;
    }

    public XYChart.Series getSeriesEuler() {
        return seriesEuler;
    }

    public XYChart.Series getSeriesImprovedEuler() {
        return seriesImprovedEuler;
    }

    public XYChart.Series getSeriesRungeKutta() {
        return seriesRungeKutta;
    }

    public XYChart.Series getSeriesEulerError() {
        return seriesEulerError;
    }

    public XYChart.Series getSeriesImprovedEulerError() {
        return seriesImprovedEulerError;
    }

    public XYChart.Series getSeriesRungeKuttaError() {
        return seriesRungeKuttaError;
    }

    public XYChart.Series getSeriesEulerErrorN() {
        return seriesEulerErrorN;
    }

    public XYChart.Series getSeriesImprovedEulerErrorN() {
        return seriesImprovedEulerErrorN;
    }

    public XYChart.Series getSeriesRungeKuttaErrorN() {
        return seriesRungeKuttaErrorN;
    }
}