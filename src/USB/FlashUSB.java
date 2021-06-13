package USB;

import java.util.Map;

class FlashUSB extends USBDevice {
    private static Map<String, String> encryptedData = Map.of(
            "google.com", ""
    );

    public FlashUSB() {
        this.path = "/home/flash";
        this.contents = encryptedData;
        this.isPinVerified = true;
        this.deviceType = FLASH_USB_TYPE;
    }
}
