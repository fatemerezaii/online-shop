package model;

import model.accounts.Customer;
import model.products.Product;

public class Rate {
    private Customer user;
    private double score;
    private Product product;

    public Rate(Product product, double score, Customer user) {
        this.product = product;
        this.score = score;
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public double getScore() {
        return score;
    }

    public Customer getUser() {
        return user;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public void setUser(Customer user) {
        this.user = user;
    }
}
