package Jeu;


import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Paint;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;

import Commun.*;


public class Case extends JPanel{

	private static final long serialVersionUID = -3347630978748093310L;
	private boolean selectionnee;
	private int i;							// ligne
	private int j; 							// colonne
	private boolean etat; 					// permet de savoir si la case contient un pion ou non => true : il y a un pion dans la case
	private HashMap<Case, Commun.Direction> voisins;

	public Case(boolean etat, int i, int j){
		this.etat = etat;
		this.i = i;
		this.j = j;
		
		this.voisins = new HashMap<Case, Commun.Direction>();
		
        setLayout(new GridLayout(1,0));
    }

	public int getIndexArrayList() {
		return this.i*Commun.NOMBRECOLONNES+this.j;
	}
	
	//Creation d'une liste contenant les cases voisines lors de la creation de la grille et du plateau
	public void sAjouterAuxVoisins(ArrayList<Case> listeCase){
		
		if(this.j > 0) {
			// Ajoute a la case courante, la case qui la precede, dans ses voisins.
			this.voisins.put(listeCase.get(this.getIndexArrayList()-1), Commun.Direction.getDirection(0, -1));
			// Sachant qu'a la creation de la case precedente, la case courante n'existait pas, on l'ajoute maintenant aux voisins de la case precedente.
			listeCase.get(this.getIndexArrayList()-1).voisins.put(this, Commun.Direction.getDirection(0, 1));
			
			if(this.i > 0) {
				//Case en diagonale haut-gauche
				this.voisins.put(listeCase.get(this.getIndexArrayList()-1-Commun.NOMBRECOLONNES), Commun.Direction.getDirection(-1, -1));
				listeCase.get(this.getIndexArrayList()-1-Commun.NOMBRECOLONNES).voisins.put(this,Commun.Direction.getDirection(1, 1));
			}
		}
		
		if(this.i > 0) {
			//Case au dessus
			this.voisins.put(listeCase.get(this.getIndexArrayList()-Commun.NOMBRECOLONNES), Commun.Direction.getDirection(-1, 0));
			listeCase.get(this.getIndexArrayList()-Commun.NOMBRECOLONNES).voisins.put(this, Commun.Direction.getDirection(1, 0));
		}
		
		if(this.j < Commun.NOMBRECOLONNES-1 && this.i > 0) {
			//Case en diagonale-droite
			this.voisins.put(listeCase.get(this.getIndexArrayList()+1-Commun.NOMBRECOLONNES), Commun.Direction.getDirection(-1, 1));
			listeCase.get(this.getIndexArrayList()+1-Commun.NOMBRECOLONNES).voisins.put(this, Commun.Direction.getDirection(1, -1));
		}
	}

    public boolean isEtat() {
		return etat;
	}

	public void setEtat(boolean etat) {
		this.etat = etat;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public int getJ() {
		return j;
	}

	public void setJ(int j) {
		this.j = j;
	}

    public boolean isSelectionnee() {
        return selectionnee;
    }

	public HashMap<Case, Commun.Direction> getVoisins() {
		return voisins;
	}

    public void setSelectionnee(boolean selectionnee) {
    	this.selectionnee = selectionnee;
        if(selectionnee){
            setBackground(Color.RED);
        }
        else {
            initCouleur();
        }
    }
    
    public void initCouleur(){
    	setBackground(Color.WHITE);
    }
    
    @Override
    public void paintComponent(Graphics g){
    	//Définit le graphisme d'une case
        Paint paint;
        Graphics2D g2d;
        if (g instanceof Graphics2D) {
            g2d = (Graphics2D) g;
        }
        else {
            System.out.println("Error");
            return;
        }
        paint = new GradientPaint(0,0,getBackground(), getWidth(), getHeight(), Color.BLACK);
        g2d.setPaint(paint);
        g.fillRect(0, 0, getWidth(), getHeight());
    }
    
    public String toString() {
    	return "Case : "+this.getI()+":"+this.getJ()+" => "+this.isEtat();
    }
    

    public void checkCouleurPion(Plateau p, Case courante) {
		
		//Recupere la couleur du pion de la case courante
		Couleur color = p.getPion(courante).getCouleur();
			    courante.getVoisins().entrySet().stream().forEach((couple) -> {
				// Cree liste cases
		        ArrayList<Case> listeCases = new ArrayList<Case>();
		        
		        listeCases = p.checkRecursif(listeCases, courante, color, couple.getValue());
		        
		        if(listeCases.size() > 0) {
			        for(Case caseOk : listeCases) {
			        	p.suppPion(caseOk);
			    		p.ajouterPion(caseOk, color);
			        }
		        }
	    	});
	}
	
}
