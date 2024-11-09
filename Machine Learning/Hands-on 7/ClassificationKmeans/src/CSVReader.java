import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
//Humberto Hernández Trejo
public class CSVReader {
    // Método para leer puntos de datos desde un archivo CSV
    public static List<Point> readDataPoints(String filePath) throws Exception {
        List<Point> points = new ArrayList<>(); // Lista para almacenar los puntos
        BufferedReader br = new BufferedReader(new FileReader(filePath));

        // Leer la primera línea y descartarla (cabecera)
        String line = br.readLine();

        // Leer las demás líneas y procesar los datos
        while ((line = br.readLine()) != null) { // Lee cada línea hasta el final del archivo
            String[] values = line.split(","); // Divide la línea en valores por comas
            List<Double> coordinates = new ArrayList<>(); // Lista para almacenar las coordenadas del punto

            // Convertir cada valor a un número y añadirlo a la lista de coordenadas
            for (String value : values) {
                coordinates.add(Double.parseDouble(value)); // Convierte a Double y añade a la lista
            }
            // Crear un nuevo punto y añadirlo a la lista de puntos
            points.add(new Point(coordinates));
        }

        br.close();
        return points; // Devuelve la lista de puntos
    }
}