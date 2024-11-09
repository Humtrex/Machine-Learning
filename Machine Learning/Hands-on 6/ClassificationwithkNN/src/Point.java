//Humberto Hernández Trejo
public class Point {
    private double[] coordinates;  // Array que almacena las coordenadas del punto
    private int label;  // Etiqueta de la clase

    // Constructor que acepta un array de doubles para inicializar las coordenadas
    public Point(double[] coordinates, int label) {
        this.coordinates = coordinates;
        this.label = label;
    }

    // Método para obtener las coordenadas
    public double[] getCoordinates() {
        return coordinates;
    }

    // Método para obtener la etiqueta de clase
    public int getLabel() {
        return label;
    }

    // Sobrescribir el método toString() para imprimir solo las coordenadas
    @Override
    public String toString() {
        // Imprimir las coordenadas junto con la clase
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < coordinates.length; i++) {
            sb.append(coordinates[i]);
            if (i < coordinates.length - 1) sb.append(", ");
        }
        sb.append("] ");
        return sb.toString();
    }
}
