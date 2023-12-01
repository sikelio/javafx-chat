module wtf.sikelio.javafxchat {
    requires javafx.controls;
    requires javafx.fxml;


    opens wtf.sikelio.javafxchat to javafx.fxml;
    exports wtf.sikelio.javafxchat;
    exports wtf.sikelio.javafxchat.client;
    exports wtf.sikelio.javafxchat.server;
    exports wtf.sikelio.javafxchat.common;
}