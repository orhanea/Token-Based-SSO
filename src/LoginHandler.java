import USB.USBDevice;

import java.util.List;

public class LoginHandler implements Observer {
    private final AuthenticationTemplateFactory factory;
    private final Browser browser;

    public LoginHandler(Browser browser) {
        this.browser = browser;
        browser.addObserver(this);
        factory = new AuthenticationTemplateFactory();
    }

    @Override
    public void update(boolean needsAuthentication) {
        if (!needsAuthentication) {
            return;
        }
        String deviceType = selectDeviceType();
        System.out.println("Selected device: " + deviceType);
        List<Account> accounts = getAccountsOnDevice(deviceType);
        Account selectedAccount = selectOneOfTheAccounts(accounts);
        signInWithAccountOnBrowser(selectedAccount);
    }

    private Account selectOneOfTheAccounts(List<Account> accounts) {
        System.out.println("==================");
        System.out.println("Select one of the accounts");
        for (int i = 1; i <= accounts.size(); i++) {
            Account account = accounts.get(i-1);
            System.out.println(i + " - " + account.getUserName());
        }
        int selectedOption = KitUtils.getValidIntOptionInput(1, accounts.size());
        return accounts.get(selectedOption - 1);
    }

    private String selectDeviceType(){
        System.out.println("Select one of the devices: ");
        System.out.println("1 - Flash USB Token");
        System.out.println("2 - Smart Card USB");
        int selected = KitUtils.getValidIntOptionInput(1, 2);
        return selected == 1 ? USBDevice.FLASH_USB_TYPE : USBDevice.SMART_CARD_TYPE;
    }

    private List<Account> getAccountsOnDevice(String deviceType) {
        AuthenticationTemplate template = factory.createKit(deviceType);
        return template.findsAccountsOnUrl(browser.getUrl());
    }

    private void signInWithAccountOnBrowser(Account account){
        browser.signIn(account);
    }
}
