package view;

import controller.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class UserMenuController {

    @FXML
    private Button creditBtn;

    @FXML
    private Button historyBtn;

    @FXML
    private Button productsBtn;

    @FXML
    private Button profileBtn;

    @FXML
    void credit(ActionEvent event) throws IOException {
        SceneManager.switchScene(event, "ChargeCredit.fxml");
    }

    @FXML
    void history(ActionEvent event) throws IOException {
        SceneManager.switchScene(event, "History.fxml");
    }

    @FXML
    void products(ActionEvent event) throws IOException {
        SceneManager.switchScene(event, "Products.fxml");
    }

    @FXML
    void profile(ActionEvent event) throws IOException {
        SceneManager.switchScene(event, "Profile.fxml");
    }

}
