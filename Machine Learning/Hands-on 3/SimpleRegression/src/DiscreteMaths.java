import java.util.List;
//Humberto Hernández Trejo

public class DiscreteMaths {

    // Método para calcular la suma de los elementos de una lista de números decimales (Double)
    public static double sum(List<Double> values) {
        double total = 0.0;
        for (Double value : values) {
            total += value;
        }
        return total;
    }

    // Método para calcular la suma de los productos de dos listas de números decimales (Double)
    public static double sumOfProducts(List<Double> values1, List<Double> values2) {
        double sum = 0;
        // Recorre ambas listas, multiplicando los elementos correspondientes y sumando los resultados
        for (int i = 0; i < values1.size(); i++) {
            sum += values1.get(i) * values2.get(i);
        }
        return sum;
    }

    // Método para calcular la suma de los cuadrados de los elementos de una lista de números decimales (Double)
    public static double sumOfSquares(List<Double> values) {
        double total = 0.0;
        for (Double value : values) {
            total += value * value;
        }
        return total;
    }

}
