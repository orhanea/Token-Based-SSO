public class AuthenticationKitFactory {
    public AuthenticationTemplate createKit(String deviceType){

        if(deviceType=="FlashUSB"){
            USBDeviceFactory usbDeviceFactory= new USBDeviceFactory();
            USBDevice flashUSB= usbDeviceFactory.createDevice("FlashUSB");
            USBKit usbKit = new USBKit();
            usbKit.setConnectedUSB(flashUSB);
            USBAdapter usbAdapter = new USBAdapter(usbKit);
            return usbAdapter;


        }
        else if(deviceType=="SmartCardUSB"){
            USBDeviceFactory usbDeviceFactory= new USBDeviceFactory();
            USBDevice smartCardUSB= usbDeviceFactory.createDevice("SmartCardUSB");
            SmartCardKit smartCardKit = new SmartCardKit();
            smartCardKit.setConnectedCard(smartCardUSB);
            SmartCardAdapter smartCardAdapter = new SmartCardAdapter(smartCardKit);
            return smartCardAdapter;


        }

    }
}
