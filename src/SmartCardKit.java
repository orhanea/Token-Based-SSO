import USB.USBDevice;
import utils.UserInputUtils;

import java.util.ArrayList;
import java.util.HashMap;
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
            int selectedOption = UserInputUtils.getValidIntOptionInput(1, ports.size());
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
        List<Map<String, String>> decryptedData = new ArrayList<>();
        String[] accounts = data.split("\n");
        for (String account: accounts) {
            String[] fields = account.split(" ");
            Map<String, String> accountMap = new HashMap<>();
            accountMap.put("username", fields[0]);
            accountMap.put("password", fields[1]);
            decryptedData.add(accountMap);
        }
        return decryptedData;
    }

    public String encryptData(List<Map<String, String>> data) {
        StringBuilder builder = new StringBuilder();

        for(Map<String, String> account: data) {
            builder.append(account.get("username"));
            builder.append(" ");
            builder.append(account.get("password"));
            builder.append("\n");
        }
        return builder.toString();
    }

    public boolean verifyPin(USBDevice device, int pin) {
        return device.verifyPin(pin);
    }

    public void deleteFile(USBDevice device) {
        device.getContents().remove(selectedFile);
    }
}
