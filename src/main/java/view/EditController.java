package view;

import controller.AuthController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class EditController {

    @FXML
    private Button editButton;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField phoneNumberField;

    @FXML
    private TextField usernameField;

    @FXML
    void edit(ActionEvent event) {
        AuthController authController = new AuthController();
        String result = authController.editPersonalInfo(usernameField.getText(), emailField.getText(), phoneNumberField.getText(), passwordField.getText());
        if (result.equals("Personal information updated successfully.")){
            showAlert("Success", result);
        } else{
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

}
