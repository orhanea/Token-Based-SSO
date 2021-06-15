import java.util.ArrayList;
import java.util.List;

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

    public List<String> getAvailablePorts() {
        List<String> availablePorts = new ArrayList<>();
        for (Port port: ports) {
           if (port.isAvailable()) availablePorts.add(port.getName());
        }
        return availablePorts;
    }

    public USBDevice getDeviceOnPort(String portName) {
        Port port = getPortWithName(portName);
        if (port == null) {
            return null;
        }
        return port.getAttachedDevice();
    }

    private Port getPortWithName(String name) {
        for (Port p: ports) {
            if (p.getName().equals(name)) {
                return p;
            }
        }
        return null;
    }

    public USBDevice getDeviceOfTypeIfThereIs(String deviceType) {
        for (Port p: ports) {
            USBDevice device = p.getAttachedDevice();
            if (device != null && device.getDeviceType().equals(deviceType)) {
                return device;
            }
        }
        return null;
    }

    public USBDevice attachDeviceOnPort(String portName, String deviceType) {
        Port port = getPortWithName(portName);
        if (port == null) {
            return null;
        }
        port.attachDevice(deviceType);
        return port.getAttachedDevice();
    }
}

class Port {
    private final String name;
    private final USBDeviceFactory deviceFactory;
    private USBDevice attachedDevice;
    private boolean isAvailable;

    public Port(String name) {
        this.name = name;
        this.isAvailable = true;
        this.deviceFactory = new USBDeviceFactory();
    }

    public boolean attachDevice(String deviceType) {
        if (!this.isAvailable) return false;
        this.attachedDevice = deviceFactory.createDevice(deviceType);
        this.isAvailable = false;
        return true;
    }

    public void ejectDevice() {
        this.attachedDevice = null;
        this.isAvailable = true;
    }

    public String getName() {
        return name;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public USBDevice getAttachedDevice() {
        return attachedDevice;
    }
}
