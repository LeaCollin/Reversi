import java.awt.GridLayout;
import javax.swing.JPanel;


public class Plateau extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2516116318800873412L;
	private int taille;
	private boolean tourNoir; //plus simple de créer une classe jouer ?
	 
	public Plateau(int taille){
		this.taille = taille;
		tourNoir = true;
		setLayout(new GridLayout(taille, taille));
        for(int i=0; i<taille; i++){
            for(int j=0; j<taille; j++){
                if((j%2==0 && i%2==0) || (j%2!=0 && i%2!=0)){
                    ajouterCase(i, j);
                }
                else{
                    ajouterCase(i, j);
                }
            }
        }
        init();
    }
	
	 private void ajouterCase(int i, int j){
		 Case case1 = new Case(false, i, j);
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
	     jouer(tourNoir);
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
		//Avoir accès à n'importe quel piont du plateau
		Case c = getCase(i,j);
	    return (Pion) c.getComponent(0) ;
	}
	
	public Pion getPion(Case c){
		//Avoir accès au pion d'une case précise
	    return (Pion) c.getComponent(0) ;
	}
		 
	public Pion creerPion(Couleur couleur){
		Pion pion = new Pion(couleur);
	    return pion;
	}
	
	public Case ajouterPion(Case c, Couleur color){
		//retourne la case où est posé le nouveau pion (mieux que de retourner pion car on ne peut pas avoir les coordonnées d'un pion)
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
	
	//affiche toutes les possibilités pour un joueur
	public void afficherLesPossibilites(){
		 for(int i=0; i<taille; i++){
			 for(int j=0; j<taille; j++){
				if (tourNoir && getCase(i,j).isEtat()){
					if (getPion(i,j).getCouleur() == Couleur.Noir){
						possibilite(getCase(i,j));
					}
			 	}
				if (!tourNoir && getCase(i,j).isEtat()){
					if (getPion(i,j).getCouleur() == Couleur.Blanc){
						possibilite(getCase(i,j));
					}
				}
			} 
		 }
	}
	
	//affiche tout les possibilités pour une case donnée (et marche)
	public void possibilite(Case c){
		int x = c.getJ();
		int y = c.getI();
		//on test du côté droit du pion
		while (getCase(y,x).isEtat() && x <= taille-3){
			if (getCase(y,x+1).isEtat() && getPion(y,x+1).getCouleur() != getPion(c).getCouleur()){
				x = x + 2;
			}
			else {
				break;
				}
		}
		if (getCase(y,x).isEtat() == false){
			selectionnerCases(y,x);
		}
		x = c.getJ();
		y = c.getI();
		
		//on test le côté gauche du pion
		while (getCase(y,x).isEtat() && x >= 2){
			if (getCase(y,x-1).isEtat() && getPion(y,x-1).getCouleur() != getPion(c).getCouleur()){
				x = x - 2;
			}
			else {
				break;
				}
		}
		if (getCase(y,x).isEtat() == false){
			selectionnerCases(y,x);
		}
		x = c.getJ();
		y = c.getI();
		
		//on test au dessus du pions
		while (getCase(y,x).isEtat() && y >= 2){
			if (getCase(y-1,x).isEtat() && getPion(y-1,x).getCouleur() != getPion(c).getCouleur()){
				y = y - 2;
			}
			else {
				break;
				}
		}
		if (getCase(y,x).isEtat() == false){
			selectionnerCases(y,x);
		}
		x = c.getJ();
		y = c.getI();
		
		//on test en dessous du pions
		while (getCase(y,x).isEtat() && y <= taille-3){
			if (getCase(y+1,x).isEtat() && getPion(y+1,x).getCouleur() != getPion(c).getCouleur()){
				y = y + 2;
			}
			else {
				break;
				}
		}
		if (getCase(y,x).isEtat() == false){
			selectionnerCases(y,x);
		}
		x = c.getJ();
		y = c.getI();
		
		//On test la diagonale nord-ouest
		while (getCase(y,x).isEtat() && y >=2 && x >= 2){
			if (getCase(y-1,x-1).isEtat() && getPion(y-1,x-1).getCouleur() != getPion(c).getCouleur()){
				y = y - 2;
				x = x - 2;
			}
			else {
				break;
				}
		}
		if (getCase(y,x).isEtat() == false){
			selectionnerCases(y,x);
		}
		x = c.getJ();
		y = c.getI();
		
		//On test la diagonale nord-est
		while (getCase(y,x).isEtat() && y >=2 && x <= taille-3){
			if (getCase(y-1,x+1).isEtat() && getPion(y-1,x+1).getCouleur() != getPion(c).getCouleur()){
				y = y - 2;
				x = x + 2;
			}
			else {
				break;
			}
		}
		if (getCase(y,x).isEtat() == false){
			selectionnerCases(y,x);
		}
		x = c.getJ();
		y = c.getI();
		
		//On test la diagonale sud-est
		while (getCase(y,x).isEtat() && y <= 6 && x <= 6){
			if (getCase(y+1,x+1).isEtat() && getPion(y+1,x+1).getCouleur() != getPion(c).getCouleur()){
				y = y + 2;
				x = x + 2;
			}
			else {
				break;
			}
		}
		if (getCase(y,x).isEtat() == false){
			selectionnerCases(y,x);
		}
		x = c.getJ();
		y = c.getI();
		
		//On test la diagonale sud-ouesr
		while (getCase(y,x).isEtat() && y <= 6 && x >= 0){
			if (getCase(y+1,x-1).isEtat() && getPion(y+1,x-1).getCouleur() != getPion(c).getCouleur()){
				y = y + 2;
				x = x - 2;
			}
			else {
				break;
			}
		}
		if (getCase(y,x).isEtat() == false){
			selectionnerCases(y,x);
		}
		x = c.getJ();
		y = c.getI();

		
	}
	
	public void jouer(boolean bool){
		//à faire : gerer le cas où le plateau est plein
		afficherLesPossibilites();		
	}
	
	public void actualiserPlateau(){
		for(int i=0; i<taille; i++){
            for(int j=0; j<taille; j++){
            	getCase(i,j).setSelectionnee(false);
            }
		}
	}
	
	public void checkCouleurPion(Case c){
		int i = c.getI();
		int j = c.getJ();
    	// on test l'est
    	if (j < 6 && getCase(i,j+1).isEtat() && getCase(i,j+2).isEtat() && getPion(i,j).getCouleur() != getPion(i,j+1).getCouleur() && getPion(i,j+2).getCouleur() == getPion(i,j).getCouleur()){
    		suppPion(getCase(i,j+1));
    		ajouterPion(getCase(i,j+1),getPion(i,j).getCouleur()); 
       	}
    	// on test l'ouest
    	if (j > 1 && getCase(i,j-1).isEtat() && getCase(i,j-2).isEtat() && getPion(i,j).getCouleur() != getPion(i,j-1).getCouleur() && getPion(i,j-2).getCouleur() == getPion(i,j).getCouleur()){
    		suppPion(getCase(i,j-1));
    		ajouterPion(getCase(i,j-1),getPion(i,j).getCouleur()); 
       	}
    	// on test le nord
    	if (i > 1 && getCase(i+1,j).isEtat() && getCase(i+2,j).isEtat() && getPion(i,j).getCouleur() != getPion(i+1,j).getCouleur() && getPion(i+2,j).getCouleur() == getPion(i,j).getCouleur()){
    		suppPion(getCase(i+1,j));
    		ajouterPion(getCase(i+1,j),getPion(i,j).getCouleur()); 
       	}
    	// on test le sud
    	if (i < 6 && getCase(i-1,j).isEtat() && getCase(i-2,j).isEtat() && getPion(i,j).getCouleur() != getPion(i-1,j).getCouleur() && getPion(i-2,j).getCouleur() == getPion(i,j).getCouleur()){
    		suppPion(getCase(i-1,j));
    		ajouterPion(getCase(i-1,j),getPion(i,j).getCouleur()); 
       	}
    	// on test le nord-est
    	if (i > 1 && j < 5 && getCase(i-1,j+1).isEtat() && getCase(i-2,j+2).isEtat() && getPion(i,j).getCouleur() != getPion(i-1,j+1).getCouleur() && getPion(i-2,j+2).getCouleur() == getPion(i,j).getCouleur()){
    		suppPion(getCase(i-1,j+1));
    		ajouterPion(getCase(i-1,j+1),getPion(i,j).getCouleur());  
       	}
       	// on test le nord-ouest
    	if (i > 1 && j > 1 && getCase(i-1,j-1).isEtat() && getCase(i-2,j-2).isEtat() && getPion(i,j).getCouleur() != getPion(i-1,j-1).getCouleur() && getPion(i-2,j-2).getCouleur() == getPion(i,j).getCouleur()){
    		suppPion(getCase(i-1,j-1));
    		ajouterPion(getCase(i-1,j-1),getPion(i,j).getCouleur()); 
       	}
       	// on test le sud-ouest
    	if (i < 5 && j > 1 && getCase(i+1,j-1).isEtat() && getCase(i+2,j-2).isEtat() && getPion(i,j).getCouleur() != getPion(i+1,j-1).getCouleur() && getPion(i+2,j-2).getCouleur() == getPion(i,j).getCouleur()){
    		suppPion(getCase(i+1,j-1));
    		ajouterPion(getCase(i+1,j-1),getPion(i,j).getCouleur()); 
       	}
       	// on test le sud-est
    	if (i < 5 && j < 5 && getCase(i+1,j+1).isEtat() && getCase(i+2,j+2).isEtat() && getPion(i,j).getCouleur() != getPion(i+1,j+1).getCouleur() && getPion(i+2,j+2).getCouleur() == getPion(i,j).getCouleur()){
    		suppPion(getCase(i+1,j+1));
    		ajouterPion(getCase(i+1,j+1),getPion(i,j).getCouleur()); 
       	}    	
    			
	}

	public void unePartie(Case c){
		if (isTourNoir()){
			ajouterPion(c, Couleur.Noir);
			updateUI();									//OH OUI CA MARCHE ENFIN MERCI OPENCLASSROOM !!!!
			setTourNoir(!isTourNoir());
			checkCouleurPion(c);
			actualiserPlateau();						//déselection toutes les cases pour pouvoir passser au tour suivant
			jouer(isTourNoir());
		}
		else {
			ajouterPion(c, Couleur.Blanc); 			
			updateUI();
			checkCouleurPion(c);
			setTourNoir(!isTourNoir());
			actualiserPlateau();						//déselection toutes les cases pour pouvoir passser au tour suivant
			jouer(isTourNoir());
		}
	}
}

