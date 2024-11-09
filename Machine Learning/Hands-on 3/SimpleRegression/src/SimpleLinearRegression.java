import java.util.List;
//Humberto Hernández Trejo

public class SimpleLinearRegression {
    private DataSet dataset;
    private Model model;

    public SimpleLinearRegression(DataSet dataset) {
        this.dataset = dataset;
        this.model = new Model();
    }

    public void calculateParameters() {
        double xSum = DiscreteMaths.sum(dataset.getX());
        double ySum = DiscreteMaths.sum(dataset.getY());
        double xySum = DiscreteMaths.sumOfProducts(dataset.getX(), dataset.getY());// X*Y
        double xSquaredSum = DiscreteMaths.sumOfSquares(dataset.getX());// X2
        int n = dataset.getX().size();

        // Cálculo de los coeficientes beta1 y beta0 usando las fórmulas de mínimos cuadrados
        double beta1 = (n * xySum - xSum * ySum) / (n * xSquaredSum - xSum * xSum);
        double beta0 = (ySum - beta1 * xSum) / n;

        // Almacena los coeficientes en el modelo
        model.setBeta_0(beta0);
        model.setBeta_1(beta1);
    }

    public Model getModel() {
        return model;
    }

    // Método para calcular el coeficiente de determinación (R²) dado un conjunto de prueba (x, y)
    public double calculateRSquared(List<Double> x, List<Double> y) {
        double beta_0 = model.getBeta_0();
        double beta_1 = model.getBeta_1();

        double ssTot = 0.0; // Suma de los cuadrados totales
        double ssRes = 0.0; // Suma de los cuadrados residuales
        double meanY = DiscreteMaths.sum(y) / y.size(); // Calcula la media de Y

        // Itera sobre los valores de x para predecir y calcular los errores
        for (int i = 0; i < x.size(); i++) {
            double predictedY = beta_0 + beta_1 * x.get(i);// Predicción del valor de y
            ssRes += Math.pow(y.get(i) - predictedY, 2); // Error residual (diferencia entre y real y y predicha)
            ssTot += Math.pow(y.get(i) - meanY, 2);// Diferencia con respecto a la media de y
        }
        // Devuelve el coeficiente de determinación R²
        return 1 - (ssRes / ssTot);
    }

}

