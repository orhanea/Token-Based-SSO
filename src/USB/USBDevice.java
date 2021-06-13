package USB;

import java.util.Map;

/**
 * @author  anıl
 * @author  cankurtaran
 * @author  sahin
 * @author  erdoğan
 * @project TokenBasedSSO
 */

public abstract class USBDevice {
    public static final String FLASH_USB_TYPE = "FlashUsb";
    public static final String SMART_CARD_TYPE = "SmartCard";

    public String path;
    protected String deviceType;
    protected boolean isPinVerified;
    protected int pin;
    protected Map<String, String> contents;

    public Map<String, String> getContents() {
        return contents;
    }

    public void updateFileContent(String fileName, String data) {
        contents.put(fileName, data);
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public abstract boolean verifyPin(int pin);

}

