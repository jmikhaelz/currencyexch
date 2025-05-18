package mx.aula.currency;

import java.io.IOException;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mx.aula.currency.util.CleanTerm;

/**
 * JavaFX App
 */
public class App extends Application {
    private static final Logger logger = Logger.getLogger(App.class.getName());

    private static Scene scene;

    @SuppressWarnings("exports")
    @Override
    public void start(Stage stage) throws IOException {
        try {
            scene = new Scene(loadFXML("WelcomeController"), 900, 600);
            stage.setTitle("Currency Exchange ONE-NEXT-G8");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            logger.warning("[Start] " + e.getMessage());
        }
    }

    public static void setRoot(String fxml) throws IOException {
        Parent root = loadFXML(fxml);
        if (scene != null) {
            scene.setRoot(root);
        } else {
            logger.severe("Intento de cambiar escena antes de inicializar `scene`.");
        }
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        new CleanTerm().start();
        logger.info("[Panel] App");
        launch();
    }
}
