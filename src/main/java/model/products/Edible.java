package model.products;

import model.Comment;
import enums.Category;

import java.util.List;

public class Edible extends Product{
    private String productionDate;
    private String expiry;

    public Edible(double averageRating, Category category, List<Comment> comments, double cost, String name, int productId, String status, String expiry, String productionDate) {
        super(averageRating, category, comments, cost, name, productId, status);
        this.expiry = expiry;
        this.productionDate = productionDate;
    }

    public String getExpiry() {
        return expiry;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

    public String getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(String productionDate) {
        this.productionDate = productionDate;
    }
}
