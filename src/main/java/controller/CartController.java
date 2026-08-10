package controller;

import model.Factor;
import model.accounts.Customer;
import model.products.Product;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CartController {

    private final Customer customer;

    public CartController() {
        this.customer = SessionManager.getCurrentCustomer();
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<Product> getCartProducts() {

        if (customer == null) {
            return new ArrayList<>();
        }

        return new ArrayList<>(customer.getShoppingCart());
    }

    public int getQuantity(Product product) {

        if (customer == null || product == null) {
            return 0;
        }

        int quantity = 0;

        for (Product p : customer.getShoppingCart()) {

            if (p.getProductId() == product.getProductId()) {
                quantity++;
            }
        }

        return quantity;
    }

    public double getProductTotal(Product product) {

        if (product == null) {
            return 0;
        }

        return product.getCost() * getQuantity(product);
    }

    public double getTotalPrice() {

        if (customer == null) {
            return 0;
        }

        double total = 0;

        for (Product product : customer.getShoppingCart()) {
            total += product.getCost();
        }

        return total;
    }

    public List<Product> getUniqueProducts() {

        List<Product> uniqueProducts = new ArrayList<>();

        if (customer == null) {
            return uniqueProducts;
        }

        for (Product product : customer.getShoppingCart()) {

            boolean exists = false;

            for (Product unique : uniqueProducts) {

                if (unique.getProductId() == product.getProductId()) {
                    exists = true;
                    break;
                }
            }

            if (!exists) {
                uniqueProducts.add(product);
            }
        }

        return uniqueProducts;
    }

    public String checkout() {

        if (customer == null) {
            return "You must be logged in!";
        }

        if (customer.getShoppingCart() == null || customer.getShoppingCart().isEmpty()) {

            return "Your shopping cart is empty!";
        }

        Map<Integer, Integer> quantities = new LinkedHashMap<>();

        for (Product product : customer.getShoppingCart()) {

            int id = product.getProductId();

            quantities.put(id, quantities.getOrDefault(id, 0) + 1);
        }

        for (Product product : getUniqueProducts()) {

            int quantity = quantities.get(product.getProductId());

            if (product.getInventory() < quantity) {
                return "Not enough inventory for product: " + product.getName();
            }
        }
        double totalPrice = getTotalPrice();

        if (customer.getCredit() < totalPrice) {
            return "Insufficient credit!";
        }

        customer.setCredit((int) (customer.getCredit() - totalPrice));

        for (Product product : getUniqueProducts()) {

            int quantity = quantities.get(product.getProductId());
            product.setInventory(product.getInventory() - quantity);
        }

        int factorId = generateFactorId();
        String factorDate = LocalDateTime.now().toString();
        List<Product> boughtProducts = new ArrayList<>(customer.getShoppingCart());
        Factor factor = new Factor(boughtProducts, factorDate, factorId, totalPrice);
        customer.getFactors().add(factor);
        customer.getShoppingCart().clear();
        return "Purchase completed successfully.";
    }

    private int generateFactorId() {

        int maxId = 0;

        if (customer == null) {
            return 1;
        }

        for (Factor factor : customer.getFactors()) {
            if (factor.getFactorId() > maxId) {
                maxId = factor.getFactorId();
            }
        }

        return maxId + 1;
    }
}