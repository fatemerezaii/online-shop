package view;

import controller.AccountController;
import controller.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ChargeCreditController {

    @FXML
    private TextField cardNumberField;

    @FXML
    private PasswordField cardPasswordField;

    @FXML
    private TextField cvv2Field;

    @FXML
    private TextField amountField;


    @FXML
    public void increaseCredit(ActionEvent event) {

        AccountController accountController = new AccountController();
        String result = accountController.requestChargeCredit(cardNumberField.getText().trim(), cardPasswordField.getText().trim(), cvv2Field.getText().trim(), amountField.getText().trim());
        if (result.equals("Credit increase request sent successfully.")) {
            showAlert("Success", result);
            cardNumberField.clear();
            cardPasswordField.clear();
            cvv2Field.clear();
            amountField.clear();
        } else {
            showAlert("Failed", result);
        }
    }


    @FXML
    public void back(ActionEvent event) {
        try {
            SceneManager.switchScene(event, "UserMenu.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}