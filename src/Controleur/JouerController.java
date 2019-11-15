package Controleur;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import static javafx.application.Platform.exit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class JouerController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private ComboBox choixJoueur1;
    @FXML
    private ComboBox choixJoueur2;
    @FXML
    private Button Commencer;
    @FXML
    private Button Jouer_Resaux;
    @FXML
    private Button Quitter;
    @FXML
    private Button Accueil;
    
    private ObservableList<String> choixJoueurList = FXCollections
        .observableArrayList("Joueur 1","Joueur 2", "IA Facile", "IA Moyenne", "IA Experte");
    
    
    public static String Player1="";
    public static String Player2="";
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        choixJoueur1.setItems(choixJoueurList);
        choixJoueur2.setItems(choixJoueurList);
        
        choixJoueur1.setPromptText("Nom joueur 1");
        choixJoueur2.setPromptText("Nom joueur 2");
        // TODO
    }

    @FXML
    private void handleButtonCommencer(ActionEvent event) throws IOException {
            Player1=(String)choixJoueur1.getEditor().getText();
            Player2=(String)choixJoueur2.getEditor().getText();
            FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemClassLoader().getResource("Vue/table.fxml"));
            AnchorPane pane = loader.load();
            rootPane.getChildren().setAll(pane);
    }
    
    @FXML
    private void handleJouer_Resaux(ActionEvent event) throws IOException {
            FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemClassLoader().getResource("Vue/Reseau.fxml"));
            AnchorPane pane = loader.load();
            rootPane.getChildren().setAll(pane);
    }

    @FXML
    private void handleButtonAccueil(ActionEvent event) throws IOException {
            FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemClassLoader().getResource("Vue/accueil.fxml"));
            AnchorPane pane = loader.load();
            rootPane.getChildren().setAll(pane);
    }

    @FXML
    private void handleButtonQuitter(ActionEvent event) {
            exit();
    }

    
}
