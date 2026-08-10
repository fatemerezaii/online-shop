package model.products.stationery;

import model.Comment;
import model.products.Product;
import enums.Category;

import java.util.List;

public class Stationery extends Product {
    private String country;

    public Stationery(double averageRating, Category category, List<Comment> comments, double cost, String name, int productId, String status, int inventory,String country) {
        super(averageRating, category, comments, cost, name, productId, status, inventory);
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Stationery{" +
                "country='" + country + '\'' +
                '}';
    }
}
