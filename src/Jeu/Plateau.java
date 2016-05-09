package Jeu;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Commun.Commun;
import Commun.Commun.Direction;
import Joueur.Joueur;
import Joueur.OrdiAmeliore;


public class Plateau extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2516116318800873412L;
	private int taille;
	public Joueur joueurBlanc;
	public Joueur joueurNoir; //plus simple de cr�er une classe jouer ?
	private ArrayList<Case> plateauCase;
	//private OrdiRandom ordi;
	private OrdiAmeliore ordi;
	private ArrayList<Case> casesposs;
	 
	public Plateau(int taille){
		casesposs = new ArrayList<Case>();
		this.taille = taille;
		//ordi = new OrdiRandom();
		joueurBlanc = new Joueur(Couleur.Blanc, false);
		joueurNoir = new Joueur(Couleur.Blue, true);
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
		 getCase(3,3).add(creerPion(Couleur.Blue));
		 getCase(3,3).setEtat(true);
	     getCase(3,4).add(creerPion(Couleur.Blanc));
	     getCase(3,4).setEtat(true);
	     getCase(4,3).add(creerPion(Couleur.Blanc));
	     getCase(4,3).setEtat(true);
	     getCase(4,4).add(creerPion(Couleur.Blue));
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
	public void afficherLesPossibilites(){
        ordi = new OrdiAmeliore(this);
		 for(int i=0; i<taille; i++){
			 for(int j=0; j<taille; j++){
				if (joueurNoir.isSonTour() && getCase(i,j).isEtat()){
					if (getPion(i,j).getCouleur() == Couleur.Blue){
						possibilite(getCase(i,j));
					}
			 	}
				if (joueurBlanc.isSonTour() && getCase(i,j).isEtat()){
					if (getPion(i,j).getCouleur() == Couleur.Blanc){
						possibilite(getCase(i,j));
					}
				}
			 }
		
		 }
		
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

			//ne pas passer à la ligne
			if (!c.getVoisins().containsKey(caseSuivante)){
				return;
			}

			// Si la case est vide, notre pion n'entoure pas des pions adverses donc on ne fait rien.
			if(!caseSuivante.isEtat())
				return;
			
			// si la case est pleine on continue d'avancer
			Pion pionSuivant = getPion(caseSuivante);
			
			//on teste si le dernier pion est de la meme couleur que celle du joueur
			if(color != pionSuivant.getCouleur()) {

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
		
	    //ne pas passer à la ligne
		if (!c.getVoisins().containsKey(caseSuivante)){
			return casesposs;
		}

		//si la case est vide
		if(!caseSuivante.isEtat()){
			casesposs.add(caseSuivante);
			selectionnerCases(caseSuivante.getI(), caseSuivante.getJ());
			return casesposs;
		}		
		
		// si la case est pleine on continue d'avancer
		Pion pionSuivant = getPion(caseSuivante);
			
		//le pion suivant est de la meme couleur qu'initial
		if (pionSuivant.getCouleur() == color){
			return casesposs;
		}

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
            	getCase(i,j).setSelectionnee(false);
            }
		}
	}
	
	public ArrayList<Case> checkRecursif(ArrayList<Case> liste, Case courante, Couleur color, Direction direction) {
		
		//On recupere le pion que l'on vient de mettre :
		Pion pionCourant = getPion(courante);
		
		// Retourne la case suivante en fonction de sa direction
		int index = courante.getIndexArrayList() + direction.getI()*Commun.NOMBRECOLONNES+direction.getJ();
		
		//Ne pas sortir du tableau
		if (index<0 || index >63) return new ArrayList<Case>();
		
		Case caseSuivante = plateauCase.get(index);

		//ne pas passer à la ligne
		if (!courante.getVoisins().containsKey(caseSuivante)){
				return new ArrayList<Case>();
		}

				
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
		for(int i=0; i<taille; i++){
			 for(int j=0; j<taille; j++){
				 if (getCase(i,j).isEtat()){
					if (getPion(i,j).getCouleur() == Couleur.Blue){
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
	
	/*public void unePartieJcJ(Case c){
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
	}*/
	
	public ArrayList<Case> TourJoueur(Case c){
		if (joueurNoir.isSonTour()){
			System.out.println("---- tourNoir ---- \n");
			casesposs = new ArrayList<Case>();

			ajouterPion(c, Couleur.Blue);
			updateUI();				
			
			joueurNoir.setSonTour(false);
			joueurBlanc.setSonTour(true);
						
			c.checkCouleurPion(this,c);
			score();
			
			System.out.println("Score Bleu : "+joueurNoir.getScore());
			System.out.println("Score Blanc : "+joueurBlanc.getScore()+"\n");
			actualiserPlateau();	
			afficherLesPossibilites();
			//System.out.println(casesposs);

			if (casesposs.size() == 0 ){
				return casesposs;
			}	
			return casesposs;
		}
		return new ArrayList<Case>();
	}
	
	public Case initTourIA(){
		System.out.println("---- Tour Ordi ---- \n");
		Case c = ordi.jouer(casesposs);
		if (c!=null){
			c.setSelectionnee(false);
			System.out.println("Cocuou point vert");
			ajouterPion(c, Couleur.Attente);
			updateUI();	
		}
		return c;
	}
	
	public void TourIA(Case c) {
	
		if (c!=null){
			suppPion(c);
			ajouterPion(c, Couleur.Blanc);
			
			casesposs = new ArrayList<Case>();
			
			c.checkCouleurPion(this,c);
			score();
			
			updateUI();

			System.out.println("Score Bleu : "+joueurNoir.getScore());
			System.out.println("Score Blanc : "+joueurBlanc.getScore()+"\n");
			
			actualiserPlateau();	
		}
		joueurNoir.setSonTour(true);
		joueurBlanc.setSonTour(false);
		
		if (finDePartie()){
			//Quand on clique sur ok: fermer la fentre avec dispose
			ImageIcon img;
			JOptionPane jop = new JOptionPane();
			String fin = "La partie est terminee \n";
			fin += "\nScore Bleu : "+joueurNoir.getScore();
			fin += "\nScore Blanc : "+joueurBlanc.getScore()+"\n";
			if (joueurNoir.getScore() < joueurBlanc.getScore()){
				fin += "\nDommage l'ordi a gagne !!";
				img = new ImageIcon("images/looser.jpg");
			}
			else if(joueurNoir.getScore()==joueurBlanc.getScore()){
				fin += "\n Egalite!";
				img = new ImageIcon() ; //Rajouter une image !!
				} else {
					fin += "\nBravo vous avez gagne !!";
					img = new ImageIcon("images/winner.png");
					}
	        jop.showMessageDialog(null, fin, "Fin de la partie", JOptionPane.INFORMATION_MESSAGE,img); 
	        return;

		}
		
		afficherLesPossibilites();
		
		if (casesposs.size() == 0){
			joueurNoir.setSonTour(false);
			joueurBlanc.setSonTour(true);
			afficherLesPossibilites();

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			Case ca = initTourIA();

			Timer timer = new Timer();
	        timer.schedule (new TimerTask() {
	            public void run()
	            {
	                TourIA(ca);    
	                
	            }
	        }, 1000);  
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

