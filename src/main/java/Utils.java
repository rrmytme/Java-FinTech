import org.json.JSONArray;
import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.charset.StandardCharsets;
import java.io.IOException;

public class Utils {
    public static boolean validateAccount(String filePath, int accountNumber, int pin) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            JSONObject json = new JSONObject(content);
            JSONArray accounts = json.getJSONArray("Accounts");

            for (int i = 0; i < accounts.length(); i++) {
                JSONObject account = accounts.getJSONObject(i);
                if (account.getInt("accountNumber") == accountNumber &&
                        account.getInt("pin") == pin) {
                    return true; // Match found
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false; // No match found
    }

    public static int getAccountBalance(String filePath, int accountNumber, int pin) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            JSONObject json = new JSONObject(content);
            JSONArray accounts = json.getJSONArray("Accounts");

            for (int i = 0; i < accounts.length(); i++) {
                JSONObject account = accounts.getJSONObject(i);
                if (account.getInt("accountNumber") == accountNumber &&
                        account.getInt("pin") == pin) {
                    return account.getInt("balance");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void withdraw(String filePath, int accountNumber, int pin, double amount) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)), StandardCharsets.UTF_8);
            JSONObject json = new JSONObject(content);
            JSONArray accounts = json.getJSONArray("Accounts");

            for (int i = 0; i < accounts.length(); i++) {
                JSONObject account = accounts.getJSONObject(i);

                if (account.getInt("accountNumber") == accountNumber &&
                        account.getInt("pin") == pin) {

                    int currentBalance = account.getInt("balance");

                    if (amount > currentBalance) {
                        System.out.println("Insufficient funds.");
                    } else {
                        double recentBalance = currentBalance - amount;
                        System.out.println("Your account balance after withdrawal: "+ recentBalance);
                        account.put("balance", recentBalance);
                        // Write updated JSON back to file
                        Files.write(Paths.get(filePath), json.toString(4).getBytes(StandardCharsets.UTF_8),
                                StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deposit(String filePath, int accountNumber, int pin, double amount) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)), StandardCharsets.UTF_8);
            JSONObject json = new JSONObject(content);
            JSONArray accounts = json.getJSONArray("Accounts");

            for (int i = 0; i < accounts.length(); i++) {
                JSONObject account = accounts.getJSONObject(i);

                if (account.getInt("accountNumber") == accountNumber &&
                        account.getInt("pin") == pin) {

                    int currentBalance = account.getInt("balance");

                    if (amount <= 0) {
                        System.out.println("Please enter valid amount.");
                    } else {
                        double recentBalance = currentBalance + amount;
                        System.out.println("Your account balance after deposit: "+ recentBalance);
                        account.put("balance", recentBalance);
                        // Write updated JSON back to file
                        Files.write(Paths.get(filePath), json.toString(4).getBytes(StandardCharsets.UTF_8),
                                StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
