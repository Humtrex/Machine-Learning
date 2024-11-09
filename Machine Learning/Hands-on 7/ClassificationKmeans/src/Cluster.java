import java.util.ArrayList;
import java.util.List;
//Humberto Hernández Trejo
public class Cluster {
    private List<Point> points; // Puntos que pertenecen a este clúster
    private Point centroid; // Centroide del clúster

    public Cluster(Point initialCentroid) {
        this.centroid = initialCentroid;
        this.points = new ArrayList<>();
    }

    public Point getCentroid() {
        return centroid;
    }

    public List<Point> getPoints() {
        return points;
    }

    // Método para agregar un punto al clúster
    public void addPoint(Point point) {
        points.add(point);
    }

    // Método para limpiar los puntos del clúster
    public void clearPoints() {
        points.clear();
    }

    // Método para actualizar el centroide del clúster
    public void updateCentroid() {
        List<Double> newCoordinates = new ArrayList<>();
        int numPoints = points.size();
        for (int i = 0; i < centroid.getCoordinates().size(); i++) {
            double sum = 0.0;
            for (Point point : points) {
                sum += point.getCoordinates().get(i);
            }
            newCoordinates.add(sum / numPoints);
        }
        centroid = new Point(newCoordinates);
    }
}
