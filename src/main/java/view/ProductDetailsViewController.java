package view;

import controller.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.products.Product;
import model.products.electronicProduct.PC;
import model.products.electronicProduct.storageEquipment.StorageEquipment;
import model.products.stationery.NoteBook;
import model.products.stationery.Pen;
import model.products.stationery.Pencil;
import model.products.vehicles.Bicycle;
import model.products.vehicles.Car;
import model.products.vehicles.Vehicles;
import model.products.Edible;

import java.io.IOException;

public class ProductDetailsViewController {

    private Product product;

    private final controller.ProductDetailsController productController = new controller.ProductDetailsController();

    @FXML
    private Label nameLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private Label statusLabel;

    @FXML
    private Label ratingLabel;

    @FXML
    private Label categoryLabel;

    @FXML
    private Label detailsLabel;

    @FXML
    private TextField ratingField;

    @FXML
    private TextArea commentArea;

    @FXML
    private Button addToCartButton;

    @FXML
    private Button commentButton;

    @FXML
    private Button rateButton;


    public void setProduct(Product product) {
        this.product = product;
        showProduct();
    }


    private void showProduct() {
        if (product == null) {
            return;
        }
        nameLabel.setText(product.getName());
        priceLabel.setText(String.valueOf(product.getCost()));
        statusLabel.setText(product.getStatus());
        ratingLabel.setText(String.format("%.2f", product.getAverageRating()));
        categoryLabel.setText(product.getCategory().toString());
        showSpecificDetails();
    }


    private void showSpecificDetails() {
        StringBuilder details = new StringBuilder();
        if (product instanceof Car car) {
            details.append("Company: ").append(car.getCompanyName()).append("\n");
            details.append("Engine Volume: ").append(car.getEngineVolume()).append("\n");
            details.append("Automatic: ").append(car.isAutomate());
        }
        else if (product instanceof Bicycle bicycle) {
            details.append("Company: ").append(bicycle.getCompanyName()).append("\n");
            details.append("Bicycle Type: ").append(bicycle.getBicycleType());
        }
        else if (product instanceof PC pc) {
            details.append("CPU: ").append(pc.getCPUType()).append("\n");
            details.append("RAM: ").append(pc.getRAMCapacity()).append("\n");
            details.append("Weight: ").append(pc.getWeight()).append("\n");
            details.append("Dimensions: ").append(pc.getDimensions());
        }
        else if (product instanceof StorageEquipment storage) {
            details.append("Capacity: ").append(storage.getCapacity()).append("\n");
            details.append("Weight: ").append(storage.getWeight()).append("\n");
            details.append("Dimensions: ").append(storage.getDimensions());
        }
        else if (product instanceof NoteBook notebook) {
            details.append("Country: ").append(notebook.getCountry()).append("\n");
            details.append("Pages: ").append(notebook.getPages()).append("\n");
            details.append("Paper Type: ").append(notebook.getPaperType());
        }
        else if (product instanceof Pen pen) {
            details.append("Country: ").append(pen.getCountry()).append("\n");
            details.append("Color: ").append(pen.getColor());
        }
        else if (product instanceof Pencil pencil) {
            details.append("Country: ").append(pencil.getCountry()).append("\n");
            details.append("Pencil Type: ").append(pencil.getPencilType());
        }
        else if (product instanceof Edible edible) {
            details.append("Production Date: ").append(edible.getProductionDate()).append("\n");
            details.append("Expiry: ").append(edible.getExpiry());
        }
        else if (product instanceof model.products.electronicProduct.ElectronicProduct electronic) {
            details.append("Weight: ").append(electronic.getWeight()).append("\n");
            details.append("Dimensions: ").append(electronic.getDimensions());
        }

        else if (product instanceof Vehicles vehicle) {
            details.append("Company: ").append(vehicle.getCompanyName());
        }
        detailsLabel.setText(details.toString());
    }


    @FXML
    private void addToCart(ActionEvent event) {
        String result = productController.addToCart(product);
        showAlert(result);
    }


    @FXML
    private void addComment(ActionEvent event) {
        String text = commentArea.getText();
        String result = productController.addComment(product, text);
        showAlert(result);
        if (result.equals("Comment submitted successfully.")) {
            commentArea.clear();
        }
    }


    @FXML
    private void rateProduct(ActionEvent event) {
        String text = ratingField.getText();
        if (text == null || text.isBlank()) {
            showAlert("Please enter a rating.");
            return;
        }
        try {
            double rating = Double.parseDouble(text);
            String result = productController.rateProduct(product, rating);
            showAlert(result);
            if (result.equals("Product rated successfully.") || result.equals("Rating updated successfully.")) {
                ratingField.clear();
                ratingLabel.setText(String.format("%.2f", product.getAverageRating()));
            }
        } catch (NumberFormatException e) {
            showAlert("Rating must be a number.");
        }
    }


    @FXML
    private void back(ActionEvent event) {
        try {
            SceneManager.switchScene(event, "Products.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Product");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
