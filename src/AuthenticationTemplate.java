import USB.USBDevice;

import java.util.*;

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

class KitUtils {
    public static String encryptData(List<Map<String, String>> data) {
        StringBuilder builder = new StringBuilder();

        for(Map<String, String> account: data) {
            builder.append(account.get("username"));
            builder.append(" ");
            builder.append(account.get("password"));
            builder.append("\n");
        }
        return builder.toString();
    }

    public static List<Map<String, String>> decryptData(String data) {
        List<Map<String, String>> decryptedData = new ArrayList<>();
        String[] accounts = data.split("\n");
        for (String account: accounts) {
            String[] fields = account.split(" ");
            Map<String, String> accountMap = new HashMap<>();
            accountMap.put("username", fields[0]);
            accountMap.put("password", fields[1]);
            decryptedData.add(accountMap);
        }
        return decryptedData;
    }

    public static int getValidIntOptionInput(int min, int max) {
        Scanner scanner = new Scanner(System.in);
        int selectedOption;
        boolean hasSelectedValidOption;
        do {
            selectedOption = scanner.nextInt();
            hasSelectedValidOption = selectedOption <= min || selectedOption >= max;
            if (!hasSelectedValidOption) {
                System.out.println("Please select a valid option");
            }
        } while (hasSelectedValidOption);
        return selectedOption;
    }
}
