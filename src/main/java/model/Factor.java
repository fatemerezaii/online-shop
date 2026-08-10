package model;

import model.products.Product;

import java.util.List;

public class Factor {
    private int factorId;
    private String factorDate;
    private double payedPrice;
    private List<Product> boughtProducts;

    public Factor(List<Product> boughtProducts, String factorDate, int factorId, double payedPrice) {
        this.boughtProducts = boughtProducts;
        this.factorDate = factorDate;
        this.factorId = factorId;
        this.payedPrice = payedPrice;
    }

    public List<Product> getBoughtProducts() {
        return boughtProducts;
    }

    public String getFactorDate() {
        return factorDate;
    }

    public int getFactorId() {
        return factorId;
    }

    public double getPayedPrice() {
        return payedPrice;
    }

    public void setBoughtProducts(List<Product> boughtProducts) {
        this.boughtProducts = boughtProducts;
    }

    public void setFactorDate(String factorDate) {
        this.factorDate = factorDate;
    }

    public void setFactorId(int factorId) {
        this.factorId = factorId;
    }

    public void setPayedPrice(double payedPrice) {
        this.payedPrice = payedPrice;
    }

    @Override
    public String toString() {
        return "Factor{" +
                "boughtProducts=" + boughtProducts +
                ", factorId=" + factorId +
                ", factorDate='" + factorDate + '\'' +
                ", payedPrice=" + payedPrice +
                '}';
    }
}
