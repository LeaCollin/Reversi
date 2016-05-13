package Jeu;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Pion extends JPanel {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 3028002815862069208L;
	private Couleur couleur;
	private Case emplacement;
	
	public Pion(Couleur couleur) {
        this.couleur = couleur;
        setOpaque(false);
        switch (couleur) {						//Definit la couleur du poin en fonction de l'appartenance de la cas à tel ou tel joueur
        case Blanc :
            setForeground(Color.LIGHT_GRAY);
            setBackground(Color.WHITE);
            break;
        case Bleu :
            setForeground(Color.BLUE);
            setBackground(Color.CYAN);
            break;
        case Attente :							//Permet au joueur de voir où l'IA a pose son pion avant l'actualisation du plateau.
        	setForeground(Color.BLACK);
        	setBackground(Color.GREEN);
        	break;
        }
    }
	
	
	@Override
    public void paintComponent(Graphics g){
		//initialise le graphisme des pions
        Paint paint;
        Graphics2D g2d;
        if (g instanceof Graphics2D) {
            g2d = (Graphics2D) g;
        }
        else {
            System.out.println("Error");
            return;
        }
        paint = new GradientPaint(0,0, getBackground(), getWidth(), getHeight(), getForeground());
        g2d.setPaint(paint);
        g.fillOval(5, 5, getWidth()-10, getHeight()-10);

    }



	public Case getEmplacement() {
		return emplacement;
	}

	public void setEmplacement(Case emplacement) {
		this.emplacement = emplacement;
	}

	public Couleur getCouleur() {
		return couleur;
	}
	
	public void isCouleur(Couleur color){
		couleur = color;
	}
	
	
}
