package Modele.Ludo;
import Modele.*;
import Util.*;
import java.util.*;
import java.io.Serializable;

public class JoueurLudo extends Joueur implements Serializable{

  public JoueurLudo(String s, int i, int depart){
    this.name = s;
    this.valPion = i;  //!!!
    this.pionBloqué = 4;
    this.pionArrivé = 0;
    this.CaseDepart = depart;
  }


}
