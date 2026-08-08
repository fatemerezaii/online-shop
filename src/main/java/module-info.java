module com.example.onlineShop {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.onlineShop to javafx.fxml;
    opens view to javafx.fxml;

    exports com.example.onlineShop;
}