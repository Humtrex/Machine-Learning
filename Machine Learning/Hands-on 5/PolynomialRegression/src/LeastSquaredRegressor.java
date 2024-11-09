import java.util.*;
//Humberto Hernández Trejo
public class LeastSquaredRegressor {
    private Model model;
    private double[][] X; // Matriz de diseño X, que contiene las características del dataset
    private double[] Y; // Vector Y, que contiene los valores objetivo del dataset

    // Constructor que inicializa el modelo y crea la matriz de diseño X y el vector Y
    public LeastSquaredRegressor(DataSet dataSet, int degree) {
        this.model = new Model();
        this.X = createDesignMatrix(dataSet.getX(), degree); // Crea la matriz de diseño
        this.Y = dataSet.getY().stream().mapToDouble(Double::doubleValue).toArray(); // Convierte la lista Y a un array
    }

    // Método para calcular los parámetros del modelo usando la ecuación normal
    public void calculateParameters() {
        // Calcula (X'X) y (X'Y) para la solución de la ecuación normal
        double[][] XtX = DiscreteMaths.multiplyMatrices(DiscreteMaths.transpose(X), X); // Transpuesta de X multiplicada por X
        double[] XtY = DiscreteMaths.multiplyMatrixAndVector(DiscreteMaths.transpose(X), Y); // Transpuesta de X multiplicada por Y
        double[] betas = DiscreteMaths.solveNormalEquation(XtX, XtY); // Resuelve la ecuación normal

        // Asigna los valores de Beta al modelo (coeficientes)
        model.setBeta_0(betas[0]); // Intercepto
        model.setBeta_1(betas[1]); // Coeficiente para x^1
        model.setBeta_2(betas.length > 2 ? betas[2] : 0); // Coeficiente para x^2, 0 si no existe
        model.setBeta_3(betas.length > 3 ? betas[3] : 0); // Coeficiente para x^3, 0 si no existe
    }

    // Método que devuelve el modelo calculado
    public Model getModel() {
        return model;
    }

    // Método para calcular el Coeficiente de Determinación (R²)
    public double calculateRSquared(List<Double> testX, List<Double> testY) {
        double ssTot = 0, ssRes = 0; // Inicializa la suma de cuadrados total y residual
        double yMean = testY.stream().mapToDouble(Double::doubleValue).average().orElse(0.0); // Calcula la media de Y

        // Calcula los valores de R²
        for (int i = 0; i < testX.size(); i++) {
            double predictedY = model.predict(testX.get(i)); // Predice Y usando el modelo
            ssRes += Math.pow(testY.get(i) - predictedY, 2); // Suma de cuadrados residual
            ssTot += Math.pow(testY.get(i) - yMean, 2); // Suma de cuadrados total
        }

        return 1 - (ssRes / ssTot); // Calcula y devuelve R²
    }

    // Método para crear la matriz de diseño X (polinomio de grado n)
    private double[][] createDesignMatrix(List<Double> x, int degree) {
        double[][] matrix = new double[x.size()][degree + 1]; // Inicializa la matriz con el tamaño adecuado
        for (int i = 0; i < x.size(); i++) {
            for (int j = 0; j <= degree; j++) {
                matrix[i][j] = Math.pow(x.get(i), j); // Llena la matriz con potencias de x
            }
        }
        return matrix;
    }
}
