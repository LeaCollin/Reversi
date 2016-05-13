package Jeu;

import Joueur.Joueur;

public class Partie {

	private Joueur j1;
	private Joueur j2;
	private Plateau p;
	private Boolean tourBlanc;
	
	public Partie(){
		this.j1=new Joueur(Couleur.Bleu, true);			// Joueur
		this.j2=new Joueur(Couleur.Blanc, false);		// IA
		this.p=new Plateau(8);	
		this.tourBlanc=false;
	}

	public void jeu(){
			tourBlanc = !tourBlanc;
			p.afficherLesPossibilites();
	}
	
	public Plateau getP() {
		return p;
	}
}
