package Modele.XO;
import Modele.*;

import Util.*;
import java.util.*;
import java.io.Serializable;
import javafx.scene.canvas.GraphicsContext;

public class JeuXO extends Jeu implements Serializable{

	//public Mode modeJeu; 	// Vaut 0 si (Joueur VS Joueur) Sinon le 1,2,3 selon l'IA

        public  GraphicsContext gc;
  public JeuXO(){
    this.GameName="XO";
		cc = new ConsoleColors();
		this.plateau = new Plateau(3,3);
		this.player1 = new JoueurHumain("player1",1);
		this.player2 = new JoueurHumain("player2",2);
		this.jCourant = player1;
		initCases();
    tabPlateau = new int [9];
	}

  public void initCases(){
    for (int i = 0; i < plateau.LIGNE; i++) {
		   for (int j = 0; j < plateau.COLONNE; j++) {
          this.plateau.set_Val(i,j,0);
       }
    }
  }

  public void RemplirTabPlateau(){
    for (int i = 0; i < plateau.LIGNE; i++) {
		   for (int j = 0; j < plateau.COLONNE; j++) {
          tabPlateau[i*3+j] = this.plateau.get_Val(i,j);
       }
    }
  }

  public boolean CanCoup(Coup c){
    if(c.c<3 && c.l<3) return this.plateau.get_Val(c.l,c.c)==0;
    return false;
  }

  public void JouerCoup(Coup c){
    this.plateau.set_Val(c.l,c.c,jCourant.valPion);
  }

  public void jouer(){
    Scanner sc = new Scanner(System.in);
    int x,y;
    String jcourant = cc.col("["+jCourant.getName()+"]",cc.YELLOW_B); //changer la couleur en Jaune Gras
    System.out.print(jcourant+" : veuillez saisir une ligne puis une colone :");
    x = sc.nextInt();
    y = sc.nextInt();
    Coup c= new CoupXO(x,y);
    while(!CanCoup(c)){
      System.out.print(jcourant+" : veuillez saisir une autre ligne et colone :");
      x = sc.nextInt();
      y = sc.nextInt();
      c= new CoupXO(x,y);
    }
    JouerCoup(c);
  }

  public boolean finJeu(){
    //tests si tous les case ne sont pas vide
    this.RemplirTabPlateau();
    int i=0; int j;
    while(i<9 && tabPlateau[i]!=0){
      i++;
    }
    if(i==9)return true;
    for(int x=0;x<9;x++){
      if(tabPlateau[x]!=0){
        i=x/3;
        j=x%3;
        //*************************test de Horizontale*************************
        if(j==0){
          int a=1;
          while(a<=2 && tabPlateau[x]==tabPlateau[x+a]){
            a++;
          }
          if(a==3) return true;
        }
        //*************************test de Verticale*************************
        if(i==0){
          int a=1;
          while(a<=2 && tabPlateau[x]==tabPlateau[x+a*3]){
            a++;
          }
          if(a==3) return true;
        }
        //*************************test de diagonale*************************
        if(i==0 && j==0){
          int a=1;
          while(a<=2 && tabPlateau[x]==tabPlateau[x+a*4]){
            a++;
          }
          if(a==3) return true;
        }
        //*************************test de diagonale*************************
        if(i==0 && j==2){
          int a=1;
          while(a<=2 && tabPlateau[x]==tabPlateau[x+a*2]){
            a++;
          }
          if(a==3) return true;
        }

      }
    }
    return false;
  }

 }
