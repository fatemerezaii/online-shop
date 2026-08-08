package model.products.electronicProduct.storageEquipment;

import model.Comment;
import enums.Category;

import java.util.List;

public class SSD extends StorageEquipment{
    private String readingSpeed;
    private String writingSpeed;

    public SSD(double averageRating, Category category, List<Comment> comments, double cost, String name, int productId, String status, double dimensions, double weight, double capacity, String readingSpeed, String writingSpeed) {
        super(averageRating, category, comments, cost, name, productId, status, dimensions, weight, capacity);
        this.readingSpeed = readingSpeed;
        this.writingSpeed = writingSpeed;
    }

    public String getReadingSpeed() {
        return readingSpeed;
    }

    public void setReadingSpeed(String readingSpeed) {
        this.readingSpeed = readingSpeed;
    }

    public String getWritingSpeed() {
        return writingSpeed;
    }

    public void setWritingSpeed(String writingSpeed) {
        this.writingSpeed = writingSpeed;
    }
}
