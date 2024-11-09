import java.util.*;
//Humberto Hernández Trejo

public class Main {
    public static void main(String[] args) {
        // Dataset hardcoded (Batch Size - Machine Efficiency)
        List<Double> batchSize = Arrays.asList(108.0, 115.0, 106.0, 97.0, 95.0, 91.0, 97.0, 83.0, 83.0, 78.0,
                54.0, 67.0, 56.0, 53.0, 61.0, 115.0, 81.0, 78.0, 30.0, 45.0,
                99.0, 32.0, 25.0, 28.0, 90.0, 89.0);

        List<Double> efficiency = Arrays.asList(95.0, 96.0, 95.0, 97.0, 93.0, 94.0, 95.0, 93.0, 92.0, 86.0,
                73.0, 80.0, 65.0, 69.0, 77.0, 96.0, 87.0, 89.0, 60.0, 63.0,
                95.0, 61.0, 55.0, 56.0, 94.0, 93.0);

        // Realizamos el proceso dos veces y seleccionamos el mejor modelo basado en el coeficiente de determinación (R²)
        Model bestModel = null;
        double bestRSquared = -1;

        for (int i = 0; i < 2; i++) { // Realiza dos segmentaciones
            System.out.println("Segmentación " + (i + 1) + ":");

            // Inicializar conjuntos de entrenamiento y prueba
            DataSet trainSet = new DataSet(new ArrayList<>(), new ArrayList<>());
            DataSet testSet = new DataSet(new ArrayList<>(), new ArrayList<>());

            // Llamar al método para dividir el dataset en conjuntos de entrenamiento y prueba
            splitDataSet(batchSize, efficiency, trainSet, testSet, 0.7);

            // Crear el modelo cúbico usando el conjunto de entrenamiento
            LeastSquaredRegressor regression = new LeastSquaredRegressor(trainSet, 3);
            regression.calculateParameters(); // Calcular parámetros del modelo
            Model currentModel = regression.getModel(); // Obtener el modelo actual

            // Imprimir la curva de regresión
            System.out.println("\nCurva de Regresión: " + currentModel);

            // Calcular el coeficiente de determinación (R²) usando el conjunto de prueba
            double rSquared = regression.calculateRSquared(testSet.getX(), testSet.getY());
            System.out.println("Coeficiente de Determinación (R²): " + rSquared);
            System.out.println();

            // Si el coeficiente de determinación actual es mejor que el mejor conocido, actualizar
            if (rSquared > bestRSquared) {
                bestRSquared = rSquared;
                bestModel = currentModel;
            }
        }

        // Imprimir el mejor modelo y su coeficiente de determinación más alto
        System.out.println("Mejor modelo basado en R²: " + bestModel);
        System.out.println("Coeficiente de Determinación más alto (R²): " + bestRSquared);

        // Calcular e imprimir la correlación entre Batch Size y Machine Efficiency
        double correlationValue = DiscreteMaths.correlation(batchSize, efficiency);
        System.out.println("Correlación: " + correlationValue);
        System.out.println("");

        // Definir un array de Batch Sizes para realizar predicciones
        double[] testBatchSizes = {30.0, 45.0, 108.0, 50.0, 75.0};
        System.out.println("Predicciones con el mejor modelo:");

        // Realizar predicciones para los Batch Sizes definidos
        for (double batch : testBatchSizes) {
            double predictedEfficiency = bestModel.predict(batch); // Predecir eficiencia
            System.out.println("Batch Size " + batch + " -> Machine Efficiency (predicha): " + predictedEfficiency);
        }
    }

    // Método para dividir el dataset en conjuntos de entrenamiento y prueba
    private static void splitDataSet(List<Double> x, List<Double> y, DataSet trainSet, DataSet testSet, double trainPercentage) {
        int dataSize = x.size();
        List<Integer> indices = new ArrayList<>();

        // Agregar índices del dataset a la lista
        for (int i = 0; i < dataSize; i++) {
            indices.add(i);
        }
        Collections.shuffle(indices); // Barajar índices para aleatorizar la selección

        int trainSize = (int) (dataSize * trainPercentage); // Tamaño del conjunto de entrenamiento

        // Llenar el conjunto de entrenamiento
        for (int i = 0; i < trainSize; i++) {
            trainSet.getX().add(x.get(indices.get(i))); // Agregar valores de x
            trainSet.getY().add(y.get(indices.get(i))); // Agregar valores de y
        }

        // Llenar el conjunto de prueba
        for (int i = trainSize; i < dataSize; i++) {
            testSet.getX().add(x.get(indices.get(i)));
            testSet.getY().add(y.get(indices.get(i)));
        }
    }
}
