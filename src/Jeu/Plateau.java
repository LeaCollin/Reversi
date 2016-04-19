package Jeu;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import Commun.Commun;
import Commun.Commun.Direction;
import Joueur.Joueur;
import Joueur.OrdiRandom;


public class Plateau extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2516116318800873412L;
	private int taille;
	private Joueur joueurBlanc;
	private Joueur joueurNoir; //plus simple de cr�er une classe jouer ?
	private ArrayList<Case> plateauCase;
	private OrdiRandom ordi;
	private ArrayList<Case> casesposs;
	 
	public Plateau(int taille){
		casesposs = new ArrayList<Case>();
		this.taille = taille;
		ordi = new OrdiRandom();
		joueurBlanc = new Joueur(Couleur.Blanc, false);
		joueurNoir = new Joueur(Couleur.Noir, true);
		plateauCase = new ArrayList<Case>();
		setLayout(new GridLayout(taille, taille));
        for(int i=0; i<taille; i++){
            for(int j=0; j<taille; j++){
            	ajouterCase(i, j);
            }
        }
        init();
    }
	
	private void ajouterCase(int i, int j){
		 Case case1 = new Case(false, i, j);
		 plateauCase.add(case1);
		 case1.sAjouterAuxVoisins(plateauCase);
	     case1.addMouseListener(new ActionSurCases(case1, this));
	     add(case1);
	     
	 }
		
	private void init(){
		 getCase(3,3).add(creerPion(Couleur.Noir));
		 getCase(3,3).setEtat(true);
	     getCase(3,4).add(creerPion(Couleur.Blanc));
	     getCase(3,4).setEtat(true);
	     getCase(4,3).add(creerPion(Couleur.Blanc));
	     getCase(4,3).setEtat(true);
	     getCase(4,4).add(creerPion(Couleur.Noir));
	     getCase(4,4).setEtat(true);
	     afficherLesPossibilites();

	 }
	 
	public Case getCase(int i, int j){
	    return (Case) getComponent(j+i*taille);
	}
	
	public Pion getPion(int i, int j){
		//Avoir acc�s � n'importe quel piont du plateau
		Case c = getCase(i,j);
	    return (Pion) c.getComponent(0) ;
	}
	
	public Pion getPion(Case c){
		//Avoir acc�s au pion d'une case pr�cise
	    return (Pion) c.getComponent(0) ;
	}
		 
	public Pion creerPion(Couleur couleur){
		Pion pion = new Pion(couleur);
	    return pion;
	}
	
	public Case ajouterPion(Case c, Couleur color){
		//retourne la case o� est pos� le nouveau pion (mieux que de retourner pion car on ne peut pas avoir les coordonn�es d'un pion)
		if (!(c.getComponentCount()!=0 && c.isEtat())){
			Pion p = new Pion(color);
			getCase(c.getI(),c.getJ()).add(p);
			getCase(c.getI(),c.getJ()).setEtat(true);
			return c;
		}
		else return null;
	}
	
	
	public void suppPion(Case c){
		if (c.getComponentCount()!=0 && c.isEtat()){
			c.remove(0);
			c.repaint();
			c.setEtat(false);
		}
	}
	
	public void selectionnerCases(int i, int j){
		getCase(i, j).setSelectionnee(true);		
		
	}
	
	//affiche toutes les possibilites pour un joueur
	public int afficherLesPossibilites(){
		int test = 0;
		 for(int i=0; i<taille; i++){
			 for(int j=0; j<taille; j++){
				if (joueurNoir.isSonTour() && getCase(i,j).isEtat()){
					if (getPion(i,j).getCouleur() == Couleur.Noir){
						System.out.println("On etudie la case : "+ getCase(i,j).toString());
						possibilite(getCase(i,j));
						test = 1;
					}
			 	}
				if (joueurBlanc.isSonTour() && getCase(i,j).isEtat()){
					if (getPion(i,j).getCouleur() == Couleur.Blanc){
						System.out.println("On etudie la case : "+ getCase(i,j).toString());
						possibilite(getCase(i,j));
						test = 1;
					}
				}
			 }
		
		 }
		 return test;
		
	}
	
	//affiche tout les possibilites pour une case donnee
	public void possibilite(Case c){
		//Recupere la couleur du pion de la case
		Couleur color = getPion(c).getCouleur();
			
		c.getVoisins().entrySet().stream().forEach((pair) -> { //pair.getValue() => direction

			int index = c.getIndexArrayList() + pair.getValue().getI()*Commun.NOMBRECOLONNES+pair.getValue().getJ();
			
			//Ne pas sortir du tableau
			if (index<0 || index >63) return;
			
			Case caseSuivante = plateauCase.get(index);

			// Si la case est vide, notre pion n'entoure pas des pions adverses donc on ne fait rien.
			if(!caseSuivante.isEtat())
				return;
			
			// si la case est pleine on continue d'avancer
			Pion pionSuivant = getPion(caseSuivante);
			
			//on teste si le dernier pion est de la meme couleur que celle du joueur
			if(color != pionSuivant.getCouleur()) {
				System.out.println("On passe � la case : "+ caseSuivante.toString());

				possibilite(caseSuivante, color, pair.getValue());
			}
			
			// si couleur identique on ne fait rien
			if(color == pionSuivant.getCouleur()) {
				return;
			}		
			
		});
	
		return;
	}
		
	public ArrayList<Case> possibilite(Case c, Couleur color,  Direction d){
		int index = c.getIndexArrayList() + d.getI()*Commun.NOMBRECOLONNES + d.getJ();
				
	    //Ne pas sortir du tableau
		if (index<0 || index >63) return casesposs;
		
		Case caseSuivante = plateauCase.get(index);
		
		//si la case est vide
		if(!caseSuivante.isEtat()){
			if ((c.getI() == 0 || c.getJ() == 0 || c.getI() == 7 || c.getJ() == 7)){
				return casesposs;
			}
			else {
				casesposs.add(caseSuivante);
				selectionnerCases(caseSuivante.getI(), caseSuivante.getJ());

			}
			return casesposs;
		}		
		
		// si la case est pleine on continue d'avancer
		Pion pionSuivant = getPion(caseSuivante);
			
		//le pion suivant est de la meme couleur qu'initial
		if (pionSuivant.getCouleur() == color){
			return casesposs;
		}
		
		//lorsqu'on arrive au bord du plateau
		//if ((c.getVoisins() == null) && (c.getI() == 0 || c.getJ() == 0 || c.getI() == 7 || c.getJ() == 7)){
		//	selectionnerCases(c.getI(), c.getJ());
		//}

		System.out.println("On passe � la case : "+ caseSuivante.toString());


		possibilite(caseSuivante, color, d);
				
		return casesposs;	 
	}		
	

	
	public void actualiserPlateau(){
		int test;
		for(int i=0; i<taille; i++){
            for(int j=0; j<taille; j++){
            	if (getCase(i,j).isSelectionnee()){
            		test=1;
            	}
            	/*if (test==0){
            		finDePartie()==true;
            	}*/
            	getCase(i,j).setSelectionnee(false);
            }
		}
	}
	
	
	public void checkCouleurPion(Case courante) {
		
		//Recupere la couleur du pion de la case courante
		Couleur color = getPion(courante).getCouleur();
					
			    courante.getVoisins().entrySet().stream().forEach((couple) -> {
				// Cree liste cases
		        ArrayList<Case> listeCases = new ArrayList<Case>();
		        
		        listeCases = checkRecursif(listeCases, courante, color, couple.getValue());
		        
		        if(listeCases.size() > 0) {
			        for(Case caseOk : listeCases) {
			        	suppPion(caseOk);
			    		ajouterPion(caseOk, color);
			        }
		        }
	    	});
	}
	
	
	public ArrayList<Case> checkRecursif(ArrayList<Case> liste, Case courante, Couleur color, Direction direction) {
		
		//On recupere le pion que l'on vient de mettre :
		Pion pionCourant = getPion(courante);
		
		// Retourne la case suivante en fonction de sa direction
		int index = courante.getIndexArrayList() + direction.getI()*Commun.NOMBRECOLONNES+direction.getJ();
		
		//Ne pas sortir du tableau
		if (index<0 || index >63) return new ArrayList<Case>();
		
		Case caseSuivante = plateauCase.get(index);

		
		if (!caseSuivante.isEtat()) return new ArrayList<Case>();
					
		// si la case est pleine on continue d'avancer
		Pion pionSuivant = getPion(caseSuivante);
		
		//on teste si le dernier pion est de la meme couleur que celle du joueur
		if(color != pionSuivant.getCouleur()) {
			liste.add(caseSuivante);
			return checkRecursif(liste, caseSuivante, color, direction);
		}
		
		// si couleur identique on retourne la liste pour changer les couleurs de tout les pions
		if(pionCourant.getCouleur() == pionSuivant.getCouleur()) {
			return liste;
		}
		
		return liste;
	}

	public int score(){
		joueurNoir.setScore(0);
		joueurBlanc.setScore(0);
		for(int i=0; i<taille-1; i++){
			 for(int j=0; j<taille-1; j++){
				 if (getCase(i,j).isEtat()){
					if (getPion(i,j).getCouleur() == Couleur.Noir){
						joueurNoir.setScore(joueurNoir.getScore()+1);			 
					 
				 	}
					if (getPion(i,j).getCouleur() == Couleur.Blanc){
						joueurBlanc.setScore(joueurBlanc.getScore()+1);			 
					 
				 	}
				 }
			 }
		}
		return 0;
	}
	
	public void unePartieJcJ(Case c){
		if (joueurNoir.isSonTour()){
			System.out.println("\n ---- tourNoir ---- \n");
			ajouterPion(c, Couleur.Noir);
			updateUI();				
			
			joueurNoir.setSonTour(false);
			joueurBlanc.setSonTour(true);
						
			checkCouleurPion(c);
			score();
			
			System.out.println("Score Noir : "+joueurNoir.getScore());
			System.out.println("Score Blanc : "+joueurBlanc.getScore()+"\n");
			actualiserPlateau();	
			afficherLesPossibilites();

			if (afficherLesPossibilites() == 0 ){
				joueurNoir.setSonTour(true);
				joueurBlanc.setSonTour(false);
			}			
		}
		else {
			System.out.println("\n ---- tourBlanc ---- \n");

			ajouterPion(c, Couleur.Blanc); 			
			updateUI();
			
			joueurNoir.setSonTour(true);
			joueurBlanc.setSonTour(false);
				
			checkCouleurPion(c);
			score();
			
			System.out.println("Score Noir : "+joueurNoir.getScore());
			System.out.println("Score Blanc : "+joueurBlanc.getScore()+"\n");
			
			actualiserPlateau();	
			afficherLesPossibilites();

			if (afficherLesPossibilites() == 0 ){
				joueurNoir.setSonTour(true);
				joueurBlanc.setSonTour(false);
			}
		}
		
		if (finDePartie()){
			score();
			System.out.println("\n La partie est terminee");
			if (joueurNoir.getScore() < joueurBlanc.getScore()){
				System.out.println("Bravo le joueur Blanc a gagne !!");
			}
			
			else{
				System.out.println("Bravo le joueur Noir a gagne !!");
			}
			
		}
	}
	
	public ArrayList<Case> TourJoueur(Case c){
		if (joueurNoir.isSonTour()){
			System.out.println("---- tourNoir ---- \n");
			casesposs = new ArrayList<Case>();

			ajouterPion(c, Couleur.Noir);
			updateUI();				
			
			joueurNoir.setSonTour(false);
			joueurBlanc.setSonTour(true);
						
			checkCouleurPion(c);
			score();
			
			System.out.println("Score Noir : "+joueurNoir.getScore());
			System.out.println("Score Blanc : "+joueurBlanc.getScore()+"\n");
			actualiserPlateau();	
			afficherLesPossibilites();
			System.out.println(casesposs);

			if (afficherLesPossibilites() == 0 ){
				joueurNoir.setSonTour(true);
				joueurBlanc.setSonTour(false);
			}	
			TourIA(casesposs);
			return casesposs;
		}
		
		if (finDePartie()){
			System.out.println("La partie est termin�e");
			if (joueurNoir.getScore() < joueurBlanc.getScore()){
				System.out.println("Dommage l'ordinateur a gagn� !!");
			}
			
			else{
				System.out.println("Bravo vous avez gagn� !!");
			}
		}
		return new ArrayList<Case>();
	}
	
	public void TourIA(ArrayList<Case> casesposs){
		System.out.println("---- Tour Ordi ---- \n");
		Case c = ordi.jouer(casesposs);
		if (c != null){
			ajouterPion(c, Couleur.Blanc);
		}
		casesposs = new ArrayList<Case>();

		updateUI();
		
		joueurNoir.setSonTour(true);
		joueurBlanc.setSonTour(false);
		
		if (c != null){
			checkCouleurPion(c);
		}
		score();
		
		System.out.println("Score Noir : "+joueurNoir.getScore());
		System.out.println("Score Blanc : "+joueurBlanc.getScore()+"\n");
		
		actualiserPlateau();	
		afficherLesPossibilites();
		if (afficherLesPossibilites() == 0 ){
			joueurNoir.setSonTour(true);
			joueurBlanc.setSonTour(false);
		}
		
		if (finDePartie()){
			System.out.println("La partie est termin�e");
			System.out.println("Score Noir : "+joueurNoir.getScore());
			System.out.println("Score Blanc : "+joueurBlanc.getScore()+"\n");
			if (joueurNoir.getScore() < joueurBlanc.getScore()){
				System.out.println("Dommage l'ordi a gagn� !!");
			}
			
			else{
				System.out.println("Bravo vous avez gagn� !!");
			}
		}
	}
	
	
	
	public boolean finDePartie(){ //gerer possibilite ou plateau non plein mais plus de possibilit�
		int compt = 0;
		for(int i=0; i<taille; i++){
			 for(int j=0; j<taille; j++){	
				 if (getCase(i,j).isEtat()){
					 compt +=1;
				 }
			 }
		}
		
		if (compt == 64){
			return true;
		}
		return false;
	}
}

