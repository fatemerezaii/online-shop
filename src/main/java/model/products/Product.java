package model.products;

import model.Comment;
import enums.Category;

import java.util.List;

public class Product {
    private int productId;
    private String name;
    private double cost;
    private String status;
    private double  averageRating;
    private Category category;
    private List<Comment> comments;

    public Product(double averageRating, Category category, List<Comment> comments, double cost, String name, int productId, String status) {
        this.averageRating = averageRating;
        this.category = category;
        this.comments = comments;
        this.cost = cost;
        this.name = name;
        this.productId = productId;
        this.status = status;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public Category getCategory() {
        return category;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public double getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }

    public int getProductId() {
        return productId;
    }

    public String getStatus() {
        return status;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
