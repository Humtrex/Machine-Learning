import java.util.List;
//Humberto Hernández Trejo
public class Normalizer {
    // Método para normalizar los puntos con Min-Max
    public static void normalizeMinMax(List<Point> points) {
        // Encontrar los valores máximos y mínimos para cada característica
        int numFeatures = points.get(0).getCoordinates().length;
        double[] minValues = new double[numFeatures];
        double[] maxValues = new double[numFeatures];

        // Inicializamos con los valores del primer punto
        for (int i = 0; i < numFeatures; i++) {
            minValues[i] = points.get(0).getCoordinates()[i];
            maxValues[i] = points.get(0).getCoordinates()[i];
        }

        // Encontrar el valor mínimo y máximo de cada característica
        for (Point point : points) {
            for (int i = 0; i < numFeatures; i++) {
                double value = point.getCoordinates()[i];
                if (value < minValues[i]) {
                    minValues[i] = value;
                }
                if (value > maxValues[i]) {
                    maxValues[i] = value;
                }
            }
        }

        // Normalizar los puntos
        for (Point point : points) {
            double[] normalizedCoordinates = point.getCoordinates();
            for (int i = 0; i < numFeatures; i++) {
                normalizedCoordinates[i] = (point.getCoordinates()[i] - minValues[i]) /
                        (maxValues[i] - minValues[i]);
            }
        }
    }

    // Método para normalizar los puntos con Z-score
    public static void normalizeZScore(List<Point> points) {
        int numFeatures = points.get(0).getCoordinates().length;
        double[] means = new double[numFeatures];
        double[] stdDevs = new double[numFeatures];

        // Calcular la media para cada característica
        for (int i = 0; i < numFeatures; i++) {
            double sum = 0;
            for (Point point : points) {
                sum += point.getCoordinates()[i];
            }
            means[i] = sum / points.size();
        }

        // Calcular la desviación estándar para cada característica
        for (int i = 0; i < numFeatures; i++) {
            double sum = 0;
            for (Point point : points) {
                sum += (point.getCoordinates()[i] - means[i]) * (point.getCoordinates()[i] - means[i]);
            }
            stdDevs[i] = Math.sqrt(sum / points.size());
        }

        // Normalizar los puntos con Z-score
        for (Point point : points) {
            double[] normalizedCoordinates = point.getCoordinates();
            for (int i = 0; i < numFeatures; i++) {
                if (stdDevs[i] != 0) {
                    normalizedCoordinates[i] = (point.getCoordinates()[i] - means[i]) / stdDevs[i];
                }
            }
        }
    }
}


