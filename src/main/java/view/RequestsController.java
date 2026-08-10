package view;

import controller.RequestController;
import controller.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import model.Comment;
import model.Request;
import model.accounts.Customer;

import java.io.IOException;
import java.util.List;

public class RequestsController {

    @FXML
    private VBox requestsContainer;

    @FXML
    private Label emptyLabel;

    private final RequestController requestController = new RequestController();

    @FXML
    public void initialize() {
        loadRequests();
    }

    private void loadRequests() {
        requestsContainer.getChildren().clear();
        List<Request> requests = requestController.getPendingRequests();
        if (requests.isEmpty()) {
            emptyLabel.setVisible(true);
            return;
        }
        emptyLabel.setVisible(false);
        for (Request request : requests) {
            requestsContainer.getChildren().add(createRequestCard(request));
        }
    }

    private VBox createRequestCard(Request request) {

        VBox card = new VBox(10);
        card.setStyle("-fx-background-color: rgba(255,255,255,0.10);" + "-fx-background-radius: 15;" + "-fx-border-color: rgba(255,255,255,0.20);" + "-fx-border-radius: 15;" + "-fx-border-width: 1;" + "-fx-padding: 18;");
        Label typeLabel = new Label("Request Type: " + request.getType());
        typeLabel.setStyle("-fx-text-fill:  #243B55;" + "-fx-font-size: 17px;" + "-fx-font-weight: bold;");
        Label infoLabel = new Label(getRequestInformation(request));
        infoLabel.setWrapText(true);
        infoLabel.setStyle("-fx-text-fill: #B8C6D9;" + "-fx-font-size: 14px;");
        Button approveButton = new Button("Approve");
        Button rejectButton = new Button("Reject");
        approveButton.setStyle("-fx-background-color: #FFFFFF;" + "-fx-text-fill: #243B55;" + "-fx-font-weight: bold;" + "-fx-background-radius: 8;" + "-fx-cursor: hand;");
        rejectButton.setStyle("-fx-background-color: #8B3A3A;" + "-fx-text-fill: white;" + "-fx-font-weight: bold;" + "-fx-background-radius: 8;" + "-fx-cursor: hand;");
        approveButton.setOnAction(event -> approve(request));
        rejectButton.setOnAction(event -> reject(request));
        javafx.scene.layout.HBox buttons = new javafx.scene.layout.HBox(10, approveButton, rejectButton);
        card.getChildren().addAll(typeLabel, infoLabel, buttons);

        return card;
    }

    private String getRequestInformation(Request request) {

        switch (request.getType()) {

            case REGISTER:
                Customer customer = (Customer) request.getData();
                return "Username: " + customer.getUsername() + "\nEmail: " + customer.getEmail() + "\nPhone: " + customer.getPhoneNumber();

            case COMMENT:
                Comment comment = (Comment) request.getData();
                return "User: " + comment.getUser().getUsername() + "\nProduct ID: " + comment.getProductId() + "\nComment: " + comment.getText() + "\nBought this product: " + (comment.isHasBought() ? "Yes" : "No");

            case BALANCE:
                return "User: " + request.getSender().getUsername() + "\nAmount: " + request.getData();

            default:
                return "Unknown request";
        }
    }

    private void approve(Request request) {
        String result = requestController.approveRequest(request);
        showAlert("Approve Request", result);
        loadRequests();
    }

    private void reject(Request request) {
        String result = requestController.rejectRequest(request);
        showAlert("Reject Request", result);
        loadRequests();
    }

    @FXML
    private void back(ActionEvent event) {
        try {SceneManager.switchScene(event, "AdminMenu.fxml");
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