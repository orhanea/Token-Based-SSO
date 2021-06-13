package USB;

public class USBDeviceFactory {
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
