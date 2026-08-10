package view;

import controller.SceneManager;
import controller.SessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
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
    @FXML
    private Button backButton;


    @FXML
    void credit(ActionEvent event) throws IOException {
        SceneManager.switchScene(event, "ChargeCredit.fxml");
    }

    @FXML
    void history(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/onlineShop/History.fxml"));
        Parent root = loader.load();
        HistoryController controller = loader.getController();
        controller.setCustomer(SessionManager.getCurrentCustomer());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void products(ActionEvent event) throws IOException {
        SceneManager.switchScene(event, "Products.fxml");
    }

    @FXML
    void profile(ActionEvent event) throws IOException {
        Customer customer = SessionManager.getCurrentCustomer();
        if (customer == null) {
            return;
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/onlineShop/Profile.fxml"));
        Parent root = loader.load();
        ProfileController controller = loader.getController();
        controller.setCustomer(customer);
        SceneManager.switchScene(event, root);
    }

    @FXML
    void back(ActionEvent event) throws IOException {
        SceneManager.switchScene(event, "MainPage.fxml");
    }
}