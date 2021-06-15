import USB.USBDevice;

import java.util.List;

/**
 * @author  anıl
 * @author  cankurtaran
 * @author  sahin
 * @author  erdoğan
 * @project TokenBasedSSO
 */
public interface AuthenticationDeviceKit {

    USBDevice waitForInsertion();

    void open(String fileName);

    void close(String fileName);

    String readData(USBDevice device);

    void writeData(USBDevice device, String data);

    String encryptData(List<Account> accounts);

    List<Account> decryptData(String data);

    void delete(USBDevice device);

    boolean verifyPin(USBDevice device, int pin);

}
