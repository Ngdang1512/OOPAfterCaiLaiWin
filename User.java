import java.util.ArrayList;
import java.util.List;

abstract class User {
    protected  String userName;

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

class UserManager {
    private List<User> userList;

    public UserManager() {
        userList = new ArrayList<>();
    }

    public void addUser(User user) {
        userList.add(user);
        System.out.println("User added: " + user.getUserName());
    }

    public void removeUser(String userName) {
        for (User user : userList) {
            if (user.getUserName().equals(userName)) {
                userList.remove(user);
                System.out.println("User removed: " + user.getUserName());
                return;
            }
        }
        System.out.println("User with Name: " + userName + " Not Found.");
    }

    public void updatedUser(String userName, String newUserName, String newCustomerName, String newStaffName) {
        for (User user : userList) {
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
        for (User user : userList) {
            if (user.getUserName().equals(userName)) {
                System.out.println("User Founded: " + user.getUserName());
            }
        }
        System.out.println("User with Name: " + userName + " Not Found.");
    }

    public void displayAllUser() {
        for (User user : userList) {
            if (user instanceof Customers) {
                ((Customers) user).displayCustomers();
            } else if (user instanceof Staff) {
                ((Staff) user).displayStaff();
            }
            System.out.println("---------------");
        }
    }
}