package Modele.j2048;

import Modele.*;
import Util.*;
import java.util.*;
import java.io.Serializable;

public class Jeu2048 extends Jeu implements Serializable{

	//public Mode modeJeu; 	// Vaut 0 si (Joueur VS Joueur) Sinon le 1,2,3 selon l'IA
  int[] Tab=new int[10];//Contient que des 2 et 4..
  Random r;

  public Jeu2048(){
		cc = new ConsoleColors();
		this.plateau = new Plateau(4,4);
		this.player1 = new JoueurHumain("player1",1);
    r = new Random();
    for(int i=0;i<10;i++) Tab[i]=2;
    Tab[r.nextInt(10)]=4;
    Tab[r.nextInt(10)]=4;
    Tab[r.nextInt(10)]=4;
    initCases();
    tabPlateau = new int[16];
	}

  public void initCases(){
    for (int i = 0; i < plateau.LIGNE; i++) {
		   for (int j = 0; j < plateau.COLONNE; j++) {
          this.plateau.set_Val(i,j,1);
       }
    }
    this.plateau.set_Val(r.nextInt(4),r.nextInt(4),Tab[r.nextInt(10)]);
    this.plateau.set_Val(r.nextInt(4),r.nextInt(4),Tab[r.nextInt(10)]);
  }

  public void RemplirTabPlateau(){
    int x=0;
    for (int i = 0; i < plateau.LIGNE; i++) {
		   for (int j = 0; j < plateau.COLONNE; j++) {
          tabPlateau[x] = this.plateau.get_Val(i,j);
       }
    }
  }

  public boolean CanCoup(Coup c){
    int a = 0;
    switch(c.direction) {
      //NORD
      case 0:
        for(int j=0;j<4;j++){
          a=0;
          for(int i=0;i<4;i++){
            if(a==0 && this.plateau.get_Val(i,j)==1)a++;
            if(a==1 && this.plateau.get_Val(i,j)!=1)a++;
            if(a==2) return true;
            if(0<i && this.plateau.get_Val(i,j)==this.plateau.get_Val(i-1,j)) return true;
          }
        }

        break;
      //EST
      case 2:
        for(int i=0;i<4;i++){
          a=0;
          for(int j=0;j<4;j++){
            if(a==0 && this.plateau.get_Val(i,j)!=1)a++;
            if(a==1 && this.plateau.get_Val(i,j)==1)a++;
            if(a==2) return true;
            if(0<j && this.plateau.get_Val(i,j)==this.plateau.get_Val(i,j-1)) return true;
          }
        }
        break;
      //SUD
      case 4:
        for(int j=0;j<4;j++){
          a=0;
          for(int i=0;i<4;i++){
            if(a==0 && this.plateau.get_Val(i,j)!=1)a++;
            if(a==1 && this.plateau.get_Val(i,j)==1)a++;
            if(a==2) return true;
            if(0<i && this.plateau.get_Val(i,j)==this.plateau.get_Val(i-1,j)) return true;
          }
        }
        break;
      //OUEST
      default:
        for(int i=0;i<4;i++){
          a=0;
          for(int j=0;j<4;j++){
            if(a==0 && this.plateau.get_Val(i,j)==1)a++;
            if(a==1 && this.plateau.get_Val(i,j)!=1)a++;
            if(a==2) return true;
            if(0<j && this.plateau.get_Val(i,j)==this.plateau.get_Val(i,j-1)) return true;
          }
        }
        break;

    }
    return false;
  }



  public void JouerCoup(Coup c){
    switch(c.direction){
      //NORD
      case 0:
        for(int j=0;j<4;j++){
          int[]t=new int[4];
          int x=0;
          for(int i=0;i<4;i++){
            if(this.plateau.get_Val(i,j)!=1){
                t[x]=this.plateau.get_Val(i,j);
                x++;
            }
          }
          while(x<4){
            t[x]=1;
            x++;
          }
          x=0;
          for(int i=0;i<4;i++){
            if(t[i]!=1 && i<3 && t[i]==t[i+1]){
              this.plateau.set_Val(x,j,t[i]*2);
              i=i+1;
            }else{
              this.plateau.set_Val(x,j,t[i]);
            }
            x++;
          }
          while(x<4){
            this.plateau.set_Val(x,j,1);
            x++;
          }
        }
        break;
      //EST
      case 2:
        for(int i=0;i<4;i++){
          int[] t=new int[4];
          int x=0;
          for(int j=3;j>=0;j--){
            if(this.plateau.get_Val(i,j)!=1){
              t[3-x]=this.plateau.get_Val(i,j);
              x++;
            }
          }
          while(x<4){
            t[3-x]=1;
            x++;
          }
          System.out.println(t[0]+"  "+t[1]+"  "+t[2]+"  "+t[3]+"  ");
          x=3;
          for(int j=3;j>=0;j--){
            if(t[j]!=1 && j>0 && t[j]==t[j-1]){
              this.plateau.set_Val(i,x,t[j]*2);
              j=j-1;
            }else{
              this.plateau.set_Val(i,x,t[j]);
            }
            x--;
          }
          while(x>=0){
            this.plateau.set_Val(i,x,1);
            x--;
          }
        }
        break;
      //SUD
      case 4:
        for(int j=0;j<4;j++){
          int[]t=new int[4];
          int x=3;
          for(int i=3;i>=0;i--){
            if(this.plateau.get_Val(i,j)!=1){
                t[x]=this.plateau.get_Val(i,j);
                x--;
            }
          }
          while(x>=0){
            t[x]=1;
            x--;
          }
          x=3;
          for(int i=3;i>=0;i--){
            if(t[i]!=1 && i>0 && t[i]==t[i-1]){
              this.plateau.set_Val(x,j,t[i]*2);
              i=i-1;
            }else{
              this.plateau.set_Val(x,j,t[i]);
            }
            x--;
          }
          while(x>=0){
            this.plateau.set_Val(x,j,1);
            x--;
          }
        }
        break;
      //OUEST
      default:
        for(int i=0;i<4;i++){
          int[]t=new int[4];
          int x=0;
          for(int j=0;j<4;j++){
            if(this.plateau.get_Val(i,j)!=1){
                t[x]=this.plateau.get_Val(i,j);
                x++;
            }
          }
          while(x<4){
            t[x]=1;
            x++;
          }
        //  System.out.println(t[0]+"  "+t[1]+"  "+t[2]+"  "+t[3]+"  ");
          x=0;
          for(int j=0;j<4;j++){
            if(t[j]!=1 && j<3 && t[j]==t[j+1]){
              this.plateau.set_Val(i,x,t[j]*2);
              j=j+1;
            }else{
              this.plateau.set_Val(i,x,t[j]);
            }
            x++;
          }
          while(x<4){
            this.plateau.set_Val(i,x,1);
            x++;
          }
          //System.out.println(this.plateau.get_Val(i,0)+"  "+this.plateau.get_Val(i,1)+"  "+this.plateau.get_Val(i,2)+"  "+this.plateau.get_Val(i,3)+"  ");
        }
        break;

    }
    if(!finJeu()){
      int a=r.nextInt(4);
      int b=r.nextInt(4);

      while(this.plateau.get_Val(a,b)!=1){
        a=r.nextInt(4);
        b=r.nextInt(4);
      }
      this.plateau.set_Val(a,b,Tab[r.nextInt(10)]);
    }
  }

  public void jouer(){
    Scanner sc = new Scanner(System.in);
    int d;
    d = sc.nextInt();
    Coup c= new Coup2048(d);
    while(!CanCoup(c)){
      d = sc.nextInt();
      c= new Coup2048(d);
    }
    JouerCoup(c);
  }


  public boolean finJeu(){
    for(int i=0;i<4;i++){
      for(int j=0;j<4;j++){
        if(this.plateau.get_Val(i,j)==1) return false;
      }
    }
    return true;
  }
}
