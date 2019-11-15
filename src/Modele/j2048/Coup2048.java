package Modele.j2048;
import Modele.*;

import Util.*;
import java.util.*;
import java.io.Serializable;

public class Coup2048 extends Coup implements Serializable{

	

  public Coup2048(int d){
    this.direction = d;
  }
}
