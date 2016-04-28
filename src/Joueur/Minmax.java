package Joueur;

import java.util.ArrayList;

import Jeu.*;

public class Minmax {
	
	private Plateau plateauInit;		//permet de garder la disposition des pions avant simulation de coups
	private Plateau plateauMod;	
	private int maxVal;
	private int minVal;
	private int profondeur;
	private ArrayList<Case> casesposs;
	
	public Minmax(Plateau plateau){
		plateauInit = plateau;
		
	}
	
	public void jouer(){
		
	}
	
	public int min(){
		if (profondeur == 0 || plateauMod.finDePartie()){
			return eval();
		}
		
		for (Case c : casesposs){
			
		}
		return 0;
	}
	
	public void max(){
		
	}
	
	public int eval(){
		return 0;
		
	}

}
