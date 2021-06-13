import java.util.ArrayList;

public class PortCollection {
    private static PortCollection instance;
    static PortCollection getInstance() {
        if (instance == null) instance = new PortCollection();
        return instance;
    }

    ArrayList<Port> ports;

    private PortCollection() {
        ports = new ArrayList<>();
        ports.add(new Port("Port1"));
        ports.add(new Port("Port2"));
        ports.add(new Port("Port3"));
        ports.add(new Port("Port4"));
    }
}
