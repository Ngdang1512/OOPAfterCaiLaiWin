package oop;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("===== Main Menu =====");
            System.out.println("1. Product Management");
            System.out.println("2. User Management");
            System.out.println("3. Invoice Management");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    ProductMain.main(args);
                    break;
                }

                case 2 -> {
                    UserMain.main(args);
                    break;
                }

                case 3 -> {
                    InvoiceDetailMain.main(args);
                    break;
                }

                case 4 -> {
                    System.out.println("Exiting the program.");
                    break;
                }

                default -> {
                    System.out.println("Invalid choice. Please try again.");
                }
            }

        } while (choice != 4);

        scanner.close();
    }
}
