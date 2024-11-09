import java.util.List;
//Humberto Hernández Trejo
public class Main {
    public static void main(String[] args) {
        String filePath = "G:\\7mo semestre\\Aprendizaje auto\\glass.csv";

        // Cargar los puntos desde el archivo utilizando el método loadPointsFromCSV de DataLoader
        List<Point> points = DataLoader.loadPointsFromCSV(filePath);

        // Verificar si los puntos fueron cargados correctamente
        if (points.isEmpty()) {
            System.out.println("No se pudieron cargar los puntos desde el archivo.");
            return; // Terminar el programa si no hay puntos cargados
        }

        // Normalizar los puntos con Min-Max o Z-score
        Normalizer.normalizeMinMax(points);  // Min-Max
        //Normalizer.normalizeZScore(points);  // Z-Score

        // Crear una instancia de KNN con el número deseado de vecinos (k) y los puntos cargados
        KnnClassifier knn = new KnnClassifier(7, points);

        // Iterar sobre todos los puntos cargados desde el archivo
        for (Point testPoint : points) {
            int predictedClass = knn.classify(testPoint);
            System.out.println("El punto con coordenadas " + testPoint + " pertenece a la clase: " + predictedClass);
        }
    }
}







