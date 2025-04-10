package org.bernard.myappjavafx;



import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainApplication extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/UserView.fxml")); // Assure-toi que le fichier est bien dans resources/views/
            AnchorPane root = loader.load();
            Scene scene = new Scene(root, 600, 400);

            primaryStage.setTitle("Gestion des Utilisateurs");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
