package Modele.Dames;
import Modele.*;

import Modele.XO.*;
import Modele.P4.*;
import Util.*;
import java.util.*;
import java.io.Serializable;

public class JeuDames extends Jeu implements Serializable{

	//public Mode modeJeu; 	// Vaut 0 si (Joueur VS Joueur) Sinon le 1,2,3 selon l'IA

  public JeuDames(){
    this.GameName="Dames";
		cc = new ConsoleColors();
		this.plateau = new Plateau(10,10);
		this.player1 = new JoueurHumain("player1",1);
		this.player2 = new JoueurHumain("player2",2);
		this.jCourant = player1;
		initCases();
	}

  public Jeu initJeu(String s){
		Jeu x=null;
		if(s=="XO") x= new JeuXO();
		if(s=="P4") x= new JeuP4();
		if(s=="Dames") x= new JeuDames();
		return x;
	}

  public void initCases(){
    for (int i = 0; i < plateau.LIGNE; i++) {
		   for (int j = 0; j < plateau.COLONNE; j++) {
          if((i+j)%2==0)this.plateau.set_Val(i,j,0);
          else if(i<4) this.plateau.set_Val(i,j,1);
          else if(i>=6) this.plateau.set_Val(i,j,2);
          else this.plateau.set_Val(i,j,0);
       }
    }
  }

  public boolean CanCoup(Coup c){
    if(this.plateau.get_Val(c.Arrl,c.Arrc)==0){
        if(this.plateau.get_Val(c.l,c.c)==1){
          if((c.Arrl==c.l+1 && c.Arrc==c.c-1)||(c.Arrl==c.l+1 && c.Arrc==c.c+1)) return true;
          if((c.Arrl==c.l+2 && c.Arrc==c.c-2 && this.plateau.get_Val(c.l+1,c.c-1)==2)||(c.Arrl==c.l+2 && c.Arrc==c.c+2 && this.plateau.get_Val(c.l+1,c.c+1)==2)) return true;
        }else if(this.plateau.get_Val(c.l,c.c)==2){
          if((c.Arrl==c.l-1 && c.Arrc==c.c-1)||(c.Arrl==c.l-1 && c.Arrc==c.c+1)) return true;
          if((c.Arrl==c.l-2 && c.Arrc==c.c-2 && this.plateau.get_Val(c.l-1,c.c-1)==1)||(c.Arrl==c.l-2 && c.Arrc==c.c+2 && this.plateau.get_Val(c.l-1,c.c+1)==1)) return true;
        }else{// val vaut 3 ou 4!
          if((c.Arrl==c.l+1 && c.Arrc==c.c-1)||(c.Arrl==c.l+1 && c.Arrc==c.c+1)) return true;
          if((c.Arrl==c.l-1 && c.Arrc==c.c-1)||(c.Arrl==c.l-1 && c.Arrc==c.c+1)) return true;
          if(this.plateau.get_Val(c.l,c.c)==3){
            if((c.Arrl==c.l+2 && c.Arrc==c.c-2 && this.plateau.get_Val(c.l+1,c.c-1)==2)||(c.Arrl==c.l+2 && c.Arrc==c.c+2 && this.plateau.get_Val(c.l+1,c.c+1)==2)) return true;
            if((c.Arrl==c.l-2 && c.Arrc==c.c-2 && this.plateau.get_Val(c.l-1,c.c-1)==2)||(c.Arrl==c.l-2 && c.Arrc==c.c+2 && this.plateau.get_Val(c.l-1,c.c+1)==2)) return true;
          }else if(this.plateau.get_Val(c.l,c.c)==4){
            if((c.Arrl==c.l+2 && c.Arrc==c.c-2 && this.plateau.get_Val(c.l+1,c.c-1)==1)||(c.Arrl==c.l+2 && c.Arrc==c.c+2 && this.plateau.get_Val(c.l+1,c.c+1)==1)) return true;
            if((c.Arrl==c.l-2 && c.Arrc==c.c-2 && this.plateau.get_Val(c.l-1,c.c-1)==1)||(c.Arrl==c.l-2 && c.Arrc==c.c+2 && this.plateau.get_Val(c.l-1,c.c+1)==1)) return true;
          }
        }
    }
    return false;
  }



  public void JouerCoup(Coup c){
    //Deplacement avec capture!
    if(!((c.Arrl==c.l+1 && c.Arrc==c.c-1) || (c.Arrl==c.l+1 && c.Arrc==c.c+1) || (c.Arrl==c.l-1 && c.Arrc==c.c-1) || (c.Arrl==c.l-1 && c.Arrc==c.c+1))){
      switch (c.direction) {
        case Direction.NO:
          this.plateau.set_Val(c.l-1,c.c-1,0);
          break;
        case Direction.SE:
          this.plateau.set_Val(c.l+1,c.c+1,0);
          break;
        case Direction.NE:
          this.plateau.set_Val(c.l-1,c.c+1,0);
          break;
        case Direction.SO:
          this.plateau.set_Val(c.l+1,c.c-1,0);
          break;
      }
    }
    this.plateau.set_Val(c.Arrl,c.Arrc,this.plateau.get_Val(c.l,c.c));
    this.plateau.set_Val(c.l,c.c,0);
    if((c.Arrl==0 ||c.Arrl==9) && this.plateau.get_Val(c.Arrl,c.Arrc)<=2) this.plateau.set_Val(c.Arrl,c.Arrc,this.plateau.get_Val(c.Arrl,c.Arrc)+2);
  }

  public void jouer(){
    Scanner sc = new Scanner(System.in);
    int x,y,Arrx,Arry;
    String jcourant = cc.col("["+jCourant.getName()+"]",cc.YELLOW_B); //changer la couleur en Jaune Gras
    System.out.print(jcourant+" : veuillez saisir une ligne puis une colone :");
    x = sc.nextInt();
    y = sc.nextInt();
    Arrx = sc.nextInt();
    Arry = sc.nextInt();
    Coup c= new CoupDames(x,y,Arrx,Arry);
    while(!CanCoup(c)){
      System.out.print(jcourant+" : veuillez saisir une autre ligne et colone :");
      x = sc.nextInt();
      y = sc.nextInt();
      Arrx = sc.nextInt();
      Arry = sc.nextInt();
      c= new CoupDames(x,y,Arrx,Arry);
    }
    JouerCoup(c);
  }


  public boolean finJeu(){
    int j1=0;int j2=0;
    for(int i=0;i<10;i++){
      for(int j=0;j<10;j++){
        if(this.plateau.get_Val(i,j)==1) j1=1;
        if(this.plateau.get_Val(i,j)==2) j2=1;
        if(j1==1 && j2==1)return false;
      }
    }
    return true;
  }
}
