package Modele.XO;
import Modele.*;
import Util.*;
import java.util.*;
import java.io.Serializable;

public class CoupXO extends Coup implements Serializable{

	//public Mode modeJeu; 	// Vaut 0 si (Joueur VS Joueur) Sinon le 1,2,3 selon l'IA
  //public int c;
  public CoupXO(int l,int c){
    this.l = l ;
    this.c = c ;
  }
}
