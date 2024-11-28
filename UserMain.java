package oop;

import java.util.Scanner;

public class UserMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserManager userManager = new UserManager();
        int choice;

        do {
            System.out.println("===== User Management Menu =====");
            System.out.println("1. Add User");
            System.out.println("2. Remove User");
            System.out.println("3. Update User");
            System.out.println("4. Search User");
            System.out.println("5. Display All Users");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Username: ");
                    String userName = scanner.nextLine();
                    System.out.print("Enter Customer Name: ");
                    String customerName = scanner.nextLine();

                    Customers newCustomer = new Customers(userName, customerName) {};
                    userManager.addUser (newCustomer);
                    break;

                case 2:
                    System.out.print("Enter User ID to Remove: ");
                    String removeUserName = scanner.nextLine();
                    userManager.removeUser(removeUserName);
                    break;

                case 3:
                    System.out.print("Enter User Name to Update: ");
                    String updateUserName = scanner.nextLine();
                    System.out.print("Enter New Username: ");
                    String newUserName = scanner.nextLine();
                    System.out.print("Enter New Customer Name: ");
                    String newCustomerName = scanner.nextLine();

                    userManager.updatedUser(updateUserName, newUserName, newCustomerName, null);
                    break;

                case 4:
                    System.out.print("Enter User ID to Search: ");
                    String searchUserName = scanner.nextLine();
                    userManager.searchUser(searchUserName);
                    break;

                case 5:
                    userManager.displayAllUser ();
                    break;

                case 6:
                    System.out.println("Exiting User Management.");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 6);

        scanner.close();
    }
}
