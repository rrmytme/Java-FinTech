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

    public static void createCustomer(String filePath, String customerName, String dob, String accountPreference){
        try {
            JSONObject json;
            JSONArray customers;

            // Check if file exists and read existing data
            if (Files.exists(Paths.get(filePath))) {
                String content = new String(Files.readAllBytes(Paths.get(filePath)), StandardCharsets.UTF_8);
                json = new JSONObject(content);
                customers = json.getJSONArray("Customer");
            } else {
                json = new JSONObject();
                customers = new JSONArray();
                json.put("Customer", customers);
            }

            // Create new customer entry
            JSONObject newCustomer = new JSONObject();
            newCustomer.put("name", customerName);
            newCustomer.put("dob", dob);
            newCustomer.put("accountPreference", accountPreference);

            // Add to array
            customers.put(newCustomer);

            // Write updated JSON back to file
            Files.write(Paths.get(filePath), json.toString(4).getBytes(StandardCharsets.UTF_8),
                    StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

            System.out.println("Customer data saved successfully!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void searchCustomer(String filePath, String searchName) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)), StandardCharsets.UTF_8);
            JSONObject json = new JSONObject(content);
            JSONArray customers = json.getJSONArray("Customer");

            for (int i = 0; i < customers.length(); i++) {
                JSONObject customer = customers.getJSONObject(i);
                if (customer.getString("name").equalsIgnoreCase(searchName)) {
                    System.out.println("Customer Found: " + customer.toString(4));
                    return;
                }
            }
            System.out.println("Customer not found.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateCustomer(String filePath, String searchName, String newDob, String newAccountPreference) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)), StandardCharsets.UTF_8);
            JSONObject json = new JSONObject(content);
            JSONArray customers = json.getJSONArray("Customer");

            for (int i = 0; i < customers.length(); i++) {
                JSONObject customer = customers.getJSONObject(i);
                if (customer.getString("name").equalsIgnoreCase(searchName)) {
                    customer.put("dob", newDob);
                    customer.put("accountPreference", newAccountPreference);

                    // Write updated JSON back to file
                    Files.write(Paths.get(filePath), json.toString(4).getBytes(StandardCharsets.UTF_8),
                            StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

                    System.out.println("Customer details updated successfully!");
                    return;
                }
            }
            System.out.println("Customer not found.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteCustomer(String filePath, String searchName) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)), StandardCharsets.UTF_8);
            JSONObject json = new JSONObject(content);
            JSONArray customers = json.getJSONArray("Customer");

            for (int i = 0; i < customers.length(); i++) {
                JSONObject customer = customers.getJSONObject(i);
                if (customer.getString("name").equalsIgnoreCase(searchName)) {
                    customers.remove(i);

                    // Write updated JSON back to file
                    Files.write(Paths.get(filePath), json.toString(4).getBytes(StandardCharsets.UTF_8),
                            StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

                    System.out.println("Customer deleted successfully!");
                    return;
                }
            }
            System.out.println("Customer not found.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
