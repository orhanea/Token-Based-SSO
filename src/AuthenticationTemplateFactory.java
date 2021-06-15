import USB.USBDevice;

public class AuthenticationTemplateFactory {
    public AuthenticationTemplate createKit(String deviceType){

        if(deviceType.equals(USBDevice.FLASH_USB_TYPE)){
            return new USBAdapter(new USBKit());
        }

        if(deviceType.equals(USBDevice.SMART_CARD_TYPE)) {
            return new SmartCardAdapter(new SmartCardKit());
        }

        return null;
    }
}
