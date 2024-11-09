import java.util.List;
//Humberto Hernández Trejo
public class DataSet {
    private List<Double> X;
    private List<Double> Y;

    public DataSet(List<Double> X, List<Double> Y) {
        this.X = X;
        this.Y = Y;
    }

    public List<Double> getX() {
        return X;
    }

    public List<Double> getY() {
        return Y;
    }
}

