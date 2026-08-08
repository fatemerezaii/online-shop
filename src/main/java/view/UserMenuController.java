package view;

import controller.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import model.accounts.Customer;

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

    private Customer customer;

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

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

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/onlineShop/Profile.fxml"));
        Parent root = loader.load();
        ProfileController controller = loader.getController();
        controller.setCustomer(customer);
        SceneManager.switchScene(event, root);
    }
}
