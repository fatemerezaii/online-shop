package view;

import controller.CartController;
import controller.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import model.products.Product;

import java.io.IOException;
import java.util.List;

public class CartViewController {

    private final CartController cartController = new CartController();

    @FXML
    private VBox cartItemsBox;

    @FXML
    private Label totalLabel;

    @FXML
    private Label creditLabel;

    @FXML
    private Button checkoutButton;


    @FXML
    public void initialize() {
        loadCart();
    }


    private void loadCart() {

        cartItemsBox.getChildren().clear();
        List<Product> products = cartController.getUniqueProducts();

        for (Product product : products) {
            int quantity = cartController.getQuantity(product);
            double total = cartController.getProductTotal(product);
            Label productLabel = new Label();
            productLabel.setText(product.getName() + "    x" + quantity + "    Total: " + total);
            productLabel.setStyle("-fx-text-fill: white;" + "-fx-font-size: 16px;" + "-fx-padding: 10px;");
            cartItemsBox.getChildren().add(productLabel);
        }

        totalLabel.setText(String.format("Total: %.2f", cartController.getTotalPrice()));
        if (cartController.getCustomer() != null) {
            creditLabel.setText("Your Credit: " + cartController.getCustomer().getCredit());
        }
    }


    @FXML
    private void checkout(ActionEvent event) {

        String result = cartController.checkout();
        showAlert(result);
        if (result.equals("Purchase completed successfully.")) {
            try {
                SceneManager.switchScene(event, "UserMenu.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
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
        alert.setTitle("Shopping Cart");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}