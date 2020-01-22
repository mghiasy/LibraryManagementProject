package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;

public class OtherMain extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("newLogin.fxml"));
        Scene scene = new Scene(root, 700, 450);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Welcome");
        primaryStage.show();
    }

    public static void main(String[] args) {

        launch(args);
    }
}
