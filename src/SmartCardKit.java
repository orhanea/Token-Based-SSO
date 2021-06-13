import USB.USBDevice;

import java.util.List;
import java.util.Map;

public class SmartCardKit {
    private String selectedFile;

    public USBDevice waitForCardInsertion() {
        PortCollection collection = PortCollection.getInstance();
        USBDevice pluggedInDevice = collection.getDeviceOfTypeIfThereIs(USBDevice.SMART_CARD_TYPE);
        if (pluggedInDevice == null) {
            List<String> ports = collection.getAvailablePorts();
            System.out.println("Select Port to attach Smart Card");
            int counter = 1;
            for (String p: ports) {
                System.out.println(counter++ + p);
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
