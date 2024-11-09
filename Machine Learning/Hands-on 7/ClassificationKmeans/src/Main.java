import java.util.*;
//Humberto Hernández Trejo
public class Main {
    public static void main(String[] args) {
        try {
            String filePath = "G:\\7mo semestre\\Aprendizaje auto\\xclara.csv";

            // Cargar los puntos de datos desde el archivo CSV
            List<Point> dataPoints = CSVReader.readDataPoints(filePath);

            // Ejecutar el Elbow method para determinar el número óptimo de clústeres
            elbowMethod(dataPoints);

            // Definir el número de clústeres
            int k = 3;

            // Inicializar el objeto KMeans y ejecutar el algoritmo
            KMeans kMeans = new KMeans(k, dataPoints);
            kMeans.run();

            // Imprimir los resultados de los clústeres
            int clusterIndex = 1; // Contador para identificar el índice del clúster
            for (Cluster cluster : kMeans.getClusters()) {
                System.out.println("\nCluster " + clusterIndex); // Imprimir el número de clúster actual
                for (Point point : cluster.getPoints()) {
                    System.out.println("Punto: " + point.getCoordinates() + " pertenece al clúster " + clusterIndex);
                }
                // Imprimir el centroide del clúster
                System.out.println("Centroide: " + cluster.getCentroid().getCoordinates());
                clusterIndex++; // Incrementa el índice del clúster después de imprimir todos los puntos en el clúster actual
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método Elbow para determinar el número óptimo de clústeres
    public static void elbowMethod(List<Point> dataPoints) {
        List<Double> wcssValues = new ArrayList<>();

        for (int k = 1; k <= 10; k++) {
            KMeans kMeans = new KMeans(k, dataPoints);
            kMeans.run();
            double wcss = kMeans.calculateWCSS();
            wcssValues.add(wcss);
        }

        // Imprimir WCSS para cada valor de k
        for (int i = 0; i < wcssValues.size(); i++) {
            System.out.println("k = " + (i + 1) + " WCSS = " + wcssValues.get(i));
        }
    }
}

