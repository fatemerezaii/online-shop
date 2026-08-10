package view;

import controller.SceneManager;
import model.Factor;
import model.accounts.Customer;
import model.products.Product;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HistoryController {

    @FXML
    private ListView<String> invoiceListView;

    @FXML
    private TableView<PurchasedProductRow> productTable;

    @FXML
    private TableColumn<PurchasedProductRow, Integer> idColumn;

    @FXML
    private TableColumn<PurchasedProductRow, String> nameColumn;

    @FXML
    private TableColumn<PurchasedProductRow, Object> categoryColumn;

    @FXML
    private TableColumn<PurchasedProductRow, Double> priceColumn;

    @FXML
    private TableColumn<PurchasedProductRow, Integer> quantityColumn;

    @FXML
    private TableColumn<PurchasedProductRow, String> statusColumn;

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
        idColumn.setCellValueFactory(new PropertyValueFactory<>("productId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("cost"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        invoiceListView.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            int index = newValue.intValue();
            if (index >= 0 && index < factors.size()) {
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
        productTable.getItems().clear();
        List<Product> products = factor.getBoughtProducts();
        if (products == null || products.isEmpty()) {
            return;
        }
        Map<Integer, PurchasedProductRow> productMap = new HashMap<>();
        for (Product product : products) {
            int productId = product.getProductId();
            if (productMap.containsKey(productId)) {
                PurchasedProductRow oldRow = productMap.get(productId);
                productMap.put(productId, new PurchasedProductRow(oldRow.getProduct(), oldRow.getQuantity() + 1));
            } else {
                productMap.put(productId, new PurchasedProductRow(product, 1));
            }
        }
        ObservableList<PurchasedProductRow> rows = FXCollections.observableArrayList(productMap.values());
        productTable.setItems(rows);
    }

    @FXML
    public void back(ActionEvent event) throws IOException {
        SceneManager.switchScene(event, "UserMenu.fxml");
    }
}