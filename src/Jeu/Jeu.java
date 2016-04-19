package Jeu;
import javax.swing.JFrame;


public class Jeu {
		
	public static void main(String args[]){
		Fenetre fenetre = new Fenetre();
		fenetre.isVisible();
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
