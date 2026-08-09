package view;

import controller.ProductController;
import controller.SceneManager;
import model.products.Product;
import enums.Category;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductsController {

    @FXML
    private TextField searchField;

    @FXML
    private ComboBox<String> categoryCombo;

    @FXML
    private ComboBox<String> statusCombo;

    @FXML
    private TextField minPriceField;

    @FXML
    private TextField maxPriceField;

    @FXML
    private TextField minRatingField;

    @FXML
    private TextField maxRatingField;

    @FXML
    private VBox pcFilters;

    @FXML
    private VBox storageFilters;

    @FXML
    private VBox notebookFilters;

    @FXML
    private VBox penFilters;

    @FXML
    private VBox carFilters;

    @FXML
    private ComboBox<String> cpuCombo;

    @FXML
    private ComboBox<String> ramCombo;

    @FXML
    private TextField minCapacityField;

    @FXML
    private TextField maxCapacityField;

    @FXML
    private TextField minPagesField;

    @FXML
    private TextField maxPagesField;

    @FXML
    private ComboBox<String> colorCombo;

    @FXML
    private ComboBox<String> automaticCombo;

    @FXML
    private TextField minEngineField;

    @FXML
    private TextField maxEngineField;

    @FXML
    private FlowPane productsContainer;

    @FXML
    private Label resultLabel;

    @FXML
    private Label pageLabel;

    @FXML
    private Label paginationLabel;

    @FXML
    private Button previousButton;

    @FXML
    private Button nextButton;

    private final ProductController productController = new ProductController();
    private List<Product> filteredProducts = new ArrayList<>();
    private int currentPage = 1;

    @FXML
    public void initialize() {
        initializeCategories();
        initializeStatus();
        initializeCategoryFilters();
        filteredProducts = productController.getAllProducts();
        updateProducts();
    }

    private void initializeCategories() {
        categoryCombo.getItems().add("All");
        categoryCombo.getItems().add("Electronic");
        categoryCombo.getItems().add("Stationery");
        categoryCombo.getItems().add("Edible");
        categoryCombo.getItems().add("Vehicles");
        categoryCombo.setValue("All");
    }


    private void initializeStatus() {
        statusCombo.getItems().add("All");
        statusCombo.getItems().add("Available");
        statusCombo.getItems().add("Unavailable");
        statusCombo.setValue("All");
    }


    private void initializeCategoryFilters() {
        cpuCombo.getItems().addAll("Intel", "AMD");
        ramCombo.getItems().addAll("4GB", "8GB", "16GB", "32GB", "64GB");
        colorCombo.getItems().addAll("Black", "Blue", "Red", "Green", "White");
        automaticCombo.getItems().addAll("Automatic", "Manual");
    }

    @FXML
    private void search() {
        currentPage = 1;
        applyAllFilters();
    }

    @FXML
    private void categoryChanged() {
        hideAllCategoryFilters();
        String category = categoryCombo.getValue();
        if (category == null) {
            return;
        }
        switch (category) {
            case "Electronic" -> {
                pcFilters.setManaged(true);
                pcFilters.setVisible(true);
                storageFilters.setManaged(true);
                storageFilters.setVisible(true);
            }
            case "Stationery" -> {
                notebookFilters.setManaged(true);
                notebookFilters.setVisible(true);
                penFilters.setManaged(true);
                penFilters.setVisible(true);
            }
            case "Vehicles" -> {
                carFilters.setManaged(true);
                carFilters.setVisible(true);
            }
        }
        currentPage = 1;
        applyAllFilters();
    }

    private void hideAllCategoryFilters() {
        pcFilters.setVisible(false);
        pcFilters.setManaged(false);
        storageFilters.setVisible(false);
        storageFilters.setManaged(false);
        notebookFilters.setVisible(false);
        notebookFilters.setManaged(false);
        penFilters.setVisible(false);
        penFilters.setManaged(false);
        carFilters.setVisible(false);
        carFilters.setManaged(false);
    }

    @FXML
    private void applyFilters() {
        currentPage = 1;
        applyAllFilters();
    }

    private void applyAllFilters() {

        List result = productController.getAllProducts();
        result = productController.search(result, searchField.getText());
        Category category = getSelectedCategory();
        if (category != null) {
            result = productController.filterByCategory(result, category);
        }
        String status = statusCombo.getValue();
        if (status != null && !status.equals("All")) {
            result = productController.filterByStatus(result, status);
        }
        Double minPrice = parseDouble(minPriceField.getText());
        Double maxPrice = parseDouble(maxPriceField.getText());
        if (minPrice != null || maxPrice != null) {
            double min;
            double max;
            if (minPrice != null) {
                min = minPrice;
            } else {
                min = 0;
            }
            if (maxPrice != null) {
                max = maxPrice;
            } else {
                max = Double.MAX_VALUE;
            }
            result = productController.filterByPrice(result, min, max);
        }

        Double minRating = parseDouble(minRatingField.getText());
        Double maxRating = parseDouble(maxRatingField.getText());
        if (minRating != null || maxRating != null) {
            double min;
            double max;
            if (minRating != null) {
                min = minRating;
            } else {
                min = 0;
            }
            if (maxRating != null) {
                max = maxRating;
            } else {
                max = 5;
            }
            result = productController.filterByRating(result, min, max);
        }

        if (category == Category.ELECTRONIC_PRODUCT) {
            if (cpuCombo.getValue() != null) {
                result = productController.filterPCByCPU(result, cpuCombo.getValue());
            }
            if (ramCombo.getValue() != null) {
                result = productController.filterPCByRAM(result, ramCombo.getValue());
            }
            Double minCapacity = parseDouble(minCapacityField.getText());
            Double maxCapacity = parseDouble(maxCapacityField.getText());
            if (minCapacity != null || maxCapacity != null) {
                double min;
                double max;
                if (minCapacity != null) {
                    min = minCapacity;
                } else {
                    min = 0;
                }
                if (maxCapacity != null) {
                    max = maxCapacity;
                } else {
                    max = Double.MAX_VALUE;
                }
                result = productController.filterByCapacity(result, min, max);
            }
        }
        if (category == Category.STATIONERY) {
            Double minPages = parseDouble(minPagesField.getText());
            Double maxPages = parseDouble(maxPagesField.getText());
            if (minPages != null || maxPages != null) {
                int min;
                int max;
                if (minPages != null) {
                    min = minPages.intValue();
                } else {
                    min = 0;
                }
                if (maxPages != null) {
                    max = maxPages.intValue();
                } else {
                    max = Integer.MAX_VALUE;
                }
                result = productController.filterNotebookByPages(result, min, max);
            }
            if (colorCombo.getValue() != null) {
                result = productController.filterPenByColor(result, colorCombo.getValue());
            }
        }
        if (category == Category.VEHICLES) {
            if (automaticCombo.getValue() != null) {
                boolean automatic;
                if (automaticCombo.getValue().equals("Automatic")) {
                    automatic = true;
                } else {
                    automatic = false;
                }
                result = productController.filterCarsByAutomatic(result, automatic);
            }
            Double minEngine = parseDouble(minEngineField.getText());
            Double maxEngine = parseDouble(maxEngineField.getText());
            if (minEngine != null || maxEngine != null) {
                double min;
                double max;
                if (minEngine != null) {
                    min = minEngine;
                } else {
                    min = 0;
                }
                if (maxEngine != null) {
                    max = maxEngine;
                } else {
                    max = Double.MAX_VALUE;
                }
                result = productController.filterCarsByEngineVolume(result, min, max);
            }
        }
        filteredProducts = result;
        currentPage = 1;
        updateProducts();
    }

    private Category getSelectedCategory() {
        String category = categoryCombo.getValue();
        if (category == null || category.equals("All")) {
            return null;
        }
        return switch (category) {
            case "Electronic" -> Category.ELECTRONIC_PRODUCT;
            case "Stationery" -> Category.STATIONERY;
            case "Edible" -> Category.EDIBLE;
            case "Vehicles" -> Category.VEHICLES;
            default -> null;
        };
    }

    private void updateProducts() {
        productsContainer.getChildren().clear();
        List<Product> pageProducts = productController.getProductsPage(filteredProducts, currentPage);
        for (Product product : pageProducts) {
            productsContainer.getChildren().add(createProductCard(product));
        }
        updatePagination();
        resultLabel.setText(filteredProducts.size() + " product(s) found");
    }

    private VBox createProductCard(Product product) {
        VBox card = new VBox(8);
        card.setPrefWidth(210);
        card.setPrefHeight(190);
        card.setStyle("-fx-background-color: white;" + "-fx-background-radius: 12;" + "-fx-border-radius: 12;" + "-fx-border-color: #E1E6EF;" + "-fx-padding: 15;" + "-fx-cursor: hand;");
        Label name = new Label(product.getName());
        name.setWrapText(true);
        name.setStyle("-fx-text-fill: #172B4D;" + "-fx-font-size: 16px;" + "-fx-font-weight: bold;");
        Label category = new Label(product.getCategory().toString());
        category.setStyle("-fx-text-fill: #7A869A;" + "-fx-font-size: 11px;");
        Label price = new Label(String.format("%.2f", product.getCost()) + " $");
        price.setStyle("-fx-text-fill: #4C6FFF;" + "-fx-font-size: 17px;" + "-fx-font-weight: bold;");
        Label rating = new Label("★ " + product.getAverageRating());
        rating.setStyle("-fx-text-fill: #F5A623;" + "-fx-font-size: 13px;");
        Label status = new Label(product.getStatus());
        status.setStyle("-fx-text-fill: #5E6C84;" + "-fx-font-size: 12px;");
        Region spacer = new Region();
        VBox.setVgrow(spacer, javafx.scene.layout.Priority.ALWAYS);
        Button detailsButton = new Button("View Details");
        detailsButton.setMaxWidth(Double.MAX_VALUE);
        detailsButton.setStyle("-fx-background-color: #4C6FFF;" + "-fx-text-fill: white;" + "-fx-font-weight: bold;" + "-fx-background-radius: 7;" + "-fx-cursor: hand;");
        detailsButton.setOnAction(event -> {
            openProductDetails(event, product);
        });
        card.getChildren().addAll(name, category, price, rating, status, spacer, detailsButton);
        return card;
    }

    private void updatePagination() {
        int pageCount = productController.getPageCount(filteredProducts);
        if (pageCount == 0) {
            pageLabel.setText("Page 0");
            paginationLabel.setText("0 / 0");
        } else {
            pageLabel.setText("Page " + currentPage);
            paginationLabel.setText(currentPage + " / " + pageCount);
        }
        previousButton.setDisable(currentPage <= 1);
        nextButton.setDisable(currentPage >= pageCount);
    }

    @FXML
    private void previousPage() {
        if (currentPage > 1) {
            currentPage--;
            updateProducts();
        }
    }

    @FXML
    private void nextPage() {
        int pageCount = productController.getPageCount(filteredProducts);
        if (currentPage < pageCount) {
            currentPage++;
            updateProducts();
        }
    }

    @FXML
    private void clearFilters() {
        searchField.clear();
        categoryCombo.setValue("All");
        statusCombo.setValue("All");
        minPriceField.clear();
        maxPriceField.clear();
        minRatingField.clear();
        maxRatingField.clear();
        cpuCombo.setValue(null);
        ramCombo.setValue(null);
        minCapacityField.clear();
        maxCapacityField.clear();
        minPagesField.clear();
        maxPagesField.clear();
        colorCombo.setValue(null);
        automaticCombo.setValue(null);
        minEngineField.clear();
        maxEngineField.clear();
        hideAllCategoryFilters();
        filteredProducts = productController.getAllProducts();
        currentPage = 1;
        updateProducts();
    }

    private Double parseDouble(String text) {
        if (text.isBlank()) {
            return null;
        }
        try {
            return Double.parseDouble(text.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private void openProductDetails(ActionEvent event, Product product) {
        try {
            FXMLLoader loader = SceneManager.loadFXML("ProductDetails.fxml");
            Parent root = loader.getRoot();
            ProductDetailsController controller = loader.getController();
            controller.setProduct(product);
            SceneManager.switchScene(event, root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void back(ActionEvent event) throws IOException {
        SceneManager.switchScene(event, "MainPage.fxml");
    }
}
