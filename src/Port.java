import USB.USBDevice;
import USB.USBDeviceFactory;

public class Port {
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
