package sample.nummethods;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import static java.lang.Math.exp;
import static java.lang.Math.log;
import static java.lang.Math.pow;

public class Exact {
    private XYChart.Series series;

    public Exact(double x0, double y0, double x, double n) {
        this.series = setSeries(x0, x, y0, n);
    }


    /**
     * Function for the Exact Solution
     * @param y0 - input initial conditions
     * @param x0 - input initial conditions
     * @param x - input value
     * @return
     */
    private double exactFunction(double y0, double x0, double x){
        double cons = constanta(y0, x0);
        return pow(exp(x+ cons)-1,2);
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


    /**
     * Function to set Series for Exact Solution Graph
     * @param x0 - input initial conditions
     * @param X - input initial conditions
     * @param y0 - input initial conditions
     * @param N - input initial conditions
     * @return
     */
    private XYChart.Series setSeries(double x0, double X, double y0, double N){
        double h = (X-x0)/N;
        XYChart.Series series = new XYChart.Series();

        ObservableList<XYChart.Data> datas = FXCollections.observableArrayList();
        for(double x=x0; x<=X; x+=h){
            datas.add(new XYChart.Data(x, exactFunction(y0, x0, x)));
        }
        series.setName("Exact Solution");
        series.setData(datas);
        return series;
    }

    /**
     * Method to Get Series for Exact Solution
     * @return series of data for Exact Solution
     */
        public XYChart.Series getSeries() {
        return series;
    }
}