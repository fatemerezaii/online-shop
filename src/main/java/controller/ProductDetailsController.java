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

        for (Factor factor : customer.getFactors()) {

            for (Product boughtProduct : factor.getBoughtProducts()) {

                if (boughtProduct.getProductId() == product.getProductId()) {
                    return true;
                }
            }
        }

        return false;
    }

    public String addToCart(Product product) {

        Customer customer = SessionManager.getCurrentCustomer();

        if (customer == null) {
            return "You must be logged in!";
        }

        if (product == null) {
            return "Product not found!";
        }

        if (customer.getShoppingCart().contains(product)) {
            return "Product is already in your cart!";
        }

        customer.getShoppingCart().add(product);

        return "Product added to cart successfully.";
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

        boolean hasBought = hasBought(customer, product);
        Comment comment = new Comment(hasBought, product.getProductId(), CommentStatus.WAITING, text, customer);
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

        if (rating < 0 || rating > 5) {
            return "Rating must be between 0 and 5!";
        }

        List<Rate> rates = Admin.getInstance().getRates();

        for (Rate rate : rates) {

            if (rate.getProduct() == product && rate.getUser() == customer) {

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

        double sum = 0;
        int count = 0;

        for (Rate rate : rates) {

            if (rate.getProduct() == product) {
                sum += rate.getScore();
                count++;
            }
        }

        if (count > 0) {
            product.setAverageRating(sum / count);
        }
    }
}
