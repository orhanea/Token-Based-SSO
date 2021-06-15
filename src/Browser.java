import java.util.ArrayList;

public class Browser extends Subject {
    private String url;
    private boolean needsAuthentication;

    public Browser(ArrayList<Observer> observers) {
        super(observers);
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
    }

    public String getUrl() {
        return url;
    }
}
