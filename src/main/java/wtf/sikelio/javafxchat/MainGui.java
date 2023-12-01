package wtf.sikelio.javafxchat;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainGui extends Application {
    public static void main(String[] args) {
        Application.launch(MainGui.class, args);
    }

    public void start(Stage stage) throws Exception {
        Text text = new Text(10, 30, "Lorem Ipsum");

        Group root = new Group();

        root.getChildren().add(text);

        Scene scene = new Scene(root);

        stage.setWidth(500);
        stage.setHeight(500);
        stage.setTitle("JavaFX Chat - Sikelio");
        stage.setScene(scene);
        stage.show();
    }
}
