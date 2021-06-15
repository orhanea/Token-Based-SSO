package USB;

import java.util.Map;

public class SmartCardUSB extends USBDevice {
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
