/*package Joueur;

import Jeu.Case;
import Jeu.Plateau;

public class Minmax {
	
	private Plateau p;

	public int[][] plateau = {
			{30 , -30, 10, 5, 5, 10, -30, 30},
			{-30, -30, 1,  1,1, 1, -30, -30},
			{10, 1,5,2,2,5,1,10},
			{5,1,2,1,1,2,1,5},
			{5,1,2,1,1,2,1,5},
			{5,1,2,1,1,2,1,5},
			{-30, -30, 1,  1,1, 1, -30, -30},
			{30 , -30, 10, 5, 5, 10, -30, 30},
	};
	
	public Case jouer(){
		//utilise MinMax
	}
	
	public int minMax(int profondeur, Joueur joueur){
		if (p.finDePartie() || profondeur==0){
			return eval(); //evaluation pour l'ordinateur
		}
		
		int max; //Valeur du score au mieux possible
		int min;
		int score = 0;
		
		//initialisation des valeurs max
		min=Integer.MIN_VALUE; //-l'infini //pour le joueur ordi
		max=Integer.MAX_VALUE; //+l'infini // pour nous
		
		for (){
			
		}
		
		
		
	}
	
	public ArrayList<Case> possibilites(){
		ArrayList<Case> possibilites = new ArrayList<>();
		for(Case c : p.case)
		{
			// si c'est une possiblité
		}
	}
	

}*/
