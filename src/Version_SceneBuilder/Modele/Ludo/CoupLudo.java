package Modele.Ludo;
import Modele.*;

import Util.*;
import java.util.*;
import java.io.Serializable;

public class CoupLudo extends Coup implements Serializable{


  public CoupLudo(int depart, int longeur){
    //***********
    this.l = depart;
    this.longeur = longeur;
  }

}
