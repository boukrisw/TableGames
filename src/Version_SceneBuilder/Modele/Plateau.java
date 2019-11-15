package Modele;
import Util.*;
import java.util.*;
import java.io.Serializable;


public class Plateau implements Serializable{

	public ConsoleColors c = new ConsoleColors();
	public int[][] grille;
	public final int LIGNE;
	public final int COLONNE;

  /* Constructeur: Creation de plateau  */
	public Plateau(int l,int c){
    LIGNE=l;
    COLONNE=c;
		this.grille = new int[LIGNE][COLONNE];
	}

  public int get_Val(int l,int c){
    return this.grille[l][c];
  }

  public void set_Val(int l, int c, int val){
    this.grille[l][c]=val;
  }

  public void afficher(){
      String s=" +-----------+";
      String axes=" |    axes   |";
      for(int i=0; i < COLONNE; i++){
         s=s+"---";
         axes=axes+" "+i+" ";
      }
      s=s+"+";
      axes=axes+"|";
	  	System.out.println(s);
			System.out.println(axes);
			System.out.println(s);
			for (int i = 0; i < LIGNE; i++) {
				System.out.print(" |     "+i+"     |");
			   for (int j = 0; j < COLONNE; j++) {
					 if (this.get_Val(i,j)==1) System.out.print(c.col(" X ",c.GREEN_B));
					 if (this.get_Val(i,j)==2) System.out.print(c.col(" O ",c.RED_B));
					 if (this.get_Val(i,j)==0) System.out.print(c.col(" - ",c.WHITE_B));
				}
				System.out.println("|");
			}
			System.out.println(s);
			System.out.println("");
		}


		  public void afficherINT(){
		      String s=" +-----------+";
		      String axes=" |    axes   |";
		      for(int i=0; i < COLONNE; i++){
		         s=s+"---";
		         axes=axes+" "+i+" ";
		      }
		      s=s+"+";
		      axes=axes+"|";
			  	System.out.println(s);
					System.out.println(axes);
					System.out.println(s);
					for (int i = 0; i < LIGNE; i++) {
						System.out.print(" |     "+i+"     |");
					   for (int j = 0; j < COLONNE; j++) {
							 System.out.print(c.col(" "+this.get_Val(i,j)+" ",c.GREEN_B));
						 }
						System.out.println("|");
					}
					System.out.println(s);
					System.out.println("");
				}

}
