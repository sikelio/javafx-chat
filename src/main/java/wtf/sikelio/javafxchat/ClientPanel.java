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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.TextFlow;
import wtf.sikelio.javafxchat.client.Client;
import wtf.sikelio.javafxchat.common.Message;

public class ClientPanel extends Parent {
    private TextArea textToSend;
    private TextFlow receivedText;
    private ScrollPane scrollReceivedText;
    private Button sendBtn;
    private Button clearBtn;
    private Client client;

    public ClientPanel() {
        this.textToSend = new TextArea();
        this.textToSend.setLayoutX(50);
        this.textToSend.setLayoutY(425);
        this.textToSend.setPrefWidth(375);
        this.textToSend.setPrefHeight(25);
        this.textToSend.setPromptText("Message...");
        this.textToSend.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER && !textToSend.getText().trim().isEmpty()) {
                    Message message = new Message("User", textToSend.getText());

                    printNewMessage(message);
                    client.sendMessage(message);

                    textToSend.clear();
                }
            }
        });

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
                if (!textToSend.getText().trim().isEmpty()) {
                    Message message = new Message("User", textToSend.getText());

                    printNewMessage(message);
                    client.sendMessage(message);

                    textToSend.clear();
                }
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

                receivedText.getChildren().add(text);
            }
        });
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
