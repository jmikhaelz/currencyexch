package mx.aula.currency.controller;

import java.io.IOException;
import java.util.logging.Logger;
import javafx.concurrent.Task;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import mx.aula.currency.App;

public class WelcomeController {
    private static final Logger logger = Logger.getLogger(WelcomeController.class.getName());
    @FXML
    private ImageView apiImage, cursoImage, aulaImage, iconGithub, iconLinkedln;
    @FXML
    private ProgressIndicator progressIndicator;
    @FXML
    private Label progressLabel;

    @FXML
    private void initialize() throws IOException {
        // Cargar la imagen desde el JAR o directorio
        try {
            setImage(apiImage, "/mx/aula/currency/img/api-icon.png");
            setImage(cursoImage, "/mx/aula/currency/img/course-icon.png");
            setImage(aulaImage, "/mx/aula/currency/img/aula-icon.png");
            setImage(iconGithub, "/mx/aula/currency/img/github-icon.png");
            setImage(iconLinkedln, "/mx/aula/currency/img/linkedin-icon.png");
            logger.info("[JAVA] : WelcomeController");
            TransitionScene("PrimaryController");
        } catch (Exception e) {
            logger.warning("[initialize] " + e.getMessage());
        }
    }

    @SuppressWarnings("unused")
    private void TransitionScene(String fxml) {
        Task<Void> task = new Task<>() {
            @Override
            protected Void call() {
                for (double i = 0; i <= 1.3; i += 0.1) {
                    try {
                        Thread.sleep(150); // Simula la carga
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    final double progress = i;
                    Platform.runLater(() -> {
                        progressIndicator.setProgress(progress);
                    });
                }
                return null;
            }
        };

        task.setOnSucceeded(event -> {
            Platform.runLater(() -> {
                try {
                    App.setRoot(fxml);
                } catch (IOException e) {
                    logger.warning("[TransitionScene] " + e.getMessage());
                }
            });
        });

        new Thread(task).start();
    }

    private void setImage(ImageView imageView, String path) {
        try {
            Image image = new Image(getClass().getResourceAsStream(path));
            imageView.setImage(image);
        } catch (Exception e) {
            logger.warning("[IMAGEN] " + e.getMessage());
        }
    }

}
