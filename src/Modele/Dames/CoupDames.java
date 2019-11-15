package Modele.Dames;
import Modele.*;

import Util.*;
import java.util.*;
import java.io.Serializable;

public class CoupDames extends Coup implements Serializable{

	//public Mode modeJeu; 	// Vaut 0 si (Joueur VS Joueur) Sinon le 1,2,3 selon l'IA
  //public int c;
  public CoupDames(int l,int c,int Arrl,int Arrc){
    this.l = l;
    this.c = c ;
    this.Arrl = Arrl;
    this.Arrc = Arrc ;
    this.direction=this.Direction();
  }

  public int Direction(){

			if((this.l-1>=this.Arrl)&&(this.c-1>=this.Arrc)) return Direction.NO;
			if((this.l+1<=this.Arrl)&&(this.c+1<=this.Arrc)) return Direction.SE;

			if((this.l-1>=this.Arrl)&&(this.c+1<=this.Arrc)) return Direction.NE;
			if((this.l+1<=this.Arrl)&&(this.c-1>=this.Arrc)) return Direction.SO;
			return -8;
  }
}
