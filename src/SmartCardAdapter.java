import USB.USBDevice;
import java.util.List;
import java.util.Map;

//This is an adapter class
//Adaptee SmartCardKit
//Target AuthenticationTemplate
//This uses object adapter

public class SmartCardAdapter extends AuthenticationTemplate {
    private SmartCardKit scAdaptee;

    public SmartCardAdapter(SmartCardKit adaptee) {
        scAdaptee = adaptee;
    }

    @Override
    public void waitForInsertion() {
        scAdaptee.waitForCardInsertion();
    }

    @Override
    public void open(String fileName) {
        System.out.println("You do not need to open a file");
    }

    @Override
    public void close(String fileName) {
        System.out.println("You do not need to close a file");
    }

    @Override
    public String readAccounts(USBDevice device) {
        return scAdaptee.readCard(device);
    }

    @Override
    public void writeAccount(USBDevice device, String data) {
        scAdaptee.writeCard(device,data);
    }

    @Override
    public String encryptData(List<Map<String, String>> data) {
        return scAdaptee.encryptData(data);
    }

    @Override
    public List<Map<String, String>> decryptData(String data) {
        return scAdaptee.decryptData(data);
    }

    @Override
    public void delete(USBDevice device) {
        scAdaptee.deleteFile(device);
    }

    @Override
    public boolean verifyPin(USBDevice device, int pin) {
        return scAdaptee.verifyPin(device,pin);
    }
}
