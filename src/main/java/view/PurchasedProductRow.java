package view;

import model.products.Product;

public class PurchasedProductRow {

    private Product product;
    private int quantity;

    public PurchasedProductRow(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getProductId() {
        return product.getProductId();
    }

    public String getName() {
        return product.getName();
    }

    public Object getCategory() {
        return product.getCategory();
    }

    public double getCost() {
        return product.getCost();
    }

    public int getQuantity() {
        return quantity;
    }

    public String getStatus() {
        return product.getStatus();
    }
}
