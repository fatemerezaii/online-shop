package view;

import controller.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import model.Comment;
import model.products.Product;
import model.products.Edible;
import model.products.electronicProduct.PC;
import model.products.electronicProduct.storageEquipment.StorageEquipment;
import model.products.stationery.NoteBook;
import model.products.stationery.Pen;
import model.products.stationery.Pencil;
import model.products.vehicles.Bicycle;
import model.products.vehicles.Car;
import model.products.vehicles.Vehicles;
import enums.CommentStatus;

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
    private Label inventoryLabel;

    @FXML
    private TextField quantityField;

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

    @FXML
    private Button viewCartButton;

    @FXML
    private VBox commentsContainer;


    public void setProduct(Product product) {
        this.product = product;
        showProduct();
        showComments();
    }


    private void showProduct() {
        if (product == null) {
            return;
        }
        nameLabel.setText(product.getName());
        priceLabel.setText(String.valueOf(product.getCost()));
        statusLabel.setText(product.getStatus());
        inventoryLabel.setText(String.valueOf(product.getInventory()));
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

    private void showComments() {
        commentsContainer.getChildren().clear();
        if (product == null) {
            return;
        }
        if (product.getComments() == null || product.getComments().isEmpty()) {
            Label emptyLabel = new Label("No approved comments yet.");
            emptyLabel.setTextFill(javafx.scene.paint.Color.web("#B8C6D9"));
            commentsContainer.getChildren().add(emptyLabel);
            return;
        }
        boolean hasApprovedComment = false;
        for (Comment comment : product.getComments()) {
            if (comment == null) {
                continue;
            }
            if (comment.getStatus() != CommentStatus.CONFIRMED) {
                continue;
            }
            hasApprovedComment = true;
            VBox commentBox = new VBox(5);
            commentBox.setMaxWidth(400);
            commentBox.setStyle("-fx-background-color: rgba(255,255,255,0.08);" + "-fx-background-radius: 10px;" + "-fx-padding: 12px;" + "-fx-border-color: rgba(255,255,255,0.15);" + "-fx-border-radius: 10px;");
            Label usernameLabel = new Label();
            usernameLabel.setTextFill(javafx.scene.paint.Color.WHITE);
            usernameLabel.setStyle("-fx-font-weight: bold;" + "-fx-font-size: 14px;");
            if (comment.getUser() != null) {
                usernameLabel.setText(comment.getUser().getUsername());
            } else {
                usernameLabel.setText("Customer");
            }
            Label commentLabel = new Label(comment.getText());
            commentLabel.setTextFill(javafx.scene.paint.Color.web("#B8C6D9"));
            commentLabel.setWrapText(true);
            commentLabel.setMaxWidth(370);
            commentBox.getChildren().addAll(usernameLabel, commentLabel);
            commentsContainer.getChildren().add(commentBox);
        }


        if (!hasApprovedComment) {
            Label emptyLabel = new Label("No approved comments yet.");
            emptyLabel.setTextFill(javafx.scene.paint.Color.web("#B8C6D9"));
            commentsContainer.getChildren().add(emptyLabel);
        }
    }

    @FXML
    private void addToCart(ActionEvent event) {

        String quantityText = quantityField.getText().trim();
        if (quantityText.isBlank()) {
            showAlert("Please enter the quantity.");
            return;
        }

        try {
            int quantity = Integer.parseInt(quantityText);
            if (quantity <= 0) {
                showAlert("Quantity must be greater than zero.");
                return;
            }
            String result = productController.addToCart(product, quantity);
            showAlert(result);
            if (result.contains("added to cart successfully")) {
                quantityField.clear();
            }
        } catch (NumberFormatException e) {
            showAlert("Quantity must be a valid number.");
        }
    }

    @FXML
    private void addComment(ActionEvent event) {
        String text = commentArea.getText();
        String result = productController.addComment(product, text);
        showAlert(result);
        if (result.contains("Comment submitted successfully")) {
            commentArea.clear();
        }
    }

    @FXML
    private void rateProduct(ActionEvent event) {
        String text = ratingField.getText();
        if (text.isBlank()) {
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
    private void viewCart(ActionEvent event) {
        try {
            SceneManager.switchScene(event, "Cart.fxml");
        } catch (IOException e) {
            e.printStackTrace();
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