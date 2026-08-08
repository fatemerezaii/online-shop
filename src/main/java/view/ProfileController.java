package view;

import controller.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.accounts.Customer;

import java.io.IOException;

public class ProfileController {

    @FXML
    private Button backBtn;

    @FXML
    private Button editBtn;

    @FXML
    private Label emailText;
    @FXML
    private Label phoneNumberText;

    @FXML
    private Label usernameText;

    private Customer customer;

    public void setCustomer(Customer customer) {
        this.customer = customer;

        usernameText.setText(customer.getUsername());
        emailText.setText(customer.getEmail());
        phoneNumberText.setText(customer.getPhoneNumber());
    }

    @FXML
    void back(ActionEvent event) throws IOException {
        SceneManager.switchScene(event, "UserMenu.fxml");
    }

    @FXML
    void edit(ActionEvent event) throws IOException {
        SceneManager.switchScene(event, "Edit.fxml");
    }
}