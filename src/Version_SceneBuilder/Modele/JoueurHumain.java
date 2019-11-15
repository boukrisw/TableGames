package Modele;
import java.io.Serializable;


public class JoueurHumain extends Joueur implements Serializable{

	/*  CONSTRUCTEURS:
	 *  Cree un Joueur avec name s et valPion i.
	 */
	public JoueurHumain(String s, int i){
		this.name = s;
		this.valPion = i;
	}

}
