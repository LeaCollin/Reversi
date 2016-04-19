package Joueur;

import Jeu.Couleur;

public class Joueur {
	
	private int score;
	private Couleur couleur;
	private boolean sonTour;
	
	public Joueur(Couleur couleur, boolean sonTour) {
		this.score = 0;
		this.couleur = couleur;
		this.sonTour = sonTour;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public boolean isSonTour() {
		return sonTour;
	}

	public void setSonTour(boolean sonTour) {
		this.sonTour = sonTour;
	}	
		
}
