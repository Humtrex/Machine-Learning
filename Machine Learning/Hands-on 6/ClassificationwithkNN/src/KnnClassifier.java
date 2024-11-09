import java.util.*;
// Humberto Hernández Trejo
public class KnnClassifier {
    private int k; // Número de vecinos
    private List<Point> trainingData; // Datos de entrenamiento

    // Constructor de la clase KNN
    public KnnClassifier(int k, List<Point> trainingData) {
        this.k = k;
        this.trainingData = trainingData;
    }

    // Método para calcular la distancia entre dos puntos
    private double calculateDistance(Point p1, Point p2) {
        double sum = 0;
        for (int i = 0; i < p1.getCoordinates().length; i++) {
            sum += (p1.getCoordinates()[i] - p2.getCoordinates()[i]) * (p1.getCoordinates()[i] - p2.getCoordinates()[i]);
        }
        return Math.sqrt(sum);
    }

    // Método para clasificar un nuevo punto
    public int classify(Point newPoint) {
        List<Neighbor> neighbors = findNearestNeighbors(newPoint);

        // Contar la frecuencia de cada clase en los k vecinos más cercanos
        Map<Integer, Integer> classCount = new HashMap<>();
        for (Neighbor neighbor : neighbors) {
            int label = neighbor.getLabel();
            classCount.put(label, classCount.getOrDefault(label, 0) + 1);
        }

        // Encontrar la clase con la frecuencia más alta
        int mostFrequentClass = -1;
        int maxCount = 0;
        for (Map.Entry<Integer, Integer> entry : classCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mostFrequentClass = entry.getKey();
            }
        }

        return mostFrequentClass;
    }

    // Método para encontrar los k vecinos más cercanos
    private List<Neighbor> findNearestNeighbors(Point newPoint) {
        List<Neighbor> neighbors = new ArrayList<>();

        // Calcula la distancia de todos los puntos de entrenamiento y agrega los vecinos
        for (Point point : trainingData) {
            double distance = calculateDistance(point, newPoint);
            neighbors.add(new Neighbor(distance, point.getLabel()));
        }

        // Ordenar los vecinos por distancia
        neighbors.sort((n1, n2) -> Double.compare(n1.getDistance(), n2.getDistance()));

        // Retornar solo los k vecinos más cercanos
        return neighbors.subList(0, k);
    }

    // Clase interna para representar un vecino con su distancia
    private static class Neighbor {
        private double distance;
        private int label;

        public Neighbor(double distance, int label) {
            this.distance = distance;
            this.label = label;
        }

        public double getDistance() {
            return distance;
        }

        public int getLabel() {
            return label;
        }
    }
}
