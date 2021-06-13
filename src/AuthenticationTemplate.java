import USB.USBDevice;

/**
 * @author  anıl
 * @author  cankurtaran
 * @author  sahin
 * @author  erdoğan
 * @project TokenBasedSSO
 */
public abstract class AuthenticationTemplate implements AuthenticationDeviceKit {

    private USBDevice connectedDevice;

    public Account[] findsAccountsOnUrl(String url){
       return accounts;
    }
    public void saveAccountsOnUrl(String url, Account[] accounts){

    }
    public void deleteAccountsOnUrl(String url){

    }
}
