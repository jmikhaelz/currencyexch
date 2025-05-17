module mx.aula.currency {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;

    opens mx.aula.currency to javafx.fxml;
    opens mx.aula.currency.controller to javafx.fxml;

    exports mx.aula.currency;
}
