module mx.aula.currency {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;
    requires java.logging;

    opens mx.aula.currency to javafx.fxml;
    opens mx.aula.currency.controller to javafx.fxml;

    exports mx.aula.currency;
}
