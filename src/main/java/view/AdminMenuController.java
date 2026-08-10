package view;

import controller.AdminController;
import controller.ProductController;
import controller.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.products.Product;

import java.io.IOException;

public class AdminMenuController {

    @FXML
    private TextField commandField;

    @FXML
    private TextArea outputArea;

    @FXML
    private TextField productIdField;

    @FXML
    private TextField productNameField;

    @FXML
    private TextField productPriceField;

    @FXML
    private TextField productStatusField;

    @FXML
    private TextField productInventoryField;

    private final AdminController adminController = new AdminController();
    private final ProductController productController = new ProductController();

    @FXML
    void executeCommand(ActionEvent event) {
        String command = commandField.getText();
        if (command.isBlank()) {
            outputArea.setText("Please enter a command.");
            return;
        }
        String result = adminController.executeCommand(command);
        outputArea.setText(result);
        commandField.clear();
    }

    @FXML
    void removeProduct(ActionEvent event) {
        String idText = productIdField.getText().trim();
        if (idText.isBlank()) {
            showAlert("Failed", "Please enter product ID.");
            return;
        }

        try {
            int productId = Integer.parseInt(idText);
            String result = productController.removeProduct(productId);
            showAlert("Remove Product", result);

            if (result.equals("Product removed successfully.")) {
                clearProductFields();
            }

        } catch (NumberFormatException e) {
            showAlert("Failed", "Product ID must be a number.");
        }
    }

    @FXML
    void editProduct(ActionEvent event) {
        String idText = productIdField.getText().trim();
        String name = productNameField.getText().trim();
        String priceText = productPriceField.getText().trim();
        String status = productStatusField.getText().trim();
        String inventoryText = productInventoryField.getText().trim();
        if (idText.isBlank() || name.isBlank() || priceText.isBlank() || status.isBlank() || inventoryText.isBlank()) {
            showAlert("Failed", "Fill all product fields.");
            return;
        }

        try {
            int productId = Integer.parseInt(idText);
            double price = Double.parseDouble(priceText);
            int inventory = Integer.parseInt(inventoryText);
            String result = productController.editProduct(productId, name, price, status, inventory);
            showAlert("Edit Product", result);

            if (result.equals("Product updated successfully.")) {
                clearProductFields();
            }

        } catch (NumberFormatException e) {
            showAlert("Failed", "ID, price and inventory must be valid numbers.");
        }
    }

    @FXML
    void loadProduct(ActionEvent event) {

        String idText = productIdField.getText().trim();

        if (idText.isBlank()) {
            showAlert("Failed", "Enter product ID.");
            return;
        }

        try {
            int productId = Integer.parseInt(idText);
            Product product = findProduct(productId);
            if (product == null) {
                showAlert("Failed", "Product not found.");
                return;
            }
            productNameField.setText(product.getName());
            productPriceField.setText(String.valueOf(product.getCost()));
            productStatusField.setText(product.getStatus());
            productInventoryField.setText(String.valueOf(product.getInventory()));
        } catch (NumberFormatException e) {
            showAlert("Failed", "Product ID must be a number.");
        }
    }

    @FXML
    void requests(ActionEvent event) throws IOException {
        SceneManager.switchScene(event, "Requests.fxml");
    }

    @FXML
    void logout(ActionEvent event) throws IOException {
        adminController.logout();
        SceneManager.switchScene(event, "Login.fxml");
    }

    private Product findProduct(int productId) {
        for (Product product : productController.getAllProducts()) {
            if (product.getProductId() == productId) {
                return product;
            }
        }
        return null;
    }


    private void clearProductFields() {
        productIdField.clear();
        productNameField.clear();
        productPriceField.clear();
        productStatusField.clear();
        productInventoryField.clear();
    }


    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}