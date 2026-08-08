package model.accounts;

import Model.Factor;
import Model.Product.Product;

import java.util.ArrayList;
import java.util.List;

public class Customer extends Account{
    private int credit;
    private List<Factor> factors;
    private String history;
    private List<Product> shoppingCart;

    public Customer(String email, String password, String phoneNumber, String username, int credit, List<Factor> factors, String history, List<Product> shoppingCart) {
        super(email, password, phoneNumber, username);
        this.credit = credit;
        this.factors = factors;
        this.history = history;
        this.shoppingCart = shoppingCart;
    }

    public Customer(String email, String password, String phoneNumber, String username) {
        super(email, password, phoneNumber, username);
        this.credit = 0;
        this.factors = new ArrayList<>();
        this.history = "";
        this.shoppingCart = new ArrayList<>();
    }

    public int getCredit() {
        return credit;
    }

    public List<Factor> getFactors() {
        return factors;
    }

    public String getHistory() {
        return history;
    }

    public List<Product> getShoppingCart() {
        return shoppingCart;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public void setFactors(List<Factor> factors) {
        this.factors = factors;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public void setShoppingCart(List<Product> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
}
