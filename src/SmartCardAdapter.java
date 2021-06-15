import USB.USBDevice;

import java.util.ArrayList;
import java.util.HashMap;
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
    public USBDevice waitForInsertion() {
        return scAdaptee.waitForCardInsertion();
    }

    @Override
    public void open(String fileName) {
        scAdaptee.selectFile(fileName);
    }

    @Override
    public void close(String fileName) {
        System.out.println("You do not need to close a file");
    }

    @Override
    public String readData(USBDevice device) {
        return scAdaptee.readCard(device);
    }

    @Override
    public void writeData(USBDevice device, String data) {
        scAdaptee.writeCard(device,data);
    }

    @Override
    public String encryptData(List<Account> accounts) {
        List<Map<String, String>> data = new ArrayList<>();
        for (Account a: accounts) {
            data.add(a.toMap());
        }
        return scAdaptee.encryptData(data);
    }

    @Override
    public List<Account> decryptData(String data) {
        List<Map<String, String>> rawList = scAdaptee.decryptData(data);
        List<Account> accounts = new ArrayList<>();
        for(Map<String, String> accountData: rawList) {
            accounts.add(new Account(accountData));
        }
        return accounts;
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
