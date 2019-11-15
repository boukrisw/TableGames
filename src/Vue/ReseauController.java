package Vue;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import static javafx.application.Platform.exit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

//import static Vue.TableController.jeu;

/**
 * FXML Controller class
 *
 * @author walid
 */
public class ReseauController implements Initializable{

    @FXML
    private Button Joindre_reseau;
    @FXML
    private TextArea NPortJoindre;
    @FXML
    private Button Creer_reseau;
    @FXML
    private Label NPortCreer;
    @FXML
    private Button Accueil;
    @FXML
    private Button Quiter;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private TextArea nomJoueur;

    public static boolean creer=false;
    public static boolean joindre=false;
    
    public static String NameJ;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleJoindre_reseau(ActionEvent event) throws IOException {
            joindre=true;
            NameJ=nomJoueur.getText();
            FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemClassLoader().getResource("Vue/table.fxml"));
            AnchorPane pane = loader.load();
            rootPane.getChildren().setAll(pane);
    }

    @FXML
    private void handleCreer_reseau(ActionEvent event) throws IOException {
            creer=true;
            NameJ=nomJoueur.getText();
            FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemClassLoader().getResource("Vue/table.fxml"));
            AnchorPane pane = loader.load();
            rootPane.getChildren().setAll(pane);
    }
    

    @FXML
    private void handleAccueil(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemClassLoader().getResource("Vue/accueil.fxml"));
        AnchorPane pane = loader.load();
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    private void handleQuitter(ActionEvent event) {
                exit();
    }
}
