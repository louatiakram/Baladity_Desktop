package edu.esprit.tests;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainFX extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/AfficherEvent.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Afficher les events");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
