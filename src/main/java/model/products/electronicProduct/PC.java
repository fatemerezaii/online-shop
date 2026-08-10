package model.products.electronicProduct;

import model.Comment;
import enums.Category;

import java.util.List;

public class PC extends ElectronicProduct{
    private String CPUType;
    private String RAMCapacity;

    public PC(double averageRating, Category category, List<Comment> comments, double cost, String name, int productId, String status, int inventory, double dimensions, double weight, String CPUType, String RAMCapacity) {
        super(averageRating, category, comments, cost, name, productId, status, inventory, dimensions, weight);
        this.CPUType = CPUType;
        this.RAMCapacity = RAMCapacity;
    }

    public String getCPUType() {
        return CPUType;
    }

    public void setCPUType(String CPUType) {
        this.CPUType = CPUType;
    }

    public String getRAMCapacity() {
        return RAMCapacity;
    }

    public void setRAMCapacity(String RAMCapacity) {
        this.RAMCapacity = RAMCapacity;
    }
}
