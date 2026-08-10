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

public class SignUpController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField phoneNumberField;

    @FXML
    private Button signUpButton;

    @FXML
    private TextField usernameField;

    @FXML
    void signUP(ActionEvent event) throws IOException {
        AuthController authController = new AuthController();
        String result = authController.signUp(usernameField.getText(), emailField.getText(), phoneNumberField.getText(), passwordField.getText());
        if (result.equals("Registration request sent successfully.")){
            SceneManager.switchScene(event, "UserMenu.fxml");
        }
        else{
            showAlert("Failed", result);
        }
    }

    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    @FXML
    void back(ActionEvent event) throws IOException {
        SceneManager.switchScene(event, "MainPage.fxml");

    }
}
