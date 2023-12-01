package wtf.sikelio.javafxchat;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.text.TextFlow;

public class ClientPanel extends Parent {
    public ClientPanel() {
        TextArea textToSend = new TextArea();
        textToSend.setLayoutX(50);
        textToSend.setLayoutY(425);
        textToSend.setPrefWidth(375);
        textToSend.setPrefHeight(25);
        textToSend.setPromptText("Message...");

        TextFlow receivedText = new TextFlow();

        ScrollPane scrollReceivedText = new ScrollPane();
        scrollReceivedText.setLayoutX(50);
        scrollReceivedText.setLayoutY(50);
        scrollReceivedText.setPrefWidth(375);
        scrollReceivedText.setPrefHeight(350);
        scrollReceivedText.setContent(receivedText);
        scrollReceivedText.vvalueProperty().bind(receivedText.heightProperty());

        Button sendBtn = new Button();
        sendBtn.setVisible(true);
        sendBtn.setLayoutX(250);
        sendBtn.setLayoutY(485);
        sendBtn.setPrefWidth(175);
        sendBtn.setPrefHeight(25);
        sendBtn.setText("Send");

        Button clearBtn = new Button();
        clearBtn.setVisible(true);
        clearBtn.setLayoutX(50);
        clearBtn.setLayoutY(485);
        clearBtn.setPrefWidth(175);
        clearBtn.setPrefHeight(25);
        clearBtn.setText("Clear");

        this.getChildren().add(scrollReceivedText);
        this.getChildren().add(textToSend);
        this.getChildren().add(clearBtn);
        this.getChildren().add(sendBtn);
    }
}
