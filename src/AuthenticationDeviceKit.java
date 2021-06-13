import USB.USBDevice;

import java.util.List;
import java.util.Map;

/**
 * @author  anıl
 * @author  cankurtaran
 * @author  sahin
 * @author  erdoğan
 * @project TokenBasedSSO
 */
public interface AuthenticationDeviceKit {

    Account[] accounts = new Account[3];

    void waitForInsertion();

    void open(String fileName);

    void close(String fileName);

    String readAccounts(USBDevice device);

    void writeAccount(USBDevice device, String data);

    String encryptData(List<Map<String, String>> data);

    List<Map<String, String>> decryptData(String data);

    void delete(USBDevice device);

    boolean verifyPin(USBDevice device, int pin);

}
