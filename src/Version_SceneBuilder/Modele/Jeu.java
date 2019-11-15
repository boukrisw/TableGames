package Modele;
import Util.*;
import java.io.Serializable;
import java.util.ArrayList;

public abstract class Jeu implements Serializable{
	public String GameName;
	public String winner;
	public ConsoleColors cc;  // gere le changement de couleurs sur le terminal
	public Plateau plateau;		// Plateau de jeu
	public Joueur jCourant,player1, player2;	// Les Joueurs
	public int[] tabPlateau;

	public void switchPlayer(){
		if(jCourant.equals(player1))
				setJCourant(player2);
		else
				setJCourant(player1);
	}

	public void setJCourant(Joueur j){
		this.jCourant = j;
	}

	public abstract Jeu initJeu(String s);

	public abstract void initCases();

	public abstract boolean CanCoup(Coup c/*int c*/);

	public abstract void JouerCoup(Coup c/*int c*/);

	public abstract void jouer();


	public abstract boolean finJeu();

	//public abstract void partie();
	public void partie(){
  	while(!finJeu()){
              infos();
              jouer();
              switchPlayer();//changer le joueur courant
    }
    infos();
    System.out.println("PARTIE TERMINEE - "+jCourant.getName()+" A GAGNE");
  }

	public void partieINT(){
		while(!finJeu()){
							infosINT();
							jouer();
		}
		infos();
		System.out.println("PARTIE TERMINEE - "+jCourant.getName()+" A GAGNE");
	}

	public void infos(){
		plateau.afficher();
	}

	public void infosINT(){
		plateau.afficherINT();
	}

}
