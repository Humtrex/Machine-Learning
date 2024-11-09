import java.util.List;
//Humberto Hernández Trejo

public class DataSet {
    private List<Double> x;
    private List<Double> y;

    public DataSet(List<Double> x, List<Double> y) {
        this.x = x;
        this.y = y;
    }

    public List<Double> getX() {
        return x;
    }

    public List<Double> getY() {
        return y;
    }

}

