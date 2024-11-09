import java.util.ArrayList;
import java.util.List;
//Humberto Hernández Trejo
public class KMeans {
    private int k; // Número de clústeres
    private List<Point> dataPoints; // Lista de puntos de datos
    private List<Cluster> clusters; // Lista de clústeres

    public KMeans(int k, List<Point> dataPoints) {
        this.k = k;
        this.dataPoints = dataPoints;
        this.clusters = new ArrayList<>();
    }

    // Inicializar los centroides aleatorios
    private void initializeCentroids() {
        for (int i = 0; i < k; i++) {
            clusters.add(new Cluster(dataPoints.get(i))); // Inicializa con los primeros k puntos
        }
    }

    // Asignar cada punto al clúster más cercano
    private void assignPointsToClusters() {
        for (Point point : dataPoints) { // Itera sobre cada punto de datos
            Cluster closestCluster = clusters.get(0); // Inicializa el clúster más cercano
            double minDistance = point.distanceTo(closestCluster.getCentroid()); // Distancia inicial

            for (Cluster cluster : clusters) { // Compara con cada clúster
                double distance = point.distanceTo(cluster.getCentroid()); // Calcula la distancia al centroide
                if (distance < minDistance) { // Si es más corta que la mínima conocida
                    minDistance = distance; // Actualiza la mínima
                    closestCluster = cluster; // Actualiza el clúster más cercano
                }
            }
            closestCluster.addPoint(point); // Agrega el punto al clúster más cercano
        }
    }


    // Ejecutar el algoritmo KMeans
    public void run() {
        initializeCentroids();
        boolean centroidsChanged = true;
        int maxIterations = 100;
        int iterations = 0;

        while (centroidsChanged && iterations < maxIterations) {
            clusters.forEach(Cluster::clearPoints);
            assignPointsToClusters();
            centroidsChanged = false;

            for (Cluster cluster : clusters) {
                Point oldCentroid = cluster.getCentroid();
                cluster.updateCentroid();
                if (!oldCentroid.equals(cluster.getCentroid())) {
                    centroidsChanged = true;
                }
            }
            iterations++;
        }
    }

    // Calcular el Within-Cluster Sum of Squares (WCSS)
    public double calculateWCSS() {
        double wcss = 0.0;
        for (Cluster cluster : clusters) {
            for (Point point : cluster.getPoints()) {
                wcss += point.distanceTo(cluster.getCentroid()) * point.distanceTo(cluster.getCentroid());
            }
        }
        return wcss;
    }

    // Obtener los clústeres
    public List<Cluster> getClusters() {
        return clusters;
    }
}

