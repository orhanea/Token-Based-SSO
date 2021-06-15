import java.util.HashMap;
import java.util.Map;

/**
 * @author  anıl
 * @author  cankurtaran
 * @author  sahin
 * @author  erdoğan
 * @project TokenBasedSSO
 */
public class Account {

    private String userName;
    private String password;
    private String URL;

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Account(Map<String, String> data) {
        this.userName = data.get("username");
        this.password = data.get("password");
    }

    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();
        map.put("username", this.userName);
        map.put("password", this.password);
        return map;
    }

}
