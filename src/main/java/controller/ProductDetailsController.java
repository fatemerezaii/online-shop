package controller;

import enums.CommentStatus;
import enums.RequestType;
import model.Comment;
import model.Factor;
import model.Rate;
import model.Request;
import model.accounts.Admin;
import model.accounts.Customer;
import model.products.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailsController {

    private boolean hasBought(Customer customer, Product product) {

        if (customer == null || product == null) {
            return false;
        }

        if (customer.getFactors() == null) {
            return false;
        }

        for (Factor factor : customer.getFactors()) {

            if (factor == null || factor.getBoughtProducts() == null) {
                continue;
            }

            for (Product boughtProduct : factor.getBoughtProducts()) {

                if (boughtProduct != null && boughtProduct.getProductId() == product.getProductId()) {
                    return true;
                }
            }
        }

        return false;
    }

    public String addToCart(Product product, int quantity) {

        Customer customer = SessionManager.getCurrentCustomer();

        if (customer == null) {
            return "You must be logged in!";
        }

        if (product == null) {
            return "Product not found!";
        }

        if (quantity <= 0) {
            return "Quantity must be greater than zero!";
        }

        if (product.getInventory() <= 0) {
            return "Product is out of stock!";
        }

        int currentQuantity = 0;

        if (customer.getShoppingCart() != null) {

            for (Product p : customer.getShoppingCart()) {

                if (p != null && p.getProductId() == product.getProductId()) {
                    currentQuantity++;
                }
            }
        }

        if (currentQuantity + quantity > product.getInventory()) {
            return "Not enough products in stock!";
        }

        if (customer.getShoppingCart() == null) {
            customer.setShoppingCart(new ArrayList<>());
        }

        for (int i = 0; i < quantity; i++) {
            customer.getShoppingCart().add(product);
        }

        return quantity + " product(s) added to cart successfully.";
    }

    public String addComment(Product product, String text) {

        Customer customer = SessionManager.getCurrentCustomer();

        if (customer == null) {
            return "You must be logged in!";
        }

        if (product == null) {
            return "Product not found!";
        }

        if (text == null || text.isBlank()) {
            return "Comment cannot be empty!";
        }

        if (!hasBought(customer, product)) {
            return "You must buy this product before commenting!";
        }

        Comment comment = new Comment(true, product.getProductId(), CommentStatus.WAITING, text, customer);
        Request request = new Request(customer, RequestType.COMMENT, comment);
        Admin.getInstance().getRequests().add(request);

        return "Comment submitted successfully. Waiting for admin approval.";
    }

    public String rateProduct(Product product, double rating) {

        Customer customer = SessionManager.getCurrentCustomer();

        if (customer == null) {
            return "You must be logged in!";
        }

        if (product == null) {
            return "Product not found!";
        }

        if (!hasBought(customer, product)) {
            return "You must buy this product before rating!";
        }

        if (rating < 0 || rating > 5) {
            return "Rating must be between 0 and 5!";
        }

        List<Rate> rates = Admin.getInstance().getRates();

        if (rates == null) {
            return "Rating system is not available!";
        }

        for (Rate rate : rates) {

            if (rate.getProduct() != null && rate.getUser() != null && rate.getProduct().getProductId() == product.getProductId() && rate.getUser() == customer) {

                rate.setScore(rating);
                updateAverageRating(product);

                return "Rating updated successfully.";
            }
        }

        Rate rate = new Rate(product, rating, customer);
        rates.add(rate);
        updateAverageRating(product);

        return "Product rated successfully.";
    }

    private void updateAverageRating(Product product) {

        List<Rate> rates = Admin.getInstance().getRates();

        if (rates == null || product == null) {
            return;
        }
        double sum = 0;
        int count = 0;

        for (Rate rate : rates) {

            if (rate == null || rate.getProduct() == null) {
                continue;
            }

            if (rate.getProduct().getProductId() == product.getProductId()) {
                sum += rate.getScore();
                count++;
            }
        }

        if (count > 0) {
            product.setAverageRating(sum / count);
        } else {
            product.setAverageRating(0);
        }
    }
}