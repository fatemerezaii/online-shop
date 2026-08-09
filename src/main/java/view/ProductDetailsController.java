package view;


import controller.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.products.Product;

import java.io.IOException;

public class ProductDetailsController {

    private Product product;

    @FXML
    private Label nameLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private Label statusLabel;

    @FXML
    private Label ratingLabel;


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
        ratingLabel.setText(String.valueOf(product.getAverageRating()));
    }

    @FXML
    private void back(ActionEvent event) {
        try {
            SceneManager.switchScene(event, "Products.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

