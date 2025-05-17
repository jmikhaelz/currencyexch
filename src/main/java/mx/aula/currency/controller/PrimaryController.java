package mx.aula.currency.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PrimaryController {
    @FXML
    private ImageView iconGithub, iconLinkedln;

    @FXML
    private void initialize() throws IOException {
        // Cargar la imagen desde el JAR o directorio
        try {
            Image githubIcon = new Image(getClass().getResourceAsStream("/mx/aula/currency/img/github-icon.png"));
            iconGithub.setImage(githubIcon);
            Image linkIcon = new Image(getClass().getResourceAsStream("/mx/aula/currency/img/linkedin-icon.png"));
            iconLinkedln.setImage(linkIcon);
            System.out.println("[PANEL] : Control de Divisas "
                    + "\n[JAVA] : PrimaryController");
        } catch (Exception e) {
            System.out.println("[WARNING] : " + e.getMessage());
        }
    }
}
