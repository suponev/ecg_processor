package sample;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../../resources/sample.fxml"));
        stage.setTitle("Hello World");
        Scene scene = new Scene(root, 800, 800);
        String css = getClass().getResource("../../resources/chart.style.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
