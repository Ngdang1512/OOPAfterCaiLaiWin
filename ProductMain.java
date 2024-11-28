package oop;

import java.util.Scanner;

public class ProductMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProductManager productManager = new ProductManager();
        int choice;

        do {
            System.out.println("===== Product Management Menu =====");
            System.out.println("1. Add Product");
            System.out.println("2. Remove Product");
            System.out.println("3. Update Product");
            System.out.println("4. Search Product");
            System.out.println("5. Display All Products");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Product ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Product Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Product Price: ");
                    double price = scanner.nextDouble();
                    System.out.print("Enter Product Quantity: ");
                    int quantity = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Product Brand: ");
                    String brand = scanner.nextLine();

                    Product newProduct = new Product(id, name, price, quantity, brand);
                    productManager.addProduct(newProduct);
                    break;

                case 2:
                    System.out.print("Enter Product ID to Remove: ");
                    int removeId = scanner.nextInt();
                    productManager.removeProduct(removeId);
                    break;

                case 3:
                    System.out.print("Enter Product ID to Update: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter New Product Name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter New Product Price: ");
                    double newPrice = scanner.nextDouble();
                    System.out.print("Enter New Product Quantity: ");
                    int newQuantity = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter New Product Brand: ");
                    String newBrand = scanner.nextLine();
                    
                    productManager.updatedProduct(updateId, newName, newPrice, newQuantity, newBrand);
                    break;

                case 4:
                    System.out.print("Enter Product ID to Search: ");
                    int searchId = scanner.nextInt();
                    productManager.searchProduct(searchId);
                    break;

                case 5:
                    productManager.displayAllProducts();
                    break;

                case 6:
                    System.out.println("Exiting Product Management.");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 6);

        scanner.close();
    }
}
