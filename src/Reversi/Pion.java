package Reversi;

public class Pion {
		
	private Couleur couleur;
	private Case emplacement;

	public Pion(Couleur couleur, Case emplacement) {
		this.couleur = couleur;
		this.emplacement = emplacement;
	}

	public Pion(Couleur couleur) {
		this.couleur = couleur;
	}

	public Case getEmplacement() {
		return emplacement;
	}

	public void setEmplacement(Case emplacement) {
		this.emplacement = emplacement;
	}
	
	
	
	
}
