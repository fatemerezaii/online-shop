package controller;

import model.accounts.Account;
import model.accounts.Customer;

public class SessionManager {

    private static Account currentUser;

    public static void login(Account user) {
        currentUser = user;
        System.out.println("Session login: " + currentUser);
    }

    public static void logout() {
        currentUser = null;
    }

    public static Account getCurrentUser() {
        return currentUser;
    }

    public static Customer getCurrentCustomer() {
        System.out.println("Current user: " + currentUser);
        if (currentUser instanceof Customer) {
            return (Customer) currentUser;
        }

        return null;
    }

    public static boolean isLoggedIn() {
        return currentUser != null;
    }
}