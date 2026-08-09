package controller;

import model.accounts.Customer;

public class AccountController {

    public String chargeCredit(double amount) {
        if (amount <= 0) {
            return "Amount must be greater than zero.";
        }
        Customer customer = SessionManager.getCurrentCustomer();
        if (customer == null) {
            return "No customer is logged in.";
        }
        customer.setCredit((int) (customer.getCredit() + amount));
        return "Credit increased successfully.";
    }
}