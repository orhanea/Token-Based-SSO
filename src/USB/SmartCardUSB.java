package USB;

import java.util.Map;

public class SmartCardUSB extends USBDevice {
    private static Map<String, String> encryptedData = Map.of(
            "google.com", ""
    );

    public SmartCardUSB() {
        this.path = "/home/smartCard";
        this.contents = encryptedData;
        this.deviceType = SMART_CARD_TYPE;
        this.isPinVerified = false;
        this.pin = 2234;
    }
}
