package view;

import controller.AuthController;
import controller.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController {

    @FXML
    private Button loginBtn;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    void login(ActionEvent event) throws IOException {
        AuthController authController = new AuthController();
        String result = authController.login(usernameField.getText(), passwordField.getText());
        if (result.equals("Successfully logged in")){
            SceneManager.switchScene(event, "UserMenu.fxml");
        }else if (result.equals("Admin logged in")){
            SceneManager.switchScene(event, "AdminMenu.fxml");
        }
        else{
            showAlert("Failed", result);
        }
        SceneManager.switchScene(event, "");
    }

    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
