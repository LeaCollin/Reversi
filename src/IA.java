import java.util.ArrayList;
import java.util.Random;

public class IA {
	
	private Plateau plateau;
	
	public Case jouer(ArrayList<Case> possibilite){
		Random r = new Random();

		int val = r.nextInt(possibilite.size());

		Case emplacement = possibilite.get(val);
		System.out.println(emplacement.toString());
		plateau.ajouterPion( emplacement, Couleur.Blanc);
		return emplacement;
	}

}
