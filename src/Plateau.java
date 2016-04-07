import java.awt.GridLayout;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class Plateau extends JPanel{

	private Case c;
	
	public Plateau(int taille){
		setLayout(new GridLayout(taille, taille));
        for(int i=0; i<taille; i++){
            for(int j=0; j<taille; j++){
                if((j%2==0 && i%2==0) || (j%2!=0 && i%2!=0)){
                    ajouterCase(Couleur.Noir);
                }
                else{
                    ajouterCase(Couleur.Blanc);
                }
            }
        }
        init();
    }
	
	 private void ajouterCase(Couleur color){
		 Case case1 = new Case(color);
	     case1.addMouseListener(new ActionSurCases(case1, this));
	     add(case1);
	 }
		
	 private void init(){
		 getCase(27).add(creerPion(Couleur.Noir, false));
	     getCase(28).add(creerPion(Couleur.Blanc, true));
	     getCase(35).add(creerPion(Couleur.Blanc, false));
	     getCase(36).add(creerPion(Couleur.Noir, true));
	 }
	
	public Case getCase(int i, int j){
	    return (Case) getComponent(j+i*8);
	}
		 
	public Case getCase(int i){
		return (Case) getComponent(i);
	}
		 
	Pion creerPion(Couleur couleur, boolean monte){
		Pion pion = new Pion(couleur, monte);
	    pion.addMouseListener(new MouvementsPion(this, pion));
	    return pion;
	}
	
	private int getLigne(Case case1){
        int res=0;
        for(int i=0; i<8*8; i+=2){
            if(getCase(i).equals(case1)){
                res=i/8;
            }
        }
        return res;
    }

    private int getColonne(Case case1){
        int res=0;
        for(int i=0; i<8*8; i+=2){
            if(getCase(i).equals(case1)){
                res=i%8;
            }
        }
        return res;
    }
}
