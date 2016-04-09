import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import Commun.Commun;
import Commun.Commun.Direction;


public class Plateau extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2516116318800873412L;
	private static boolean voisinConnu;
	
	private int taille;
	private boolean tourNoir; //plus simple de cr�er une classe jouer ?
	private ArrayList<Case> plateauCase;
	 
	public Plateau(int taille){
		this.taille = taille;
		tourNoir = true;
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
	 
	public boolean isTourNoir() {
		return tourNoir;
	}

	public void setTourNoir(boolean tourNoir) {
		this.tourNoir = tourNoir;
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
	
	//affiche toutes les possibilit�s pour un joueur
	public void afficherLesPossibilites(){
		 for(int i=0; i<taille; i++){
			 for(int j=0; j<taille; j++){
				if (tourNoir && getCase(i,j).isEtat()){
					System.out.println("---- tourNoir ----");
					if (getPion(i,j).getCouleur() == Couleur.Noir){
						System.out.println("On a trouvé un point noir");

						possibilite(getCase(i,j));
					}
			 	}
				if (!tourNoir && getCase(i,j).isEtat()){
					System.out.println("---- tourBlanc ----");
					if (getPion(i,j).getCouleur() == Couleur.Blanc){
						System.out.println("On a trouvé un point blanc");

						possibilite(getCase(i,j));
					}
				}
			} 
		 }
	}
	
	//affiche tout les possibilit�s pour une case donn�e (pb lorsqu'il y a trop de cases align�es
	public void possibilite(Case c){
		System.out.println("Case choisie : "+c.toString()+"Etat : "+c.isEtat());		
		//Récupère la couleur du pion de la case
		Couleur color = getPion(c).getCouleur();
			
		c.getVoisins().entrySet().stream().forEach((pair) -> { //pair.getValue() => direction

			int index = c.getIndexArrayList() + pair.getValue().getI()*Commun.NOMBRECOLONNES+pair.getValue().getJ();
			
			Case caseSuivante = plateauCase.get(index);
			System.out.println("Case voisine à celle cliquée : "+caseSuivante.toString()+"Etat : "+caseSuivante.isEtat());		

			// Si la case est vide, notre pion n'entoure pas des pions adverses donc on ne fait rien.
			if(!caseSuivante.isEtat())
				return;
			
			// si la case est pleine on continue d'avancer
			Pion pionSuivant = getPion(caseSuivante);
			
			//on teste si le dernier pion est de la meme couleur que celle du joueur
			if(color != pionSuivant.getCouleur()) {
				System.out.println("on rentre dans la récursivité");		

				possibilite(caseSuivante, color, pair.getValue());
				
			}
			
			// si couleur identique on retourne la liste pour changer les couleurs de tout les pions
			if(color == pionSuivant.getCouleur()) {
				return;
			}		
			
		});
		return;
	}
		
	public void possibilite(Case c, Couleur color,  Direction d){
		System.out.println("Case étudiée "+c.toString()+"\n");		
		int index = c.getIndexArrayList() + d.getI()*Commun.NOMBRECOLONNES + d.getJ();
		
		voisinConnu = false;
		
			// verifie si on a un voisin ou pas: BUT: Ne pas sortir du tableau
		plateauCase.get(index).getVoisins().entrySet().stream().forEach((pair) -> {
			if(pair.getValue() == d)
				voisinConnu = true;
    	});
		
			// Ne pas sortir du tableau
		if(!voisinConnu)
			return;
		
		Case caseSuivante = plateauCase.get(index);
		System.out.println("Case suivante : "+caseSuivante.toString()+"\n Etat: "+caseSuivante.isEtat()+"\n");
			
			// Si la case est vide, notre pion n'entoure pas des pions adverses donc on ne fait rien.
		if(!caseSuivante.isEtat()){
			System.out.println("on active la case \n");		
			selectionnerCases(caseSuivante.getI(), caseSuivante.getJ());
			return;
		}
		
			// si la case est pleine on continue d'avancer
		Pion pionSuivant = getPion(caseSuivante);
			
			//le pion suivant est de la même couleur qu'initial
		if (pionSuivant.getCouleur() == color){
			System.out.println("on s'arrete car le pion suivant est de la même couleur qu'initial" );
			return;
		}
		
		System.out.println("on relance la récursivité");
		possibilite(caseSuivante, color, d);
		
		return;	 
	}		

	
	public void actualiserPlateau(){
		for(int i=0; i<taille; i++){
            for(int j=0; j<taille; j++){
            	getCase(i,j).setSelectionnee(false);
            }
		}
	}
	
	
	public void checkCouleurPion(Case courante) {
		
		//Récupère la couleur du pion de la case courante
		Couleur joueur = getPion(courante).getCouleur();
		
		// Si la case en question a des voisins:
		if(courante.getVoisins() != null) {
			
			courante.getVoisins().entrySet().stream().forEach((pair) -> {
				
				// Créé liste cases
		        ArrayList<Case> listeCases = new ArrayList<Case>();
		        
		        listeCases = checkRecursif(listeCases, courante, joueur, pair.getValue());
		        
		        if(listeCases.size() > 0) {
			        for(Case caseOk : listeCases) {
			        	suppPion(caseOk);
			    		ajouterPion(caseOk, joueur);
			        }
		        }
	    	});
		}
	}
	
	
	public ArrayList<Case> checkRecursif(ArrayList<Case> liste, Case courante, Couleur joueur, Direction direction) {
		
		//On récupère le pion que l'on vient de mettre :
		Pion pionCourant = getPion(courante);
		
		// Retourne la case suivante en fonction de sa direction
		int index = courante.getIndexArrayList() + direction.getI()*Commun.NOMBRECOLONNES+direction.getJ();
		
		voisinConnu = false;
		
		// verifie si on a un voisin ou pas: BUT: Ne pas sortir du tableau
		plateauCase.get(index).getVoisins().entrySet().stream().forEach((pair) -> {
			if(pair.getValue() == direction)
				voisinConnu = true;
    	});
		
		// Ne pas sortir du tableau
		if(!voisinConnu)
			return new ArrayList<Case>();
		
		// Case suivante prend la case dans la direction concernée (on vient de tester qu'elle existait)
		Case caseSuivante = plateauCase.get(index);
			
		// Si la case est vide, notre pion n'entoure pas des pions adverses donc on ne fait rien.
		if(!caseSuivante.isEtat())
			return new ArrayList<Case>();
		
		// si la case est pleine on continue d'avancer
		Pion pionSuivant = getPion(caseSuivante);
		
		//on teste si le dernier pion est de la meme couleur que celle du joueur
		if(joueur != pionSuivant.getCouleur()) {
			liste.add(caseSuivante);
			return checkRecursif(liste, caseSuivante, joueur, direction);
		}
		
		// si couleur identique on retourne la liste pour changer les couleurs de tout les pions
		if(pionCourant.getCouleur() == pionSuivant.getCouleur()) {
			return liste;
		}
		
		return liste;
	}

	public void unePartie(Case c){
		if (isTourNoir()){
			ajouterPion(c, Couleur.Noir);
			updateUI();									
			setTourNoir(!isTourNoir());
			checkCouleurPion(c);
			actualiserPlateau();	
			System.out.println("--------------");//d�selection toutes les cases pour pouvoir passser au tour suivant
			afficherLesPossibilites();
			
		}
		else {
			ajouterPion(c, Couleur.Blanc); 			
			updateUI();
			checkCouleurPion(c);
			setTourNoir(!isTourNoir());
			actualiserPlateau();	
			System.out.println("--------------");//d�selection toutes les cases pour pouvoir passser au tour suivant
			afficherLesPossibilites();
		}
	}
}

