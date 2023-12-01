module wtf.sikelio.javafxchat {
    requires javafx.controls;
    requires javafx.fxml;


    opens wtf.sikelio.javafxchat to javafx.fxml;
    exports wtf.sikelio.javafxchat;
}