import java.util.List;
//Humberto Hernández Trejo

public class DiscreteMaths {

    // Método para multiplicar dos matrices
    public static double[][] multiplyMatrices(double[][] firstMatrix, double[][] secondMatrix) {
        // Obtenemos el número de filas y columnas de las matrices
        int r1 = firstMatrix.length;
        int c1 = firstMatrix[0].length;
        int c2 = secondMatrix[0].length;
        double[][] result = new double[r1][c2];

        // Iteramos filas de la primera matriz
        for (int i = 0; i < r1; i++) {
            // Iteramos columnas de la segunda matriz
            for (int j = 0; j < c2; j++) {
                result[i][j] = 0;
                // Realizamos la multiplicación de matrices
                for (int k = 0; k < c1; k++) {
                    result[i][j] += firstMatrix[i][k] * secondMatrix[k][j];
                }
            }
        }

        return result;
    }

    // Método para multiplicar una matriz por un vector
    public static double[] multiplyMatrixAndVector(double[][] matrix, double[] vector) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        double[] result = new double[rows]; // Vector resultante

        // Iteramos filas de la matriz
        for (int i = 0; i < rows; i++) {
            result[i] = 0;
            // Realizamos la multiplicación
            for (int j = 0; j < cols; j++) {
                result[i] += matrix[i][j] * vector[j];
            }
        }

        return result;
    }

    // Método para transponer una matriz
    public static double[][] transpose(double[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        double[][] transposedMatrix = new double[cols][rows]; // Matriz transpuesta

        // Iteramos a través de cada elemento de la matriz original
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                transposedMatrix[j][i] = matrix[i][j]; // Intercambiamos filas y columnas
            }
        }

        return transposedMatrix;
    }

    // Método para resolver un sistema de ecuaciones mediante el método de Gauss-Jordan
    public static double[] solveNormalEquation(double[][] A, double[] B) {
        int n = A.length; // Número de ecuaciones

        // Aumentamos la matriz A con el vector B
        double[][] augmentedMatrix = new double[n][n + 1];
        for (int i = 0; i < n; i++) {
            System.arraycopy(A[i], 0, augmentedMatrix[i], 0, n);
            augmentedMatrix[i][n] = B[i]; // Agregamos el vector B como última columna
        }

        // Aplicar eliminación Gaussiana
        for (int i = 0; i < n; i++) {
            // Hacer el pivote principal igual a 1
            double pivot = augmentedMatrix[i][i];
            for (int j = 0; j <= n; j++) {
                augmentedMatrix[i][j] /= pivot; // Normalizamos la fila
            }

            // Hacer 0 los elementos debajo y arriba del pivote
            for (int k = 0; k < n; k++) {
                if (k != i) {
                    double factor = augmentedMatrix[k][i];
                    for (int j = 0; j <= n; j++) {
                        augmentedMatrix[k][j] -= factor * augmentedMatrix[i][j]; // Eliminación
                    }
                }
            }
        }

        // Extraer la solución del sistema
        double[] solution = new double[n];
        for (int i = 0; i < n; i++) {
            solution[i] = augmentedMatrix[i][n]; // Última columna contiene las soluciones
        }

        return solution; // Retornamos la solución del sistema de ecuaciones
    }

    // Método para calcular la correlación entre dos listas de datos
    public static double correlation(List<Double> X, List<Double> Y) {
        int n = X.size(); // Número de elementos en las listas
        double sumX = 0, sumY = 0, sumXY = 0, sumXSquare = 0, sumYSquare = 0;

        // Calculamos las sumas necesarias para el cálculo de la correlación
        for (int i = 0; i < n; i++) {
            sumX += X.get(i);
            sumY += Y.get(i);
            sumXY += X.get(i) * Y.get(i);
            sumXSquare += X.get(i) * X.get(i); // Suma de X^2
            sumYSquare += Y.get(i) * Y.get(i); // Suma de Y^2
        }

        // Calculamos el numerador y el denominador de la fórmula de correlación
        double numerator = (n * sumXY) - (sumX * sumY);
        double denominator = Math.sqrt(((n * sumXSquare) - (sumX * sumX)) * ((n * sumYSquare) - (sumY * sumY)));

        return numerator / denominator; // Retornamos el coeficiente de correlación
    }
}
