package model.products.vehicles;

import model.Comment;
import enums.Category;

import java.util.List;

public class Car extends Vehicles{
    private double engineVolume;
    private boolean isAutomate;

    public Car(double averageRating, Category category, List<Comment> comments, double cost, String name, int productId, String status, int inventory, String companyName, double engineVolume, boolean isAutomate) {
        super(averageRating, category, comments, cost, name, productId, status, inventory,companyName);
        this.engineVolume = engineVolume;
        this.isAutomate = isAutomate;
    }

    public double getEngineVolume() {
        return engineVolume;
    }

    public void setEngineVolume(double engineVolume) {
        this.engineVolume = engineVolume;
    }

    public boolean isAutomate() {
        return isAutomate;
    }

    public void setAutomate(boolean automate) {
        isAutomate = automate;
    }

    @Override
    public String toString() {
        return "Car{" +
                "engineVolume=" + engineVolume +
                ", isAutomate=" + isAutomate +
                '}';
    }
}
