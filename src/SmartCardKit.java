import USB.SmartCardUSB;
import USB.USBDevice;
import utils.UserInputUtils;

import java.util.List;
import java.util.Scanner;

public class SmartCardKit {
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
    }
}
