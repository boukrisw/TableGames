package Modele.Ludo;
import Modele.*;
import Util.*;
import java.util.*;
import java.io.Serializable;

public class JeuLudo extends Jeu implements Serializable{

	//public Mode modeJeu; 	// Vaut 0 si (Joueur VS Joueur) Sinon le 1,2,3 selon l'IA

  public Joueur player3;
  public Joueur player4;
  public Case[] tableau = new Case[52];
  public JeuLudo(){
		cc = new ConsoleColors();
		this.player1 = new JoueurLudo("player1",1,0);
		this.player2 = new JoueurLudo("player2",2,13);
		this.player3 = new JoueurLudo("player3",3,26);
		this.player4 = new JoueurLudo("player4",4,39);
		this.jCourant = this.player1;
    tableau.initCases();
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
          tabPlateau[i*7+j] = this.plateau.get_Val(i,j);
       }
    }
  }

  public boolean CanCoup(Coup c){
    if(c.c<7) return this.plateau.get_Val(0,c.c)==0;
    return false;
  }

  public void JouerCoup(Coup c){
    int j=5;
    while(j>=0 && this.plateau.get_Val(j,c.c)!=0){
       j--;
    }
    this.plateau.set_Val(j,c.c,jCourant.valPion);
  }

  public void jouer(){
    Scanner sc = new Scanner(System.in);
    int x;
    String jcourant = cc.col("["+jCourant.getName()+"]",cc.YELLOW_B); //changer la couleur en Jaune Gras
    System.out.print(jcourant+" : veuillez saisir une colone :");
    x = sc.nextInt();
    Coup c= new CoupP4(x);
    while(!CanCoup(c)){
      System.out.print(jcourant+" : veuillez saisir une autre colone :");
      x = sc.nextInt();
      c= new CoupP4(x);
    }
    JouerCoup(c);
  }


  public boolean finJeu(){
    this.RemplirTabPlateau();
    int i=0;int j=0;
    //tests si tous les case ne sont pas vide
    while(j<7 && this.plateau.get_Val(i,j)!=0){
      j++;
    }
    if(j==7) return true;
    //tests si un Joueur a gagner!
    for(int x=0;x<42;x++){
      if(tabPlateau[x]!=0){
            i=x/7;
            j=x%7;
            //*************************test de Horizontale*************************
            if(j<=3){
              int a=1;
              while(a<=3 && tabPlateau[x]==tabPlateau[x+a]){
                a++;
              }
              if(a==4) return true;
            }
            //*************************test de Verticale*************************
            if(i<=2){
              int a=1;
              while(a<=3 && tabPlateau[x]==tabPlateau[x+a*7]){
                a++;
              }
              if(a==4) return true;
            }
            //*************************test de diagonale*************************
            if(i<=2 && j<=3 && x+24<42){
              int a=1;
              while(a<=3 && tabPlateau[x]==tabPlateau[x+a*8]){
                a++;
              }
              if(a==4) return true;
            }
            //*************************test de diagonale*************************
            if(i<=2 && j>=3 && x+18<42){
              int a=1;
              while(a<=3 && tabPlateau[x]==tabPlateau[x+a*6]){
                a++;
              }
              if(a==4) return true;
            }
      }
    }
    return false;
  }
}
