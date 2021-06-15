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

    public List<Account> findsAccountsOnUrl(String url) throws VerifyError {
       connectedDevice = waitForInsertion();
       askForPinAndVerifyIfNeeded();
       open(url);
       String accountsData = readData(connectedDevice);
       List<Account> accounts = decryptData(accountsData);
       close(url);
       return accounts;
    }
    public void saveAccountsOnUrl(String url, List<Account> accounts) throws VerifyError {
        connectedDevice = waitForInsertion();
        askForPinAndVerifyIfNeeded();
        open(url);
        String encryptedData = encryptData(accounts);
        writeData(connectedDevice, encryptedData);
        close(url);
    }
    public void deleteAccountsOnUrl(String url) throws VerifyError {
        connectedDevice = waitForInsertion();
        askForPinAndVerifyIfNeeded();
        open(url);
        delete(connectedDevice);
        close(url);
    }

    private void askForPinAndVerifyIfNeeded() throws VerifyError {
        if (connectedDevice.isPinVerified()) {
            return;
        }
        int tries = 0;
        while (tries < 3) {
            System.out.println("Enter your pin: ");
            Scanner scanner = new Scanner(System.in);
            int pin = scanner.nextInt();
            boolean result = verifyPin(connectedDevice, pin);
            if (result) {
                return;
            } else {
                tries++;
                System.out.println("Pin is wrong, try again!");
            }
        }
        throw new VerifyError();
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
            System.out.println(account);
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
        } while (!hasSelectedValidOption);
        return selectedOption;
    }
}
