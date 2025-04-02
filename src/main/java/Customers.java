import java.util.Scanner;

public class Customers {
    public static void customerManager() {
        Scanner scanner = new Scanner(System.in);
        String filePath = "customersDB.json";

        while (true) {
            System.out.println("\n=== Customer Management ===");
            System.out.println("1. Add Customer");
            System.out.println("2. Search Customer");
            System.out.println("3. Update Customer");
            System.out.println("4. Delete Customer");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter DOB (dd/MM/yyyy): ");
                    String dob = scanner.nextLine();
                    System.out.print("Enter Account Type (Checking/Savings): ");
                    String accountType = scanner.nextLine();
                    Utils.createCustomer(filePath, name, dob, accountType);
                    break;

                case 2:
                    System.out.print("Enter Name to Search: ");
                    String searchName = scanner.nextLine();
                    Utils.searchCustomer(filePath, searchName);
                    break;

                case 3:
                    System.out.print("Enter Name to Update: ");
                    String updateName = scanner.nextLine();
                    System.out.print("Enter New DOB (dd/MM/yyyy): ");
                    String newDob = scanner.nextLine();
                    System.out.print("Enter New Account Type (Checking/Savings): ");
                    String newAccountType = scanner.nextLine();
                    Utils.updateCustomer(filePath, updateName, newDob, newAccountType);
                    break;

                case 4:
                    System.out.print("Enter Name to Delete: ");
                    String deleteName = scanner.nextLine();
                    Utils.deleteCustomer(filePath, deleteName);
                    break;

                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
