import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

public class Browser extends Subject{
    private String url;
    private boolean needsAuthentication;
    public Browser(ArrayList<Observer> observers) {
        super(observers);
        needsAuthentication=true;
    }
    void openUrl(String url) throws IOException {
        Desktop desktop = java.awt.Desktop.getDesktop();
        try {
            URI oURL = new URI(url);
            needsAuthentication=false;
            desktop.browse(oURL);

        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }
    public void signIn(Account account){


    }




}
