package model.products.vehicles;

import model.Comment;
import enums.BicycleType;
import enums.Category;

import java.util.List;

public class Bicycle extends Vehicles {
    private BicycleType bicycleType;

    public Bicycle(double averageRating, Category category, List<Comment> comments, double cost, String name, int productId, String status, String companyName, BicycleType bicycleType) {
        super(averageRating, category, comments, cost, name, productId, status, companyName);
        this.bicycleType = bicycleType;
    }

    public BicycleType getBicycleType() {
        return bicycleType;
    }

    public void setBicycleType(BicycleType bicycleType) {
        this.bicycleType = bicycleType;
    }
}
