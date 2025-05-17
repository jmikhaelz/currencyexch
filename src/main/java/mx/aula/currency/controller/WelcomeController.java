package mx.aula.currency.controller;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import mx.aula.currency.App;
import mx.aula.currency.util.CleanTerm;

import java.io.IOException;

public class WelcomeController {

    @FXML
    private ImageView apiImage, cursoImage, aulaImage, iconGithub, iconLinkedln;

    @FXML
    private void initialize() throws IOException {
        // Cargar la imagen desde el JAR o directorio
        try {
            Image apiIcon = new Image(getClass().getResourceAsStream("/mx/aula/currency/img/api-icon.png"));
            apiImage.setImage(apiIcon);
            Image cursoIcon = new Image(getClass().getResourceAsStream("/mx/aula/currency/img/course-icon.png"));
            cursoImage.setImage(cursoIcon);
            Image aulaIcon = new Image(getClass().getResourceAsStream("/mx/aula/currency/img/aula-icon.png"));
            aulaImage.setImage(aulaIcon);
            Image githubIcon = new Image(getClass().getResourceAsStream("/mx/aula/currency/img/github-icon.png"));
            iconGithub.setImage(githubIcon);
            Image linkIcon = new Image(getClass().getResourceAsStream("/mx/aula/currency/img/linkedin-icon.png"));
            iconLinkedln.setImage(linkIcon);

            new CleanTerm().start();
            System.out.println("[PANEL] : Bienvenida "
                    + "\n[JAVA] : WelcomeController");
        } catch (Exception e) {
            System.out.println("[WARNING] : " + e.getMessage());
        }
    }

    @FXML
    private void ControllerPrimary() throws IOException {
        App.setRoot("PrimaryController");
    }
}
