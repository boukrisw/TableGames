package Modele;
import java.io.Serializable;
import java.util.ArrayList;

public abstract class Joueur implements Serializable{

	public String name;      //nom du joueur
	public int valPion;      // valeur de pion
	public int nbPions;
	/*****************dans le jeu LUDO*****************/

	public int pionBloqué;
	public int pionArrivé;
	public int CaseDepart;

	/**************************************************/

	/*Renvoie le nom de joueur*/
	public String getName(){
		return this.name;
	}

	/*mets s comme nom de joueur*/
	public void setName(String s){
		this.name=s;
	}

  public int getVal(){
		return this.valPion;
	}

}
