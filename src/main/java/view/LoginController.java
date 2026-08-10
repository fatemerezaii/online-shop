package view;

import controller.AuthController;
import controller.SceneManager;
import controller.SessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.accounts.Customer;

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
        if (result.equals("Successfully logged in")) {
            Customer customer = SessionManager.getCurrentCustomer();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/onlineShop/UserMenu.fxml"));
            Parent root = loader.load();
            SceneManager.switchScene(event, root);
        } else if (result.equals("Admin logged in")) {
            SceneManager.switchScene(event, "AdminMenu.fxml");
        } else {
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