package controller;

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
        Admin admin = Admin.getInstance();
        admin.getCustomers().add(customer);
        Request request = new Request(customer, RequestType.REGISTER, customer);
        admin.getRequests().add(request);
        return "Registration request sent successfully.";
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

    public String login(String username, String password) {
        if (username.equals("Admin")) {
            if (password.equals("Admin")) {
                return "Admin logged in";
            } else {
                return "Wrong password";
            }
        }
        if (!usernameExists(username)) {
            return "Username doesn't exist";
        }
        for (Customer customer : Admin.getInstance().getCustomers()) {
            if (customer.getUsername().equals(username)) {
                if (!customer.getPassword().equals(password)) {
                    return "Wrong Password!";
                }
            }
        }
        return "Successfully logged in";
    }

    public String editPersonalInfo(String newUsername, String newEmail, String newPhoneNumber, String newPassword) {

        if (newUsername.isBlank() || newEmail.isBlank() || newPhoneNumber.isBlank() || newPassword.isBlank()) {
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
            if (customer.getUsername().equals(newUsername)) {
                for (Customer c : Admin.getInstance().getCustomers()) {

                    if (c != customer && c.getUsername().equals(newUsername)) {
                        return "Username already exists!";
                    }

                    if (c != customer && c.getEmail().equals(newEmail)) {
                        return "Email already exists!";
                    }

                    if (c != customer && c.getPhoneNumber().equals(newPhoneNumber)) {
                        return "Phone number already exists!";
                    }
                }
                customer.setUsername(newUsername);
                customer.setEmail(newEmail);
                customer.setPhoneNumber(newPhoneNumber);
                customer.setPassword(newPassword);
            }
        }
        return "Personal information updated successfully.";
    }
}