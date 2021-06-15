package USB;

import java.util.Map;

public class SmartCardUSB extends USBDevice {
    private static Map<String, String> encryptedData = Map.of(
            "google.com", "aasdfa1234",
            "facebook.com", "asdffas2ldkg"
    );

    public SmartCardUSB() {
        this.path = "/home/smartCard";
        this.contents = encryptedData;
        this.deviceType = SMART_CARD_TYPE;
        this.isPinVerified = false;
        this.pin = 2234;
    }

    @Override
    public boolean verifyPin(int pin) {
        isPinVerified = this.pin == pin;
        return isPinVerified;
    }
}
