import java.util.List;
//Humberto Hernández Trejo
public class Point {
    private List<Double> coordinates; // Coordenadas del punto

    public Point(List<Double> coordinates) {
        this.coordinates = coordinates;
    }

    public List<Double> getCoordinates() {
        return coordinates;
    }

    // Método para calcular la distancia euclidiana entre dos puntos
    public double distanceTo(Point other) {
        double sum = 0.0;
        for (int i = 0; i < coordinates.size(); i++) {
            sum += (coordinates.get(i) - other.coordinates.get(i)) * (coordinates.get(i) - other.coordinates.get(i));
        }
        return sum == 0 ? 0 : sum * 0.5;
    }

    //Verificar si este punto (this) es igual a otro objeto (obj)
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Point point = (Point) obj;
        return coordinates.equals(point.coordinates);
    }
}
