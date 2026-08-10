package controller;

import model.Request;
import model.accounts.Admin;
import model.accounts.Customer;
import enums.RequestType;

public class AccountController {

    public String requestChargeCredit(String cardNumber, String password, String cvv2, String amountText) {

        Customer customer = SessionManager.getCurrentCustomer();
        if (customer == null) {
            return "No customer is logged in.";
        }

        if (cardNumber.isBlank() || password.isBlank() || cvv2.isBlank() || amountText.isBlank()) {
            return "Fill all fields!";
        }

        if (!cardNumber.matches("^\\d{16}$")) {
            return "Invalid card number!";
        }

        if (!password.matches("^\\d{4}$")) {
            return "Invalid card password!";
        }

        if (!cvv2.matches("^\\d{3,4}$")) {
            return "Invalid CVV2!";
        }

        double amount;

        try {
            amount = Double.parseDouble(amountText);
        } catch (NumberFormatException e) {
            return "Invalid amount!";
        }

        if (amount <= 0) {
            return "Amount must be greater than zero.";
        }

        Request request = new Request(customer, RequestType.BALANCE, amount);
        Admin.getInstance().getRequests().add(request);

        return "Credit increase request sent successfully.";
    }
}