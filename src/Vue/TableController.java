package Vue;


import static Vue.ReseauController.creer;
import static Vue.ReseauController.joindre;

import static Vue.AccueilController.CurrentGame;
import static Controleur.JouerController.Player1;
import static Controleur.JouerController.Player2;
import static Vue.ReseauController.NameJ;

import Controleur.ImageLoader;
import Modele.*;

import Modele.XO.*;
import Modele.P4.*;
import Modele.Dames.*;
import Modele.j2048.*;
import Modele.Othello.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import java.io.IOException;
import static java.lang.Double.min;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Platform.exit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class TableController implements Initializable,Runnable {
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private Canvas canvas;
    @FXML
    private Button Accueil;
    @FXML
    private Button Quitter;

    public BoxBlur blur;
    public static Jeu jeu;
    public double largeurCase, hauteurCase;
    public double videlargeur, videhauteur;
    public static GraphicsContext gc;
    //Les Images
    public ImageLoader il = new ImageLoader("/Ressources/");
    public ImageView imgX = il.loadImageView("X.png");
    public ImageView imgO = il.loadImageView("O.png");
    public ImageView imgBlack = il.loadImageView("black.png");
    public ImageView img0 = il.loadImageView("0.png");
    public ImageView img2 = il.loadImageView("2.png");
    public ImageView img4 = il.loadImageView("4.png");
    public ImageView img8 = il.loadImageView("8.png");
    public ImageView img16 = il.loadImageView("16.png");
    public ImageView img32 = il.loadImageView("32.png");
    public ImageView img64 = il.loadImageView("64.png");
    public ImageView img128 = il.loadImageView("128.png");
    public ImageView img256 = il.loadImageView("256.png");
    public ImageView img512= il.loadImageView("512.png");
    public ImageView img1024 = il.loadImageView("1024.png");
    public ImageView img2048 = il.loadImageView("2048.png");
    public ImageView imgXred = il.loadImageView("Xred.png");
    public ImageView imgOred = il.loadImageView("Ored.png");

    public int DamesX=-1;
    public int DamesY=-1;
    //Reseau
    private String ip = "localhost";
    private int port = 22122;
    private Thread thread;
    private Socket socket;
    private DataOutputStream dos;
    private DataInputStream dis;
    private ServerSocket serverSocket;
    private boolean yourTurn=false;
    private boolean accepted=false;
    public  boolean reseau=false;
    @FXML
    private Label LabelGagnant;
    @FXML
    private Button AccueilG;
    @FXML
    private Button QuitterG;
    @FXML
    private Button RejouerG;
    @FXML
    private DialogPane dialogPaneVictoire;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

            dialogPaneVictoire.setVisible(false);
            // TODO
            if(CurrentGame=="XO"){
              jeu = new JeuXO();
            }else if(CurrentGame.equals("P4")){
              jeu = new JeuP4();
            }else if(CurrentGame=="Dames"){
              jeu = new JeuDames();
            }else if(CurrentGame=="2048"){
              jeu = new Jeu2048();
            }else if(CurrentGame=="Othello"){
              jeu = new JeuOthello();
            }
            
            if(creer){
                
                if (!connect()){
                    try {
                        serverSocket = new ServerSocket(port, 8, InetAddress.getByName(ip));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    yourTurn = true;
                }
                jeu.player1.setName(NameJ);
                label1.setText(NameJ);
                reseau=true;
                thread = new Thread(this, "table");
                thread.start();
            }else if (joindre){
                
                jeu.player2.setName(NameJ);
                label2.setText(NameJ);
                reseau=true;
                
                if (connect()){
                    thread = new Thread(this, "Table");
                    thread.start();
                }
            }else if(CurrentGame!="2048"){
                jeu.player1.setName(Player1);
                label1.setText(Player1);
                jeu.player2.setName(Player2);
                label2.setText(Player2);
            }
            miseAJour();
    }
    
    public double largeurCase() {
            return largeurCase;
    }

    public double hauteurCase() {
        return hauteurCase;
    }

    public double largeur() {
            return canvas.getWidth();
    }

    public double hauteur() {
            return canvas.getHeight();
    }
    
    public void changeColor(){
        if(jeu.jCourant.equals(jeu.player1)){
            label2.setDisable(true);
            label1.setDisable(false);
        }else{
            label2.setDisable(false);
            label1.setDisable(true);
        }
    }
    
    ///////////////////Dessin des plateaux de jeu\\\\\\\\\\\\\\\\\\\\ 
    public void miseAJour(){
            if(CurrentGame=="XO"){
              DessinerXO();
            }else if(CurrentGame.equals("P4")){
              DessinerP4();
            }else if(CurrentGame=="Dames"){
              DessinerDames();
            }else if(CurrentGame=="2048"){
              Dessiner2048();
            }else if(CurrentGame=="Othello"){
              DessinerOthello();
            }
            
            if (CurrentGame!="2048")changeColor();
            if(jeu.finJeu()){
                LabelGagnant.setText(jeu.winner);
                dialogPaneVictoire.setVisible(true);
                rootPane.setEffect(blur);
            }
    }
    
    
    public void DessinerOthello(){
            gc = canvas.getGraphicsContext2D();
            gc.clearRect(0, 0, largeur(), hauteur());

            largeurCase=min(canvas.getHeight()/(8),canvas.getWidth()/(8));
            hauteurCase=largeurCase;
            videlargeur=canvas.getWidth()-largeurCase*8;
            videhauteur=canvas.getHeight()-hauteurCase*8;
            videlargeur=videlargeur/2;
            videhauteur=videhauteur/2;
            
            for(int i=0;i<=8;i++){
              gc.strokeLine(videlargeur+largeurCase*i,videhauteur,videlargeur+largeurCase*i,videhauteur+largeurCase*8);
            }
            for(int i=0;i<=8;i++){
              gc.strokeLine(videlargeur,videhauteur+largeurCase*i,videlargeur+largeurCase*8,videhauteur+largeurCase*i);
            }
            
            for (int i = 0; i < jeu.plateau.LIGNE; i++) {
              for (int j = 0; j < jeu.plateau.COLONNE; j++) {
                if(jeu.plateau.get_Val(i,j)==1){
                  gc.drawImage(imgX.getImage(), videlargeur+largeurCase*i, videhauteur+largeurCase*j, largeurCase, largeurCase);
                }else if(jeu.plateau.get_Val(i,j)==2){
                  gc.drawImage(imgO.getImage(), videlargeur+largeurCase*i, videhauteur+largeurCase*j, largeurCase, largeurCase);
                }
              }
            }
    }

    public void DessinerXO(){
            gc = canvas.getGraphicsContext2D();
            gc.clearRect(0, 0, largeur(), hauteur());

            largeurCase=min(canvas.getHeight()/(3),canvas.getWidth()/(3));
            hauteurCase=largeurCase;
            videlargeur=canvas.getWidth()-largeurCase*3;
            videhauteur=canvas.getHeight()-hauteurCase*3;
            videlargeur=videlargeur/2;
            videhauteur=videhauteur/2;

            for(int i=0;i<=3;i++){
              gc.strokeLine(videlargeur+largeurCase*i,videhauteur,videlargeur+largeurCase*i,videhauteur+largeurCase*3);
            }
            for(int i=0;i<=3;i++){
              gc.strokeLine(videlargeur,videhauteur+largeurCase*i,videlargeur+largeurCase*3,videhauteur+largeurCase*i);
            }

            for (int i = 0; i < jeu.plateau.LIGNE; i++) {
              for (int j = 0; j < jeu.plateau.COLONNE; j++) {
                if(jeu.plateau.get_Val(i,j)==1){
                  gc.drawImage(imgX.getImage(), videlargeur+largeurCase*i, videhauteur+largeurCase*j, largeurCase, largeurCase);
                }else if(jeu.plateau.get_Val(i,j)==2){
                  gc.drawImage(imgO.getImage(), videlargeur+largeurCase*i, videhauteur+largeurCase*j, largeurCase, largeurCase);
                }
              }
            }
    }

    public void DessinerP4(){
            largeurCase=min(canvas.getHeight()/(6),canvas.getWidth()/(7));
            hauteurCase=largeurCase;
            videlargeur=canvas.getWidth()-largeurCase*7;
            videhauteur=canvas.getHeight()-hauteurCase*6;
            videlargeur=videlargeur/2;
            videhauteur=videhauteur/2;

            gc = canvas.getGraphicsContext2D();
            gc.setFill(Color.WHITE);
            gc.fillRect(0, 0, largeur(), hauteur());

            for(int i=0;i<=7;i++){
              gc.strokeLine(videlargeur+largeurCase*i,videhauteur,videlargeur+largeurCase*i,videhauteur+largeurCase*7);
            }
            for(int i=0;i<=6;i++){
              gc.strokeLine(videlargeur,videhauteur+largeurCase*i,videlargeur+largeurCase*7,videhauteur+largeurCase*i);
            }
            for (int i = 0; i < jeu.plateau.LIGNE; i++) {
              for (int j = 0; j < jeu.plateau.COLONNE; j++) {
                if(jeu.plateau.get_Val(i,j)==1){
                  gc.drawImage(imgX.getImage(),videlargeur+ largeurCase*j, videhauteur+largeurCase*i, largeurCase, largeurCase);
                }else if(jeu.plateau.get_Val(i,j)==2){
                  gc.drawImage(imgO.getImage(),videlargeur+ largeurCase*j,videhauteur+ largeurCase*i, largeurCase, largeurCase);
                }
              }
            }
    }

    public void DessinerDames(){
            largeurCase=min(canvas.getHeight()/(10),canvas.getWidth()/(10));
            hauteurCase=largeurCase;
            videlargeur=canvas.getWidth()-largeurCase*10;
            videhauteur=canvas.getHeight()-hauteurCase*10;
            videlargeur=videlargeur/2;
            videhauteur=videhauteur/2;

            gc = canvas.getGraphicsContext2D();

            gc.setFill(Color.WHITE);
            gc.fillRect(0, 0, largeur(), hauteur());

            for(int i=0;i<10;i++){
              for(int j=0;j<10;j++){
                if((i+j)%2!=0)gc.drawImage(imgBlack.getImage(), videlargeur+largeurCase*j,videhauteur+ largeurCase*i, largeurCase, largeurCase);


                if(jeu.plateau.get_Val(i,j)==1)gc.drawImage(imgX.getImage(), videlargeur+largeurCase*j, videhauteur+largeurCase*i, largeurCase, largeurCase);
                else if(jeu.plateau.get_Val(i,j)==2)gc.drawImage(imgO.getImage(), videlargeur+largeurCase*j,videhauteur+ largeurCase*i, largeurCase, largeurCase);
                else if(jeu.plateau.get_Val(i,j)==3)gc.drawImage(imgXred.getImage(), videlargeur+largeurCase*j,videhauteur+ largeurCase*i, largeurCase, largeurCase);
                else if(jeu.plateau.get_Val(i,j)==4)gc.drawImage(imgOred.getImage(), videlargeur+largeurCase*j,videhauteur+ largeurCase*i, largeurCase, largeurCase);
              }
            }
    }


    public void Dessiner2048(){
          gc = canvas.getGraphicsContext2D();
          gc.clearRect(0, 0, largeur(), hauteur());

          largeurCase=min(canvas.getHeight()/(4),canvas.getWidth()/(4));
          hauteurCase=largeurCase;
          videlargeur=canvas.getWidth()-largeurCase*4;
          videhauteur=canvas.getHeight()-hauteurCase*4;
          videlargeur=videlargeur/2;
          videhauteur=videhauteur/2;

          for(int i=0;i<=4;i++){
            gc.strokeLine(videlargeur+largeurCase*i,videhauteur,videlargeur+largeurCase*i,videhauteur+largeurCase*4);
          }
          for(int i=0;i<=4;i++){
            gc.strokeLine(videlargeur,videhauteur+largeurCase*i,videlargeur+largeurCase*4,videhauteur+largeurCase*i);
          }
          for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
              if(jeu.plateau.get_Val(i,j)==0)gc.drawImage(img0.getImage(), videlargeur+largeurCase*j, videhauteur+largeurCase*i, largeurCase, largeurCase);
              else if(jeu.plateau.get_Val(i,j)==2)gc.drawImage(img2.getImage(), videlargeur+largeurCase*j,videhauteur+ largeurCase*i, largeurCase, largeurCase);
              else if(jeu.plateau.get_Val(i,j)==4)gc.drawImage(img4.getImage(), videlargeur+largeurCase*j,videhauteur+ largeurCase*i, largeurCase, largeurCase);
              else if(jeu.plateau.get_Val(i,j)==8)gc.drawImage(img8.getImage(), videlargeur+largeurCase*j,videhauteur+ largeurCase*i, largeurCase, largeurCase);
              else if(jeu.plateau.get_Val(i,j)==16)gc.drawImage(img16.getImage(), videlargeur+largeurCase*j,videhauteur+ largeurCase*i, largeurCase, largeurCase);
              else if(jeu.plateau.get_Val(i,j)==32)gc.drawImage(img32.getImage(), videlargeur+largeurCase*j,videhauteur+ largeurCase*i, largeurCase, largeurCase);
              else if(jeu.plateau.get_Val(i,j)==64)gc.drawImage(img64.getImage(), videlargeur+largeurCase*j,videhauteur+ largeurCase*i, largeurCase, largeurCase);
              else if(jeu.plateau.get_Val(i,j)==128)gc.drawImage(img128.getImage(), videlargeur+largeurCase*j,videhauteur+ largeurCase*i, largeurCase, largeurCase);
              else if(jeu.plateau.get_Val(i,j)==256)gc.drawImage(img256.getImage(), videlargeur+largeurCase*j,videhauteur+ largeurCase*i, largeurCase, largeurCase);
              else if(jeu.plateau.get_Val(i,j)==512)gc.drawImage(img512.getImage(), videlargeur+largeurCase*j,videhauteur+ largeurCase*i, largeurCase, largeurCase);
              else if(jeu.plateau.get_Val(i,j)==1024)gc.drawImage(img1024.getImage(), videlargeur+largeurCase*j,videhauteur+ largeurCase*i, largeurCase, largeurCase);
              else if(jeu.plateau.get_Val(i,j)==2048)gc.drawImage(img2048.getImage(), videlargeur+largeurCase*j,videhauteur+ largeurCase*i, largeurCase, largeurCase);

            }
          }

    }

    ///////////////////Gestion des cliques souris et claviers\\\\\\\\\\\\\\\\\\\\  
    @FXML
    private void setOnMouseClicked(MouseEvent event) throws IOException {
          int yy = (int) ((event.getY()-videhauteur) / hauteurCase());
          int xx = (int) ((event.getX()-videlargeur) / largeurCase());
          //Jouer le coup si c'est possible
          Coup c=null;
          if(yourTurn || !reseau){
                if(CurrentGame=="XO"){
                    c= new CoupXO(xx,yy);
                }else if(CurrentGame=="P4"){
                    c= new CoupP4(xx);
                }else if(CurrentGame=="Dames"){
                  if(DamesX!=-1&&DamesY!=-1){
                System.out.println("DamesY       :"+DamesY+",DamesX         :"+DamesX+",yy         :"+yy+",xx     :"+xx);
                    c= new CoupDames(DamesY,DamesX,yy,xx);
                    DamesX=-1;
                    DamesY=-1;
                  }else{
                    DamesX=xx;
                    DamesY=yy;
                    if(reseau){
                        dos.writeInt(xx);
                        dos.writeInt(yy);
                    }
                    
                  }

                }else if(CurrentGame=="Othello"){
                    c= new CoupOthello(xx,yy);
                }
            if(c!=null && jeu.CanCoup(c)){
                jeu.JouerCoup(c);
                jeu.switchPlayer();
                System.out.println("*************setOnMouseClicked************");
                System.out.println("xx :"+xx);
                System.out.println("yy :"+yy);
                System.out.println("******************************************");
                try {
                    if(DamesX!=xx && DamesY!=yy){
                        if(reseau){
                            dos.writeInt(yy);
                            dos.writeInt(xx);
                            dos.flush();
                        }
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                //La mise a jour de plateau!
                miseAJour();
                yourTurn=false;
            }
            //La fin de Partie!
            if(jeu.finJeu()){
                System.out.println("Fin Jeu!");
            }
          }
    }

    @FXML
    private void keyPressed(KeyEvent event) {
            if(CurrentGame=="2048"){
               Coup c=null;
        	     switch (event.getCode()) {
                        case LEFT:
                            c= new Coup2048(6);
                            break;
                        case RIGHT:
                            c= new Coup2048(2);
                            break;
                        case UP:
                            c= new Coup2048(0);
                            break;
                        case DOWN:
                            c= new Coup2048(4);
                            break;
                }
                if( jeu.CanCoup(c)){
                  jeu.JouerCoup(c);
                }
                miseAJour();

                //La fin de Partie!
                if(jeu.finJeu()){
                    System.out.println("Fin Jeu!");
                }
            }
    }
    
    ///////////////////Gestion de reseau\\\\\\\\\\\\\\\\\\\\  
    @Override
   public void run(){
       int xx,yy,Dx=0,Dy=0;
       while (reseau) {

           if (!accepted) {
               listenForServerRequest();
           }else{
               try {
                   xx =dis.readInt();
                   yy =dis.readInt();
                   
                   if(CurrentGame=="Dames"){
                       Dx =dis.readInt();
                       Dy =dis.readInt();
                   }
                   
                   System.out.println("*************run************");
                   System.out.println("xx :"+xx);
                   System.out.println("yy :"+yy);
                   System.out.println("******************************************");
                   Coup c=null;
                   if(CurrentGame=="XO")c= new CoupXO(yy,xx);
                   else if(CurrentGame=="P4")c= new CoupP4(yy);
                   else if(CurrentGame=="Dames")c= new CoupDames(yy,xx,Dx,Dy);
                  
                    System.out.println("DamesY       :"+yy+",DamesX         :"+xx+",yy         :"+Dx+",xx     :"+Dy);
                    if(jeu.CanCoup(c)){
                       jeu.JouerCoup(c);
                       jeu.switchPlayer();
                       yourTurn=true;
                       miseAJour();
                       //La fin de Partie!
                       if(jeu.finJeu()){
                         System.out.println("Fin Jeu!");
                       }
                   }
               } catch (IOException ex) {
                   Logger.getLogger(ReseauController.class.getName()).log(Level.SEVERE, null, ex);
               }
           }

       }
   }

   private void listenForServerRequest() {
       /*Socket*/ socket = null;
       try {
           socket = serverSocket.accept();
           dos = new DataOutputStream(socket.getOutputStream());
           dis = new DataInputStream(socket.getInputStream());
           accepted = true;
           
           /*String m=dis.readLine();
           
           jeu.player2.setName(m);
           label2.setText(m);
           */System.out.println("CLIENT HAS REQUESTED TO JOIN, AND WE HAVE ACCEPTED");
       } catch (IOException e) {
           e.printStackTrace();
       }
   }
   
   private boolean connect() {
        try {
            socket = new Socket(ip, port);
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());
            accepted = true;
            
            //dos.writeChars(NameJ);
        } catch (IOException e) {
            System.out.println("Unable to connect to the address: " + ip + ":" + port + " | Starting a server");
            return false;
        }
        System.out.println("Successfully connected to the server.");
        return true;
    }
   
   ////////////////////Handler Buttons\\\\\\\\\\\\\\\\\\\\
   @FXML
    private void handleButtonAccueil(ActionEvent event) throws IOException {
            reseau=false;
            if(creer) serverSocket.close();
            else if (joindre) socket.close();
            
            FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemClassLoader().getResource("Vue/accueil.fxml"));
            AnchorPane pane = loader.load();
            rootPane.getChildren().setAll(pane);
    }

    @FXML
    private void handleButtonQuitter(ActionEvent event) throws IOException {
              reseau=false;
              if(creer){
                  dos.close();
                  dis.close();
                  serverSocket.close();
              }
              else if (joindre) socket.close();
              
              serverSocket.close();
              exit();
    }

    @FXML
    private void handleButtonAccueilG(ActionEvent event) throws IOException {
        reseau=false;
        if(creer) serverSocket.close();
        else if (joindre) socket.close();

        FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemClassLoader().getResource("Vue/accueil.fxml"));
        AnchorPane pane = loader.load();
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    private void handleButtonQuitterG(ActionEvent event) throws IOException {
        reseau=false;
        if(creer){
            dos.close();
            dis.close();
            serverSocket.close();
        }
        else if (joindre) socket.close();

        serverSocket.close();
        exit();
    }

    @FXML
    private void handleButtonRejouerG(ActionEvent event) {
            dialogPaneVictoire.setVisible(false);
            // TODO
            if(CurrentGame=="XO"){
              jeu = new JeuXO();
            }else if(CurrentGame.equals("P4")){
              jeu = new JeuP4();
            }else if(CurrentGame=="Dames"){
              jeu = new JeuDames();
            }else if(CurrentGame=="2048"){
              jeu = new Jeu2048();
            }
            miseAJour();
        
    }
}
