package Vue;

import java.io.IOException;
import javafx.scene.control.Button;
import java.net.URL;
import java.util.ResourceBundle;
import static javafx.application.Platform.exit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.layout.AnchorPane;

public class AccueilController implements Initializable {

    @FXML
    private AnchorPane rootPane;


    @FXML
    private Button XO;
    @FXML
    private Button P4;
    @FXML
    private Button Dames;
    @FXML
    private Button Quitter;
    @FXML
    private Button j2048;
    @FXML
    private Button Othello;
    
    public static String CurrentGame;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //setToolTip();
    }

    @FXML
    private void handlebuttonXO(ActionEvent event) throws IOException {
            CurrentGame="XO";
            FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemClassLoader().getResource("Vue/jouer.fxml"));
            AnchorPane pane = loader.load();
            rootPane.getChildren().setAll(pane);

    }

    @FXML
    private void handlebuttonP4(ActionEvent event) throws IOException {
            CurrentGame="P4";
            FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemClassLoader().getResource("Vue/jouer.fxml"));
            AnchorPane pane = loader.load();
            rootPane.getChildren().setAll(pane);
    }

    @FXML
    private void handelbuttonDames(ActionEvent event) throws IOException {
            CurrentGame="Dames";
            FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemClassLoader().getResource("Vue/jouer.fxml"));
            AnchorPane pane = loader.load();
            rootPane.getChildren().setAll(pane);
    }

    @FXML
    private void handelbutton2048(ActionEvent event) throws IOException {
            CurrentGame="2048";
            FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemClassLoader().getResource("Vue/table.fxml"));
            AnchorPane pane = loader.load();
            rootPane.getChildren().setAll(pane);
    }
    
    @FXML
    private void handlebuttonOthello(ActionEvent event) throws IOException {
            CurrentGame="Othello";
            FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemClassLoader().getResource("Vue/jouer.fxml"));
            AnchorPane pane = loader.load();
            rootPane.getChildren().setAll(pane);
    }
    
    @FXML
    private void handelbuttonQuitter(ActionEvent event) {
            exit();
    }

    /*public void setToolTip(){
            Tooltip.install(XO, new Tooltip("XO"));
            Tooltip.install(P4, new Tooltip("P4"));
            Tooltip.install(Dames, new Tooltip("Dames"));
            Tooltip.install(Quitter, new Tooltip("Quitter"));
    }*/

    

    
}
