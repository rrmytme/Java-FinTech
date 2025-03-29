import org.json.JSONArray;
import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;

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
}
