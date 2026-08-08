package model.accounts;

import Model.Product.Product;
import Model.Request;

import java.util.ArrayList;
import java.util.List;

public class Admin extends Account {

    private static Admin instance;

    private List<Product> products;
    private List<Customer> customers;
    private List<Request> requests;

    private Admin(String email, String password, String phoneNumber, String username) {
        super(email, password, phoneNumber, username);
        products = new ArrayList<>();
        customers = new ArrayList<>();
        requests = new ArrayList<>();
    }

    public static Admin getInstance() {
        if (instance == null) {
            instance = new Admin("admin@gmail.com", "admin", "", "admin");
        }
        return instance;
    }

    public List<Product> getProducts() {
        return products;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public List<Request> getRequests() {
        return requests;
    }
}