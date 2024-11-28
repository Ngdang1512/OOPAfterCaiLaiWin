package oop;

import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;


public class InvoiceDetailMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InvoiceManager invoiceManager = new InvoiceManager();
        int choice;

        do {
            System.out.println("===== Invoice Management Menu =====");
            System.out.println("1. Add Invoice");
            System.out.println("2. Remove Invoice");
            System.out.println("3. Update Invoice");
            System.out.println("4. Search Invoice");
            System.out.println("5. Display All Invoices");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Staff Name: ");
                    String staffName = scanner.nextLine();
                    System.out.print("Enter Customer Name: ");
                    String userName = scanner.nextLine();
                    System.out.print("Enter Invoice Number: ");
                    String invoiceNumber = scanner.nextLine();
                    System.out.print("Enter Payment Date (dd/MM/yyyy): ");
                    String paymentDateStr = scanner.nextLine();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Date paymentDate;
                    try {
                        paymentDate = sdf.parse(paymentDateStr);
                    } catch (Exception e) {
                        System.out.println("Invalid date format. Plaese use dd/MM/yyyy.");
                        continue;
                    }
                    System.out.print("Enter Total Payment Amount: ");
                    double totalPaymentAmount = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Enter Payment Method: ");
                    String paymentMethod = scanner.nextLine();

                    Payment_Invoice newInvoice = new Payment_Invoice(staffName, userName, invoiceNumber, paymentDate, totalPaymentAmount, paymentMethod);
                    invoiceManager.addInvoice(newInvoice);
                    break;

                case 2:
                    System.out.print("Enter Invoice Number to Remove: ");
                    String removeInvoiceNumber = scanner.nextLine();
                    invoiceManager.removeInvoice(removeInvoiceNumber);
                    break;

                case 3:
                    System.out.print("Enter Invoice Number to Update: ");
                    String updateInvoiceNumber = scanner.nextLine();
                    System.out.print("Enter New Payment Date (dd/MM/yyyy): ");
                    String newPaymentDateStr = scanner.nextLine();
                    SimpleDateFormat nsdf = new SimpleDateFormat("dd/MM/yyyy");
                    Date newPaymentDate;
                    try {
                        newPaymentDate = nsdf.parse(newPaymentDateStr);
                    } catch (ParseException e) {
                        System.out.println("Invalid date format. Please use dd/MM/yyyy.");
                        continue;
                    }
                    System.out.print("Enter New Total Payment Amount: ");
                    double newTotalPaymentAmount = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Enter New Payment Method: ");
                    String newPaymentMethod = scanner.nextLine();
                
                    invoiceManager.updatedInvoice(updateInvoiceNumber, newPaymentDate, newTotalPaymentAmount, newPaymentMethod);
                    break;

                case 4:
                    System.out.print("Enter Invoice Number to Search: ");
                    String searchInvoiceNumber = scanner.nextLine();
                    invoiceManager.searchInvoice(searchInvoiceNumber);
                    break;

                case 5:
                    invoiceManager.displayAllInvoice();
                    break;

                case 6:
                    System.out.println("Exiting Invoice Management.");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 6);

        scanner.close();
    }
}
