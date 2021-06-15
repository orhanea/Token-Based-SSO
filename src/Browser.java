import java.util.ArrayList;
import java.util.Scanner;

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
