package Controleur;

import Modele.*;
import Modele.XO.*;
import Modele.P4.*;

import Vue.FenetreGraphique;

public class Controleur {
	public Jeu jeu;
	FenetreGraphique f;

	public Controleur(Jeu j, FenetreGraphique fen) {
		jeu = j;
		f = fen;

	}

	public void clicSouris(double x, double y) {
		if(f.CurrentGame=="XO"){

			int yy = (int) (y / f.hauteurCase());
			int xx = (int) (x / f.largeurCase());
			Coup c= new CoupXO(xx,yy);
			jeu.JouerCoup(c);
			jeu.switchPlayer();
		}else if(f.CurrentGame=="P4"){

			int xx = (int) (x / f.largeurCase());
			System.out.println("xxxxxx: "+xx);
			Coup c= new CoupP4(xx);
			jeu.JouerCoup(c);
			jeu.switchPlayer();
		}else if(f.CurrentGame=="Dames"){
			
		}

		f.miseAJour();



	}/*
	public void initJeu(int l, int c, Mode m) {
		jeu.initJeu(l,c,m);
	}*/
/*
	public void Modechoice(int m){
		jeu.setMode(m);
	}
*/
}
