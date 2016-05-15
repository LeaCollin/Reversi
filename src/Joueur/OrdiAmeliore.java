package Joueur;

import java.util.ArrayList;

import Commun.Commun.Direction;
import Jeu.Case;
import Jeu.Couleur;
import Jeu.Plateau;

public class OrdiAmeliore {
	
	private int[][] copiePlateau = new int[8][8];
	private int compt;
	private int compteurInterne;
	private Case bestCase;
	
	public OrdiAmeliore(){
		
	}
	
	public int[][] pointsCase = {
			{  30, -30, 10, 5, 5, 10, -30,  30},
			{ -30, -30,  1, 1, 1,  1, -30, -30},
			{  10,   1,  5, 2, 2,  5,   1,  10},
			{   5,   1,  2, 1, 1,  2,   1,   5},
			{   5,   1,  2, 1, 1,  2,   1,   5},
			{   5,   1,  2, 1, 1,  2,   1,   5},
			{ -30, -30,  1, 1, 1,  1, -30, -30},
			{  30, -30, 10, 5, 5, 10, -30,  30},
	};
	
	public Case jouer(ArrayList<Case> possibilite, Plateau plat){
		copiePlateau=copiePlat(plat);
		 /*for(int i=0; i<8; i++){
        for(int j=0; j<8; j++){
        	System.out.print(copiePlateau[i][j]);
        }
        System.out.println();
 		}*/
		bestCase=null;
		if (possibilite.size() != 0){
				if (coin(possibilite)){
					return bestCase;
				}
				return meilleurCoup(possibilite);
			}
		return null;
	}
	
	public int[][] copiePlat(Plateau plat){
		for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
            	Case c=plat.getCase(i, j);
            	if(c.isEtat()){
            		if(plat.getPion(c).getCouleur()==Couleur.Bleu){
            			copiePlateau[i][j]=2; //2 est le joueur bleu
            		} 
            		else copiePlateau[i][j]=1; //1 est le joueur blanc	

            	} 
            	else copiePlateau[i][j]=0;
            }
        }
		return copiePlateau;
	}
	
	public Boolean coin(ArrayList<Case> possibilites){
		for (Case c: possibilites){
			if (pointsCase[c.getI()][c.getJ()]==30){
				bestCase=c;
				return true;
			}
		} 
		return false;
	}
	
	/*public int point(Case c){
		copiePlateau[c.getI()][c.getJ()]=1;
		c.getVoisins().entrySet().stream().forEach((pair) -> { 
			
		});

		//System.out.println(cTemp.toString());
		cTemp.checkCouleurPion(platTemp,cTemp);
		platTemp.score();
		System.out.println("score: "+platTemp.joueurBlanc.getScore());
		
		return platTemp.joueurBlanc.getScore();
		return 0;
		
	}*/
	
	public Case meilleurCoup(ArrayList<Case> possibilite){
		int score=0;
		for (Case c : possibilite){
			//if (c==null) return null;
			int nb = nombrePoints(c);
			if (score < nb){
				bestCase = c;
				score  = nb;
			}
		}
		return bestCase;
	}
	
	
	public void checkRecursif(int i, int j, Direction direction) {

		i+=direction.getI();
		j+=direction.getJ();

		//Ne pas sortir du tableau
		if (i < 0 || i > 7 || j > 7 || j < 0 ) {
			compteurInterne=0;
			return;
		}

		//Si on tombe dans le vide avant de rencontrer un autre blanc
		if (copiePlateau[i][j] == 0){
			compteurInterne=0;
			return;
		}
		if (copiePlateau[i][j] == 2){
			compteurInterne+=1;
			
			checkRecursif(i,j, direction);
			return;
		}
		
		if (copiePlateau[i][j]==1){
			return;
		}
		
		
	}

public int nombrePoints(Case courante) {
	compt = 0;
	compteurInterne=0;
	
	//Cree un compteur de cases
	//int i = courante.getI(); 
	//int j = courante.getJ();
	
	courante.getVoisins().entrySet().stream().forEach((couple) -> {
		int i = courante.getI(); 
		int j = courante.getJ();
		
		i += couple.getValue().getI();
		j += couple.getValue().getJ();
		
		if (copiePlateau[i][j] == 0 || copiePlateau[i][j] == 1){
			return;
		}
		compteurInterne += 1;
		checkRecursif(i,j, couple.getValue());
		compt+=compteurInterne;
		compteurInterne=0;
	});
	return compt;
		
	}
	
}
