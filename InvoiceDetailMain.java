package oop;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Scanner;

class InvoiceDetailsMain {
    private String staffName;
    private String userName;

    public InvoiceDetailsMain(String staffName, String userName) {
        this.staffName = staffName;
        this.userName = userName;
    }

    public void displayInvoice() {
        System.out.println("Staff Name: " + staffName);
        System.out.println("User Name: " + userName);
    }
}

class Payment_Invoice extends InvoiceDetailsMain {
    protected String invoiceNumber;
    protected Date paymentDate;
    protected static double totalPaymentAmount;
    protected String paymentMethod;

    public Payment_Invoice(String staffName, String userName, String invoiceNumber, Date paymentDate, double totalPaymentAmount, String paymentMethod) {
        super(staffName, userName);
        this.invoiceNumber = invoiceNumber;
        this.paymentDate = paymentDate;
        Payment_Invoice.totalPaymentAmount = totalPaymentAmount;
        this.paymentMethod = paymentMethod;
    }

    String getInvoiceNumber() {
        return invoiceNumber;
    }

    void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public void generateInvoice() {
        System.out.println("Invoice Number: " + invoiceNumber);
        System.out.println("Payment Date: " + paymentDate);
        System.out.println("Total Payment Amount: " + totalPaymentAmount);
        System.out.println("Payment Method: " + paymentMethod);
    }

    public void sendInvoice() {
        System.out.println("Sending Invoice...");
        System.out.println("Invoice Number: " + invoiceNumber);
        System.out.println("Payment Date: " + paymentDate);
        System.out.println("Total Payment Amount: " + totalPaymentAmount);
        System.out.println("Payment Method: " + paymentMethod);
    }

    public static double getTotalPaymentAmount() {
        return totalPaymentAmount;
    }

    @Override
    public void displayInvoice() {
        super.displayInvoice();
        System.out.println("Invoice Number: " + invoiceNumber);
        System.out.println("Payment Date: " + paymentDate);
        System.out.println("Total Payment Amount: " + totalPaymentAmount);
        System.out.println("Payment Method: " + paymentMethod);
    }
}

class InvoiceManager implements iDataManagement<Payment_Invoice> {
    private List<Payment_Invoice> invoices;

    public InvoiceManager() {
        invoices = new ArrayList<>();
    }

    @SuppressWarnings("unchecked")
    public List<Payment_Invoice> readDataFromFile(String filePath) {
        List<Payment_Invoice> loadedInvoices = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            loadedInvoices = (List<Payment_Invoice>) ois.readObject();
            System.out.println("Invoice successfully loaded from file.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading invoices from file: " + e.getMessage());
        }
        return loadedInvoices;
    }

    @Override
    public void writeDataToFile(String filePath, List<Payment_Invoice> data) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(data);
            System.out.println("Invoice successfully saved to file.");
        } catch (IOException e) {
            System.out.println("Error writing invoices to file: " + e.getMessage());
        }
    }

    public void addInvoice(Payment_Invoice invoice) {
        invoices.add(invoice);
        System.out.println("Invoice added: " + invoice.getInvoiceNumber());
    }

    public void removeInvoice(String invoiceNumber) {
        for (int i = 0; i < invoices.size(); i++) {
            if (invoices.get(i).getInvoiceNumber().equals(invoiceNumber)) {
                System.out.println("Invoice Removed: " + invoices.get(i).getInvoiceNumber());
                invoices.remove(i);
                return;
            }
        }
        System.out.println("Invoice with Number " + invoiceNumber + " Not Found");
    }

    public void updatedInvoice(String invoiceNumber, Date paymentDate, double totalPaymentAmount, String paymentMethod) {
        for (Payment_Invoice invoice : invoices) {
            if (invoice.getInvoiceNumber().equals(invoiceNumber)) {
                invoice.setInvoiceNumber(invoiceNumber);
                invoice.paymentDate = paymentDate;
                Payment_Invoice.totalPaymentAmount = totalPaymentAmount;
                invoice.paymentMethod = paymentMethod;
                System.out.println("Invoice updated: " + invoice.getInvoiceNumber());
                return;
            }
        }
        System.out.println("Invoice with Number " + invoiceNumber + " Not Found.");
    }

    public void searchInvoice(String invoiceNumber) {
        for (Payment_Invoice invoice : invoices) {
            if (invoice.getInvoiceNumber().equals(invoiceNumber)) {
                System.out.println("Invoice Founded: " + invoice.getInvoiceNumber());
                return;
            }
        }
        System.out.println("Invoice with Number: " + invoiceNumber + " Not Found");
    }

    public void displayAllInvoice() {
        for (Payment_Invoice invoice : invoices) {
            invoice.displayInvoice();
            System.out.println("---------------");
        }
    }
}

public class InvoiceDetailMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InvoiceManager invoiceManager = new InvoiceManager();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            invoiceManager.addInvoice(new Payment_Invoice(
                    "John Doe",
                    "Alice Smith",
                    "INV001",
                    sdf.parse("15/11/2024"),
                    500.0,
                    "Credit Card"
            ));
            invoiceManager.addInvoice(new Payment_Invoice(
                    "Jane Roe",
                    "Bob Brown",
                    "INV002",
                    sdf.parse("16/11/2024"),
                    300.0,
                    "Cash"
            ));
            invoiceManager.addInvoice(new Payment_Invoice(
                    "John Doe",
                    "Charlie Green",
                    "INV003",
                    sdf.parse("17/11/2024"),
                    450.0,
                    "Bank Transfer"
            ));
        } catch (ParseException e) {
            System.out.println("Error parsing default dates.");
        }

        int choice;

        do {
            System.out.println("===== Invoice Management Menu =====");
            System.out.println("1. Add Invoice");
            System.out.println("2. Remove Invoice");
            System.out.println("3. Update Invoice");
            System.out.println("4. Search Invoice");
            System.out.println("5. Display All Invoices");
            System.out.println("6. Back to Main");
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
                    Date newPaymentDate;
                    try {
                        newPaymentDate = sdf.parse(newPaymentDateStr);
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
                    System.out.println("Returning to Main.");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 6);

    }
}