package view;

import controller.SceneManager;
import model.Factor;
import model.accounts.Customer;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.util.List;

public class HistoryController {

    @FXML
    private ListView<String> invoiceListView;

    @FXML
    private Label factorIdLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private Label paidPriceLabel;

    @FXML
    private Button backButton;

    private Customer customer;
    private List<Factor> factors;


    @FXML
    public void initialize() {

        invoiceListView.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
                    int index = newValue.intValue();
                    if (factors != null && index >= 0 && index < factors.size()) {
                        showFactor(factors.get(index));
                    }
                });
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
        loadFactors();
    }

    private void loadFactors() {
        invoiceListView.getItems().clear();

        if (customer == null) {
            return;
        }

        factors = customer.getFactors();

        if (factors == null || factors.isEmpty()) {
            return;
        }

        for (Factor factor : factors) {
            String invoiceText = "Invoice #" + factor.getFactorId() + "  |  " + factor.getFactorDate();
            invoiceListView.getItems().add(invoiceText);
        }
    }

    private void showFactor(Factor factor) {
        if (factor == null) {
            return;
        }
        factorIdLabel.setText(String.valueOf(factor.getFactorId()));
        dateLabel.setText(factor.getFactorDate());
        paidPriceLabel.setText(String.valueOf(factor.getPayedPrice()));
    }

    @FXML
    public void back(ActionEvent event) throws IOException {
        SceneManager.switchScene(event, "UserMenu.fxml");
    }
}