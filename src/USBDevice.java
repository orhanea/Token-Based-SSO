import java.util.Map;

public abstract class USBDevice {
    public static final String FLASH_USB_TYPE = "FlashUsb";
    public static final String SMART_CARD_TYPE = "SmartCard";

    public String path;
    protected String deviceType;
    protected boolean isPinVerified;
    protected int pin;
    protected Map<String, String> contents;

    public Map<String, String> getContents() {
        return contents;
    }

    public void updateFileContent(String fileName, String data) {
        contents.put(fileName, data);
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public boolean isPinVerified() {
        return this.isPinVerified;
    }

    public abstract boolean verifyPin(int pin);
}

class USBDeviceFactory {
    public USBDevice createDevice(String deviceType) {
        switch(deviceType) {
            case USBDevice.FLASH_USB_TYPE:
                return new FlashUSB();
            case USBDevice.SMART_CARD_TYPE:
                return new SmartCardUSB();
            default:
                return null;
        }
    }
}

class SmartCardUSB extends USBDevice {
    private static Map<String, String> encryptedData = Map.of(
            "google.com",
            "example@gmail.com 1234asdflkj\n" +
                    "example2@gmail.com 1tg83jslfd",
            "facebook.com",
            "sasdf@gmail.com asdf1223\n" +
                    "asdf20@gmail.com 5823asdf"
    );

    public SmartCardUSB() {
        this.path = "/home/smartCard";
        this.contents = encryptedData;
        this.deviceType = SMART_CARD_TYPE;
        this.isPinVerified = false;
        this.pin = 0000;
    }

    @Override
    public boolean verifyPin(int pin) {
        isPinVerified = this.pin == pin;
        return isPinVerified;
    }
}

class FlashUSB extends USBDevice {
    private static Map<String, String> encryptedData = Map.of(
            "google.com",
            "example@gmail.com 1234asdflkj\n" +
                    "example2@gmail.com 1tg83jslfd",
            "facebook.com",
            "sasdf@gmail.com asdf1223\n" +
                    "asdf20@gmail.com 5823asdf"
    );

    public FlashUSB() {
        this.path = "/home/flash";
        this.contents = encryptedData;
        this.isPinVerified = true;
        this.deviceType = FLASH_USB_TYPE;
    }

    @Override
    public boolean verifyPin(int pin) {
        return true;
    }
}
