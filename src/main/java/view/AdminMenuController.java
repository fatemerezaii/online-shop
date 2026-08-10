package view;

import controller.AdminController;
import controller.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AdminMenuController {

    @FXML
    private TextField commandField;

    @FXML
    private TextArea outputArea;


    private final AdminController adminController =
            new AdminController();


    @FXML
    void executeCommand(ActionEvent event) {
        String command = commandField.getText();
        if (command == null || command.isBlank()) {
            outputArea.setText("Please enter a command.");
            return;
        }
        String result = adminController.executeCommand(command);
        outputArea.setText(result);
        commandField.clear();
    }


    @FXML
    void logout(ActionEvent event) throws IOException {
        adminController.logout();
        SceneManager.switchScene(event, "Login.fxml");
    }
}
