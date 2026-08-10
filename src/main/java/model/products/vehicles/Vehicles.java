package model.products.vehicles;

import model.Comment;
import model.products.Product;
import enums.Category;

import java.util.List;

public class Vehicles extends Product {
    private String companyName;

    public Vehicles(double averageRating, Category category, List<Comment> comments, double cost, String name, int productId, String status,int inventory, String companyName) {
        super(averageRating, category, comments, cost, name, productId, status, inventory);
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
