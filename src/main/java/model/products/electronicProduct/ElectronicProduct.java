package model.products.electronicProduct;

import model.Comment;
import model.products.Product;
import enums.Category;

import java.util.List;

public class ElectronicProduct extends Product {
    private double weight;
    private double dimensions;

    public ElectronicProduct(double averageRating, Category category, List<Comment> comments, double cost, String name, int productId, String status, double dimensions, double weight) {
        super(averageRating, category, comments, cost, name, productId, status);
        this.dimensions = dimensions;
        this.weight = weight;
    }

    public double getDimensions() {
        return dimensions;
    }

    public double getWeight() {
        return weight;
    }

    public void setDimensions(double dimensions) {
        this.dimensions = dimensions;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
