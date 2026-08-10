package model.products.stationery;

import model.Comment;
import enums.Category;

import java.util.List;

public class Pen extends Stationery{
    private String color;

    public Pen(double averageRating, Category category, List<Comment> comments, double cost, String name, int productId, String status,int inventory, String country, String color) {
        super(averageRating, category, comments, cost, name, productId, status, inventory,country);
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Pen{" +
                "color='" + color + '\'' +
                '}';
    }
}
