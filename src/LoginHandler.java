public class LoginHandler implements Observer {
    private Browser browser;
    @Override
    public void update(Subject subject) {
        System.out.println("Notified "+subject);
    }

    public String selectDeviceType(){


    }
    public void getAccountsOnDevice(USBDevice device,Account[] accounts){
        //burada device taki account lara direkt ulaşım sağlayamadım
    }
    public void signInWithAccountOnBrowser(Account account){
        browser.signIn(account);

    }
}
