package Modele.Othello;
import Modele.*;

import Util.*;
import java.util.*;
import java.io.Serializable;

public class JeuOthello extends Jeu implements Serializable{

	//public Mode modeJeu; 	// Vaut 0 si (Joueur VS Joueur) Sinon le 1,2,3 selon l'IA
  public boolean h=false;
  public boolean hd=false;
  public boolean d=false;
  public boolean bd=false;
  public boolean b=false;
  public boolean bg=false;
  public boolean g=false;
  public boolean hg=false;

  public JeuOthello(){
    this.GameName="Othello";
		cc = new ConsoleColors();
		this.plateau = new Plateau(8,8);
		this.player1 = new JoueurHumain("player1",1,2);
		this.player2 = new JoueurHumain("player2",2,2);
		this.jCourant = player1;
		initCases();
    tabPlateau = new int [64];
	}

  public void initCases(){
    for (int i = 0; i < plateau.LIGNE; i++) {
		   for (int j = 0; j < plateau.COLONNE; j++) {
          if((i==3 && j==3) || (i==4 && j==4)){
            this.plateau.set_Val(i,j,1);
          }else if((i==3 && j==4) || (i==4 && j==3)){
            this.plateau.set_Val(i,j,2);
          }else{
            this.plateau.set_Val(i,j,0);
          }
      }
    }
  }

  public void RemplirTabPlateau(){
    for (int i = 0; i < plateau.LIGNE; i++) {
		   for (int j = 0; j < plateau.COLONNE; j++) {
          tabPlateau[i*8+j] = this.plateau.get_Val(i,j);
       }
    }
  }
  //********************Acorriger*************************
  public boolean CanCoup(Coup c){
    int x,y;
    boolean res=false;
    //System.out.println("xxxxxx:"+c.x+"yyyyyyy"+c.y);
    //haut
    x=c.l-1;
    y=c.c;
    while(0<=x && (this.plateau.get_Val(x,y)!=0) && (this.plateau.get_Val(x,y)!=this.plateau.get_Val(c.l,c.c))){
      x--;
    }
    if(0<=x && (this.plateau.get_Val(x,y)==this.plateau.get_Val(c.l,c.c))){
      res=true;
      h=true;
    }
    //haut droite
    x=c.l-1;
    y=c.c+1;
    while(0<=x && y<plateau.COLONNE && (this.plateau.get_Val(x,y)!=0)&& (this.plateau.get_Val(x,y)!=this.plateau.get_Val(c.l,c.c))){
      x--;
      y++;
    }
    if(0<=x && y<plateau.COLONNE && (this.plateau.get_Val(x,y)==this.plateau.get_Val(c.l,c.c))){
      res=true;
      hd=true;
    }
    //droite
    x=c.l;
    y=c.c+1;
    while(y<plateau.COLONNE && (this.plateau.get_Val(x,y)!=0)&& (this.plateau.get_Val(x,y)!=this.plateau.get_Val(c.l,c.c))){
      y++;
    }
    if(y<plateau.COLONNE && (this.plateau.get_Val(x,y)==this.plateau.get_Val(c.l,c.c))){
      res=true;
      d=true;
    }
    //bas droite
    x=c.l+1;
    y=c.c+1;
    while(x<plateau.LIGNE && y<plateau.COLONNE && (this.plateau.get_Val(x,y)!=0)&& (this.plateau.get_Val(x,y)!=this.plateau.get_Val(c.l,c.c))){
      x++;
      y++;
    }
    if(x<plateau.LIGNE && y<plateau.COLONNE && (this.plateau.get_Val(x,y)==this.plateau.get_Val(c.l,c.c))){
      res=true;
      bd=true;
    }
    //bas
    x=c.l+1;
    y=c.c;
    while(x<plateau.LIGNE && (this.plateau.get_Val(x,y)!=0)&& (this.plateau.get_Val(x,y)!=this.plateau.get_Val(c.l,c.c))){
      x++;
    }
    if(x<plateau.LIGNE && (this.plateau.get_Val(x,y)==this.plateau.get_Val(c.l,c.c))){
      res=true;
      b=true;
    }
    //bas gauche
    x=c.l+1;
    y=c.c-1;
    while(x<plateau.LIGNE && 0<y && (this.plateau.get_Val(x,y)!=0)&& (this.plateau.get_Val(x,y)!=this.plateau.get_Val(c.l,c.c))){
      x++;
      y--;
    }
    if(x<plateau.LIGNE && 0<=y && (this.plateau.get_Val(x,y)==this.plateau.get_Val(c.l,c.c))){
      res=true;
      bg=true;
    }
    //gauche
    x=c.l;
    y=c.c-1;
    while(0<=y && (this.plateau.get_Val(x,y)!=0)&& (this.plateau.get_Val(x,y)!=this.plateau.get_Val(c.l,c.c))){
      y--;
    }
    if(0<=y && (this.plateau.get_Val(x,y)==this.plateau.get_Val(c.l,c.c))){
      res=true;
      g=true;
    }
    //haut gauche
    x=c.l-1;
    y=c.c-1;
    while(0<=x && 0<=y && (this.plateau.get_Val(x,y)!=0)&& (this.plateau.get_Val(x,y)!=this.plateau.get_Val(c.l,c.c))){
      x--;
      y--;
    }
    if(0<=x && 0<=y && (this.plateau.get_Val(x,y)==this.plateau.get_Val(c.l,c.c))){
      res=true;
      hg=true;
    }

    return res;
  }

  public void JouerCoup(Coup c){

    //System.out.println("xxxxxx:"+c.x+"yyyyyyy"+c.y);
    this.plateau.set_Val(c.l,c.c,jCourant.valPion);
    int x,y,nb;
    //haut
    x=c.l-1;
    y=c.c;
    nb=0;
    System.out.println("weeeeeeeeeeeeesh");
    while(h && 0<=x && (this.plateau.get_Val(x,y)!=0) && (this.plateau.get_Val(x,y)!=this.plateau.get_Val(c.l,c.c))){
      System.out.println("wesh haut:  "+h);
      this.plateau.set_Val(x,y,jCourant.valPion);
      x--;
      nb++;
    }
    h=false;
    //haut droite
    x=c.l-1;
    y=c.c+1;
    while(hd && 0<=x && y<plateau.COLONNE && (this.plateau.get_Val(x,y)!=0)&& (this.plateau.get_Val(x,y)!=this.plateau.get_Val(c.l,c.c))){
      System.out.println("wesh haut dddd:  "+h);
      this.plateau.set_Val(x,y,jCourant.valPion);
      x--;
      y++;
      nb++;
    }
    hd=false;
    //droite
    x=c.l;
    y=c.c+1;
    while(d && y<plateau.COLONNE && (this.plateau.get_Val(x,y)!=0)&& (this.plateau.get_Val(x,y)!=this.plateau.get_Val(c.l,c.c))){
System.out.println("wesh ddddddroite:  "+h);
      this.plateau.set_Val(x,y,jCourant.valPion);
      y++;
      nb++;
    }
    d=false;
    //bas droite
    x=c.l+1;
    y=c.c+1;
    while(bd && x<plateau.LIGNE && y<plateau.COLONNE && (this.plateau.get_Val(x,y)!=0)&& (this.plateau.get_Val(x,y)!=this.plateau.get_Val(c.l,c.c))){
System.out.println("wesh bas droite  "+h);
      this.plateau.set_Val(x,y,jCourant.valPion);
      x++;
      y++;
      nb++;
    }
    bd=false;
    //bas
    x=c.l+1;
    y=c.c;
    while(b && x<plateau.LIGNE && (this.plateau.get_Val(x,y)!=0)&& (this.plateau.get_Val(x,y)!=this.plateau.get_Val(c.l,c.c))){
System.out.println("wesh baaaaaaaaas:  "+h);
      this.plateau.set_Val(x,y,jCourant.valPion);
      x++;
      nb++;
    }
    b=false;

    //bas gauche
    x=c.l+1;
    y=c.c-1;
    while(bg && x<plateau.LIGNE && 0<y && (this.plateau.get_Val(x,y)!=0)&& (this.plateau.get_Val(x,y)!=this.plateau.get_Val(c.l,c.c))){
System.out.println("wesh bas gauuuuuuuuuche:  "+h);
      this.plateau.set_Val(x,y,jCourant.valPion);
      x++;
      y--;
      nb++;
    }
    bg=false;

    //gauche
    x=c.l;
    y=c.c-1;
    while(g && 0<=y && (this.plateau.get_Val(x,y)!=0)&& (this.plateau.get_Val(x,y)!=this.plateau.get_Val(c.l,c.c))){
System.out.println("wesh gauuuuuuuuuuuuch:  "+h);
      this.plateau.set_Val(x,y,jCourant.valPion);
      y--;
      nb++;
    }
    g=false;

    //haut gauche
    x=c.l-1;
    y=c.c-1;
    while(hg && 0<=x && 0<=y && (this.plateau.get_Val(x,y)!=0)&& (this.plateau.get_Val(x,y)!=this.plateau.get_Val(c.l,c.c))){
System.out.println("wesh haut gauche:  "+h);
      this.plateau.set_Val(x,y,jCourant.valPion);
      x--;
      y--;
      nb++;
    }
    hg=false;

    if(jCourant==player1){
      jCourant.nbPions+=nb;
      player2.nbPions-=nb;
    }else{
      jCourant.nbPions+=nb;
      player1.nbPions-=nb;
    }

  }

  public void jouer(){
    Scanner sc = new Scanner(System.in);
    int x,y;
    String jcourant = cc.col("["+jCourant.getName()+"]",cc.YELLOW_B); //changer la couleur en Jaune Gras
    System.out.print(jcourant+" : veuillez saisir une ligne puis une colone :");
    x = sc.nextInt();
    y = sc.nextInt();
    Coup c= new CoupOthello(x,y);
    while(!CanCoup(c)){
      System.out.print(jcourant+" : veuillez saisir une autre ligne et colone :");
      x = sc.nextInt();
      y = sc.nextInt();
      c= new CoupOthello(x,y);
    }
    JouerCoup(c);
  }
  //*********************************************

  public boolean finJeu(){
    //tests si tous les case ne sont pas vide
    return (this.player1.nbPions+this.player2.nbPions)==64;
  }

 }
