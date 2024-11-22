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
    private String invoiceNumber;
    protected Date paymentDate;
    protected static double  totalPaymentAmount;
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

    public void generataInvoice() {
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

class InvoiceManager {
    private List<Payment_Invoice> invoices;

    public void InvoiceManager() {
        invoices = new ArrayList<>();
    }

    public void addInvoice(Payment_Invoice invoice) {
        invoices.add(invoice);
        System.out.println("Invoice added: " + invoice.getInvoiceNumber());
    }

    public void removeInvoice(String invoiceNumber) {
        for (Payment_Invoice invoice : invoices) {
            if (invoice.getInvoiceNumber().equals(invoiceNumber)) {
                invoices.remove(invoice);
                System.out.println("Invoice Removed: " + invoice.getInvoiceNumber());
                return;
            }
        }
        System.out.println("Invoice with Number " + invoiceNumber + " Not Found");
    }

    public void updateInvoice(String invoiceNumber, Date paymentDate, double totalPaymentAmount, String paymentMethod) {
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
        for (Invoice_Details invoice : invoices) {
            invoice.displayInvoice();
            System.out.println("---------------");
        }
    }
}