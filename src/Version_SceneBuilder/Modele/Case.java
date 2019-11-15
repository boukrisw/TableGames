package Modele;
import Util.*;
import java.util.*;
import java.io.Serializable;

public class Case implements Serializable{

  public int nbj1,nbj2,nbj3,nbj4;
  public boolean securise;
  //Les derniers cases!!!
  public Case(int i){
    this.nbj1=0;
    this.nbj2=0;
    this.nbj3=0;
    this.nbj4=0;
    if(i==0 || i==13 || i==26 || i==39) this.securise = true;
    else this.securise = false;

  }


}
