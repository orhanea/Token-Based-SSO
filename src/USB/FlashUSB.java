package USB;

import java.util.Map;

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
