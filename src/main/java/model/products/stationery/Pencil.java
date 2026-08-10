package model.products.stationery;

import model.Comment;
import enums.Category;
import enums.PencilType;

import java.util.List;

public class Pencil extends Stationery {
    private PencilType pencilType;

    public Pencil(double averageRating, Category category, List<Comment> comments, double cost, String name, int productId, String status, int inventory,String country, PencilType pencilType) {
        super(averageRating, category, comments, cost, name, productId, status, inventory,country);
        this.pencilType = pencilType;
    }

    public PencilType getPencilType() {
        return pencilType;
    }

    public void setPencilType(PencilType pencilType) {
        this.pencilType = pencilType;
    }

    @Override
    public String toString() {
        return "Pencil{" +
                "pencilType=" + pencilType +
                '}';
    }
}
