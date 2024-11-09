import java.io.*;
import java.util.*;
//Humberto Hernández Trejo
public class DataLoader {

    public static List<Point> loadPointsFromCSV(String filePath) {
        List<Point> points = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean firstLine = true;

            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;  // Ignorar la primera línea
                    continue;
                }

                String[] values = line.split(",");  // Divide la línea por las comas
                double[] coordinates = new double[values.length - 1];  // Todas las columnas menos la última
                int label = Integer.parseInt(values[values.length - 1]);  // La última columna es la etiqueta de clase

                // Convertir las características (valores de las primeras columnas) a números
                try {
                    for (int i = 0; i < values.length - 1; i++) {
                        coordinates[i] = Double.parseDouble(values[i]);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error al convertir a número: " + values[0]);  // Muestra qué valor está fallando
                    continue;  // Ignorar este punto si ocurre un error
                }

                // Crear el punto con las coordenadas y etiqueta
                points.add(new Point(coordinates, label));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return points;
    }

}
