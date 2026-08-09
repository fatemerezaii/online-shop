package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneManager {

    public static void switchScene(ActionEvent event, String fxmlFile) throws IOException {
        Parent root = FXMLLoader.load(SceneManager.class.getResource("/com/example/onlineShop/" + fxmlFile));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void switchScene(ActionEvent event, Parent root) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static FXMLLoader loadFXML(String fxmlFile) throws IOException {
        FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource("/com.example.onlineShop/" + fxmlFile));
        loader.load();
        return loader;
    }
}
