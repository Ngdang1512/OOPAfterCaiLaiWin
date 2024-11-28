package oop;

import java.io.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Product {
    private int id;
    private String name;
    private double price;
    private int quantity;
    private  String brand;

    public Product(int id, String name, double price, int quantity, String brand) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.brand = brand;
    }

    int getId() {
        return id;
    }

    String getName() {
        return name;
    }

    double getPrice() {
        return price;
    }

    int getQuantity() {
        return quantity;
    }

    String getBrand() {
        return brand;
    }

    void setId(int id) {
        this.id = id;
    }

    void setName(String name) {
        this.name = name;
    }

    void setPrice(double price) {
        this.price = price;
    }

    void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    void setBrand(String brand) {
        this.brand = brand;
    }

    public void displayInfo() {
        System.out.println("Product ID: " + id);
        System.out.println("Product Name: " + name);
        System.out.println("Product Price: " + price);
        System.out.println("Product Quantity: " + quantity);
        System.out.println("Product Brand: " + brand);
    }
}

class Racket extends Product {
    private String specifications;

    public Racket(int id, String name, double price, int quantity, String brand, String specifications) {
        super(id, name, price, quantity, brand);
        this.specifications = specifications;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Product Specification: " + specifications);
    }
}

class Shoes extends Product {
    private int sizeShoes;

    public Shoes(int id, String name, double price, int quantity, String brand, int sizeShoes) {
        super(id, name, price, quantity, brand);
        this.sizeShoes = sizeShoes;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Size of Shoes: " + sizeShoes);
    }
}

class Clothing extends Product {
    private int sizeClothing;
    private String gender;

    public Clothing(int id, String name, double price, int quantity, String brand, int sizeClothing, String gender) {
        super(id, name, price, quantity, brand);
        this.sizeClothing = sizeClothing;
        this.gender = gender;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Size of Clothing: " + sizeClothing);
        System.out.println("Gender of Clothing: " + gender);
    }
}

class ProductManager implements iDataManagement<Product> {
    private List<Product> products;

    public ProductManager() {
        products = new ArrayList<>();
    }

    @Override
    public List<Product> readDataFromFile(String filePath) {
        List<Product> loadedProducts = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            loadedProducts = (List<Product>) ois.readObject();
            System.out.println("Products successfully loaded from file.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading products from file: " + e.getMessage());
        }
        return loadedProducts;
    }

    @Override
    public void writeDataToFile(String filePath, List<Product> data) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(data);
            System.out.println("Products successfully written to file.");
        } catch (IOException e) {
            System.out.println("Error writing products to file: " + e.getMessage());
        }
    }

    public void addProduct(Product product) {
        products.add(product);
        System.out.println("Product added: " + product.getName());
    }

    public void removeProduct(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                products.remove(product);
                System.out.println("Product Remove: " + product.getName());
                return;
            }
        }
        System.out.println("Product with ID: " + id + " Not Found.");
    }

    public void updatedProduct(int id, String name, double price, int quantity, String brand) {
        for (Product product : products) {
            if (product.getId() == id) {
                product.setName(name);
                product.setPrice(price);
                product.setQuantity(quantity);
                product.setBrand(brand);
                System.out.println("Product Updated: " + product.getName());
                return;
            }
        }
        System.out.println("Product with ID: " + id + " Not Found.");
    }

    public void searchProduct(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                System.out.println("Product Founded: " + product.getName());
                return;
            }
        }
        System.out.println("Product with ID: " + id + " Not Found");
    }

    public void displayAllProducts() {
        for (Product product : products) {
            product.displayInfo();
            System.out.println("---------------");
        }
    }
}