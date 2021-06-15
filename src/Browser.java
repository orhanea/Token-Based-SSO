import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author  anıl
 * @author  cankurtaran
 * @author  sahin
 * @author  erdoğan
 * @project TokenBasedSSO
 */

interface Observer {
    void update(boolean needsAuthentication);
}

abstract class Subject {
    protected ArrayList<Observer> observers;

    public Subject(){
        this.observers = new ArrayList<>();
    }

    void addObserver(Observer observer){
        observers.add(observer);
    }

    void removeObserver(Observer observer){
        for (int i = 0; i< observers.size(); i++) {
            if (observers.get(i) == observer) {
                observers.remove(i);
                return;
            }
        }
    }

    public abstract void notifyObservers();
}

public class Browser extends Subject {
    private String url;
    private boolean needsAuthentication;

    public Browser() {
        super();
        needsAuthentication = false;
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this.needsAuthentication);
        }
    }

    void openUrl(String url) {
        this.url = url;
        System.out.println("Opening " + url);
        System.out.println("Enter username and password");
        needsAuthentication = true;
        notifyObservers();
    }

    public void signIn(Account account) {
        System.out.println("Address: " + url);
        System.out.println("Username: " + account.getUserName());
        System.out.println("Password: " + account.getPassword());
        System.out.println("Successfully signed in!");
        needsAuthentication = false;
        notifyObservers();
    }

    public String getUrl() {
        return url;
    }

    public static void main(String[] args) {
        Browser browser = new Browser();
        LoginHandler loginHandler = new LoginHandler(browser);
        browser.addObserver(loginHandler);
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("=======================");
            System.out.println("Enter url to open");
            System.out.println("Type exit to terminate");
            String url = scanner.nextLine();
            if (url.equals("exit")) {
                break;
            }
            browser.openUrl(url);
        }
    }
}

class LoginHandler implements Observer {
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
        AuthenticationTemplate template = factory.createTemplate(deviceType);
        return template.findsAccountsOnUrl(browser.getUrl());
    }

    private void signInWithAccountOnBrowser(Account account){
        browser.signIn(account);
    }
}
