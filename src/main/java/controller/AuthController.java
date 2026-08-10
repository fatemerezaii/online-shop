package controller;

import controller.SessionManager;
import model.accounts.Admin;
import model.accounts.Customer;
import model.Request;
import enums.RequestType;

public class AuthController {

    public String signUp(String username, String email, String phoneNumber, String password) {
        if (username.isBlank() || email.isBlank() || phoneNumber.isBlank() || password.isBlank()) {
            return "Fill all fields!";
        }

        if (!phoneNumber.matches("^09\\d{9}$")) {
            return "Invalid phone number!";
        }

        if (!email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            return "Invalid email!";
        }

        if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&]).{8,}$")) {
            return "Weak password!";
        }

        if (usernameExists(username)) {
            return "Username already exists!";
        }

        if (emailExists(email)) {
            return "Email already exists!";
        }

        if (phoneExists(phoneNumber)) {
            return "Phone number already exists!";
        }
        Customer customer = new Customer(email, password, phoneNumber, username);
        Request request = new Request(customer, RequestType.REGISTER, customer);
        Admin admin = Admin.getInstance();
        admin.getRequests().add(request);
        login(username, password);
        return "Registration request sent successfully.";
    }

    public String login(String username, String password) {
        if (username == null || password == null || username.isBlank() || password.isBlank()) {
            return "Fill all fields!";
        }
        if (username.equals("Admin")) {
            if (password.equals("Admin")) {
                SessionManager.login(Admin.getInstance());
                return "Admin logged in";
            } else {
                return "Wrong password";
            }
        }
        for (Customer customer : Admin.getInstance().getCustomers()) {
            if (customer.getUsername().equals(username)) {
                if (!customer.getPassword().equals(password)) {
                    return "Wrong password!";
                }
                System.out.println("Customer found: " + customer);
                System.out.println("Customer username: " + customer.getUsername());
                SessionManager.login(customer);
                System.out.println("Session after login: " + SessionManager.getCurrentCustomer());
                return "Successfully logged in";
            }
        }
        return "Username doesn't exist";
    }

    public String editPersonalInfo(String newEmail, String newPhoneNumber, String newPassword) {
        Customer currentCustomer = SessionManager.getCurrentCustomer();
        if (currentCustomer == null) {
            return "No customer is logged in!";
        }
        if (newEmail == null || newEmail.isBlank() || newPhoneNumber == null || newPhoneNumber.isBlank() || newPassword == null || newPassword.isBlank()) {
            return "Fill all fields!";
        }
        if (!newPhoneNumber.matches("^09\\d{9}$")) {
            return "Invalid phone number!";
        }
        if (!newEmail.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            return "Invalid email!";
        }
        if (!newPassword.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&]).{8,}$")) {
            return "Weak password!";
        }
        for (Customer customer : Admin.getInstance().getCustomers()) {
            if (customer != currentCustomer && customer.getEmail().equals(newEmail)) {
                return "Email already exists!";
            }
        }
        for (Customer customer : Admin.getInstance().getCustomers()) {
            if (customer != currentCustomer && customer.getPhoneNumber().equals(newPhoneNumber)) {
                return "Phone number already exists!";
            }
        }
        currentCustomer.setEmail(newEmail);
        currentCustomer.setPhoneNumber(newPhoneNumber);
        currentCustomer.setPassword(newPassword);
        return "Personal information updated successfully.";
    }

    private boolean usernameExists(String username) {
        for (Customer customer : Admin.getInstance().getCustomers()) {
            if (customer.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    private boolean emailExists(String email) {
        for (Customer customer : Admin.getInstance().getCustomers()) {
            if (customer.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    private boolean phoneExists(String phoneNumber) {
        for (Customer customer : Admin.getInstance().getCustomers()) {
            if (customer.getPhoneNumber().equals(phoneNumber)) {
                return true;
            }
        }
        return false;
    }
}
