import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class Plateau extends JPanel{

	private Case caseActive;
	
	public Plateau(int taille){
		setLayout(new GridLayout(taille, taille));
        for(int i=0; i<taille; i++){
            for(int j=0; j<taille; j++){
                if((j%2==0 && i%2==0) || (j%2!=0 && i%2!=0)){
                    ajouterCase(Couleur.Noir, i, j);
                }
                else{
                    ajouterCase(Couleur.Blanc, i, j);
                }
            }
        }
        init();
    }
	
	 private void ajouterCase(Couleur color, int i, int j){
		 Case case1 = new Case(color, i, j);
	     case1.addMouseListener(new ActionSurCases(case1, this));
	     add(case1);
	 }
		
	 private void init(){
		 getCase(3,3).add(creerPion(Couleur.Noir));
	     getCase(3,4).add(creerPion(Couleur.Blanc));
	     getCase(4,3).add(creerPion(Couleur.Blanc));
	     getCase(4,4).add(creerPion(Couleur.Noir));
	 }
	public void afficherPion(Case c){
		if (c.getComponentCount()!=0 && !(c.getComponent(0)== null)){
			System.out.println("FUCK");
		}
		
		else {
			Pion p = new Pion(Couleur.Blanc); 
			getCase(c.getI(),c.getJ()).add(p);
			System.out.println(c.getComponent(0).equals(p));
			System.out.println("F");
		}
	}
	
	public Case getCase(int i, int j){
	    return (Case) getComponent(j+i*8);
	}
		 
	public Pion creerPion(Couleur couleur){
		Pion pion = new Pion(couleur);
	    return pion;
	}
	
	public void selectionnerCases(int i, int j){
		caseActive = getCase(i,j);
		getCase(i, j).setSelectionnee(true);
		
	}

}
