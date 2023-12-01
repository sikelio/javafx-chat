package wtf.sikelio.javafxchat;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import wtf.sikelio.javafxchat.client.Client;

public class MainGui extends Application {
    public static void main(String[] args) {
        Application.launch(MainGui.class, args);
    }

    public void start(Stage stage) throws Exception {
        Client client = new Client("127.0.0.1", 5001);
        ClientPanel clientPanel = new ClientPanel();

        client.setView(clientPanel);
        clientPanel.setClient(client);

        Group root = new Group();
        root.getChildren().add(clientPanel);

        Scene scene = new Scene(root);
        stage.getIcons().add(new Image(MainGui.class.getResourceAsStream("/logo.png")));
        stage.setWidth(500);
        stage.setHeight(600);
        stage.setTitle("JavaFX Chat - Sikelio");
        stage.setScene(scene);
        stage.show();
    }
}
