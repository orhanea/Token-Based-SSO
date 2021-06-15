import USB.USBDevice;

import java.util.ArrayList;
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
    public USBDevice waitForInsertion() {
        return usbAdaptee.waitForUSBTokenInsertion();
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
        return true;
    }

    @Override
    public String readData(USBDevice device) {
        return usbAdaptee.readData(device);
    }

    @Override
    public void writeData(USBDevice device, String data) {
        usbAdaptee.writeData(device,data);
    }

    @Override
    public String encryptData(List<Account> accounts) {
        List<Map<String, String>> data = new ArrayList<>();
        for (Account a: accounts) {
            data.add(a.toMap());
        }
        return usbAdaptee.encryptData(data);
    }

    @Override
    public List<Account> decryptData(String data) {
        List<Map<String, String>> rawList = usbAdaptee.decryptData(data);
        List<Account> accounts = new ArrayList<>();
        for(Map<String, String> accountData: rawList) {
            accounts.add(new Account(accountData));
        }
        return accounts;
    }
}
