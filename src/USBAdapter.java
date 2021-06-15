import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//This is an adapter class
//Adaptee USBKit
//Target AuthenticationTemplate
//This uses object adapter
class USBAdapter extends AuthenticationTemplate {

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

class USBKit {
    private String openedFile;

    public USBDevice waitForUSBTokenInsertion() {
        PortCollection collection = PortCollection.getInstance();
        USBDevice pluggedInDevice = collection.getDeviceOfTypeIfThereIs(USBDevice.FLASH_USB_TYPE);
        if (pluggedInDevice == null) {
            List<String> ports = collection.getAvailablePorts();
            System.out.println("Select Port to attach USB Token");
            int counter = 1;
            for (String p: ports) {
                System.out.println(counter++ + " - " + p);
            }
            int selectedOption = KitUtils.getValidIntOptionInput(1, ports.size());
            pluggedInDevice = collection.attachDeviceOnPort(ports.get(selectedOption - 1), USBDevice.FLASH_USB_TYPE);
        }
        return pluggedInDevice;
    }

    public void openFile(String fileName) {
        this.openedFile = fileName;
    }

    public void closeFile(String fileName) {
        this.openedFile = null;
    }

    public String readData(USBDevice device) {
        return device.getContents().get(openedFile);
    }

    public void writeData(USBDevice device, String data) {
        device.updateFileContent(openedFile, data);
    }

    public String encryptData(List<Map<String, String>> data) {
        return KitUtils.encryptData(data);
    }

    public List<Map<String, String>> decryptData(String data) {
        return KitUtils.decryptData(data);
    }

    public void deleteFile(USBDevice device) {
        device.getContents().remove(openedFile);
    }
}

//This is an adapter class
//Adaptee SmartCardKit
//Target AuthenticationTemplate
//This uses object adapter
class SmartCardAdapter extends AuthenticationTemplate {
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
    public void close(String fileName) {}

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

class SmartCardKit {
    private String selectedFile;

    public USBDevice waitForCardInsertion() {
        PortCollection collection = PortCollection.getInstance();
        USBDevice pluggedInDevice = collection.getDeviceOfTypeIfThereIs(USBDevice.SMART_CARD_TYPE);
        if (pluggedInDevice == null) {
            List<String> ports = collection.getAvailablePorts();
            System.out.println("Select Port to attach Smart Card");
            int counter = 1;
            for (String p: ports) {
                System.out.println(counter++ + " - " + p);
            }
            int selectedOption = KitUtils.getValidIntOptionInput(1, ports.size());
            pluggedInDevice = collection.attachDeviceOnPort(ports.get(selectedOption - 1), USBDevice.SMART_CARD_TYPE);
        }
        return pluggedInDevice;
    }

    public void selectFile(String fileName) {
        selectedFile = fileName;
    }

    public String readCard(USBDevice device) {
        return device.getContents().get(selectedFile);
    }

    public void writeCard(USBDevice device, String data) {
        device.updateFileContent(selectedFile, data);
    }

    public List<Map<String, String>> decryptData(String data) {
        return KitUtils.decryptData(data);
    }

    public String encryptData(List<Map<String, String>> data) {
        return KitUtils.encryptData(data);
    }

    public boolean verifyPin(USBDevice device, int pin) {
        return device.verifyPin(pin);
    }

    public void deleteFile(USBDevice device) {
        device.getContents().remove(selectedFile);
    }
}
