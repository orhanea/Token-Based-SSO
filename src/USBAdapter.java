import USB.USBDevice;
import java.util.List;
import java.util.Map;

//This is an adapter class
//Adaptee USBKit
//Target AuthenticationTemplate
//This uses object adapter

public class USBAdapter extends AuthenticationTemplate {

    private USBKit usbAdaptee;

    public USBAdapter (USBKit adaptee){
        usbAdaptee = adaptee;
    }

    @Override
    public void waitForInsertion() {
        usbAdaptee.waitForUSBTokenInsertion();
    }

    @Override
    public void open(String fileName) {
        usbAdaptee.openFile(fileName);
    }

    @Override
    public void close(String fileName) {
        usbAdaptee.closeFile(fileName);
    }

    @Override
    public void delete(USBDevice device) {
        usbAdaptee.deleteFile(device);
    }

    @Override
    public boolean verifyPin(USBDevice device, int pin) {
        System.out.println("pin is not required");
        return false;
    }

    @Override
    public String readAccounts(USBDevice device) {
        return usbAdaptee.readData(device);
    }

    @Override
    public void writeAccount(USBDevice device, String data) {
        usbAdaptee.writeData(device,data);
    }

    @Override
    public String encryptData(List<Map<String, String>> data) {
        return usbAdaptee.encryptData(data);
    }

    @Override
    public List<Map<String, String>> decryptData(String data) {
        return usbAdaptee.decryptData(data);
    }
}
