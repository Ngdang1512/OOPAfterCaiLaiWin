package oop;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class User {
    protected String userName;

    public User(String userName) {
        this.userName = userName;
    }

    String getUserName() {
        return userName;
    }

    abstract void viewProduct();
}

abstract class Customers extends User {
    protected String customerName;

    public Customers(String userName, String customerName) {
        super(userName);
        this.customerName = customerName;
    }

    public void displayCustomers() {
        System.out.println("Customer Name: " + customerName);
    }

    @Override
    void viewProduct() {
        System.out.println("Customer Name: " + customerName + " is viewing products.");
    }
}

abstract class Staff extends User {
    protected String staffName;

    public Staff(String userName, String staffName) {
        super(userName);
        this.staffName = staffName;
    }

    public void displayStaff() {
        System.out.println("Staff Name: " + staffName);
    }

    @Override
    void viewProduct() {
        System.out.println("Staff Name: " + staffName + " is viewing products.");
    }

    abstract void addProduct();
    abstract void updateProduct();
    abstract void removeProduct();
    abstract void searchProduct();
}

class UserManager implements iDataManagement<User> {
    private List<User> users;

    public UserManager() {
        users = new ArrayList<>();
    }

    @SuppressWarnings("unchecked")
    public List<User> readDataFromFile(String filePath) {
        List<User> loadedUsers = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            loadedUsers = (List<User>) ois.readObject();
            System.out.println("Users successfully loaded from file.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading users from file: " + e.getMessage());
        }
        return loadedUsers;
    }

    @Override
    public void writeDataToFile(String filePath, List<User> data) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(data);
            System.out.println("Users successfully saved to file.");
        } catch (IOException e) {
            System.out.println("Error writing users to file: " + e.getMessage());
        }
    }

    public void addUser(User user) {
        users.add(user);
        System.out.println("User added: " + user.getUserName());
    }

    public void removeUser(String userName) {
        for (User user : users) {
            if (user.getUserName().equals(userName)) {
                users.remove(user);
                System.out.println("User removed: " + user.getUserName());
                return;
            }
        }
        System.out.println("User with Name: " + userName + " Not Found.");
    }

    public void updatedUser(String userName, String newUserName, String newCustomerName, String newStaffName) {
        for (User user : users) {
            if (user.getUserName().equals(userName)) {
                user.userName = newUserName;

                if (user instanceof Customers) {
                    ((Customers) user).customerName = newCustomerName;
                } else if (user instanceof Staff) {
                    ((Staff) user).staffName = newStaffName;
                }

                System.out.println("User updated: " + user.getUserName());
                return;
            }
        }
        System.out.println("User with Name: " + userName + " Not Found.");
    }

    public void searchUser(String userName) {
        for (User user : users) {
            if (user.getUserName().equals(userName)) {
                System.out.println("User Founded: " + user.getUserName());
            }
        }
        System.out.println("User with Name: " + userName + " Not Found.");
    }

    public void displayAllUser() {
        for (User user : users) {
            if (user instanceof Customers) {
                ((Customers) user).displayCustomers();
            } else if (user instanceof Staff) {
                ((Staff) user).displayStaff();
            }
            System.out.println("---------------");
        }
    }
}

public class UserMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserManager userManager = new UserManager();

        userManager.addUser(new Customers("customer1", "Alice") {});
        userManager.addUser(new Customers("customer2", "Bob") {});
        userManager.addUser(new Staff("staff1", "Charlie") {
            @Override
            public void addProduct() {}
            @Override
            public void updateProduct() {}
            @Override
            public void removeProduct() {}
            @Override
            public void searchProduct() {}
        });
        userManager.addUser(new Staff("staff2", "Diana") {
            @Override
            public void addProduct() {}
            @Override
            public void updateProduct() {}
            @Override
            public void removeProduct() {}
            @Override
            public void searchProduct() {}
        });

        int choice;

        do {
            System.out.println("===== User Management Menu =====");
            System.out.println("1. Add User");
            System.out.println("2. Remove User");
            System.out.println("3. Update User");
            System.out.println("4. Search User");
            System.out.println("5. Display All Users");
            System.out.println("6. Back to Main");
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
                    System.out.println("Returning to Main...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 6);

    }
}