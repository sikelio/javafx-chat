package wtf.sikelio.javafxchat;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainGui extends Application {
    public static void main(String[] args) {
        Application.launch(MainGui.class, args);
    }

    public void start(Stage stage) throws Exception {
        ClientPanel clientPanel = new ClientPanel();

        Group root = new Group();
        root.getChildren().add(clientPanel);

        Scene scene = new Scene(root);
        stage.setWidth(500);
        stage.setHeight(600);
        stage.setTitle("JavaFX Chat - Sikelio");
        stage.setScene(scene);
        stage.show();
    }
}
