package model.products.electronicProduct.storageEquipment;

import model.Comment;
import model.products.electronicProduct.ElectronicProduct;
import enums.Category;

import java.util.List;

public class StorageEquipment extends ElectronicProduct {
    private double capacity;

    public StorageEquipment(double averageRating, Category category, List<Comment> comments, double cost, String name, int productId, String status, int inventory, double dimensions, double weight, double capacity) {
        super(averageRating, category, comments, cost, name, productId, status, inventory, dimensions, weight);
        this.capacity = capacity;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }
}
