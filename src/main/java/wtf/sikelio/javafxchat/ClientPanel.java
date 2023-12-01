package wtf.sikelio.javafxchat;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.text.TextFlow;
import wtf.sikelio.javafxchat.common.Message;

public class ClientPanel extends Parent {
    TextArea textToSend;
    TextFlow receivedText;
    ScrollPane scrollReceivedText;
    Button sendBtn;
    Button clearBtn;

    public ClientPanel() {
        this.textToSend = new TextArea();
        this.textToSend.setLayoutX(50);
        this.textToSend.setLayoutY(425);
        this.textToSend.setPrefWidth(375);
        this.textToSend.setPrefHeight(25);
        this.textToSend.setPromptText("Message...");

        this.receivedText = new TextFlow();
        this.receivedText.setPrefWidth(325);

        this.scrollReceivedText = new ScrollPane();
        this.scrollReceivedText.setLayoutX(50);
        this.scrollReceivedText.setLayoutY(50);
        this.scrollReceivedText.setPrefWidth(375);
        this.scrollReceivedText.setPrefHeight(350);
        this.scrollReceivedText.setContent(receivedText);
        this.scrollReceivedText.vvalueProperty().bind(receivedText.heightProperty());

        this.sendBtn = new Button();
        this.sendBtn.setVisible(true);
        this.sendBtn.setLayoutX(250);
        this.sendBtn.setLayoutY(485);
        this.sendBtn.setPrefWidth(175);
        this.sendBtn.setPrefHeight(25);
        this.sendBtn.setText("Send");
        this.sendBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                printNewMessage(new Message("User", textToSend.getText()));

                textToSend.clear();
            }
        });

        this.clearBtn = new Button();
        this.clearBtn.setVisible(true);
        this.clearBtn.setLayoutX(50);
        this.clearBtn.setLayoutY(485);
        this.clearBtn.setPrefWidth(175);
        this.clearBtn.setPrefHeight(25);
        this.clearBtn.setText("Clear");
        this.clearBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                textToSend.clear();
            }
        });

        this.getChildren().add(scrollReceivedText);
        this.getChildren().add(textToSend);
        this.getChildren().add(clearBtn);
        this.getChildren().add(sendBtn);
    }

    public void printNewMessage(Message message) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Label text = new Label("\n" + message);
                text.setPrefWidth(receivedText.getPrefWidth() - 20);
                text.setAlignment(Pos.CENTER_LEFT);

                System.out.println(text.getText());

                receivedText.getChildren().add(text);
            }
        });
    }
}