import USB.USBDevice;

import java.util.List;
import java.util.Map;

public class USBKit {
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
