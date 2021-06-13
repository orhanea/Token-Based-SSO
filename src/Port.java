public class Port {
    private final String portName;
    private USBDevice attachedDevice;
    private boolean isAvailable;
    private USBDeviceFactory deviceFactory;

    public Port(String portName) {
        this.portName = portName;
        this.isAvailable = true;
        this.deviceFactory = new USBDeviceFactory();
    }

    public void attachDevice(String deviceType) {
        this.attachedDevice = deviceFactory.createDevice(deviceType);
        this.isAvailable = false;
    }

    public void ejectDevice() {
        this.attachedDevice = null;
        this.isAvailable = true;
    }

    public String getPortName() {
        return portName;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public USBDevice getAttachedDevice() {
        return attachedDevice;
    }
}
