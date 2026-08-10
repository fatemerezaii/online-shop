package model.products.electronicProduct.storageEquipment;

import model.Comment;
import enums.Category;

import java.util.List;

public class Memory extends StorageEquipment{
    private String USBVersion;

    public Memory(double averageRating, Category category, List<Comment> comments, double cost, String name, int productId, String status, int inventory, double dimensions, double weight, double capacity, String USBVersion) {
        super(averageRating, category, comments, cost, name, productId, status, inventory, dimensions, weight, capacity);
        this.USBVersion = USBVersion;
    }

    public String getUSBVersion() {
        return USBVersion;
    }

    public void setUSBVersion(String USBVersion) {
        this.USBVersion = USBVersion;
    }

    @Override
    public String toString() {
        return "Memory{" +
                "USBVersion='" + USBVersion + '\'' +
                '}';
    }
}
