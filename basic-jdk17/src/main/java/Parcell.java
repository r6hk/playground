import java.util.HashMap;

public class Parcell {

    private final HashMap<String, String> testMap = new HashMap<>();

    class Contents {
        // 返回一个外部类的引用.
        public Parcell ParcellRef = Parcell.this;
    }

    class Destination {
        public void putSomethingInMap() {
            testMap.put("hello", "world");
            System.out.println(testMap.get("hello"));
        }

    }

    public Destination to() {
        return new Destination();
    }

    public Contents contents() {
        return new Contents();
    }

    public void ship(String dest) {
        Contents c = new Contents();
        Destination d = new Destination();
    }

    public static void main(String[] args) {
        Parcell p = new Parcell();
        Parcell.Contents c = p.contents();
        Parcell.Destination d = p.to();
        d.putSomethingInMap();
        Parcell.Contents c1 = p.new Contents();
    }

}