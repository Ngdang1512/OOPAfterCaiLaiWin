package oop;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class Invoice_Details {
    private String staffName;
    private String userName;

    public Invoice_Details(String staffName, String userName) {
        this.staffName = staffName;
        this.userName = userName;
    }

    public void displayInvoice() {
        System.out.println("Staff Name: " + staffName);
        System.out.println("User Name: " + userName);
    }
}

class Payment_Invoice extends Invoice_Details {
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

    @Override
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
