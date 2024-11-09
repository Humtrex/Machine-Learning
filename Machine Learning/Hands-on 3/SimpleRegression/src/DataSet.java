import java.util.List;
//Humberto Hernández Trejo

public class DataSet {
    // Declaración de las variables privadas x e y, que son listas de números decimales (Double)
    private List<Double> x;
    private List<Double> y;

    // Constructor que inicializa los valores de x e y cuando se crea un objeto DataSet
    public DataSet(List<Double> x, List<Double> y) {
        this.x = x;
        this.y = y;
    }

    // Método que devuelve la lista x
    public List<Double> getX() {
        return x;
    }

    // Método que devuelve la lista y
    public List<Double> getY() {
        return y;
    }
}
