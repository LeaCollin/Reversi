package Joueur;
import java.util.ArrayList;
import java.util.Random;

import Jeu.Case;
import Jeu.Plateau;

public class OrdiRandom {
	
	public Case jouer(ArrayList<Case> possibilite){
		Random r = new Random();
		System.out.println("Possibilités pour l'ordi: "+possibilite.size());
		if (possibilite.size() != 0){
			int val = r.nextInt(possibilite.size());
			Case emplacement = possibilite.get(val);
			return emplacement;
		}
		return null;
	}

}
