package view;

import controller.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class MainPageController {

    @FXML
    private Button loginBtn;

    @FXML
    private Button productsBtn;

    @FXML
    private Button signUpBtn;

    @FXML
    void login(ActionEvent event) throws IOException {
        SceneManager.switchScene(event , "Login.fxml");
    }

    @FXML
    void products(ActionEvent event) throws IOException {
        SceneManager.switchScene(event , "Products.fxml");
    }

    @FXML
    void signUp(ActionEvent event) throws IOException {
        SceneManager.switchScene(event , "SignUp.fxml");
    }

}
