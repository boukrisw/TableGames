import Vue.*;
import Modele.*;
import Controleur.*;

import Modele.XO.*;
import Modele.P4.*;
import Modele.Dames.*;


import java.io.InputStream;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import javafx.scene.layout.Priority;
import javafx.stage.WindowEvent;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.image.Image;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import javafx.event.ActionEvent;

import javafx.scene.control.TextArea.*;


public class InterfaceGraphique extends Application{

  public Jeu jeu= new JeuXO();
  public Jeu jeuXO = new JeuXO();
  public Jeu jeuP4 = new JeuXO();
  public Jeu jeuDames = new JeuXO();

  @Override
  public void start(Stage primaryStage){



          FenetreGraphique f = new FenetreGraphique(jeu,primaryStage);
          Controleur c= new Controleur(jeu,f);

          f.ecouteurBAccueil(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
              //control!!!!!
              f.page = 1;
              f.miseAJour();
            }
          });

          f.ecouteurBNewGame(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
              //control!!!!!
              f.page = 2;
              f.miseAJour();
            }
          });

          f.ecouteurBCommencer(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
              //control!!!!!
              f.page = 3;
              f.miseAJour();

            }
          });

          f.ecouteurBXO(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
              //control!!!!!
              f.page = 3;
              jeu=jeuXO;
              //jeu=jeu.initJeu("XO");

              f.CurrentGame="XO";
              f.miseAJour();

            }
          });
          f.ecouteurBP4(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
              //control!!!!!
              f.page = 3;
              jeu=jeuP4;
              //jeu=jeu.initJeu("P4");

              f.CurrentGame="P4";
              f.miseAJour();

            }
          });

          f.ecouteurBDames(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
              //control!!!!!
              f.page = 3;
              jeu=jeuDames;
              //jeu=jeu.initJeu("Dames");

              f.CurrentGame="Dames";
              f.miseAJour();

            }
          });

          f.ecouteurBQuitter(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
              //control!!!!!
              System.exit(0);
            }
          });

          f.ecouteurDeRedimensionnement(new ChangeListener<Number>() {
      			@Override
      			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
      				//c.redimensionnement();
      			}
      		});

          f.ecouteurDeSouris(new EventHandler<MouseEvent>() {
      			@Override
      			public void handle(MouseEvent e) {
      				c.clicSouris(e.getX(), e.getY());
              f.miseAJour();
            }
      		});
  }
}
