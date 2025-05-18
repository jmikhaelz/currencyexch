package mx.aula.currency.controller;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import mx.aula.currency.client.exchangerateClient;
import mx.aula.currency.models.CurrencyCode;
import mx.aula.currency.models.InfoChange;
import mx.aula.currency.service.ExchangerateServiceImpl;

public class PrimaryController {

    private static final Logger logger = Logger.getLogger(PrimaryController.class.getName());

    private final exchangerateClient client = new exchangerateClient();
    private final ExchangerateServiceImpl service = new ExchangerateServiceImpl();

    private List<CurrencyCode> currencyList;

    @FXML
    private TextField amountInput;

    @FXML
    private ComboBox<CurrencyCode> fromCurrency;

    @FXML
    private ComboBox<CurrencyCode> toCurrency;

    @FXML
    private Label conversionLabel;

    @FXML
    private Label convertedAmount;

    @FXML
    private Label convertedAmountInv;

    @FXML
    private ImageView iconGithub;

    @FXML
    public void initialize() {
        try {
            logger.info("[PANEL] : Control de Divisas [JAVA] : PrimaryController");

            // Cargar imagen
            Image image = new Image(getClass().getResourceAsStream("/mx/aula/currency/img/github-icon.png"));
            iconGithub.setImage(image);

            // Llamada a la API para obtener monedas
            String json = client.getCurrency();
            currencyList = service.parseCurrencies(json);

            if (currencyList != null) {
                fromCurrency.getItems().addAll(currencyList);
                toCurrency.getItems().addAll(currencyList);
                String targetCodeFrom = "MXN";
                String targetCodeTo = "BRL";
                fromCurrency.getSelectionModel().select(currencyList.stream()
                        .filter(currency -> currency.code().equals(targetCodeFrom))
                        .findFirst()
                        .orElse(null));

                toCurrency.getSelectionModel().select(currencyList.stream()
                        .filter(currency -> currency.code().equals(targetCodeTo))
                        .findFirst()
                        .orElse(null));

            } else {
                logger.severe("[currencyList] null");
                showExit("Error en conseguir informacion [currencyList]");
            }

        } catch (Exception e) {
            logger.warning("[initialize] " + e.getMessage());
            showError("Error al inicializar la vista.");
        }
    }

    @FXML
    private void handleConvertAction() throws InterruptedException, IOException {
        try {
            double amount = Double.parseDouble(amountInput.getText());

            if (amount <= 0) {
                throw new NumberFormatException("Ingrese numeros positivos y mayores de 0");
            }
            CurrencyCode from = fromCurrency.getValue();
            CurrencyCode to = toCurrency.getValue();

            if (from == null || to == null) {
                showError("Seleccione monedas válidas.");
                return;
            }

            String json = client.getChange(from.code(), to.code());
            InfoChange info = service.getChange(json);

            if (info != null) {
                double converted = amount * info.conversion_rate();
                convertedAmount.setText(String.format("%.4f %s", converted, to.code()));
                conversionLabel.setText("1 " + from.code() + " = " + info.conversion_rate() + " " + to.code());
                convertedAmountInv
                        .setText(String.format("1 %s = %.4f %s", to.code(), (1 / info.conversion_rate()), from.code()));
            } else {
                logger.severe("[infoChange] null");
                showError("No se pudo obtener la tasa de conversión.");
            }

        } catch (NumberFormatException e) {
            cleanLabel();
            logger.warning("[input] No number");
            showError("Ingrese un valor numérico válido.");
        }
    }

    @FXML
    private void handleSwitchAction() throws InterruptedException, IOException {
        CurrencyCode from = fromCurrency.getValue();
        CurrencyCode to = toCurrency.getValue();
        fromCurrency.setValue(to);
        toCurrency.setValue(from);
        handleConvertAction();
    }

    private void showError(String message) {
        Platform.runLater(() -> {
            convertedAmount.setText("");
            conversionLabel.setText("");
            convertedAmountInv.setText(message);
        });
    }

    private void showExit(String message) {
        Platform.runLater(() -> {
            Stage stage = (Stage) convertedAmount.getScene().getWindow();
            stage.hide();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.show();
            // Crear un hilo para cerrar la aplicación después de N segundos
            new Thread(() -> {
                try {
                    Thread.sleep(5000);
                    Platform.exit();
                    System.exit(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        });
    }

    private void cleanLabel() {
        Platform.runLater(() -> {
            convertedAmount.setText("");
            conversionLabel.setText("");
            convertedAmountInv.setText("");
        });
    }
}
