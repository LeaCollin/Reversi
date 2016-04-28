package Joueur;

import java.util.ArrayList;
import Jeu.Case;
import Jeu.Couleur;
import Jeu.Plateau;

public class OrdiAmeliore {
	
	private Plateau plateau;
	
	public OrdiAmeliore(Plateau plat){
		plateau = plat;
	}
	
	public Case jouer(ArrayList<Case> possibilite){
		if (possibilite.size() != 0){
			for (Case c : possibilite){
				if (coin(c)){
					System.out.println("coin");
					return c;
				}
			return meilleurCoup(possibilite);
			}
		}
		return null;
	}
	
	public boolean coin(Case c){
		if ((c.getI() == 0 && c.getJ() == 0) || (c.getI() == 0 && c.getJ() == 7) || (c.getI() == 7 && c.getJ() == 0) || (c.getI() == 7 && c.getJ() == 7 )){
			return true;
		}
		return false;
	}
	
	public boolean bord(Case c){
		if (c.getI() == 0 || c.getI() == 7 || c.getJ() == 7 || c.getJ() == 0 ){
			return true;
		}
		return false;
	}
	
	public int point(Case c){
		Plateau platTemp = plateau;
		Case cTemp= new Case(false,c.getI(),c.getJ());
		platTemp.ajouterPion(cTemp, Couleur.Blanc);
		System.out.println(cTemp.toString());
		cTemp.checkCouleurPion(platTemp,cTemp);
		platTemp.score();
		System.out.println("score: "+platTemp.joueurBlanc.getScore());
		
		return platTemp.joueurBlanc.getScore();
		
	}
	
	public Case meilleurCoup(ArrayList<Case> possibilite){
		Case bestcase = null;
		int score = 0;
		for (Case c : possibilite){
			if (c==null) return null;
			if (score < point(c)){
				bestcase = c;
				score  = point(c);
			}
		}
		return bestcase;
	}


}
