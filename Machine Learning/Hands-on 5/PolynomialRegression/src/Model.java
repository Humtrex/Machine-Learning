//Humberto Hernández Trejo
public class Model {
    private double beta_0;
    private double beta_1;
    private double beta_2;
    private double beta_3;

    public void setBeta_0(double beta_0) {
        this.beta_0 = beta_0;
    }

    public void setBeta_1(double beta_1) {
        this.beta_1 = beta_1;
    }

    public void setBeta_2(double beta_2) {
        this.beta_2 = beta_2;
    }

    public void setBeta_3(double beta_3) {
        this.beta_3 = beta_3;
    }

    public double getBeta_0() {
        return beta_0;
    }

    public double getBeta_1() {
        return beta_1;
    }

    public double getBeta_2() {
        return beta_2;
    }

    public double getBeta_3() {
        return beta_3;
    }

    // Predicción para el modelo cúbico
    public double predict(double x) {
        return beta_0 + beta_1 * x + beta_2 * x * x + beta_3 * x * x * x;
    }

    @Override
    public String toString() {
        return "y = " + beta_0 + " + " + beta_1 + " * x + " + beta_2 + " * x^2 + " + beta_3 + " * x^3";
    }
}
