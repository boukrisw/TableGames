package Modele.P4;
import Modele.*;

import Util.*;
import java.util.*;
import java.io.Serializable;

public class CoupP4 extends Coup implements Serializable{

	//public Mode modeJeu; 	// Vaut 0 si (Joueur VS Joueur) Sinon le 1,2,3 selon l'IA
  //public int c;
  public CoupP4(int c){
    this.c = c ;
  }
}
