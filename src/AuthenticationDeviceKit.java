/**
 * @author  anıl
 * @author  cankurtaran
 * @author  sahin
 * @author  erdoğan
 * @project TokenBasedSSO
 */
public interface AuthenticationDeviceKit {

    Account[] accounts = new Account[3];

    static void waitForInsertion() {
        System.out.println("Waiting for device insertion...");
    }
    static void open(String fileName) {
        System.out.println("Opening Device...");
    }
    static void close(String fileName) {
        System.out.println("Closing Device...");
    }
    static Account[] readAccounts(String fileName) {
        System.out.println("Reading account data information's...");
        return accounts;
    }
    static void writeAccount(String fileName, Account[] accounts) {
       System.out.println("Writing account data information's...");
    }
    static String encryptData(Account[] data){
        System.out.println("Encrypting data of the device... ");
        return "data";
    }
    static Account[] decryptData(String data){
        System.out.println("Decrypting data of the device... ");
        return accounts;
    }
    static void delete(String fileName){
        System.out.println("Deleting data of device...");
    }
    static boolean verifyPin(int pin){
        System.out.println("Verifying PIN, please wait...");
        return true;
    }

}
