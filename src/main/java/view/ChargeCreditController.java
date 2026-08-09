package view;

import controller.AccountController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class ChargeCreditController {

    @FXML
    private TextField amountField;

    @FXML
    public void increaseCredit(ActionEvent event) {
        AccountController accountController = new AccountController();
        String amountText = amountField.getText().trim();
        try {
            double amount = Double.parseDouble(amountText);
            if (amount <= 0) {
                showAlert("Failed", "Amount must be greater than zero.");
                return;
            }
            accountController.chargeCredit(amount);
            showAlert("Success", "Credit increased successfully.");
            amountField.clear();
        } catch (NumberFormatException e) {
            showAlert("Failed", "Please enter a valid amount.");
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
