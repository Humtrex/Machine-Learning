import java.util.*;
//Humberto Hernández Trejo

public class Main {
    public static void main(String[] args) {
        // Dataset hardcoded (Batch Size - Machine Efficiency)
        //List<Double> batchSize = Arrays.asList(1.0,2.0,3.0,4.0,5.0,6.0,7.0,8.0,9.0);
        //List<Double> efficiency = Arrays.asList(3.0,6.0,9.0,12.0,15.0,18.0,21.0,24.0,27.0);
        List<Double> batchSize = Arrays.asList(108.0, 115.0, 106.0, 97.0, 95.0, 91.0, 97.0, 83.0, 83.0, 78.0,
                54.0, 67.0, 56.0, 53.0, 61.0, 115.0, 81.0, 78.0, 30.0, 45.0,
                99.0, 32.0, 25.0, 28.0, 90.0, 89.0);
        List<Double> efficiency = Arrays.asList(95.0, 96.0, 95.0, 97.0, 93.0, 94.0, 95.0, 93.0, 92.0, 86.0,
                73.0, 80.0, 65.0, 69.0, 77.0, 96.0, 87.0, 89.0, 60.0, 63.0,
                95.0, 61.0, 55.0, 56.0, 94.0, 93.0);

        // Realizamos el proceso dos veces y seleccionamos el mejor modelo basado en el coeficiente de determinación (R²)
        Model bestModel = null;
        double bestRSquared = -1;
        // Dos segmentos para el conjunto de datos
        for (int i = 0; i < 2; i++) {
            System.out.println("Segmentación " + (i + 1) + ":");

            // Dividir el dataset en 70% - 30% de forma aleatoria
            DataSet trainSet = new DataSet(new ArrayList<>(), new ArrayList<>());
            DataSet testSet = new DataSet(new ArrayList<>(), new ArrayList<>());

            // Llama al método para dividir el conjunto de datos
            splitDataSet(batchSize, efficiency, trainSet, testSet, 0.7);

            // Crear el modelo de regresión para el conjunto de entrenamiento
            SimpleLinearRegression regression = new SimpleLinearRegression(trainSet);
            regression.calculateParameters();
            Model currentModel = regression.getModel();

            // Imprimir el modelo de regresión
            System.out.println("Línea de Regresión: " + currentModel);

            // Calcular el coeficiente de determinación para el conjunto de prueba
            double rSquared = regression.calculateRSquared(testSet.getX(), testSet.getY());
            System.out.println("Coeficiente de Determinación (R²): " + rSquared);
            System.out.println();

            // Seleccionar el mejor modelo
            if (rSquared > bestRSquared) {
                bestRSquared = rSquared;
                bestModel = currentModel;
            }
        }

        // Imprimir el mejor modelo
        System.out.println("Mejor modelo basado en R²: " + bestModel);
        System.out.println("Coeficiente de Determinación más alto (R²): " + bestRSquared);

        System.out.println("");

        // Predicciones (usando valores conocidos y desconocidos)
        double[] testBatchSizes = {30.0, 45.0, 108.0, 50.0, 75.0};
        System.out.println("Predicciones con el mejor modelo:");
        // Realiza predicciones utilizando el mejor modelo
        for (double batch : testBatchSizes) {
            double predictedEfficiency = bestModel.predict(batch);
            System.out.println("Batch Size " + batch + " -> Machine Efficiency (predicha): " + predictedEfficiency);
        }
    }

    // Método para dividir el dataset en entrenamiento y prueba
    private static void splitDataSet(List<Double> x, List<Double> y, DataSet trainSet, DataSet testSet, double trainPercentage) {
        int dataSize = x.size();
        List<Integer> indices = new ArrayList<>();// Lista para almacenar los índices de los datos
        for (int i = 0; i < dataSize; i++) {
            indices.add(i);
        }
        Collections.shuffle(indices);

        int trainSize = (int) (dataSize * trainPercentage); // Calcula el tamaño del conjunto de entrenamiento
        // Llena el conjunto de entrenamiento
        for (int i = 0; i < trainSize; i++) {
            trainSet.getX().add(x.get(indices.get(i)));
            trainSet.getY().add(y.get(indices.get(i)));
        }
        // Llena el conjunto de prueba
        for (int i = trainSize; i < dataSize; i++) {
            testSet.getX().add(x.get(indices.get(i)));
            testSet.getY().add(y.get(indices.get(i)));
        }
    }
}
